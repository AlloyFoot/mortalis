package dev.alloy.event;

import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import dev.alloy.soul.SoulManager;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.UUID;

public class ModEvents {

    public static void register() {

        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {

            String playerName = handler.getPlayer().getName().getString();

            UUID id = handler.getPlayer().getUuid();

            SoulManager.SOULS.putIfAbsent(id, 1);

            System.out.println(playerName + " is now mortal.");

        });

        ServerLivingEntityEvents.AFTER_DEATH.register((entity, damageSource) -> {

            if (entity instanceof ServerPlayerEntity player) {

                UUID id = player.getUuid();

                int souls = SoulManager.SOULS.getOrDefault(id, 1);

                souls--;

                SoulManager.SOULS.put(id, souls);

                System.out.println(
                        player.getName().getString()
                                + " has "
                                + souls
                                + " souls remaining."
                );

                player.sendMessage(
                        Text.literal("You have " + souls + " souls remaining.").formatted(Formatting.GOLD)
                );

                if (souls < 0) {

                    System.out.println(
                            player.getName().getString()
                                    + " has run out of souls."
                    );

                }

                player.sendMessage(
                        Text.literal("Your final soul has been lost.").formatted(Formatting.DARK_RED, Formatting.BOLD)
                );

                String cause = damageSource.getName();

                System.out.println(
                        player.getName().getString()
                                + " died from "
                                + cause
                );
            }
        });
    }
}