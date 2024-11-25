package net.unillion.ea.utils;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ParticleUtil {

    public static void spawnParticlesOnBlockSides(World world, BlockPos pos, net.minecraft.particle.ParticleEffect particle, int count) {
        if (!(world instanceof ServerWorld serverWorld)) return;

        for (Direction direction : Direction.values()) {
            Vec3d sideCenter = getSideCenter(pos, direction);

            for (int i = 0; i < count; i++) {
                double offsetX = (world.random.nextDouble() - 0.5) * 0.6;
                double offsetY = (world.random.nextDouble() - 0.5) * 0.6;
                double offsetZ = (world.random.nextDouble() - 0.5) * 0.6;
                serverWorld.spawnParticles(particle,
                        sideCenter.x + offsetX,
                        sideCenter.y + offsetY,
                        sideCenter.z + offsetZ,
                        1,
                        0, 0, 0, 0
                );
            }
        }
    }

    private static Vec3d getSideCenter(BlockPos pos, Direction direction) {
        double x = pos.getX() + 0.5;
        double y = pos.getY() + 0.5;
        double z = pos.getZ() + 0.5;

        switch (direction) {
               case UP -> y += 0.5;
               case DOWN -> y -= 0.5;
               case NORTH -> z -= 0.5;
               case SOUTH -> z += 0.5;
               case WEST -> x -= 0.5;
               case EAST -> x += 0.5;
        }

        return new Vec3d(x, y, z);
    }
}


