package dev.alloy.command;

import com.mojang.brigadier.CommandDispatcher;
import dev.alloy.soul.SoulManager;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class ModCommands {

    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            registerHelp(dispatcher);
            registerSouls(dispatcher);
        });
    }

    private static void registerHelp(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("helpme")
                .executes(context -> {
                    context.getSource().sendFeedback(() ->
                            Text.literal("You are mortal."), false);
                    return 1;
                })
        );
    }

    private static void registerSouls(CommandDispatcher<ServerCommandSource> dispatcher) {

        dispatcher.register(CommandManager.literal("souls")
                .executes(context -> {

                    var player = context.getSource().getPlayer();
                    var server = context.getSource().getServer();

                    int souls = SoulManager.getSouls(server, player.getUuid());

                    player.sendMessage(
                            Text.literal("You have " + souls + " souls.")
                    );

                    return 1;
                })
        );
    }
}