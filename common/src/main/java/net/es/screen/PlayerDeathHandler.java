package net.es.screen;

import net.es.EndlessStoreMod;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;

public class PlayerDeathHandler {
    private static final ResourceLocation CUSTOM_DIMENSION_ID =
            ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "esdim");

    /**
     * Вызывается при возрождении игрока в измерении esdim.
     * Платформенные обработчики должны передать старого и нового игрока.
     */
    public static void handleRespawn(ServerPlayer oldPlayer, ServerPlayer newPlayer) {
        if (oldPlayer.level().dimension().location().equals(CUSTOM_DIMENSION_ID)
                && !oldPlayer.isCreative() && !oldPlayer.isSpectator()) {
            giveRespawnBook(newPlayer);
        }
    }

    private static void giveRespawnBook(ServerPlayer player) {
        ItemStack book = new ItemStack(BuiltInRegistries.ITEM.get(
                ResourceLocation.fromNamespaceAndPath("patchouli", "guide_book")));

        // Новый способ: устанавливаем компонент patchouli:book напрямую
        ResourceLocation bookId = ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "torn_pages");

        // Получаем тип компонента из реестра
        DataComponentType<ResourceLocation> patchouliBookType =
                (DataComponentType<ResourceLocation>) BuiltInRegistries.DATA_COMPONENT_TYPE.get(
                        ResourceLocation.fromNamespaceAndPath("patchouli", "book"));

        if (patchouliBookType != null) {
            book.set(patchouliBookType, bookId);
        } else {
            // Fallback для старых версий Patchouli (если компонент не найден)
            CompoundTag tag = new CompoundTag();
            tag.putString("patchouli:book", bookId.toString());
            book.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
        }

        player.getInventory().placeItemBackInInventory(book);

        player.sendSystemMessage(Component.translatable("message.endless_store_mod.give_book")
                .withStyle(ChatFormatting.YELLOW), false);
    }
}