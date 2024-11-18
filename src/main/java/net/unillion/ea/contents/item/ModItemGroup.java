package net.unillion.ea.contents.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.unillion.ea.EthernumArtMod;

public class ModItemGroup {
    public static final net.minecraft.item.ItemGroup ETHERNUM_ART_GROUP =
            FabricItemGroupBuilder.build(new Identifier(EthernumArtMod.MOD_ID, "main"),
            () -> new ItemStack(ModItem.AETHERIAL_SHARD));
}
