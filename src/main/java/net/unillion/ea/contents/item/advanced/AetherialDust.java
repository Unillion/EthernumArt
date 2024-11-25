package net.unillion.ea.contents.item.advanced;

import net.fabricmc.fabric.api.event.server.ServerTickCallback;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.unillion.ea.contents.item.ModItem;
import net.unillion.ea.particle.ModParticles;
import net.unillion.ea.utils.ParticleUtil;
import net.unillion.ea.utils.RunnableManager;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class AetherialDust extends Item {
    private static RunnableManager runnableManager;
    public AetherialDust(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        AtomicInteger c = new AtomicInteger();
        AtomicBoolean isRunned = new AtomicBoolean(false);


        World world = context.getWorld();
        BlockState state = world.getBlockState(context.getBlockPos());
        if (state.getBlock() != Blocks.BOOKSHELF) return ActionResult.FAIL;

        PlayerEntity player = context.getPlayer();

        if (player.getStackInHand(player.getActiveHand()) == null) return ActionResult.FAIL;
        player.getStackInHand(player.getActiveHand()).decrement(1);

        ServerTickCallback.EVENT.register(tick -> {
            player.sendMessage(Text.of(String.valueOf(isRunned.get())), false);
            if (isRunned.get()) return;
            ParticleUtil.spawnParticlesOnBlockSides(world, context.getBlockPos(), ModParticles.AETHERIAL_PARTICLE, 5);

            c.getAndIncrement();
            if (c.get() == 30) isRunned.getAndSet(true);
            if (isRunned.get()) transformBookshelf(world, context.getBlockPos());
        });

        return ActionResult.SUCCESS;
    }

    private void transformBookshelf(World world, BlockPos pos) {
        world.breakBlock(pos, false);

        ItemEntity book = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItem.LEXICON_ITEM));
        world.spawnEntity(book);

        Runnable bookParticle = () -> {
            do {
                world.getServer().getOverworld().spawnParticles(
                        ModParticles.AETHERIAL_PARTICLE, book.getX(), book.getY(), book.getZ(), 1,0,0,0, 0
                );
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Preserve interrupt status
                    break;
                }
            } while (!Thread.currentThread().isInterrupted());
        };

        runnableManager.start(bookParticle);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        runnableManager.stop();
    }
}
