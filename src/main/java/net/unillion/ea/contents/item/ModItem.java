package net.unillion.ea.contents.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.unillion.ea.EthernumArtMod;

public class ModItem {

    public static final Item AETHERIAL_SHARD = registerItem("aetherial_shard",
            new Item(new FabricItemSettings().group(ModItemGroup.ETHERNUM_ART_GROUP)));

    public static final Item STABLE_AETHERIAL_SHARD = registerItem("stabilized_aetherial_shard",
            new Item(new FabricItemSettings().group(ModItemGroup.ETHERNUM_ART_GROUP)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(EthernumArtMod.MOD_ID, name), item);
    }

    public static void init() {
        EthernumArtMod.LOGGER.info("Initialising items...");
    }
}
