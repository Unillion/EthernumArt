package net.unillion.ea.utils;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ScreenShakeUtil {

    private static final Map<UUID, ShakeData> activeShakes = new HashMap<>();

    private static class ShakeData {
        int duration;
        float intensity;

        ShakeData(int duration, float intensity) {
            this.duration = duration;
            this.intensity = intensity;
        }
    }

    public static void init() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;

            activeShakes.entrySet().removeIf(entry -> {
                UUID playerId = entry.getKey();
                ShakeData shakeData = entry.getValue();

                PlayerEntity player = client.world.getPlayerByUuid(playerId);
                if (player instanceof ClientPlayerEntity) {
                    applyScreenShake((ClientPlayerEntity) player, shakeData.intensity);
                }

                shakeData.duration--;
                return shakeData.duration <= 0;
            });
        });
    }

    public static void triggerScreenShake(PlayerEntity player, int duration, float intensity) {
        if (player == null || player.getUuid() == null) return;
        activeShakes.put(player.getUuid(), new ShakeData(duration, intensity));
    }

    private static void applyScreenShake(ClientPlayerEntity player, float intensity) {
        float randomYaw = (float) (Math.random() * 2 - 1) * intensity;
        float randomPitch = (float) (Math.random() * 2 - 1) * intensity;

        player.setYaw(player.getYaw() + randomYaw);
        player.setPitch(player.getPitch() + randomPitch);
    }
}
