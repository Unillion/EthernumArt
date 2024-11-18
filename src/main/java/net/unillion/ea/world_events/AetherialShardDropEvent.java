package net.unillion.ea.world_events;

import net.fabricmc.fabric.api.event.server.ServerTickCallback;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.unillion.ea.contents.item.ModItem;

import java.util.concurrent.atomic.AtomicBoolean;

public class AetherialShardDropEvent {
    private static final float FALL_RADIUS = 50.0F;
    private static final float FALL_VELOCITY = 4.5F;

    public static void dropFallingStar(MinecraftServer server) {
        AtomicBoolean spawned = new AtomicBoolean(false);
        for (PlayerEntity player : server.getOverworld().getPlayers()) {
            BlockPos playerPos = player.getBlockPos();
            World world = player.getWorld();

            double x = playerPos.getX() + MathHelper.nextDouble(world.random, -FALL_RADIUS, FALL_RADIUS);
            double z = playerPos.getZ() + MathHelper.nextDouble(world.random, -FALL_RADIUS, FALL_RADIUS);
            double y = playerPos.getY() + 100;

            ItemEntity shard = new ItemEntity(world, x, y, z, new ItemStack(ModItem.AETHERIAL_SHARD));
            shard.setVelocity(0, -FALL_VELOCITY, 0);

            world.spawnEntity(shard);

            ServerTickCallback.EVENT.register(tick -> {
                if (spawned.get()) return;
                if (shard.isOnGround()) {
                    server.getOverworld().spawnParticles(ParticleTypes.END_ROD, shard.getX()+0.5d, shard.getY()+0.5d, shard.getZ()+0.5d,
                            30, 1,1,1, 0.4d);
                    spawned.set(true);
                    return;
                }
                server.getOverworld().spawnParticles(ParticleTypes.END_ROD, shard.getX()+0.5d, shard.getY()+0.5d, shard.getZ()+0.5d,
                        1, 0,0,0, 0);

            });
        }
    }

    public static void scheduleAetherialDrop(int tick, MinecraftServer server) {
        if (tick % 60 == 0 && server.getOverworld().isNight()) {
            dropFallingStar(server);
        }
    }
}
