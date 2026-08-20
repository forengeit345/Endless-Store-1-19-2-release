package net.es.item;

import com.google.common.base.Suppliers;
import net.es.EndlessStoreMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

public class EndlessStoreItemGroup {
    public static final Supplier<CreativeModeTab> ENDLESS_STORE_TAB = Suppliers.memoize(() ->
            CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
                    .icon(() -> new ItemStack(BuiltInRegistries.ITEM.get(
                            ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "wooden_board")
                    )))
                    .title(Component.translatable("itemGroup." + EndlessStoreMod.MOD_ID + ".endless_store_tab"))
                    .build()
    );
}