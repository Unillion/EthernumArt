package net.unillion.ea.contents.sound;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.unillion.ea.EthernumArtMod;

public class ModSound {
    public static SoundEvent SHARD_FALL_EVENT = registerSoundEvent("shard_fall");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(EthernumArtMod.MOD_ID, name);
        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
    }
    public static void init() {

    }
}