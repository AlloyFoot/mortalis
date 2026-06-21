package dev.alloy.event;

import dev.alloy.soul.SoulManager;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.UUID;

public class ModEvents {

    public static void register() {

        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {

            var player = handler.getPlayer();
            UUID id = player.getUuid();

            if (SoulManager.getSouls(server, id) <= 0) {
                player.sendMessage(
                        Text.literal("You are soulless. Get your soul back to eliminate mortality.")
                                .formatted(Formatting.DARK_RED)
                );
            }
        });

        ServerLivingEntityEvents.AFTER_DEATH.register((entity, damageSource) -> {



            if (entity instanceof ServerPlayerEntity player) {

                var server = player.getEntityWorld().getServer();
                UUID id = player.getUuid();

                SoulManager.removeSoul(server, id);

                int souls = SoulManager.getSouls(server, id);

                System.out.println(player.getName().getString() + " has " + souls + " souls remaining.");

                player.sendMessage(
                        Text.literal("You have " + souls + " souls remaining.")
                                .formatted(Formatting.GOLD)
                );

                if (souls == 0) {

                    System.out.println(player.getName().getString() + " is soulless.");

                    player.sendMessage(
                            Text.literal("Your soul has been lost.")
                                    .formatted(Formatting.DARK_RED, Formatting.BOLD)
                    );
                }

                if (souls < 0) {

                    System.out.println(player.getName().getString() + " is banned.");

                    player.sendMessage(
                            Text.literal("You have died without a soul. You have been banned.")
                                    .formatted(Formatting.BLACK, Formatting.BOLD)
                    );
                }

                String cause = damageSource.getName();

                System.out.println(player.getName().getString() + " died from " + cause);
            }
        });
    }
}