package net.unillion.ea.contents.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.unillion.ea.EthernumArtMod;

public class ModParticles {
    public static final DefaultParticleType AETHERIAL_PARTICLE = FabricParticleTypes.simple();

    public ModParticles(ClientWorld level, double x, double y, double z, SpriteProvider sprites, double dx, double dy, double dz) {
    }

    public static void registerParticles() {
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(EthernumArtMod.MOD_ID, "aetherial_particle"),
                AETHERIAL_PARTICLE);
    }

}
