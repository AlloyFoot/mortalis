package dev.alloy.item;

import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final String MOD_ID = "mortalis";

    public static Item RAW_CRYSTAL;

    public static void register() {
        Identifier id = Identifier.of(MOD_ID, "raw_crystal");

        RAW_CRYSTAL = Registry.register(
                Registries.ITEM,
                id,
                new Item(new Item.Settings().registryKey(
                        RegistryKey.of(RegistryKeys.ITEM, id)
                ))
        );
    }
}