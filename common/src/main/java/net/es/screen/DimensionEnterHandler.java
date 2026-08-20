package net.es.screen;

import net.es.EndlessStoreMod;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DimensionEnterHandler {
    private static final Map<UUID, ResourceKey<Level>> LAST_DIMENSIONS = new HashMap<>();
    private static final ResourceLocation CUSTOM_DIMENSION_ID =
            ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "esdim");

    /**
     * Вызывается каждый серверный тик для каждого игрока.
     * Отслеживает смену измерения и реагирует при входе в esdim.
     */
    public static void onPlayerTick(ServerPlayer player) {
        ResourceKey<Level> currentDim = player.level().dimension();
        ResourceKey<Level> lastDim = LAST_DIMENSIONS.get(player.getUUID());

        if (lastDim == null) {
            LAST_DIMENSIONS.put(player.getUUID(), currentDim);
            return;
        }

        if (!currentDim.equals(lastDim)) {
            onDimensionChange(player, lastDim, currentDim);
            LAST_DIMENSIONS.put(player.getUUID(), currentDim);
        }
    }

    private static void onDimensionChange(ServerPlayer player,
                                          ResourceKey<Level> from,
                                          ResourceKey<Level> to) {
        if (to.location().equals(CUSTOM_DIMENSION_ID)) {
            BlockPos spawnPos = player.blockPosition();
            player.setRespawnPosition(to, spawnPos, 0.0f, true, false);

            player.sendSystemMessage(Component.translatable("message.endless_store_mod.welcome")
                    .withStyle(ChatFormatting.GOLD), false);

            if (!player.isCreative() && !player.isSpectator()) {
                clearPlayerInventory(player);
            }
        }
    }

    private static void clearPlayerInventory(ServerPlayer player) {
        // Полная очистка инвентаря
        player.getInventory().clearContent();

        // Дополнительно очищаем все слоты экипировки
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            player.setItemSlot(slot, ItemStack.EMPTY);
        }

        // Синхронизация с клиентом
        player.containerMenu.broadcastChanges();
        player.getInventory().setChanged();

        player.sendSystemMessage(Component.translatable("message.endless_store_mod.clear")
                .withStyle(ChatFormatting.RED), false);

        giveGuideBook(player);
    }

    private static void giveGuideBook(ServerPlayer player) {
        ItemStack book = new ItemStack(BuiltInRegistries.ITEM.get(
                ResourceLocation.fromNamespaceAndPath("patchouli", "guide_book")));

        ResourceLocation bookId = ResourceLocation.fromNamespaceAndPath(
                EndlessStoreMod.MOD_ID, "torn_pages");

        // Новый способ для Patchouli 1.21.1
        DataComponentType<ResourceLocation> patchouliBookType =
                (DataComponentType<ResourceLocation>) BuiltInRegistries.DATA_COMPONENT_TYPE.get(
                        ResourceLocation.fromNamespaceAndPath("patchouli", "book"));

        if (patchouliBookType != null) {
            book.set(patchouliBookType, bookId);
        } else {
            // Fallback для старых версий Patchouli
            CompoundTag tag = new CompoundTag();
            tag.putString("patchouli:book", bookId.toString());
            book.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
        }

        player.getInventory().placeItemBackInInventory(book);
    }
}