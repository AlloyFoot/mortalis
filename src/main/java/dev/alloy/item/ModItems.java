package dev.alloy.item;

import dev.alloy.item.custom.SoulCrystalItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final String MOD_ID = "mortalis";

    public static Item RAW_CRYSTAL;
    public static Item SOUL_CRYSTAL;

    public static void register() {

        Identifier rawId = Identifier.of(MOD_ID, "raw_crystal");

        RAW_CRYSTAL = Registry.register(
                Registries.ITEM,
                rawId,
                new Item(new Item.Settings().registryKey(
                        RegistryKey.of(RegistryKeys.ITEM, rawId)
                ))
        );

        // 💀 SOUL CRYSTAL
        Identifier soulId = Identifier.of(MOD_ID, "soul_crystal");

        SOUL_CRYSTAL = Registry.register(
                Registries.ITEM,
                soulId,
                new SoulCrystalItem(new Item.Settings().registryKey(
                        RegistryKey.of(RegistryKeys.ITEM, soulId)
                ))
        );
    }
}