package net.es.endless_store_mod.block;

import net.es.endless_store_mod.EndlessStoreMod;
import net.es.endless_store_mod.block.custom.*;
import net.es.endless_store_mod.block.custom.Mirror;
import net.es.endless_store_mod.item.EndlessStoreItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class EndlessStoreBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, EndlessStoreMod.MOD_ID);

    public static final RegistryObject<Block> LAMINATE_FLOORING_LIGHT = registerBlock("laminate_flooring_light", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).sound(SoundType.WOOD).strength(1.15f)));
    public static final RegistryObject<Block> LAMINATE_FLOORING_LIGHT_VERTICAL = registerBlock("laminate_flooring_light_vertical", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).sound(SoundType.WOOD).strength(1.15f)));
    public static final RegistryObject<Block> LAMINATE_FLOORING_DARK = registerBlock("laminate_flooring_dark", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).sound(SoundType.WOOD).strength(1.15f)));
    public static final RegistryObject<Block> LAMINATE_FLOORING_MANGROVE = registerBlock("laminate_flooring_mangrove", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).sound(SoundType.WOOD).strength(1.15f)));
    public static final RegistryObject<Block> LAMINATE_FLOORING_WHITE = registerBlock("laminate_flooring_white", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.WOOD).strength(1.15f)));
    public static final RegistryObject<Block> PLITKA_WATER = registerBlock("plitka_water", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).sound(SoundType.STONE).strength(1.8f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CONCRETE = registerBlock("concrete", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(2.5f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> MOSSY_CONCRETE = registerBlock("mossy_concrete", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> OVERGROWN_CONCRETE = registerBlock("overgrown_concrete", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).sound(SoundType.STONE).strength(1.9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> DARK_MOSSY_CONCRETE = registerBlock("dark_mossy_concrete", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> DARK_OVERGROWN_CONCRETE = registerBlock("dark_overgrown_concrete", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).sound(SoundType.STONE).strength(1.9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> DARK_CRACKED_CONCRETE = registerBlock("dark_cracked_concrete", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).sound(SoundType.STONE).strength(1.75f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> DARK_CONCRETE = registerBlock("dark_concrete", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).sound(SoundType.STONE).strength(2.5f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CONCRETE_SLAB = registerBlock("concrete_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(2.5f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CONCRETE_STAIRS = registerBlock("concrete_stairs", () -> new StairBlock(CONCRETE.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(2.5f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> DARK_CONCRETE_SLAB = registerBlock("dark_concrete_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).sound(SoundType.STONE).strength(2.5f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> DARK_CONCRETE_STAIRS = registerBlock("dark_concrete_stairs", () -> new StairBlock(DARK_CONCRETE.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).sound(SoundType.STONE).strength(2.5f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> YELLOW_CONCRETE = registerBlock("yellow_concrete", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).sound(SoundType.STONE).strength(2.5f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ORANGE_GRANIT = registerBlock("orange_granit", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).sound(SoundType.STONE).strength(1.5f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> WHITE_BRICK = registerBlock("white_brick", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.STONE).strength(3f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> WHITE_BRICK_ALTERNATIVE = registerBlock("white_brick_alternative", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.STONE).strength(3f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> WHITE_BRICK_SLAB = registerBlock("white_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.STONE).strength(3f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> WHITE_BRICK_STAIRS_ES = registerBlock("white_brick_stairs_es", () -> new StairBlock(WHITE_BRICK.get().defaultBlockState(),BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.STONE).strength(3f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> WHITE_BRICK_SLAB_ALTERNATIVE = registerBlock("white_brick_slab_alternative", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.STONE).strength(3f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> WHITE_BRICK_STAIRS_ALTERNATIVE = registerBlock("white_brick_stairs_alternative", () -> new StairBlock(WHITE_BRICK_ALTERNATIVE.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.STONE).strength(3f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> LINOLEUM_LIGHT = registerBlock("linoleum_light", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).sound(SoundType.WOOD).strength(1.2f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> LINOLEUM_BROWN = registerBlock("linoleum_brown", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).sound(SoundType.WOOD).strength(1.2f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> WALLPAPER_YELLOW = registerBlock("wallpaper_yellow", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).sound(SoundType.WOOD).strength(0.65f)));
    public static final RegistryObject<Block> WALLPAPER_WHITE = registerBlock("wallpaper_white", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.WOOD).strength(0.65f)));
    public static final RegistryObject<Block> WALLPAPER_BLUE = registerBlock("wallpaper_blue", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).sound(SoundType.WOOD).strength(0.65f)));
    public static final RegistryObject<Block> METAL_GARAGE_DOOR = registerBlock("metal_garage_door", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).sound(SoundType.METAL).strength(2.6f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PATTERNED_CARPET = registerBlock("patterned_carpet", () -> new CarpetBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.WOOL).strength(1f)));
    public static final RegistryObject<Block> PLASTIC_GARBAGE_COLORED = registerBlock("plastic_garbage_colored", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).sound(SoundType.STONE).strength(1f)));
    public static final RegistryObject<Block> PLASTIC_GARBAGE_GREY = registerBlock("plastic_garbage_grey", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.STONE).strength(1f)));
    public static final RegistryObject<Block> PLASTIC_GARBAGE_BLUE = registerBlock("plastic_garbage_blue", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.DIAMOND).sound(SoundType.STONE).strength(1f)));
    public static final RegistryObject<Block> CONCRETE_FURNACE = registerBlock("concrete_furnace", () -> new CustomFurnaceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GRAY).sound(SoundType.STONE).strength(3.5f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CEILING_TILES_WHITE = registerBlock("ceiling_tiles_white", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.STONE).strength(3f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> CONCRETE_MUD = registerBlock("concrete_mud", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.DIRT).sound(SoundType.ROOTED_DIRT).strength(2.25f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PROBABLY_MUD = registerBlock("probably_mud", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).sound(SoundType.MUD).strength(1.25f)));

    public static final RegistryObject<Block> CHAIN_MAIL = registerBlock("chain_mail", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.RAW_IRON).sound(SoundType.CHAIN).strength(3f).requiresCorrectToolForDrops().noOcclusion()));

//    private static boolean never(BlockState p_50779_, BlockGetter p_50780_, BlockPos p_50782_) {
//        return (boolean)false;
//    }

    public static final RegistryObject<Block> GREEN_MUD = registerBlock("green_mud", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).sound(SoundType.SLIME_BLOCK).strength(2.25f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> LIME_MUD = registerBlock("lime_mud", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).sound(SoundType.SLIME_BLOCK).strength(2.25f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> LAMINATE_FLOORING_DARK_DAMAGED = registerBlock("laminate_flooring_dark_damaged", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).sound(SoundType.WOOD).strength(1.05f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> LAMINATE_FLOORING_LIGHT_DAMAGED = registerBlock("laminate_flooring_light_damaged", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).sound(SoundType.WOOD).strength(1.05f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> LAMINATE_FLOORING_MANGROVE_DAMAGED = registerBlock("laminate_flooring_mangrove_damaged", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).sound(SoundType.WOOD).strength(1.05f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> LAMINATE_FLOORING_WHITE_DAMAGED = registerBlock("laminate_flooring_white_damaged", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).sound(SoundType.WOOD).strength(1.05f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> WALLPAPER_BLUE_DAMAGED = registerBlock("wallpaper_blue_damaged", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).sound(SoundType.WOOD).strength(0.55f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> WALLPAPER_WHITE_DAMAGED = registerBlock("wallpaper_white_damaged", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.WOOD).strength(0.55f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> WALLPAPER_YELLOW_DAMAGED = registerBlock("wallpaper_yellow_damaged", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).sound(SoundType.WOOD).strength(0.55f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> WHITE_BRICK_DAMAGED = registerBlock("white_brick_damaged", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.STONE).strength(2.8f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> WHITE_BRICK_MOSSY = registerBlock("white_brick_mossy", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.STONE).strength(2.5f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> LITERALLY_NOTHING = registerBlock("literally_nothing", () -> new LiterallyNothingPortal(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).sound(SoundType.NETHERRACK).strength(200f)));

    public static final RegistryObject<Block> CEILING_TILES_WHITE_ANOTHER = registerBlock("ceiling_tiles_white_another", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(4f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> WALL_BLUE_TILES = registerBlock("wall_blue_tiles", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(4f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TILES_WITH_BLACK_INCLUSIONS = registerBlock("tiles_with_black_conclusions", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(4f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> FLOOR_TILES_MULTI_COLORED = registerBlock("floor_tiles_multi_colored", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(4f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> METAL_PIPE = registerBlock("metal_pipe", () -> new MetalPipe(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).sound(SoundType.METAL).strength(2f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> METAL_CORNER_PIPE = registerBlock("metal_corner_pipe", () -> new MetalCornerPipe(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.METAL).strength(2f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> SHELVES = registerBlock("shelves", () -> new ShelvesProp(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).sound(SoundType.WOOD).strength(1.75f).noOcclusion()));
    public static final RegistryObject<Block> BASEBOARD = registerBlock("baseboard", () -> new Baseboard(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).sound(SoundType.WOOD).strength(0.6f)));
    public static final RegistryObject<Block> BASEBOARD_CORNER = registerBlock("baseboard_corner", () -> new BaseboardCorner(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).sound(SoundType.WOOD).strength(0.6f)));
    public static final RegistryObject<Block> PLUSH_MOUSE_GRAF = registerBlock("plush_mouse_graf", () -> new PlushMouseGraf(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.WOOL).strength(0.5f)));
    public static final RegistryObject<Block> OUTDOOR_SIGN = registerBlock("outdoor_sign", () -> new OutdoorSign(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).sound(SoundType.WOOD).strength(0.65f)));
    public static final RegistryObject<Block> FIRE_CRANE = registerBlock("fire_crane", () -> new FireCrane(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).sound(SoundType.METAL).strength(1.25f).noOcclusion()));
    public static final RegistryObject<Block> KETTLE = registerBlock("kettle", () -> new Kettle(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.METAL).strength(0.5f)));
    public static final RegistryObject<Block> WALL_CLOCK = registerBlock("wall_clock", () -> new WallClock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).sound(SoundType.METAL).strength(0.5f)));
    public static final RegistryObject<Block> PALLET = registerBlock("pallet", () -> new Pallet(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).sound(SoundType.WOOD).strength(1.45f).noOcclusion()));
    public static final RegistryObject<Block> FIRE_DETECTOR = registerBlock("fire_detector", () -> new FireDetector(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).sound(SoundType.METAL).strength(0.9f)));
    public static final RegistryObject<Block> WALL_MAP = registerBlock("wall_map", () -> new WallMap(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.METAL).strength(2.6f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CARGO_TROLLEY = registerBlock("cargo_trolley", () -> new CargoTrolley(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.METAL).strength(2.6f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CARGO_TROLLEY_LONG_ONE_HANDLES = registerBlock("cargo_trolley_long_one_handles", () -> new CargoTrolleyLong(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.METAL).strength(2.6f).requiresCorrectToolForDrops().noOcclusion()));
    public static final RegistryObject<Block> CARGO_TROLLEY_LONG_TWO_HANDLES = registerBlock("cargo_trolley_long_two_handles", () -> new CargoTrolleyLong(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.METAL).strength(2f).requiresCorrectToolForDrops().noOcclusion()));
    public static final RegistryObject<Block> BOOK_VARIANT_ONE = registerBlock("book_variant_one", () -> new BookVariantOne(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).sound(SoundType.STONE).strength(0.8f)));
    public static final RegistryObject<Block> BOOK_VARIANT_TWO = registerBlock("book_variant_two", () -> new BookVariantTwo(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).sound(SoundType.STONE).strength(0.8f)));
    public static final RegistryObject<Block> BOOK_VARIANT_THREE = registerBlock("book_variant_three", () -> new BookVariantThree(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).sound(SoundType.STONE).strength(0.8f)));
    public static final RegistryObject<Block> ARMCHAIR = registerBlock("armchair", () -> new Armchair(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).sound(SoundType.WOOL).strength(4.1f).noOcclusion()));
    public static final RegistryObject<Block> WARDROBE = registerBlock("wardrobe", () -> new Wardrobe(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.WOOD).strength(4.5f)));
    public static final RegistryObject<Block> BIG_WARDROBE = registerBlock("big_wardrobe", () -> new BigWardrobe(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.WOOD).strength(3.5f).noOcclusion()));
    public static final RegistryObject<Block> CARDBOARD_BOX = registerBlock("cardboard_box", () -> new CardboardBox(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).sound(SoundType.WOOD).strength(0.7f)));
    public static final RegistryObject<Block> CARDBOARD_BOX_OPEN = registerBlock("cardboard_box_open", () -> new CardboardBox(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).sound(SoundType.WOOD).strength(0.4f)));
    public static final RegistryObject<Block> CARDBOARD_BOX_MIDDLE = registerBlock("cardboard_box_middle", () -> new CardboardBoxMiddle(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).sound(SoundType.WOOD).strength(0.8f)));
    public static final RegistryObject<Block> CARDBOARD_BOX_MIDDLE_OPEN = registerBlock("cardboard_box_middle_open", () -> new CardboardBoxMiddle(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).sound(SoundType.WOOD).strength(0.5f)));
    public static final RegistryObject<Block> CARDBOARD_BOX_LARGE = registerBlock("cardboard_box_large", () -> new CardboardBoxLarge(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).sound(SoundType.WOOD).strength(1f)));
    public static final RegistryObject<Block> CARDBOARD_BOX_LARGE_OPEN = registerBlock("cardboard_box_large_open", () -> new CardboardBoxLarge(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).sound(SoundType.WOOD).strength(0.7f)));
    public static final RegistryObject<Block> CARDBOARD_BOX_HUGE = registerBlock("cardboard_box_huge", () -> new CardboardBoxHuge(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).sound(SoundType.WOOD).strength(1.5f).noOcclusion()));
    public static final RegistryObject<Block> CARDBOARD_BOX_HUGE_OPEN = registerBlock("cardboard_box_huge_open", () -> new CardboardBoxHuge(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).sound(SoundType.WOOD).strength(1f).noOcclusion()));
    public static final RegistryObject<Block> CARDBOARD_BOX_HUGE_TAPE = registerBlock("cardboard_box_huge_tape", () -> new CardboardBoxHugeTape(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).sound(SoundType.WOOD).strength(1.5f).noOcclusion()));
    public static final RegistryObject<Block> TRASH_CAN = registerBlock("trash_can", () -> new TrashCan(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GRAY).sound(SoundType.STONE).strength(1.25f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PLATE = registerBlock("plate", () -> new Plate(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.METAL).strength(0.4f)));

    public static final RegistryObject<Block> FUSE_BOX = registerBlock("fuse_box", () -> new FuseBox(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).sound(SoundType.METAL).strength(1.35f)));
    public static final RegistryObject<Block> FUSE_BOX_LARGE = registerBlock("fuse_box_large", () -> new FuseBoxLarge(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).sound(SoundType.METAL).strength(1.65f)));
    public static final RegistryObject<Block> TOOLBOX = registerBlock("toolbox", () -> new Toolbox(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).sound(SoundType.METAL).strength(0.6f)));
    public static final RegistryObject<Block> COIL_WIRES = registerBlock("coil_wires", () -> new CoilWires(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).sound(SoundType.METAL).strength(1.1f)));
    public static final RegistryObject<Block> WOODEN_CHAIR = registerBlock("wooden_chair", () -> new WoodenChair(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).sound(SoundType.WOOD).strength(2.2f)));
    public static final RegistryObject<Block> WOODEN_TABLE = registerBlock("wooden_table", () -> new AbstractTable(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).sound(SoundType.WOOD).strength(2f).noOcclusion()));
    public static final RegistryObject<Block> WOODEN_TABLE_LONG = registerBlock("wooden_table_long", () -> new AbstractTableLong(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).sound(SoundType.WOOD).strength(2.4f).noOcclusion()));
    public static final RegistryObject<Block> PLASTIC_TABLE = registerBlock("plastic_table", () -> new AbstractTable(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.STONE).strength(1.8f).noOcclusion()));
    public static final RegistryObject<Block> PLASTIC_TABLE_LONG = registerBlock("plastic_table_long", () -> new AbstractTableLong(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.STONE).strength(2f).noOcclusion()));
    public static final RegistryObject<Block> TOWEL_HOLDER = registerBlock("towel_holder", () -> new TowelHolder(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.METAL).strength(1f)));
    public static final RegistryObject<Block> PADDED_STOOL = registerBlock("padded_stool", () -> new PaddedStool(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).sound(SoundType.WOOL).strength(2.1f).noOcclusion()));
    public static final RegistryObject<Block> MIRROR = registerBlock("mirror", () -> new Mirror(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.GLASS).strength(0.8f)));
    public static final RegistryObject<Block> HIGH_MIRROR = registerBlock("high_mirror", () -> new HighMirror(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.GLASS).strength(1f)));
    public static final RegistryObject<Block> RADIATOR_RIGHT_SIDE = registerBlock("radiator_right_side", () -> new Radiator(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.METAL).strength(1.8f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> RADIATOR_LEFT_SIDE = registerBlock("radiator_left_side", () -> new Radiator(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.METAL).strength(1.8f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> SQUARE_SHELF = registerBlock("square_shelf", () -> new SquareShelf(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.WOOD).strength(1.45f)));
    public static final RegistryObject<Block> SINK = registerBlock("sink", () -> new Sink(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops().noOcclusion()));
    public static final RegistryObject<Block> SOCKET = registerBlock("socket", () -> new Socket(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.METAL).strength(1.1f)));
    public static final RegistryObject<Block> SIDE_PIPES = registerBlock("side_pipes", () -> new SidePipes(BlockBehaviour.Properties.of().mapColor(MapColor.RAW_IRON).sound(SoundType.METAL).strength(1.8f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CEILING_PIPES = registerBlock("ceiling_pipes", () -> new CeilingPipes(BlockBehaviour.Properties.of().mapColor(MapColor.RAW_IRON).sound(SoundType.METAL).strength(1.8f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PEDESTAL_ALTEA = registerBlock("pedestal_altea", () -> new PedestalAltea(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.WOOD).strength(2f)));
    public static final RegistryObject<Block> METAL_CLOSET = registerBlock("metal_closet", () -> new MetalCloset(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.METAL).strength(3.75f).requiresCorrectToolForDrops().noOcclusion()));
    public static final RegistryObject<Block> WHITE_BOOKSHELF = registerBlock("white_bookshelf", () -> new WhiteBookshelf(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.WOOD).strength(1.45f)));
    public static final RegistryObject<Block> CALENDAR = registerBlock("calendar", () -> new Calendar(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.STONE).strength(0.5f)));
    public static final RegistryObject<Block> DECORATIVE_FENCE = registerBlock("decorative_fence", () -> new DecorativeFence(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).sound(SoundType.METAL).strength(0.65f)));
    public static final RegistryObject<Block> PRACTICAL_FENCE = registerBlock("practical_fence", () -> new PracticalFence(BlockBehaviour.Properties.of().mapColor(MapColor.RAW_IRON).sound(SoundType.METAL).strength(2.5f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PLASTIC_CHAIR = registerBlock("plastic_chair", () -> new PlasticChair(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.STONE).strength(2.2f)));
    public static final RegistryObject<Block> VERTICAL_WASHING_MACHINE = registerBlock("vertical_washing_machine", () -> new VerticalWashingMachine(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).sound(SoundType.METAL).strength(3.85f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BEDSIDE_TABLE = registerBlock("bedside_table", () -> new BedsideTable(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.WOOD).strength(2.2f)));
    public static final RegistryObject<Block> GLOBE = registerBlock("globe", () -> new Globe(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).sound(SoundType.WOOD).strength(0.5f)));
    public static final RegistryObject<Block> AQUARIUM = registerBlock("aquarium", () -> new Aquarium(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).sound(SoundType.GLASS).strength(1.25f).noOcclusion()));
    public static final RegistryObject<Block> MRE = registerBlock("mre", () -> new MRE(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).sound(SoundType.BAMBOO).strength(0.5f)));
    public static final RegistryObject<Block> BOTTLE = registerBlock("bottle", () -> new Bottle(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).sound(SoundType.GLASS).strength(0.5f)));
    public static final RegistryObject<Block> SAFE = registerBlock("safe", () -> new Safe(BlockBehaviour.Properties.of().mapColor(MapColor.RAW_IRON).sound(SoundType.METAL).strength(5.5f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CEILING_GRID = registerBlock("ceiling_grid", () -> new CeilingGrid(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).sound(SoundType.METAL).strength(2f).requiresCorrectToolForDrops().noOcclusion()));
    public static final RegistryObject<Block> SIDE_STORAGE_SHELVES = registerBlock("side_storage_shelves", () -> new SideStorageShelves(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).sound(SoundType.METAL).strength(2f).requiresCorrectToolForDrops().noOcclusion()));
    public static final RegistryObject<Block> SIDE_BEAMS = registerBlock("side_beams", () -> new SideBeams(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).sound(SoundType.METAL).strength(2f).requiresCorrectToolForDrops().noOcclusion()));
    public static final RegistryObject<Block> COMPUTER = registerBlock("computer", () -> new Computer(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.METAL).strength(1.5f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> OLD_SAFE = registerBlock("old_safe", () -> new OldSafe(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).sound(SoundType.METAL).strength(-1.0F, 3600000.0F).noLootTable()));
    public static final RegistryObject<Block> VERTICAL_BOILER = registerBlock("vertical_boiler", () -> new VerticalBoiler(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.METAL).strength(3.75f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> HORIZONTAL_BOILER = registerBlock("horizontal_boiler", () -> new HorizontalBoiler(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.METAL).strength(3.75f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ORGANIZER = registerBlock("organizer", () -> new Organizer(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).sound(SoundType.WOOD).strength(0.5f)));
    public static final RegistryObject<Block> EMPTY_ORGANIZER = registerBlock("empty_organizer", () -> new Organizer(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).sound(SoundType.WOOD).strength(0.5f)));
    public static final RegistryObject<Block> SMALL_WOODEN_BARRICADES = registerBlock("small_wooden_barricades", () -> new WoodenBarricades(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).sound(SoundType.WOOD).strength(1.0f).requiresCorrectToolForDrops().noOcclusion()));
    public static final RegistryObject<Block> MEDIUM_WOODEN_BARRICADES = registerBlock("medium_wooden_barricades", () -> new WoodenBarricades(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).sound(SoundType.WOOD).strength(1.5f).requiresCorrectToolForDrops().noOcclusion()));
    public static final RegistryObject<Block> LARGE_WOODEN_BARRICADES = registerBlock("large_wooden_barricades", () -> new WoodenBarricades(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).sound(SoundType.WOOD).strength(2.0f).requiresCorrectToolForDrops().noOcclusion()));
    public static final RegistryObject<Block> MUD_ON_FLOOR = registerBlock("mud_on_floor", () -> new GarbageOnFloor(BlockBehaviour.Properties.of().noOcclusion()));
    public static final RegistryObject<Block> GARBAGE_ON_FLOOR = registerBlock("garbage_on_floor", () -> new GarbageOnFloor(BlockBehaviour.Properties.of().replaceable().noCollission().noLootTable().noOcclusion()));
    public static final RegistryObject<Block> CONCRETE_CRUMBS_ON_FLOOR = registerBlock("concrete_crumbs_on_floor", () -> new GarbageOnFloor(BlockBehaviour.Properties.of().replaceable().noCollission().noLootTable().noOcclusion()));
    public static final RegistryObject<Block> WATER_STRIDER_EGG = registerBlock("water_strider_egg", () -> new WaterStriderEgg(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).sound(SoundType.SLIME_BLOCK).strength(0.5f).noOcclusion()));
    public static final RegistryObject<Block> WATER_STRIDER_EGG_MIDDLE = registerBlock("water_strider_egg_middle", () -> new WaterStriderEgg(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).sound(SoundType.SLIME_BLOCK).strength(0.5f).noOcclusion()));
    public static final RegistryObject<Block> WATER_STRIDER_EGG_LARGE = registerBlock("water_strider_egg_large", () -> new WaterStriderEgg(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).sound(SoundType.SLIME_BLOCK).strength(0.5f).noOcclusion()));
    public static final RegistryObject<Block> WATER_STRIDER_EGG_HUGE = registerBlock("water_strider_egg_huge", () -> new WaterStriderEgg(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).sound(SoundType.SLIME_BLOCK).strength(0.5f).noOcclusion()));
    public static final RegistryObject<Block> PAINTING_HORIZONTAL_FOREST = registerBlock("painting_horizontal_forest", () -> new PaintingHorizontal(BlockBehaviour.Properties.of().sound(SoundType.HANGING_SIGN).strength(1f)));
    public static final RegistryObject<Block> PAINTING_HORIZONTAL_SUNSET = registerBlock("painting_horizontal_sunset", () -> new PaintingHorizontal(BlockBehaviour.Properties.of().sound(SoundType.HANGING_SIGN).strength(1f)));
    public static final RegistryObject<Block> PAINTING_HORIZONTAL_EMPTINESS = registerBlock("painting_horizontal_emptiness", () -> new PaintingHorizontal(BlockBehaviour.Properties.of().sound(SoundType.HANGING_SIGN).strength(1f)));
    public static final RegistryObject<Block> PAINTING_VERTICAL_LAKE = registerBlock("painting_vertical_lake", () -> new PaintingVertical(BlockBehaviour.Properties.of().sound(SoundType.HANGING_SIGN).strength(1f)));
    public static final RegistryObject<Block> PAINTING_VERTICAL_CITY = registerBlock("painting_vertical_city", () -> new PaintingVertical(BlockBehaviour.Properties.of().sound(SoundType.HANGING_SIGN).strength(1f)));
    public static final RegistryObject<Block> PAINTING_VERTICAL_PIPES = registerBlock("painting_vertical_pipes", () -> new PaintingVertical(BlockBehaviour.Properties.of().sound(SoundType.HANGING_SIGN).strength(1f)));
    public static final RegistryObject<Block> FILING_CABINET_CLOSED = registerBlock("filing_cabinet_closed", () -> new FilingCabinet(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(1.5f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> FILING_CABINET_OPEN_UP = registerBlock("filing_cabinet_open_up", () -> new FilingCabinet(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(1.5f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> FILING_CABINET_OPEN_DOWN = registerBlock("filing_cabinet_open_down", () -> new FilingCabinet(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(1.5f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> SHOWCASE_WITH_DISHES = registerBlock("showcase_with_dishes", () -> new ShowcaseWithDishes(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).sound(SoundType.METAL).strength(2f).requiresCorrectToolForDrops().noOcclusion()));


    // Не имеют лута и крафта
    public static final RegistryObject<Block> VENDING_MACHINE = registerBlock("vending_machine", () -> new VendingMachine(BlockBehaviour.Properties.of().mapColor(MapColor.RAW_IRON).sound(SoundType.METAL).strength(4f).noOcclusion().requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> GRID_VERTICAL = registerBlock("grid_vertical", () -> new GridPropVertical(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).sound(SoundType.METAL).strength(2f).requiresCorrectToolForDrops().noOcclusion()));
    public static final RegistryObject<Block> STORAGE_SHELVES = registerBlock("storage_shelves", () -> new StorageShelves(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).sound(SoundType.METAL).strength(2.3f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BATH = registerBlock("bath", () -> new Bath(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.METAL).strength(2f).requiresCorrectToolForDrops().noOcclusion()));


    // Светяшки и всё что даёт хоть какой-то свет
    public static final RegistryObject<Block> LAMP = registerBlock("lamp", () -> new LampProp(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.METAL).strength(0.5f).noOcclusion().lightLevel(litBlockEmission(10))));
    public static final RegistryObject<Block> LAMP_HIGH = registerBlock("lamp_high", () -> new LampHighProp(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.METAL).strength(1.2f).noOcclusion().lightLevel(litBlockEmission(14))));
    public static final RegistryObject<Block> LAMP_LED = registerBlock("lamp_led", () -> new LampLED(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.METAL).strength(2f).requiresCorrectToolForDrops().noOcclusion().lightLevel(litBlockEmission(14))));
    public static final RegistryObject<Block> LAMPSHADE = registerBlock("lampshade", () -> new Lampshade(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.METAL).strength(1f).noOcclusion().lightLevel(litBlockEmission(9))));
    public static final RegistryObject<Block> LIGHT_BULB = registerBlock("light_bulb", () -> new LightBulb(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.METAL).strength(1f).noOcclusion().lightLevel(litBlockEmission(11))));
    public static final RegistryObject<Block> LAMP_WALL = registerBlock("lamp_wall", () -> new LampWall(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.METAL).strength(2f).requiresCorrectToolForDrops().noOcclusion().lightLevel(litBlockEmission(11))));
    public static final RegistryObject<Block> HANGING_LAMP = registerBlock("hanging_lamp", () -> new HangingLamp(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).sound(SoundType.METAL).strength(3f).requiresCorrectToolForDrops().noOcclusion().lightLevel(litBlockEmission(14))));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static ToIntFunction<BlockState> litBlockEmission(int pLightValue) {
        return (p_50763_) -> {
            return p_50763_.getValue(BlockStateProperties.LIT) ? pLightValue : 0;
        };
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return EndlessStoreItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
