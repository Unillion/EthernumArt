package net.unillion.ea.energy;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.unillion.ea.EthernumArtMod;

public class EthernumEnergyS2CPacketSender {
    public static final Identifier ENERGY_UPDATE = new Identifier(EthernumArtMod.MOD_ID, "energy_update");

    public static void registerServerPackets() {
    }

    public static void sendEnergyUpdate(ServerPlayerEntity player, int energy, int maxEnergy) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeInt(energy);
        buf.writeInt(maxEnergy);
        ServerPlayNetworking.send(player, ENERGY_UPDATE, buf);
    }
}
