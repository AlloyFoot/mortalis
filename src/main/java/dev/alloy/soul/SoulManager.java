package dev.alloy.soul;

import net.minecraft.server.MinecraftServer;

import java.util.UUID;

public class SoulManager {

    public static int getSouls(MinecraftServer server, UUID id) {
        return SoulStateManager.get(server)
                .souls
                .getOrDefault(id, 1);
    }

    public static void setSouls(MinecraftServer server, UUID id, int value) {
        SoulState state = SoulStateManager.get(server);
        state.souls.put(id, value);
        state.markDirty();
    }

    public static void addSouls(MinecraftServer server, UUID id, int value) {
        setSouls(server, id, getSouls(server, id) + value);
    }

    public static void removeSoul(MinecraftServer server, UUID id) {
        setSouls(server, id, getSouls(server, id) - 1);
    }
}