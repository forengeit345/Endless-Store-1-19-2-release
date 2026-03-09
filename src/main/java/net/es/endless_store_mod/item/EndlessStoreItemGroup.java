package net.es.endless_store_mod.item;

import net.es.endless_store_mod.EndlessStoreMod;
import net.es.endless_store_mod.block.EndlessStoreBlocks;
import net.es.endless_store_mod.fluid.EndlessStoreFluids;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class EndlessStoreItemGroup {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EndlessStoreMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> ENDLESS_STORE_TAB = CREATIVE_MODE_TABS.register("endless_store_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(EndlessStoreItems.WOODEN_BOARD.get()))
                    .title(Component.translatable("creativetab.endless_store_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(EndlessStoreBlocks.LAMINATE_FLOORING_LIGHT.get());
                        pOutput.accept(EndlessStoreBlocks.LAMINATE_FLOORING_LIGHT_VERTICAL.get());
                        pOutput.accept(EndlessStoreBlocks.LAMINATE_FLOORING_DARK.get());
                        pOutput.accept(EndlessStoreBlocks.LAMINATE_FLOORING_MANGROVE.get());
                        pOutput.accept(EndlessStoreBlocks.LAMINATE_FLOORING_WHITE.get());
                        pOutput.accept(EndlessStoreBlocks.PLITKA_WATER.get());
                        pOutput.accept(EndlessStoreBlocks.CONCRETE.get());
                        pOutput.accept(EndlessStoreBlocks.MOSSY_CONCRETE.get());
                        pOutput.accept(EndlessStoreBlocks.OVERGROWN_CONCRETE.get());
                        pOutput.accept(EndlessStoreBlocks.DARK_MOSSY_CONCRETE.get());
                        pOutput.accept(EndlessStoreBlocks.DARK_OVERGROWN_CONCRETE.get());
                        pOutput.accept(EndlessStoreBlocks.DARK_CRACKED_CONCRETE.get());
                        pOutput.accept(EndlessStoreBlocks.DARK_CONCRETE.get());
                        pOutput.accept(EndlessStoreBlocks.CONCRETE_SLAB.get());
                        pOutput.accept(EndlessStoreBlocks.CONCRETE_STAIRS.get());
                        pOutput.accept(EndlessStoreBlocks.DARK_CONCRETE_SLAB.get());
                        pOutput.accept(EndlessStoreBlocks.DARK_CONCRETE_STAIRS.get());
                        pOutput.accept(EndlessStoreBlocks.YELLOW_CONCRETE.get());
                        pOutput.accept(EndlessStoreBlocks.ORANGE_GRANIT.get());
                        pOutput.accept(EndlessStoreBlocks.WHITE_BRICK.get());
                        pOutput.accept(EndlessStoreBlocks.WHITE_BRICK_ALTERNATIVE.get());
                        pOutput.accept(EndlessStoreBlocks.WHITE_BRICK_SLAB.get());
                        pOutput.accept(EndlessStoreBlocks.WHITE_BRICK_STAIRS_ES.get());
                        pOutput.accept(EndlessStoreBlocks.WHITE_BRICK_SLAB_ALTERNATIVE.get());
                        pOutput.accept(EndlessStoreBlocks.WHITE_BRICK_STAIRS_ALTERNATIVE.get());
                        pOutput.accept(EndlessStoreBlocks.LINOLEUM_LIGHT.get());
                        pOutput.accept(EndlessStoreBlocks.LINOLEUM_BROWN.get());
                        pOutput.accept(EndlessStoreBlocks.WALLPAPER_YELLOW.get());
                        pOutput.accept(EndlessStoreBlocks.WALLPAPER_WHITE.get());
                        pOutput.accept(EndlessStoreBlocks.WALLPAPER_BLUE.get());
                        pOutput.accept(EndlessStoreBlocks.METAL_GARAGE_DOOR.get());
                        pOutput.accept(EndlessStoreBlocks.PATTERNED_CARPET.get());
                        pOutput.accept(EndlessStoreBlocks.PLASTIC_GARBAGE_COLORED.get());
                        pOutput.accept(EndlessStoreBlocks.PLASTIC_GARBAGE_GREY.get());
                        pOutput.accept(EndlessStoreBlocks.PLASTIC_GARBAGE_BLUE.get());
                        pOutput.accept(EndlessStoreBlocks.CONCRETE_FURNACE.get());
                        pOutput.accept(EndlessStoreBlocks.CEILING_TILES_WHITE.get());

                        pOutput.accept(EndlessStoreBlocks.CONCRETE_MUD.get());
                        pOutput.accept(EndlessStoreBlocks.PROBABLY_MUD.get());
                        pOutput.accept(EndlessStoreBlocks.CHAIN_MAIL.get());
                        pOutput.accept(EndlessStoreBlocks.GREEN_MUD.get());
                        pOutput.accept(EndlessStoreBlocks.LIME_MUD.get());
                        pOutput.accept(EndlessStoreBlocks.LAMINATE_FLOORING_DARK_DAMAGED.get());
                        pOutput.accept(EndlessStoreBlocks.LAMINATE_FLOORING_LIGHT_DAMAGED.get());
                        pOutput.accept(EndlessStoreBlocks.LAMINATE_FLOORING_MANGROVE_DAMAGED.get());
                        pOutput.accept(EndlessStoreBlocks.LAMINATE_FLOORING_WHITE_DAMAGED.get());
                        pOutput.accept(EndlessStoreBlocks.WALLPAPER_BLUE_DAMAGED.get());
                        pOutput.accept(EndlessStoreBlocks.WALLPAPER_WHITE_DAMAGED.get());
                        pOutput.accept(EndlessStoreBlocks.WALLPAPER_YELLOW_DAMAGED.get());
                        pOutput.accept(EndlessStoreBlocks.WHITE_BRICK_DAMAGED.get());
                        pOutput.accept(EndlessStoreBlocks.WHITE_BRICK_MOSSY.get());
                        pOutput.accept(EndlessStoreBlocks.LITERALLY_NOTHING.get());

                        pOutput.accept(EndlessStoreBlocks.CEILING_TILES_WHITE_ANOTHER.get());
                        pOutput.accept(EndlessStoreBlocks.WALL_BLUE_TILES.get());
                        pOutput.accept(EndlessStoreBlocks.TILES_WITH_BLACK_INCLUSIONS.get());
                        pOutput.accept(EndlessStoreBlocks.FLOOR_TILES_MULTI_COLORED.get());

                        pOutput.accept(EndlessStoreBlocks.METAL_PIPE.get());
                        pOutput.accept(EndlessStoreBlocks.METAL_CORNER_PIPE.get());
                        pOutput.accept(EndlessStoreBlocks.SHELVES.get());
                        pOutput.accept(EndlessStoreBlocks.BASEBOARD.get());
                        pOutput.accept(EndlessStoreBlocks.BASEBOARD_CORNER.get());
                        pOutput.accept(EndlessStoreBlocks.PLUSH_MOUSE_GRAF.get());
                        pOutput.accept(EndlessStoreBlocks.OUTDOOR_SIGN.get());
                        pOutput.accept(EndlessStoreBlocks.FIRE_CRANE.get());
                        pOutput.accept(EndlessStoreBlocks.KETTLE.get());
                        pOutput.accept(EndlessStoreBlocks.WALL_CLOCK.get());
                        pOutput.accept(EndlessStoreBlocks.PALLET.get());
                        pOutput.accept(EndlessStoreBlocks.FIRE_DETECTOR.get());
                        pOutput.accept(EndlessStoreBlocks.WALL_MAP.get());
                        pOutput.accept(EndlessStoreBlocks.CARGO_TROLLEY.get());
                        pOutput.accept(EndlessStoreBlocks.CARGO_TROLLEY_LONG_ONE_HANDLES.get());
                        pOutput.accept(EndlessStoreBlocks.CARGO_TROLLEY_LONG_TWO_HANDLES.get());
                        pOutput.accept(EndlessStoreBlocks.BOOK_VARIANT_ONE.get());
                        pOutput.accept(EndlessStoreBlocks.BOOK_VARIANT_TWO.get());
                        pOutput.accept(EndlessStoreBlocks.BOOK_VARIANT_THREE.get());
                        pOutput.accept(EndlessStoreBlocks.ARMCHAIR.get());
                        pOutput.accept(EndlessStoreBlocks.WARDROBE.get());
                        pOutput.accept(EndlessStoreBlocks.BIG_WARDROBE.get());
                        pOutput.accept(EndlessStoreBlocks.CARDBOARD_BOX.get());
                        pOutput.accept(EndlessStoreBlocks.CARDBOARD_BOX_OPEN.get());
                        pOutput.accept(EndlessStoreBlocks.CARDBOARD_BOX_MIDDLE.get());
                        pOutput.accept(EndlessStoreBlocks.CARDBOARD_BOX_MIDDLE_OPEN.get());
                        pOutput.accept(EndlessStoreBlocks.CARDBOARD_BOX_LARGE.get());
                        pOutput.accept(EndlessStoreBlocks.CARDBOARD_BOX_LARGE_OPEN.get());
                        pOutput.accept(EndlessStoreBlocks.CARDBOARD_BOX_HUGE.get());
                        pOutput.accept(EndlessStoreBlocks.CARDBOARD_BOX_HUGE_OPEN.get());
                        pOutput.accept(EndlessStoreBlocks.CARDBOARD_BOX_HUGE_TAPE.get());
                        pOutput.accept(EndlessStoreBlocks.TRASH_CAN.get());
                        pOutput.accept(EndlessStoreBlocks.PLATE.get());

                        pOutput.accept(EndlessStoreBlocks.FUSE_BOX.get());
                        pOutput.accept(EndlessStoreBlocks.FUSE_BOX_LARGE.get());
                        pOutput.accept(EndlessStoreBlocks.TOOLBOX.get());
                        pOutput.accept(EndlessStoreBlocks.COIL_WIRES.get());
                        pOutput.accept(EndlessStoreBlocks.WOODEN_CHAIR.get());
                        pOutput.accept(EndlessStoreBlocks.WOODEN_TABLE.get());
                        pOutput.accept(EndlessStoreBlocks.WOODEN_TABLE_LONG.get());
                        pOutput.accept(EndlessStoreBlocks.PLASTIC_TABLE.get());
                        pOutput.accept(EndlessStoreBlocks.PLASTIC_TABLE_LONG.get());
                        pOutput.accept(EndlessStoreBlocks.TOWEL_HOLDER.get());
                        pOutput.accept(EndlessStoreBlocks.PADDED_STOOL.get());
                        pOutput.accept(EndlessStoreBlocks.MIRROR.get());
                        pOutput.accept(EndlessStoreBlocks.HIGH_MIRROR.get());
                        pOutput.accept(EndlessStoreBlocks.RADIATOR_RIGHT_SIDE.get());
                        pOutput.accept(EndlessStoreBlocks.RADIATOR_LEFT_SIDE.get());
                        pOutput.accept(EndlessStoreBlocks.SQUARE_SHELF.get());
                        pOutput.accept(EndlessStoreBlocks.SINK.get());
                        pOutput.accept(EndlessStoreBlocks.SOCKET.get());
                        pOutput.accept(EndlessStoreBlocks.SIDE_PIPES.get());
                        pOutput.accept(EndlessStoreBlocks.CEILING_PIPES.get());
                        pOutput.accept(EndlessStoreBlocks.PEDESTAL_ALTEA.get());
                        pOutput.accept(EndlessStoreBlocks.METAL_CLOSET.get());
                        pOutput.accept(EndlessStoreBlocks.WHITE_BOOKSHELF.get());
                        pOutput.accept(EndlessStoreBlocks.CALENDAR.get());
                        pOutput.accept(EndlessStoreBlocks.DECORATIVE_FENCE.get());
                        pOutput.accept(EndlessStoreBlocks.PRACTICAL_FENCE.get());
                        pOutput.accept(EndlessStoreBlocks.PLASTIC_CHAIR.get());
                        pOutput.accept(EndlessStoreBlocks.VERTICAL_WASHING_MACHINE.get());
                        pOutput.accept(EndlessStoreBlocks.BEDSIDE_TABLE.get());
                        pOutput.accept(EndlessStoreBlocks.GLOBE.get());
                        pOutput.accept(EndlessStoreBlocks.AQUARIUM.get());
                        pOutput.accept(EndlessStoreBlocks.MRE.get());
                        pOutput.accept(EndlessStoreBlocks.BOTTLE.get());
                        pOutput.accept(EndlessStoreBlocks.SAFE.get());
                        pOutput.accept(EndlessStoreBlocks.CEILING_GRID.get());
                        pOutput.accept(EndlessStoreBlocks.SIDE_STORAGE_SHELVES.get());
                        pOutput.accept(EndlessStoreBlocks.SIDE_BEAMS.get());
                        pOutput.accept(EndlessStoreBlocks.COMPUTER.get());
                        pOutput.accept(EndlessStoreBlocks.OLD_SAFE.get());
                        pOutput.accept(EndlessStoreBlocks.VERTICAL_BOILER.get());
                        pOutput.accept(EndlessStoreBlocks.HORIZONTAL_BOILER.get());
                        pOutput.accept(EndlessStoreBlocks.ORGANIZER.get());
                        pOutput.accept(EndlessStoreBlocks.EMPTY_ORGANIZER.get());
                        pOutput.accept(EndlessStoreBlocks.SMALL_WOODEN_BARRICADES.get());
                        pOutput.accept(EndlessStoreBlocks.MEDIUM_WOODEN_BARRICADES.get());
                        pOutput.accept(EndlessStoreBlocks.LARGE_WOODEN_BARRICADES.get());
                        pOutput.accept(EndlessStoreBlocks.MUD_ON_FLOOR.get());
                        pOutput.accept(EndlessStoreBlocks.GARBAGE_ON_FLOOR.get());
                        pOutput.accept(EndlessStoreBlocks.CONCRETE_CRUMBS_ON_FLOOR.get());
                        pOutput.accept(EndlessStoreBlocks.WATER_STRIDER_EGG.get());
                        pOutput.accept(EndlessStoreBlocks.WATER_STRIDER_EGG_MIDDLE.get());
                        pOutput.accept(EndlessStoreBlocks.WATER_STRIDER_EGG_LARGE.get());
                        pOutput.accept(EndlessStoreBlocks.WATER_STRIDER_EGG_HUGE.get());
                        pOutput.accept(EndlessStoreBlocks.PAINTING_HORIZONTAL_FOREST.get());
                        pOutput.accept(EndlessStoreBlocks.PAINTING_HORIZONTAL_SUNSET.get());
                        pOutput.accept(EndlessStoreBlocks.PAINTING_HORIZONTAL_EMPTINESS.get());
                        pOutput.accept(EndlessStoreBlocks.PAINTING_VERTICAL_LAKE.get());
                        pOutput.accept(EndlessStoreBlocks.PAINTING_VERTICAL_CITY.get());
                        pOutput.accept(EndlessStoreBlocks.PAINTING_VERTICAL_PIPES.get());
                        pOutput.accept(EndlessStoreBlocks.FILING_CABINET_CLOSED.get());
                        pOutput.accept(EndlessStoreBlocks.FILING_CABINET_OPEN_UP.get());
                        pOutput.accept(EndlessStoreBlocks.FILING_CABINET_OPEN_DOWN.get());
                        pOutput.accept(EndlessStoreBlocks.SHOWCASE_WITH_DISHES.get());
                        pOutput.accept(EndlessStoreBlocks.VENDING_MACHINE.get());
                        pOutput.accept(EndlessStoreBlocks.GRID_VERTICAL.get());
                        pOutput.accept(EndlessStoreBlocks.STORAGE_SHELVES.get());
                        pOutput.accept(EndlessStoreBlocks.BATH.get());

                        pOutput.accept(EndlessStoreBlocks.LAMP.get());
                        pOutput.accept(EndlessStoreBlocks.LAMP_HIGH.get());
                        pOutput.accept(EndlessStoreBlocks.LAMP_LED.get());
                        pOutput.accept(EndlessStoreBlocks.LAMPSHADE.get());
                        pOutput.accept(EndlessStoreBlocks.LIGHT_BULB.get());
                        pOutput.accept(EndlessStoreBlocks.LAMP_WALL.get());
                        pOutput.accept(EndlessStoreBlocks.HANGING_LAMP.get());

                        pOutput.accept(EndlessStoreItems.EMPLOYEE_SPAWN_EGG.get());
                        pOutput.accept(EndlessStoreItems.SECURITY_SPAWN_EGG.get());
                        pOutput.accept(EndlessStoreItems.JACK_SPAWN_EGG.get());
                        pOutput.accept(EndlessStoreItems.WATCHER_SPAWN_EGG.get());
                        pOutput.accept(EndlessStoreItems.WATER_STRIDER_EGG.get());

                        pOutput.accept(EndlessStoreItems.WOODEN_BOARD.get());
                        pOutput.accept(EndlessStoreItems.CONCRETE_CRUMBS.get());
                        pOutput.accept(EndlessStoreItems.PIECE_OF_CARDBOARD.get());
                        pOutput.accept(EndlessStoreItems.METAL_PIPES.get());
                        pOutput.accept(EndlessStoreItems.NAILS.get());
                        pOutput.accept(EndlessStoreItems.BOLTS.get());
                        pOutput.accept(EndlessStoreItems.METAL_PLATES.get());
                        pOutput.accept(EndlessStoreItems.CONCRETE_BRICK.get());
                        pOutput.accept(EndlessStoreItems.SCRAP.get());
                        pOutput.accept(EndlessStoreItems.MOLTEN_SCRAP.get());
                        pOutput.accept(EndlessStoreItems.PLASTIC_BUCKET.get());
                        pOutput.accept(EndlessStoreFluids.PLASTIC_ACID_BUCKET.get());
                        pOutput.accept(EndlessStoreFluids.PLASTIC_WASTE_WATER_BUCKET.get());
                        pOutput.accept(EndlessStoreItems.TRASH_PLASTIC.get());
                        pOutput.accept(EndlessStoreItems.BROKEN_GLASS.get());
                        pOutput.accept(EndlessStoreItems.PIECE_OF_LINOLEUM.get());
                        pOutput.accept(EndlessStoreItems.WIRES.get());
                        pOutput.accept(EndlessStoreItems.PIECE_OF_WALLPAPER.get());
                        pOutput.accept(EndlessStoreItems.SAWDUST.get());
                        pOutput.accept(EndlessStoreItems.COARSE_FABRIC.get());
                        pOutput.accept(EndlessStoreItems.CANISTER.get());
                        pOutput.accept(EndlessStoreItems.EMPTY_CAN.get());
                        pOutput.accept(EndlessStoreItems.ELECTRONIC_BOARD.get());
                        pOutput.accept(EndlessStoreItems.MICROCHIP.get());
                        pOutput.accept(EndlessStoreItems.COIL_COPPER_WIRES.get());
                        pOutput.accept(EndlessStoreItems.TRANSISTOR.get());

                        pOutput.accept(EndlessStoreItems.BREPSI.get());
                        pOutput.accept(EndlessStoreItems.WET_WALLPAPER.get());
                        pOutput.accept(EndlessStoreItems.SAWDUST_SOUP.get());
                        pOutput.accept(EndlessStoreItems.HARDTACK.get());
                        pOutput.accept(EndlessStoreItems.CRISP.get());
                        pOutput.accept(EndlessStoreItems.HOTDOG.get());
                        pOutput.accept(EndlessStoreItems.PANCAKE.get());
                        pOutput.accept(EndlessStoreItems.SANDWICH.get());
                        pOutput.accept(EndlessStoreItems.WATER_STRIDER_EGGS.get());

                        pOutput.accept(EndlessStoreItems.FLIMSY_PICKAXE.get());
                        pOutput.accept(EndlessStoreItems.HANDMADE_PICKAXE.get());
                        pOutput.accept(EndlessStoreItems.DURABLE_PICKAXE.get());
                        pOutput.accept(EndlessStoreItems.PROFESSIONAL_PICKAXE.get());

                        pOutput.accept(EndlessStoreItems.HANDMADE_AXE.get());
                        pOutput.accept(EndlessStoreItems.HANDMADE_SHOVEL.get());

                        pOutput.accept(EndlessStoreItems.KNIFE.get());
                        pOutput.accept(EndlessStoreItems.FRYING_PAN.get());
                        pOutput.accept(EndlessStoreItems.BATON.get());
                        pOutput.accept(EndlessStoreItems.EXTINGUISHER.get());

                        pOutput.accept(EndlessStoreItems.EMPLOYEES_SHIRT.get());
                        pOutput.accept(EndlessStoreItems.SECURITY_CAP.get());
                        pOutput.accept(EndlessStoreItems.CARDBOARD_HELMET.get());
                        pOutput.accept(EndlessStoreItems.CARDBOARD_TROUSERS.get());
                        pOutput.accept(EndlessStoreItems.HANDMADE_CHAIN_MAIL.get());
                        pOutput.accept(EndlessStoreItems.HANDMADE_CHAIN_MAIL_TROUSERS.get());
                        pOutput.accept(EndlessStoreItems.SECURITY_SHIRT.get());


                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
