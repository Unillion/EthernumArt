package net.unillion.ea.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class NBTManager {

    public static void putString(NbtCompound compound, String key, String value) {
        compound.putString(key, value);
    }

    public static String getString(NbtCompound compound, String key, String defaultValue) {
        return compound.contains(key) ? compound.getString(key) : defaultValue;
    }

    public static void putInt(NbtCompound compound, String key, int value) {
        compound.putInt(key, value);
    }

    public static int getInt(NbtCompound compound, String key, int defaultValue) {
        return compound.contains(key) ? compound.getInt(key) : defaultValue;
    }

    public static void putBoolean(NbtCompound compound, String key, boolean value) {
        compound.putBoolean(key, value);
    }

    public static boolean getBoolean(NbtCompound compound, String key, boolean defaultValue) {
        return compound.contains(key) ? compound.getBoolean(key) : defaultValue;
    }

    public static void putFloat(NbtCompound compound, String key, float value) {
        compound.putFloat(key, value);
    }

    public static float getFloat(NbtCompound compound, String key, float defaultValue) {
        return compound.contains(key) ? compound.getFloat(key) : defaultValue;
    }

    // -- Nested NbtCompound --
    public static void putCompound(NbtCompound compound, String key, NbtCompound nestedCompound) {
        compound.put(key, nestedCompound);
    }

    public static NbtCompound getCompound(NbtCompound compound, String key) {
        return compound.contains(key) ? compound.getCompound(key) : new NbtCompound();
    }

    public static boolean containsKey(NbtCompound compound, String key) {
        return compound.contains(key);
    }

    public static void saveToItemStack(NbtCompound compound, ItemStack stack) {
        stack.getOrCreateNbt().copyFrom(compound);
    }

    public static NbtCompound loadFromItemStack(ItemStack stack) {
        return stack.hasNbt() ? stack.getNbt() : new NbtCompound();
    }
}
