package net.es.endless_store_mod.block;

import net.es.endless_store_mod.block.custom.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.es.endless_store_mod.EndlessStoreMod;
import net.es.endless_store_mod.item.EndlessStoreItemGroup;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.ToIntFunction;

public class EndlessStoreBlocks {
    private static ToIntFunction<BlockState> createLightLevelFromLitBlockState() {
        return (state) -> (Boolean)state.get(Properties.LIT) ? 13 : 0;
    }

    // Блоки с ванильным шейпом
    public static final Block LAMINATE_FLOORING_LIGHT = registerBlock("laminate_flooring_light", new RotateBlock(Block.Settings.of(Material.WOOD).strength(1.15f)));
    public static final Block LAMINATE_FLOORING_LIGHT_VERTICAL = registerBlock("laminate_flooring_light_vertical", new RotateBlock(Block.Settings.of(Material.WOOD).strength(1.15f)));
    public static final Block LAMINATE_FLOORING_DARK = registerBlock("laminate_flooring_dark", new RotateBlock(Block.Settings.of(Material.WOOD).strength(1.15f)));
    public static final Block LAMINATE_FLOORING_MANGROVE = registerBlock("laminate_flooring_mangrove", new RotateBlock(Block.Settings.of(Material.WOOD).strength(1.15f)));
    public static final Block LAMINATE_FLOORING_WHITE = registerBlock("laminate_flooring_white", new RotateBlock(Block.Settings.of(Material.STONE).strength(1.15f)));
    public static final Block PLITKA_WATER = registerBlock("plitka_water", new RotateBlock(Block.Settings.of(Material.STONE).strength(1.8f).requiresTool()));
    public static final Block CONCRETE = registerBlock("concrete", new RotateBlock(Block.Settings.of(Material.STONE).strength(2.5f).requiresTool()));
    public static final Block MOSSY_CONCRETE = registerBlock("mossy_concrete", new RotateBlock(Block.Settings.of(Material.STONE).strength(2f).requiresTool()));
    public static final Block OVERGROWN_CONCRETE = registerBlock("overgrown_concrete", new RotateBlock(Block.Settings.of(Material.STONE).strength(1.9f).requiresTool()));
    public static final Block DARK_MOSSY_CONCRETE = registerBlock("dark_mossy_concrete", new RotateBlock(Block.Settings.of(Material.STONE).strength(2f).requiresTool()));
    public static final Block DARK_OVERGROWN_CONCRETE = registerBlock("dark_overgrown_concrete", new RotateBlock(Block.Settings.of(Material.STONE).strength(1.9f).requiresTool()));
    public static final Block DARK_CRACKED_CONCRETE = registerBlock("dark_cracked_concrete", new RotateBlock(Block.Settings.of(Material.STONE).strength(1.75f).requiresTool()));
    public static final Block DARK_CONCRETE = registerBlock("dark_concrete", new RotateBlock(Block.Settings.of(Material.STONE).strength(2.5f).requiresTool()));
    public static final Block CONCRETE_SLAB = registerBlock("concrete_slab", new SlabBlock(Block.Settings.of(Material.STONE).strength(2.5f).requiresTool()));
    public static final Block CONCRETE_STAIRS = registerBlock("concrete_stairs", new CustomStairsBlock(EndlessStoreBlocks.CONCRETE.getDefaultState(), Block.Settings.of(Material.STONE).strength(2.5f).requiresTool()));
    public static final Block DARK_CONCRETE_SLAB = registerBlock("dark_concrete_slab", new SlabBlock(Block.Settings.of(Material.STONE).strength(2.5f).requiresTool()));
    public static final Block DARK_CONCRETE_STAIRS = registerBlock("dark_concrete_stairs", new CustomStairsBlock(EndlessStoreBlocks.DARK_CONCRETE.getDefaultState(), Block.Settings.of(Material.STONE).strength(2.5f).requiresTool()));
    public static final Block YELLOW_CONCRETE = registerBlock("yellow_concrete", new RotateBlock(Block.Settings.of(Material.STONE).strength(2.5f).requiresTool()));
    public static final Block ORANGE_GRANIT = registerBlock("orange_granit", new RotateBlock(Block.Settings.of(Material.STONE).strength(1.5f).requiresTool()));
    public static final Block WHITE_BRICK = registerBlock("white_brick", new RotateBlock(Block.Settings.of(Material.STONE).strength(3f).requiresTool()));
    public static final Block WHITE_BRICK_ALTERNATIVE = registerBlock("white_brick_alternative", new RotateBlock(Block.Settings.of(Material.STONE).strength(3f).requiresTool()));
    public static final Block WHITE_BRICK_SLAB = registerBlock("white_brick_slab", new SlabBlock(Block.Settings.of(Material.STONE).strength(3f).requiresTool()));
    public static final Block WHITE_BRICK_STAIRS_ES = registerBlock("white_brick_stairs_es", new CustomStairsBlock(EndlessStoreBlocks.WHITE_BRICK.getDefaultState(), Block.Settings.of(Material.STONE).strength(3f).requiresTool()));
    public static final Block WHITE_BRICK_SLAB_ALTERNATIVE = registerBlock("white_brick_slab_alternative", new SlabBlock(Block.Settings.of(Material.STONE).strength(3f).requiresTool()));
    public static final Block WHITE_BRICK_STAIRS_ALTERNATIVE = registerBlock("white_brick_stairs_alternative", new CustomStairsBlock(EndlessStoreBlocks.WHITE_BRICK_ALTERNATIVE.getDefaultState(), Block.Settings.of(Material.STONE).strength(3f).requiresTool()));
    public static final Block LINOLEUM_LIGHT = registerBlock("linoleum_light", new RotateBlock(Block.Settings.of(Material.WOOD).strength(1.2f).requiresTool()));
    public static final Block LINOLEUM_BROWN = registerBlock("linoleum_brown", new RotateBlock(Block.Settings.of(Material.WOOD).strength(1.2f).requiresTool()));
    public static final Block WALLPAPER_YELLOW = registerBlock("wallpaper_yellow", new RotateBlock(Block.Settings.of(Material.WOOD).strength(0.65f)));
    public static final Block WALLPAPER_WHITE = registerBlock("wallpaper_white", new RotateBlock(Block.Settings.of(Material.WOOD).strength(0.65f)));
    public static final Block WALLPAPER_BLUE = registerBlock("wallpaper_blue", new RotateBlock(Block.Settings.of(Material.WOOD).strength(0.65f)));
    public static final Block METAL_GARAGE_DOOR = registerBlock("metal_garage_door", new RotateBlock(Block.Settings.of(Material.METAL).strength(2.6f).requiresTool()));
    public static final Block PATTERNED_CARPET= registerBlock("patterned_carpet", new CarpetCustom(Block.Settings.of(Material.WOOL).strength(1f)));
    public static final Block PLASTIC_GARBAGE_COLORED = registerBlock("plastic_garbage_colored", new RotateBlock(Block.Settings.of(Material.STONE).strength(1f)));
    public static final Block PLASTIC_GARBAGE_GREY = registerBlock("plastic_garbage_grey", new RotateBlock(Block.Settings.of(Material.STONE).strength(1f)));
    public static final Block PLASTIC_GARBAGE_BLUE = registerBlock("plastic_garbage_blue", new RotateBlock(Block.Settings.of(Material.STONE).strength(1f)));
    public static final Block CONCRETE_FURNACE = registerBlock("concrete_furnace", new CustomFurnaceBlock(Block.Settings.of(Material.STONE).strength(3.5F).requiresTool().luminance(createLightLevelFromLitBlockState())));
    public static final Block CEILING_TILES_WHITE = registerBlock("ceiling_tiles_white", new RotateBlock(Block.Settings.of(Material.STONE).strength(3f).requiresTool()));

    // Не имеет крафта и лута
    public static final Block LITERALLY_NOTHING = registerBlock("literally_nothing", new LiterallyNothingPortal(Block.Settings.of(Material.PORTAL).strength(200f)));

    // Блоки не используются, лут и крафты для них будут добавлены позже
    public static final Block CEILING_TILES_WHITE_ANOTHER = registerBlock("ceiling_tiles_white_another", new RotateBlock(Block.Settings.of(Material.STONE).strength(4f).requiresTool()));
    public static final Block WALL_BLUE_TILES = registerBlock("wall_blue_tiles", new RotateBlock(Block.Settings.of(Material.STONE).strength(4f).requiresTool()));
    public static final Block TILES_WITH_BLACK_INCLUSIONS = registerBlock("tiles_with_black_conclusions", new RotateBlock(Block.Settings.of(Material.STONE).strength(4f).requiresTool()));
    public static final Block FLOOR_TILES_MULTI_COLORED = registerBlock("floor_tiles_multi_colored", new RotateBlock(Block.Settings.of(Material.STONE).strength(4f).requiresTool()));
    public static final Block BLACK_DOOR = registerBlock("black_door", new CustomDoorBlock(Block.Settings.of(Material.WOOD)
            .strength(4.0f)));

    // Блоки с кастомным шейпом
    public static final Block METAL_PIPE = registerBlock("metal_pipe", new MetalPipe(Block.Settings.of(Material.METAL).strength(2f).requiresTool()));
    public static final Block METAL_CORNER_PIPE = registerBlock("metal_corner_pipe", new MetalCornerPipe(Block.Settings.of(Material.METAL).strength(2f).requiresTool()));
    public static final Block SHELVES = registerBlock("shelves", new ShelvesProp(Block.Settings.of(Material.WOOD).strength(1.75f).nonOpaque()));
    public static final Block BASEBOARD = registerBlock("baseboard", new Baseboard(Block.Settings.of(Material.WOOD).strength(0.6f)));
    public static final Block BASEBOARD_CORNER = registerBlock("baseboard_corner", new BaseboardCorner(Block.Settings.of(Material.WOOD).strength(0.6f)));
    public static final Block PLUSH_MOUSE_GRAF = registerBlock("plush_mouse_graf", new PlushMouseGraf(Block.Settings.of(Material.WOOL).strength(0.5f)));
    public static final Block OUTDOOR_SIGN = registerBlock("outdoor_sign", new OutdoorSign(Block.Settings.of(Material.WOOD).strength(0.65f)));
    public static final Block FIRE_CRANE = registerBlock("fire_crane", new FireCrane(Block.Settings.of(Material.METAL).strength(1.25f).nonOpaque()));
    public static final Block KETTLE = registerBlock("kettle", new Kettle(Block.Settings.of(Material.METAL).strength(0.5f)));
    public static final Block WALL_CLOCK = registerBlock("wall_clock", new WallClock(Block.Settings.of(Material.METAL).strength(0.5f)));
    public static final Block PALLET = registerBlock("pallet", new Pallet(Block.Settings.of(Material.WOOD).strength(1.45f).nonOpaque()));
    public static final Block FIRE_DETECTOR = registerBlock("fire_detector", new FireDetector(Block.Settings.of(Material.METAL).strength(0.9f)));
    public static final Block WALL_MAP = registerBlock("wall_map", new WallMap(Block.Settings.of(Material.STONE).strength(0.5f)));
    public static final Block CARGO_TROLLEY = registerBlock("cargo_trolley", new CargoTrolley(Block.Settings.of(Material.METAL).strength(2.6f).requiresTool()));
    public static final Block CARGO_TROLLEY_LONG_ONE_HANDLES = registerBlock("cargo_trolley_long_one_handles", new CargoTrolleyLong(Block.Settings.of(Material.METAL).strength(2.6f).requiresTool().nonOpaque()));
    public static final Block CARGO_TROLLEY_LONG_TWO_HANDLES = registerBlock("cargo_trolley_long_two_handles", new CargoTrolleyLong(Block.Settings.of(Material.METAL).strength(2.6f).requiresTool().nonOpaque()));
    public static final Block BOOK_VARIANT_ONE = registerBlock("book_variant_one", new BookVariantOne(Block.Settings.of(Material.STONE).strength(0.8f)));
    public static final Block BOOK_VARIANT_TWO = registerBlock("book_variant_two", new BookVariantTwo(Block.Settings.of(Material.STONE).strength(0.8f)));
    public static final Block BOOK_VARIANT_THREE = registerBlock("book_variant_three", new BookVariantThree(Block.Settings.of(Material.STONE).strength(0.8f)));
    public static final Block ARMCHAIR = registerBlock("armchair", new Armchair(Block.Settings.of(Material.WOOL).strength(4.1f).nonOpaque()));
    public static final Block WARDROBE = registerBlock("wardrobe", new Wardrobe(Block.Settings.of(Material.WOOD).strength(4.5f)));
    public static final Block BIG_WARDROBE = registerBlock("big_wardrobe", new BigWardrobe(Block.Settings.of(Material.WOOD).strength(3.5f).nonOpaque()));
    public static final Block CARDBOARD_BOX = registerBlock("cardboard_box", new CardboardBox(Block.Settings.of(Material.WOOD).strength(0.7f)));
    public static final Block CARDBOARD_BOX_OPEN = registerBlock("cardboard_box_open", new CardboardBox(Block.Settings.of(Material.WOOD).strength(0.4f)));
    public static final Block CARDBOARD_BOX_MIDDLE = registerBlock("cardboard_box_middle", new CardboardBoxMiddle(Block.Settings.of(Material.WOOD).strength(0.8f)));
    public static final Block CARDBOARD_BOX_MIDDLE_OPEN = registerBlock("cardboard_box_middle_open", new CardboardBoxMiddle(Block.Settings.of(Material.WOOD).strength(0.5f)));
    public static final Block CARDBOARD_BOX_LARGE = registerBlock("cardboard_box_large", new CardboardBoxLarge(Block.Settings.of(Material.WOOD).strength(1f)));
    public static final Block CARDBOARD_BOX_LARGE_OPEN = registerBlock("cardboard_box_large_open", new CardboardBoxLarge(Block.Settings.of(Material.WOOD).strength(0.7f)));
    public static final Block CARDBOARD_BOX_HUGE = registerBlock("cardboard_box_huge", new CardboardBoxHuge(Block.Settings.of(Material.WOOD).strength(1.5f).nonOpaque()));
    public static final Block CARDBOARD_BOX_HUGE_OPEN = registerBlock("cardboard_box_huge_open", new CardboardBoxHuge(Block.Settings.of(Material.WOOD).strength(1f).nonOpaque()));
    public static final Block TRASH_CAN = registerBlock("trash_can", new TrashCan(Block.Settings.of(Material.STONE).strength(1.25f).requiresTool()));
    public static final Block PLATE = registerBlock("plate", new Plate(Block.Settings.of(Material.STONE).strength(0.4f)));
    public static final Block FUSE_BOX = registerBlock("fuse_box", new FuseBox(Block.Settings.of(Material.METAL).strength(1.35f)));
    public static final Block FUSE_BOX_LARGE = registerBlock("fuse_box_large", new FuseBoxLarge(Block.Settings.of(Material.METAL).strength(1.65f)));
    public static final Block TOOLBOX = registerBlock("toolbox", new Toolbox(Block.Settings.of(Material.METAL).strength(0.6f)));
    public static final Block COIL_WIRES = registerBlock("coil_wires", new CoilWires(Block.Settings.of(Material.METAL).strength(1.1f)));
    public static final Block WOODEN_CHAIR = registerBlock("wooden_chair", new WoodenChair(Block.Settings.of(Material.WOOD).strength(2.2f)));
    public static final Block WOODEN_TABLE = registerBlock("wooden_table", new AbstractTable(Block.Settings.of(Material.WOOD).strength(2f).nonOpaque()));
    public static final Block WOODEN_TABLE_LONG = registerBlock("wooden_table_long", new AbstractTableLong(Block.Settings.of(Material.WOOD).strength(2.4f).nonOpaque()));
    public static final Block PLASTIC_TABLE = registerBlock("plastic_table", new AbstractTable(Block.Settings.of(Material.STONE).strength(1.8f).nonOpaque()));
    public static final Block PLASTIC_TABLE_LONG = registerBlock("plastic_table_long", new AbstractTableLong(Block.Settings.of(Material.STONE).strength(2f).nonOpaque()));
    public static final Block TOWEL_HOLDER = registerBlock("towel_holder", new TowelHolder(Block.Settings.of(Material.METAL).strength(1f)));
    public static final Block PADDED_STOOL = registerBlock("padded_stool", new PaddedStool(Block.Settings.of(Material.WOOL).strength(2.1f).nonOpaque()));
    public static final Block MIRROR = registerBlock("mirror", new Mirror(Block.Settings.of(Material.GLASS).strength(0.8f)));
    public static final Block HIGH_MIRROR = registerBlock("high_mirror", new HighMirror(Block.Settings.of(Material.GLASS).strength(1f)));
    public static final Block RADIATOR_RIGHT_SIDE = registerBlock("radiator_right_side", new Radiator(Block.Settings.of(Material.METAL).strength(1.8f).requiresTool()));
    public static final Block RADIATOR_LEFT_SIDE = registerBlock("radiator_left_side", new Radiator(Block.Settings.of(Material.METAL).strength(1.8f).requiresTool()));
    public static final Block SQUARE_SHELF = registerBlock("square_shelf", new SquareShelf(Block.Settings.of(Material.WOOD).strength(1.45f)));
    public static final Block SINK = registerBlock("sink", new Sink(Block.Settings.of(Material.STONE).strength(2f).requiresTool().nonOpaque()));
    public static final Block SOCKET = registerBlock("socket", new Socket(Block.Settings.of(Material.METAL).strength(1.1f)));
    public static final Block SIDE_PIPES = registerBlock("side_pipes", new SidePipes(Block.Settings.of(Material.METAL).strength(1.8f).requiresTool()));
    public static final Block CEILING_PIPES = registerBlock("ceiling_pipes", new CeilingPipes(Block.Settings.of(Material.METAL).strength(1.8f).requiresTool()));
    public static final Block PEDESTAL_ALTEA = registerBlock("pedestal_altea", new PedestalAltea(Block.Settings.of(Material.WOOD).strength(2f)));
    public static final Block METAL_CLOSET = registerBlock("metal_closet", new MetalCloset(Block.Settings.of(Material.METAL).strength(3.75f).requiresTool().nonOpaque()));
    public static final Block WHITE_BOOKSHELF = registerBlock("white_bookshelf", new WhiteBookshelf(Block.Settings.of(Material.WOOD).strength(1.45f)));
    public static final Block CALENDAR = registerBlock("calendar", new Calendar(Block.Settings.of(Material.STONE).strength(0.5f)));
    public static final Block DECORATIVE_FENCE = registerBlock("decorative_fence", new DecorativeFence(Block.Settings.of(Material.METAL).strength(0.65f)));
    public static final Block PRACTICAL_FENCE = registerBlock("practical_fence", new PracticalFence(Block.Settings.of(Material.METAL).strength(2.5f).requiresTool()));
    public static final Block PLASTIC_CHAIR = registerBlock("plastic_chair", new PlasticChair(Block.Settings.of(Material.STONE).strength(2.2f)));
    public static final Block VERTICAL_WASHING_MACHINE = registerBlock("vertical_washing_machine", new VerticalWashingMachine(Block.Settings.of(Material.METAL).strength(3.85f).requiresTool()));
    public static final Block BEDSIDE_TABLE = registerBlock("bedside_table", new BedsideTable(Block.Settings.of(Material.WOOD).strength(2.2f)));
    public static final Block GLOBE = registerBlock("globe", new Globe(Block.Settings.of(Material.WOOD).strength(0.5f)));
    public static final Block AQUARIUM = registerBlock("aquarium", new Aquarium(Block.Settings.of(Material.GLASS).strength(1.25f).nonOpaque()));
    public static final Block MRE = registerBlock("mre", new MRE(Block.Settings.of(Material.BAMBOO).strength(0.5f)));
    public static final Block BOTTLE = registerBlock("bottle", new Bottle(Block.Settings.of(Material.GLASS).strength(0.5f)));
    public static final Block SAFE = registerBlock("safe", new Safe(Block.Settings.of(Material.METAL).strength(5.5f).requiresTool()));
    public static final Block CEILING_GRID = registerBlock("ceiling_grid", new CeilingGrid(Block.Settings.of(Material.METAL).strength(2f).requiresTool().nonOpaque()));
    public static final Block SIDE_STORAGE_SHELVES = registerBlock("side_storage_shelves", new SideStorageShelves(Block.Settings.of(Material.METAL).strength(2f).requiresTool().nonOpaque()));
    public static final Block SIDE_BEAMS = registerBlock("side_beams", new SideBeams(Block.Settings.of(Material.METAL).strength(2f).requiresTool().nonOpaque()));
    public static final Block COMPUTER = registerBlock("computer", new Computer(Block.Settings.of(Material.METAL).strength(1.5f)));
    public static final Block OLD_SAFE = registerBlock("old_safe", new OldSafe(Block.Settings.of(Material.METAL).strength(-1.0F, 3600000.0F).dropsNothing()));
    public static final Block VERTICAL_BOILER = registerBlock("vertical_boiler", new VerticalBoiler(Block.Settings.of(Material.METAL).strength(3.75f)));
    public static final Block HORIZONTAL_BOILER = registerBlock("horizontal_boiler", new HorizontalBoiler(Block.Settings.of(Material.METAL).strength(3.75f)));
    public static final Block ORGANIZER = registerBlock("organizer", new Organizer(Block.Settings.of(Material.WOOD).strength(0.5f)));
    public static final Block EMPTY_ORGANIZER = registerBlock("empty_organizer", new Organizer(Block.Settings.of(Material.WOOD).strength(0.5f)));


    // Не имеют лута и крафта
    public static final Block VENDING_MACHINE = registerBlock("vending_machine", new VendingMachine(Block.Settings.of(Material.METAL).strength(4f).nonOpaque().requiresTool()));
    public static final Block GRID_VERTICAL = registerBlock("grid_vertical", new GridPropVertical(Block.Settings.of(Material.METAL).strength(2f).requiresTool().nonOpaque()));
    public static final Block STORAGE_SHELVES = registerBlock("storage_shelves", new StorageShelves(Block.Settings.of(Material.METAL).strength(2.3f).requiresTool()));
    public static final Block BATH = registerBlock("bath", new Bath(Block.Settings.of(Material.METAL).strength(2f).requiresTool().nonOpaque()));


    // Светяшки и всё что даёт хоть какой-то свет
    public static final Block LAMP = registerBlock("lamp", new LampProp(Block.Settings.of(Material.METAL).strength(0.5f).nonOpaque().luminance(state -> state.get(LampProp.LIT) ? 10 : 0)));
    public static final Block LAMP_HIGH = registerBlock("lamp_high", new LampHighProp(Block.Settings.of(Material.METAL).strength(1.2f).nonOpaque().luminance(state -> state.get(LampProp.LIT) ? 14 : 0)));
    public static final Block LAMP_LED = registerBlock("lamp_led", new LampLED(Block.Settings.of(Material.METAL).strength(2f).requiresTool().nonOpaque().luminance(state -> state.get(LampProp.LIT) ? 14 : 0)));
    public static final Block LAMPSHADE = registerBlock("lampshade", new Lampshade(Block.Settings.of(Material.METAL).strength(1f).nonOpaque().luminance(state -> state.get(LampProp.LIT) ? 9 : 0)));
    public static final Block LIGHT_BULB = registerBlock("light_bulb", new LightBulb(Block.Settings.of(Material.METAL).strength(1f).nonOpaque().luminance(state -> state.get(LampProp.LIT) ? 11 : 0)));
    public static final Block LAMP_WALL = registerBlock("lamp_wall", new LampWall(Block.Settings.of(Material.METAL).strength(2f).requiresTool().nonOpaque().luminance(state -> state.get(LampProp.LIT) ? 11 : 0)));
    public static final Block HANGING_LAMP = registerBlock("hanging_lamp", new HangingLamp(Block.Settings.of(Material.METAL).strength(3f).requiresTool().nonOpaque().luminance(state -> state.get(LampProp.LIT) ? 16 : 0)));
    

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registry.BLOCK, new Identifier(EndlessStoreMod.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registry.ITEM, new Identifier(EndlessStoreMod.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(EndlessStoreItemGroup.ENDLESS_MOD)));
    }
    public static void registerModBlocks() {
        EndlessStoreMod.LOGGER.debug("EndlessStoreBlocks" + EndlessStoreMod.MOD_ID);
    }
}
