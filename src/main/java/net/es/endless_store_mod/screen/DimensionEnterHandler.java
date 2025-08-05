package net.es.endless_store_mod.screen;

import net.es.endless_store_mod.EndlessStoreMod;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DimensionEnterHandler {
    private static final Map<UUID, RegistryKey<World>> PLAYER_DIMENSIONS = new HashMap<>();
    private static final Identifier CUSTOM_DIMENSION_ID = new Identifier(EndlessStoreMod.MOD_ID, "esdim");

    public static void init() {
        ServerTickEvents.START_SERVER_TICK.register(server -> {
            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                RegistryKey<World> currentDim = player.getWorld().getRegistryKey();
                RegistryKey<World> lastDim = PLAYER_DIMENSIONS.get(player.getUuid());

                if (lastDim == null) {
                    PLAYER_DIMENSIONS.put(player.getUuid(), currentDim);
                    continue;
                }

                if (!currentDim.getValue().equals(lastDim.getValue())) {
                    onDimensionChange(player, lastDim, currentDim);
                    PLAYER_DIMENSIONS.put(player.getUuid(), currentDim);
                }
            }
        });
    }

    private static void onDimensionChange(ServerPlayerEntity player,
                                          RegistryKey<World> from,
                                          RegistryKey<World> to) {
        if (to.getValue().equals(CUSTOM_DIMENSION_ID)) {
            BlockPos spawnPos = player.getBlockPos();
            player.setSpawnPoint(to, spawnPos, 0.0f, true, false);
            player.sendMessage(Text.translatable("message.endless_store_mod.welcome")
                    .formatted(Formatting.GOLD), false);
            if (!player.isCreative() && !player.isSpectator()) {
                clearPlayerInventory(player);
            }
        }
    }

    private static void clearPlayerInventory(ServerPlayerEntity player) {

        player.getInventory().clear();

        for (EquipmentSlot slot : EquipmentSlot.values()) {
            player.equipStack(slot, ItemStack.EMPTY);
        }

        player.getInventory().offHand.set(0, ItemStack.EMPTY);

        player.getInventory().selectedSlot = 0;
        player.getInventory().main.set(player.getInventory().selectedSlot, ItemStack.EMPTY);

        player.playerScreenHandler.sendContentUpdates();
        player.currentScreenHandler.sendContentUpdates();
        player.getInventory().markDirty();

        player.sendMessage(Text.translatable("message.endless_store_mod.clear")
                .formatted(Formatting.RED), false);
        ItemStack guideBook = new ItemStack(Registry.ITEM.get(new Identifier("patchouli", "guide_book")));

        NbtCompound bookTag = new NbtCompound();
        bookTag.putString("patchouli:book", "endless_store_mod:torn_pages");

        guideBook.setNbt(bookTag);

        player.getInventory().offerOrDrop(guideBook);
    }
}