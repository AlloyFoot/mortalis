package dev.alloy.soul;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.PersistentState;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SoulState extends PersistentState {

    public Map<UUID, Integer> souls = new HashMap<>();

    public static SoulState createFromNbt(NbtCompound nbt) {
        SoulState state = new SoulState();

        NbtCompound soulsNbt = nbt.getCompound("souls");

        for (String key : soulsNbt.getKeys()) {
            state.souls.put(UUID.fromString(key), soulsNbt.getInt(key));
        }

        return state;
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        NbtCompound soulsNbt = new NbtCompound();

        for (Map.Entry<UUID, Integer> entry : souls.entrySet()) {
            soulsNbt.putInt(entry.getKey().toString(), entry.getValue());
        }

        nbt.put("souls", soulsNbt);

        return nbt;
    }
}