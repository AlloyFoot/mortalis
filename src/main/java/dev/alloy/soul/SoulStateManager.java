package dev.alloy.soul;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.PersistentStateManager;
import net.minecraft.world.PersistentStateType;
import net.minecraft.datafixer.DataFixTypes;

public class SoulStateManager {

    private static final PersistentStateType<SoulState> TYPE =
            new PersistentStateType<>(
                    "mortalis_souls",
                    SoulState::new,
                    SoulState.CODEC,
                    DataFixTypes.PLAYER
            );

    public static SoulState get(MinecraftServer server) {
        PersistentStateManager manager =
                server.getOverworld().getPersistentStateManager();

        return manager.getOrCreate(TYPE);
    }
}