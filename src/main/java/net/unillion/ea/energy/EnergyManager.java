package net.unillion.ea.energy;

import net.minecraft.server.network.ServerPlayerEntity;

import java.util.HashMap;
import java.util.Map;

public class EnergyManager {
    private static final Map<ServerPlayerEntity, PlayerEnergy> energyData = new HashMap<>();

    public static void giveEnergy(ServerPlayerEntity player, int initialEnergy, int maxEnergy, int recoveryRate) {
        energyData.put(player, new PlayerEnergy(initialEnergy, maxEnergy, recoveryRate));
    }

    public static void removeEnergy(ServerPlayerEntity player) {
        energyData.remove(player);
    }

    public static PlayerEnergy getEnergy(ServerPlayerEntity player) {
        return energyData.get(player);
    }

    public static void tickEnergy(ServerPlayerEntity player) {
        PlayerEnergy energy = getEnergy(player);
        if (energy != null) {
            energy.recover();
            EthernumEnergyS2CPacketSender.sendEnergyUpdate(player, energy.getEnergy(), energy.getMaxEnergy());
        }
    }

    public static boolean hasEnergy(ServerPlayerEntity player) {
        return energyData.containsKey(player);
    }

    public static void init() {}

}
