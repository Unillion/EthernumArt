package net.unillion.ea;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.server.ServerTickCallback;

import net.minecraft.server.MinecraftServer;
import net.unillion.ea.contents.item.ModItem;
import net.unillion.ea.particle.ModParticles;
import net.unillion.ea.contents.sound.ModSound;
import net.unillion.ea.utils.ScreenShakeUtil;
import net.unillion.ea.world_events.AetherialShardDropEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EthernumArtMod implements ModInitializer {
	public static String MOD_ID = "ethernumart";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModItem.init();
		ScreenShakeUtil.init();
		ModSound.init();
		ModParticles.init();

		ServerTickCallback.EVENT.register(this::onServerTick);

	}

	private void onServerTick(MinecraftServer server)
	{
		AetherialShardDropEvent.scheduleAetherialDrop(server.getTicks(), server);

		server.getOverworld().spawnParticles(
				ModParticles.AETHERIAL_PARTICLE, -63.91d, 63.0d, 2.3d, 1,0,0,0,0
		);
	}
}
