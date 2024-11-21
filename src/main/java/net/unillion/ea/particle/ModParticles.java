package net.unillion.ea.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.unillion.ea.EthernumArtMod;

public class ModParticles {
    public static final DefaultParticleType AETHERIAL_PARTICLE = FabricParticleTypes.simple(true);

    public static void init() {
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(EthernumArtMod.MOD_ID, "aetherial_particle"),
                AETHERIAL_PARTICLE);
    }

}
