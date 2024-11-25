package net.unillion.ea.particle.custom;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.world.chunk.light.LightingProvider;
import net.unillion.ea.contents.shader.ShaderProgram;

public class AetheriumParticle extends SpriteBillboardParticle {
    private final float gravity;
    public AetheriumParticle(ClientWorld world, double xCoord, double yCoord, double zCoord,
                             SpriteProvider spriteSet, double xd, double yd, double zd) {
        super(world, xCoord, yCoord, zCoord, xd, yd, zd);

        this.velocityMultiplier = 0.95F;
        this.x = xd;
        this.y = yd;
        this.z = zd;
        this.scale *= 0.1F;
        this.maxAge = 20;
        this.setSpriteForAge(spriteSet);

        this.red = 1.0F;
        this.green = 0.8F + random.nextFloat() * 0.2F;
        this.blue = 0.2F + random.nextFloat() * 0.1F;
        this.gravity = 0.1F;
    }

    @Override
    public void tick() { super.tick();

        // Add spark-like jitter
        this.velocityX += (this.random.nextFloat() - 0.5) * 0.02;
        this.velocityZ += (this.random.nextFloat() - 0.5) * 0.02;

        this.velocityY -= gravity;
        fadeOut();
    }

    private void fadeOut() {
        this.alpha = (-(1 / (float) maxAge) * age + 1);
    }

    @Override
    public void buildGeometry(VertexConsumer vertexConsumer, Camera camera, float tickDelta) {
        vertexConsumer.light(10);
        super.buildGeometry(vertexConsumer, camera, tickDelta);
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider sprites;

        public Factory(SpriteProvider spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(DefaultParticleType particleType, ClientWorld level, double x, double y, double z,
                                       double dx, double dy, double dz) {
            return new AetheriumParticle(level, x, y, z, this.sprites, dx, dy, dz);
        }
    }
}
