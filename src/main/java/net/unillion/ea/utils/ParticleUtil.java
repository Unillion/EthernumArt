package net.unillion.ea.utils;

import net.minecraft.block.BlockState;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class ParticleUtil {

    public static void spawnParticlesAroundBlock(World world, BlockPos pos, int particleCount, ParticleEffect effect) {
        BlockState blockState = world.getBlockState(pos);

        if (!blockState.isAir()) return;
        for (Direction direction : Direction.values()) {
            double offsetX = pos.getX() + 0.5 + direction.getOffsetX() * 0.5;
            double offsetY = pos.getY() + 0.5 + direction.getOffsetY() * 0.5;
            double offsetZ = pos.getZ() + 0.5 + direction.getOffsetZ() * 0.5;

            for (int i = 0; i < particleCount; i++) {
                double particleX = offsetX + (Math.random() - 0.5) * 0.1;
                double particleY = offsetY + (Math.random() - 0.5) * 0.1;
                double particleZ = offsetZ + (Math.random() - 0.5) * 0.1;
                world.addParticle(effect, particleX, particleY, particleZ, 5, 0, 0);
            }
        }
    }
}
