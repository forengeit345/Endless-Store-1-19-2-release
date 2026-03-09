package net.es.endless_store_mod.screen;

import net.es.endless_store_mod.EndlessStoreMod;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = EndlessStoreMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DimensionEnterHandler {
    private static final Map<UUID, ResourceKey<Level>> PLAYER_DIMENSIONS = new HashMap<>();
    private static final ResourceLocation CUSTOM_DIMENSION_ID = new ResourceLocation(EndlessStoreMod.MOD_ID, "esdim");

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.START) return;

        var server = event.getServer();
        for (ServerPlayer player : server.getPlayerList().getPlayers()) {
            ResourceKey<Level> currentDim = player.level().dimension();
            ResourceKey<Level> lastDim = PLAYER_DIMENSIONS.get(player.getUUID());

            if (lastDim == null) {
                PLAYER_DIMENSIONS.put(player.getUUID(), currentDim);
                continue;
            }

            if (!currentDim.location().equals(lastDim.location())) {
                onDimensionChange(player, lastDim, currentDim);
                PLAYER_DIMENSIONS.put(player.getUUID(), currentDim);
            }
        }
    }

    private static void onDimensionChange(ServerPlayer player,
                                          ResourceKey<Level> from,
                                          ResourceKey<Level> to) {
        if (to.location().equals(CUSTOM_DIMENSION_ID)) {
            BlockPos spawnPos = player.blockPosition();
            player.setRespawnPosition(to, spawnPos, 0.0f, true, false);
            player.sendSystemMessage(Component.translatable("message.endless_store_mod.welcome")
                    .setStyle(Style.EMPTY.withColor(net.minecraft.ChatFormatting.GOLD)));
            if (!player.isCreative() && !player.isSpectator()) {
                clearPlayerInventory(player);
            }
        }
    }

    private static void clearPlayerInventory(ServerPlayer player) {
        player.getInventory().clearContent();

        for (EquipmentSlot slot : EquipmentSlot.values()) {
            player.setItemSlot(slot, ItemStack.EMPTY);
        }

        player.getInventory().offhand.set(0, ItemStack.EMPTY);

        player.getInventory().selected = 0;
        player.getInventory().items.set(player.getInventory().selected, ItemStack.EMPTY);

        player.containerMenu.broadcastChanges();
        player.inventoryMenu.broadcastChanges();
        player.getInventory().setChanged();

        player.sendSystemMessage(Component.translatable("message.endless_store_mod.clear")
                .setStyle(Style.EMPTY.withColor(net.minecraft.ChatFormatting.RED)));

        var bookItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation("patchouli", "guide_book"));
        if (bookItem != null) {
            ItemStack guideBook = new ItemStack(bookItem);
            CompoundTag bookTag = new CompoundTag();
            bookTag.putString("patchouli:book", "endless_store_mod:torn_pages");
            guideBook.setTag(bookTag);
            player.getInventory().add(guideBook);
        }
    }
}