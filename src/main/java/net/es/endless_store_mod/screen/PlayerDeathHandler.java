package net.es.endless_store_mod.screen;

import net.es.endless_store_mod.EndlessStoreMod;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class PlayerDeathHandler {
    private static final Identifier CUSTOM_DIMENSION_ID = new Identifier(EndlessStoreMod.MOD_ID, "esdim");

    public static void register() {
        ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, alive) -> {
            if (oldPlayer.getWorld().getRegistryKey().getValue().equals(CUSTOM_DIMENSION_ID) &&
                    !oldPlayer.isCreative() && !oldPlayer.isSpectator()) {
                giveRespawnBook(newPlayer);
            }
        });
    }

    private static void giveRespawnBook(ServerPlayerEntity player) {
        ItemStack book = new ItemStack(Registry.ITEM.get(new Identifier("patchouli", "guide_book")));

        NbtCompound tag = new NbtCompound();
        tag.putString("patchouli:book", "endless_store_mod:torn_pages");
        book.setNbt(tag);

        player.getInventory().offerOrDrop(book);

        player.sendMessage(Text.translatable("message.endless_store_mod.give_book")
                .formatted(Formatting.YELLOW), false);
    }
}