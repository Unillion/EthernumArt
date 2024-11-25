package net.unillion.ea.energy;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class EthernumEnergyTickHandler {
    private static int counter = 0;

    public static void init() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            counter++;

            if (counter < 20) return;
            counter = 0;

            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                EnergyManager.tickEnergy(player);
                int i = EnergyManager.getEnergy(player).getEnergy();
                player.sendMessage(Text.of(String.valueOf(i)),false);
            }
        });
    }
}
