package net.es.endless_store_mod.screen;

import net.es.endless_store_mod.EndlessStoreMod;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = EndlessStoreMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerDeathHandler {
    private static final ResourceLocation CUSTOM_DIMENSION_ID = new ResourceLocation(EndlessStoreMod.MOD_ID, "esdim");

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        if (!player.level().dimension().location().equals(CUSTOM_DIMENSION_ID)) return;
        if (player.isCreative() || player.isSpectator()) return;

        giveRespawnBook(player);
    }

    private static void giveRespawnBook(ServerPlayer player) {
        var bookItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation("patchouli", "guide_book"));
        if (bookItem == null) return;

        ItemStack book = new ItemStack(bookItem);
        CompoundTag tag = new CompoundTag();
        tag.putString("patchouli:book", "endless_store_mod:torn_pages");
        book.setTag(tag);

        player.getInventory().add(book);

        player.sendSystemMessage(Component.translatable("message.endless_store_mod.give_book")
                .setStyle(Style.EMPTY.withColor(net.minecraft.ChatFormatting.YELLOW)));
    }
}