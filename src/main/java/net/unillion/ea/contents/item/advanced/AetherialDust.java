package net.unillion.ea.contents.item.advanced;

import net.fabricmc.fabric.api.event.server.ServerTickCallback;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.unillion.ea.utils.ParticleUtil;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class AetherialDust extends Item {
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
            if (isRunned.get()) return;
            ParticleUtil.spawnParticlesAroundBlock(world, context.getBlockPos(), 5, ParticleTypes.END_ROD);

            c.getAndIncrement();
            if (c.get() == 60) isRunned.getAndSet(true);

        });
        if (isRunned.get()) transformBookshelf(world, context.getBlockPos());

        return ActionResult.SUCCESS;
    }

    private void transformBookshelf(World world, BlockPos pos) {
        world.breakBlock(pos, false);
    }
}