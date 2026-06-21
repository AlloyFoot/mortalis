package dev.alloy.item.custom;

import dev.alloy.soul.SoulManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class SoulCrystalItem extends Item {

    public SoulCrystalItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {

        ItemStack stack = user.getStackInHand(hand);

        if (!world.isClient() && user instanceof ServerPlayerEntity player) {

            var server = player.getEntityWorld().getServer();

            int before = SoulManager.getSouls(
                    server,
                    player.getUuid()
            );

            if (before < 1) {

                SoulManager.addSouls(
                        server,
                        player.getUuid(),
                        1
                );

                player.sendMessage(
                        Text.literal("You feel your soul returning.")
                                .formatted(Formatting.AQUA)
                );

                stack.decrement(1);

            } else {

                player.sendMessage(
                        Text.literal("The crystal does nothing.")
                                .formatted(Formatting.GRAY)
                );
            }
        }

        return ActionResult.SUCCESS;
    }
}