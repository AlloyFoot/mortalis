package dev.alloy.soul;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.PersistentStateManager;

public class SoulStateManager {

    private static SoulState instance;

    public static SoulState getServerState(MinecraftServer server) {
        PersistentStateManager manager =
                server.getOverworld().getPersistentStateManager();

        return manager.getOrCreate(
                SoulState::createFromNbt,
                SoulState::new,
                "mortalis_souls"
        );
    }
}