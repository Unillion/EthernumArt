package net.unillion.ea.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.unillion.ea.particle.ModParticles;
import net.unillion.ea.particle.custom.AetheriumParticle;

public class EthernumArtClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ParticleFactoryRegistry.getInstance().register(ModParticles.AETHERIAL_PARTICLE, AetheriumParticle.Factory::new);

    }
}
