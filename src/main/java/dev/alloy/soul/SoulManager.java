package dev.alloy.soul;

import net.minecraft.server.MinecraftServer;
import java.util.Map;
import java.util.UUID;

public class SoulManager {

    public static int getSouls(MinecraftServer server, UUID player) {
        SoulState state = SoulStateManager.getServerState(server);
        return state.souls.getOrDefault(player, 1);
    }

    public static void setSouls(MinecraftServer server, UUID player, int amount) {
        SoulState state = SoulStateManager.getServerState(server);
        state.souls.put(player, amount);
        state.markDirty();
    }

    public static void addSouls(MinecraftServer server, UUID player, int amount) {
        SoulState state = SoulStateManager.getServerState(server);
        int current = state.souls.getOrDefault(player, 1);

        state.souls.put(player, current + amount);
        state.markDirty();
    }

    public static void removeSoul(MinecraftServer server, UUID player) {
        SoulState state = SoulStateManager.getServerState(server);
        int current = state.souls.getOrDefault(player, 1);

        int newValue = current - 1;
        state.souls.put(player, newValue);
        state.markDirty();

        if (newValue <= 0) {
            System.out.println(
                    player + " has run out of souls."
            );
        }
    }
}