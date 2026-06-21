package dev.alloy.soul;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.PersistentState;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SoulState extends PersistentState {

    public Map<UUID, Integer> souls = new HashMap<>();

    public static final Codec<SoulState> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.unboundedMap(Codec.STRING, Codec.INT)
                            .fieldOf("souls")
                            .forGetter(state -> {
                                Map<String, Integer> map = new HashMap<>();
                                state.souls.forEach((k, v) -> map.put(k.toString(), v));
                                return map;
                            })
            ).apply(instance, map -> {
                SoulState state = new SoulState();

                map.forEach((k, v) ->
                        state.souls.put(UUID.fromString(k), v)
                );

                return state;
            })
    );

    public SoulState() {
        System.out.println("SoulState CREATED (new instance)");
    }
}