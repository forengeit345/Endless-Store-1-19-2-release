package net.es.block;

import net.es.EndlessStoreMod;
import net.es.block.custom.*;
import net.es.block.custom.Mirror;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

public class EndlessStoreBlocks {
    public static final Map<String, Supplier<Block>> BLOCK_FACTORIES = new LinkedHashMap<>();

    public static final String CONCRETE = "concrete";
    public static final String DARK_CONCRETE = "dark_concrete";
    public static final String WHITE_BRICK = "white_brick";
    public static final String WHITE_BRICK_ALTERNATIVE = "white_brick_alternative";
    public static final String BEDSIDE_TABLE = "bedside_table";

    public static final String CARDBOARD_BOX = "cardboard_box";
    public static final String CARDBOARD_BOX_OPEN = "cardboard_box_open";
    public static final String CARDBOARD_BOX_MIDDLE = "cardboard_box_middle";
    public static final String CARDBOARD_BOX_MIDDLE_OPEN = "cardboard_box_middle_open";
    public static final String CARDBOARD_BOX_LARGE = "cardboard_box_large";
    public static final String CARDBOARD_BOX_LARGE_OPEN = "cardboard_box_large_open";
    public static final String CARDBOARD_BOX_HUGE = "cardboard_box_huge";
    public static final String CARDBOARD_BOX_HUGE_OPEN = "cardboard_box_huge_open";

    public static final String PLASTIC_GARBAGE_BLUE = "plastic_garbage_blue";
    public static final String PLASTIC_GARBAGE_COLORED = "plastic_garbage_colored";
    public static final String PLASTIC_GARBAGE_GREY = "plastic_garbage_grey";

    public static final String CHAIN_MAIL = "chain_mail";
    public static final String PROBABLY_MUD = "probably_mud";
    public static final String CONCRETE_MUD = "concrete_mud";
    public static final String GREEN_MUD = "green_mud";

    public static final String CARDBOARD_BOX_HUGE_TAPE = "cardboard_box_huge_tape";
    public static final String CONCRETE_FURNACE = "concrete_furnace";
    public static final String OLD_SAFE = "old_safe";

    static {
        BLOCK_FACTORIES.put("laminate_flooring_light", () -> new Block(Block.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).strength(1.15f)));
        BLOCK_FACTORIES.put("laminate_flooring_light_vertical", () -> new Block(Block.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).strength(1.15f)));
        BLOCK_FACTORIES.put("laminate_flooring_dark", () -> new Block(Block.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).strength(1.15f)));
        BLOCK_FACTORIES.put("laminate_flooring_mangrove", () -> new Block(Block.Properties.of().mapColor(MapColor.COLOR_PINK).sound(SoundType.WOOD).strength(1.15f)));
        BLOCK_FACTORIES.put("laminate_flooring_white", () -> new Block(Block.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.WOOD).strength(1.15f)));

        BLOCK_FACTORIES.put("plitka_water", () -> new Block(Block.Properties.of().mapColor(MapColor.COLOR_CYAN).sound(SoundType.STONE).strength(1.8f).requiresCorrectToolForDrops()));

        BLOCK_FACTORIES.put("concrete", () -> new Block(Block.Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(2.5f).requiresCorrectToolForDrops()));

        BLOCK_FACTORIES.put("mossy_concrete", () -> new Block(Block.Properties.of().mapColor(MapColor.COLOR_GREEN).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));
        BLOCK_FACTORIES.put("overgrown_concrete", () -> new Block(Block.Properties.of().mapColor(MapColor.COLOR_GREEN).sound(SoundType.STONE).strength(1.9f).requiresCorrectToolForDrops()));
        BLOCK_FACTORIES.put("dark_mossy_concrete", () -> new Block(Block.Properties.of().mapColor(MapColor.COLOR_GREEN).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));
        BLOCK_FACTORIES.put("dark_overgrown_concrete", () -> new Block(Block.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).sound(SoundType.STONE).strength(1.9f).requiresCorrectToolForDrops()));
        BLOCK_FACTORIES.put("dark_cracked_concrete", () -> new Block(Block.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).sound(SoundType.STONE).strength(1.75f).requiresCorrectToolForDrops()));

        BLOCK_FACTORIES.put("dark_concrete", () -> new Block(Block.Properties.of().mapColor(MapColor.COLOR_BLACK).sound(SoundType.STONE).strength(2.5f).requiresCorrectToolForDrops()));

        BLOCK_FACTORIES.put("concrete_slab", () -> new SlabBlock(Block.Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(2.5f).requiresCorrectToolForDrops()));
        BLOCK_FACTORIES.put("concrete_stairs", () -> new CustomStairsBlock(getBaseBlockState(CONCRETE), Block.Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(2.5f).requiresCorrectToolForDrops()));

        BLOCK_FACTORIES.put("dark_concrete_slab", () -> new SlabBlock(Block.Properties.of().mapColor(MapColor.COLOR_BLACK).sound(SoundType.STONE).strength(2.5f).requiresCorrectToolForDrops()));
        BLOCK_FACTORIES.put("dark_concrete_stairs", () -> new CustomStairsBlock(getBaseBlockState(DARK_CONCRETE), Block.Properties.of().mapColor(MapColor.COLOR_BLACK).sound(SoundType.STONE).strength(2.5f).requiresCorrectToolForDrops()));

        BLOCK_FACTORIES.put("yellow_concrete", () -> new Block(Block.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).sound(SoundType.STONE).strength(2.5f).requiresCorrectToolForDrops()));

        BLOCK_FACTORIES.put("orange_granit", () -> new Block(Block.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).sound(SoundType.STONE).strength(1.5f).requiresCorrectToolForDrops()));

        BLOCK_FACTORIES.put("white_brick", () -> new Block(Block.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.STONE).strength(3f).requiresCorrectToolForDrops()));
        BLOCK_FACTORIES.put("white_brick_alternative", () -> new Block(Block.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.STONE).strength(3f).requiresCorrectToolForDrops()));

        BLOCK_FACTORIES.put("white_brick_stairs_es", () -> new CustomStairsBlock(getBaseBlockState(WHITE_BRICK), Block.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.STONE).strength(3f).requiresCorrectToolForDrops()));
        BLOCK_FACTORIES.put("white_brick_stairs_alternative", () -> new CustomStairsBlock(getBaseBlockState(WHITE_BRICK_ALTERNATIVE), Block.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.STONE).strength(3f).requiresCorrectToolForDrops()));

        BLOCK_FACTORIES.put("white_brick_slab", () -> new SlabBlock(Block.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.STONE).strength(3f).requiresCorrectToolForDrops()));
        BLOCK_FACTORIES.put("white_brick_slab_alternative", () -> new SlabBlock(Block.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.STONE).strength(3f).requiresCorrectToolForDrops()));

        BLOCK_FACTORIES.put("linoleum_light", () -> new Block(Block.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).strength(1.2f).requiresCorrectToolForDrops()));
        BLOCK_FACTORIES.put("linoleum_brown", () -> new Block(Block.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).strength(1.2f).requiresCorrectToolForDrops()));

        BLOCK_FACTORIES.put("wallpaper_yellow", () -> new Block(Block.Properties.of().mapColor(MapColor.COLOR_YELLOW).sound(SoundType.WOOD).strength(0.65f)));
        BLOCK_FACTORIES.put("wallpaper_white", () -> new Block(Block.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.WOOD).strength(0.65f)));
        BLOCK_FACTORIES.put("wallpaper_blue", () -> new Block(Block.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).sound(SoundType.WOOD).strength(0.65f)));

        BLOCK_FACTORIES.put("metal_garage_door", () -> new Block(Block.Properties.of().mapColor(MapColor.STONE).sound(SoundType.METAL).strength(2.6f).requiresCorrectToolForDrops()));

        BLOCK_FACTORIES.put("patterned_carpet", () -> new CarpetBlock(Block.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.WOOL).strength(1f)));

        BLOCK_FACTORIES.put("plastic_garbage_colored", () -> new Block(Block.Properties.of().mapColor(MapColor.COLOR_GRAY).sound(SoundType.STONE).strength(1f)));
        BLOCK_FACTORIES.put("plastic_garbage_grey", () -> new Block(Block.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.STONE).strength(1f)));
        BLOCK_FACTORIES.put("plastic_garbage_blue", () -> new Block(Block.Properties.of().mapColor(MapColor.DIAMOND).sound(SoundType.STONE).strength(1f)));

        BLOCK_FACTORIES.put("concrete_furnace", () -> new CustomFurnaceBlock(Block.Properties.of()
                .mapColor(MapColor.STONE).sound(SoundType.STONE).strength(3.5F).requiresCorrectToolForDrops()
                .lightLevel(state -> state.getValue(CustomFurnaceBlock.LIT) ? 13 : 0)));

        BLOCK_FACTORIES.put("ceiling_tiles_white", () -> new Block(Block.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).sound(SoundType.STONE).strength(3f).requiresCorrectToolForDrops()));

        BLOCK_FACTORIES.put("concrete_mud", () -> new Block(Block.Properties.of().mapColor(MapColor.DIRT).sound(SoundType.ROOTED_DIRT).strength(2.25f).requiresCorrectToolForDrops()));
        BLOCK_FACTORIES.put("probably_mud", () -> new Block(Block.Properties.of().mapColor(MapColor.COLOR_BROWN).sound(SoundType.MUD).strength(1.25f)));
        BLOCK_FACTORIES.put("chain_mail", () -> new TransparentBlock(TransparentBlock.Properties.of().mapColor(MapColor.METAL).sound(SoundType.CHAIN).strength(3f).requiresCorrectToolForDrops().noOcclusion().isViewBlocking((state, level, pos) -> false).isSuffocating((state, level, pos) -> false)));
        BLOCK_FACTORIES.put("green_mud", () -> new Block(Block.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).sound(SoundType.SLIME_BLOCK).strength(2.25f).requiresCorrectToolForDrops()));
        BLOCK_FACTORIES.put("lime_mud", () -> new Block(Block.Properties.of().mapColor(MapColor.COLOR_GREEN).sound(SoundType.SLIME_BLOCK).strength(2.25f).requiresCorrectToolForDrops()));

        BLOCK_FACTORIES.put("laminate_flooring_dark_damaged", () -> new Block(Block.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).strength(1.05f).requiresCorrectToolForDrops()));
        BLOCK_FACTORIES.put("laminate_flooring_light_damaged", () -> new Block(Block.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).strength(1.05f).requiresCorrectToolForDrops()));
        BLOCK_FACTORIES.put("laminate_flooring_mangrove_damaged", () -> new Block(Block.Properties.of().mapColor(MapColor.COLOR_PINK).sound(SoundType.WOOD).strength(1.05f).requiresCorrectToolForDrops()));
        BLOCK_FACTORIES.put("laminate_flooring_white_damaged", () -> new Block(Block.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.WOOD).strength(1.05f).requiresCorrectToolForDrops()));
        BLOCK_FACTORIES.put("wallpaper_blue_damaged", () -> new Block(Block.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).sound(SoundType.WOOD).strength(0.55f).requiresCorrectToolForDrops()));
        BLOCK_FACTORIES.put("wallpaper_white_damaged", () -> new Block(Block.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.WOOD).strength(0.55f).requiresCorrectToolForDrops()));
        BLOCK_FACTORIES.put("wallpaper_yellow_damaged", () -> new Block(Block.Properties.of().mapColor(MapColor.COLOR_YELLOW).sound(SoundType.WOOD).strength(0.55f).requiresCorrectToolForDrops()));
        BLOCK_FACTORIES.put("white_brick_damaged", () -> new Block(Block.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.STONE).strength(2.8f).requiresCorrectToolForDrops()));
        BLOCK_FACTORIES.put("white_brick_mossy", () -> new Block(Block.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.STONE).strength(2.5f).requiresCorrectToolForDrops()));

//        Применение пока что не найдено
        BLOCK_FACTORIES.put("ceiling_tiles_white_another", () -> new Block(Block.Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(4f).requiresCorrectToolForDrops()));
        BLOCK_FACTORIES.put("wall_blue_tiles", () -> new Block(Block.Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(4f).requiresCorrectToolForDrops()));
        BLOCK_FACTORIES.put("tiles_with_black_conclusions", () -> new Block(Block.Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(4f).requiresCorrectToolForDrops()));
        BLOCK_FACTORIES.put("floor_tiles_multi_colored", () -> new Block(Block.Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(4f).requiresCorrectToolForDrops()));

        BLOCK_FACTORIES.put("literally_nothing", () -> new LiterallyNothingPortal(Block.Properties.of().mapColor(MapColor.COLOR_BLACK).sound(SoundType.NETHERRACK).strength(200f)));

        BLOCK_FACTORIES.put("metal_pipe", () -> new MetalPipe(Block.Properties.of().mapColor(MapColor.STONE).sound(SoundType.METAL).strength(2f).requiresCorrectToolForDrops()));
        BLOCK_FACTORIES.put("metal_corner_pipe", () -> new MetalCornerPipe(Block.Properties.of().mapColor(MapColor.STONE).sound(SoundType.METAL).strength(2f).requiresCorrectToolForDrops()));
        BLOCK_FACTORIES.put("shelves", () -> new ShelvesProp(Block.Properties.of().mapColor(MapColor.COLOR_BROWN).sound(SoundType.WOOD).strength(1.75f).noOcclusion()));
        BLOCK_FACTORIES.put("baseboard", () -> new Baseboard(Block.Properties.of().mapColor(MapColor.COLOR_BROWN).sound(SoundType.WOOD).strength(0.6f)));
        BLOCK_FACTORIES.put("baseboard_corner", () -> new BaseboardCorner(Block.Properties.of().mapColor(MapColor.COLOR_BROWN).sound(SoundType.WOOD).strength(0.6f)));
        BLOCK_FACTORIES.put("plush_mouse_graf", () -> new PlushMouseGraf(Block.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.WOOL).strength(0.5f)));
        BLOCK_FACTORIES.put("outdoor_sign", () -> new OutdoorSign(Block.Properties.of().mapColor(MapColor.COLOR_YELLOW).sound(SoundType.WOOD).strength(0.65f)));
        BLOCK_FACTORIES.put("fire_crane", () -> new FireCrane(Block.Properties.of().mapColor(MapColor.TERRACOTTA_RED).sound(SoundType.METAL).strength(1.25f).noOcclusion()));
        BLOCK_FACTORIES.put("kettle", () -> new Kettle(Block.Properties.of().mapColor(MapColor.STONE).sound(SoundType.METAL).strength(0.5f)));
        BLOCK_FACTORIES.put("wall_clock", () -> new WallClock(Block.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.METAL).strength(0.5f)));
        BLOCK_FACTORIES.put("pallet", () -> new Pallet(Block.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).strength(1.45f).noOcclusion()));
        BLOCK_FACTORIES.put("fire_detector", () -> new FireDetector(Block.Properties.of().mapColor(MapColor.STONE).sound(SoundType.METAL).strength(0.9f)));
        BLOCK_FACTORIES.put("wall_map", () -> new WallMap(Block.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).sound(SoundType.STONE).strength(0.5f)));
        BLOCK_FACTORIES.put("cargo_trolley", () -> new CargoTrolley(Block.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.METAL).strength(2.6f).requiresCorrectToolForDrops()));
        BLOCK_FACTORIES.put("cargo_trolley_long_one_handles", () -> new CargoTrolleyLong(Block.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.METAL).strength(2.6f).requiresCorrectToolForDrops().noOcclusion()));
        BLOCK_FACTORIES.put("cargo_trolley_long_two_handles", () -> new CargoTrolleyLong(Block.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.METAL).strength(2.6f).requiresCorrectToolForDrops().noOcclusion()));
        BLOCK_FACTORIES.put("book_variant_one", () -> new BookVariantOne(Block.Properties.of().mapColor(MapColor.COLOR_GREEN).sound(SoundType.STONE).strength(0.8f)));
        BLOCK_FACTORIES.put("book_variant_two", () -> new BookVariantTwo(Block.Properties.of().mapColor(MapColor.COLOR_GREEN).sound(SoundType.STONE).strength(0.8f)));
        BLOCK_FACTORIES.put("book_variant_three", () -> new BookVariantThree(Block.Properties.of().mapColor(MapColor.COLOR_GREEN).sound(SoundType.STONE).strength(0.8f)));
        BLOCK_FACTORIES.put("armchair", () -> new Armchair(Block.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOL).strength(4.1f).noOcclusion()));
        BLOCK_FACTORIES.put("wardrobe", () -> new Wardrobe(Block.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.WOOD).strength(4.5f)));
        BLOCK_FACTORIES.put("big_wardrobe", () -> new BigWardrobe(Block.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.WOOD).strength(3.5f).noOcclusion()));
        BLOCK_FACTORIES.put("cardboard_box", () -> new CardboardBox(Block.Properties.of().mapColor(MapColor.COLOR_YELLOW).sound(SoundType.WOOD).strength(0.7f)));
        BLOCK_FACTORIES.put("cardboard_box_open", () -> new CardboardBox(Block.Properties.of().mapColor(MapColor.COLOR_YELLOW).sound(SoundType.WOOD).strength(0.4f)));
        BLOCK_FACTORIES.put("cardboard_box_middle", () -> new CardboardBoxMiddle(Block.Properties.of().mapColor(MapColor.COLOR_YELLOW).sound(SoundType.WOOD).strength(0.8f)));
        BLOCK_FACTORIES.put("cardboard_box_middle_open", () -> new CardboardBoxMiddle(Block.Properties.of().mapColor(MapColor.COLOR_YELLOW).sound(SoundType.WOOD).strength(0.5f)));
        BLOCK_FACTORIES.put("cardboard_box_large", () -> new CardboardBoxLarge(Block.Properties.of().mapColor(MapColor.COLOR_YELLOW).sound(SoundType.WOOD).strength(1f)));
        BLOCK_FACTORIES.put("cardboard_box_large_open", () -> new CardboardBoxLarge(Block.Properties.of().mapColor(MapColor.COLOR_YELLOW).sound(SoundType.WOOD).strength(0.7f)));
        BLOCK_FACTORIES.put("cardboard_box_huge", () -> new CardboardBoxHuge(Block.Properties.of().mapColor(MapColor.COLOR_YELLOW).sound(SoundType.WOOD).strength(1.5f).noOcclusion()));
        BLOCK_FACTORIES.put("cardboard_box_huge_open", () -> new CardboardBoxHuge(Block.Properties.of().mapColor(MapColor.COLOR_YELLOW).sound(SoundType.WOOD).strength(1f).noOcclusion()));
        BLOCK_FACTORIES.put("cardboard_box_huge_tape", () -> new CardboardBoxHugeTape(Block.Properties.of().mapColor(MapColor.COLOR_YELLOW).sound(SoundType.WOOD).strength(1.5f).noOcclusion()));
        BLOCK_FACTORIES.put("trash_can", () -> new TrashCan(Block.Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(1.25f).requiresCorrectToolForDrops()));
        BLOCK_FACTORIES.put("plate", () -> new Plate(Block.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.STONE).strength(0.4f)));
        BLOCK_FACTORIES.put("fuse_box", () -> new FuseBox(Block.Properties.of().mapColor(MapColor.STONE).sound(SoundType.METAL).strength(1.35f)));
        BLOCK_FACTORIES.put("fuse_box_large", () -> new FuseBoxLarge(Block.Properties.of().mapColor(MapColor.STONE).sound(SoundType.METAL).strength(1.65f)));
        BLOCK_FACTORIES.put("toolbox", () -> new Toolbox(Block.Properties.of().mapColor(MapColor.TERRACOTTA_RED).sound(SoundType.METAL).strength(0.6f)));
        BLOCK_FACTORIES.put("coil_wires", () -> new CoilWires(Block.Properties.of().mapColor(MapColor.TERRACOTTA_RED).sound(SoundType.METAL).strength(1.1f)));
        BLOCK_FACTORIES.put("wooden_chair", () -> new WoodenChair(Block.Properties.of().mapColor(MapColor.TERRACOTTA_RED).sound(SoundType.METAL).strength(1.1f)));
        BLOCK_FACTORIES.put("wooden_table", () -> new AbstractTable(Block.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).strength(2f).noOcclusion()));
        BLOCK_FACTORIES.put("wooden_table_long", () -> new AbstractTableLong(Block.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).strength(2.4f).noOcclusion()));
        BLOCK_FACTORIES.put("plastic_table", () -> new AbstractTable(Block.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.STONE).strength(1.8f).noOcclusion()));
        BLOCK_FACTORIES.put("plastic_table_long", () -> new AbstractTableLong(Block.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.STONE).strength(2f).noOcclusion()));
        BLOCK_FACTORIES.put("towel_holder", () -> new TowelHolder(Block.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.METAL).strength(1f)));
        BLOCK_FACTORIES.put("padded_stool", () -> new PaddedStool(Block.Properties.of().mapColor(MapColor.COLOR_BROWN).sound(SoundType.WOOL).strength(2.1f).noOcclusion()));
        BLOCK_FACTORIES.put("mirror", () -> new Mirror(Block.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.GLASS).strength(0.8f)));
        BLOCK_FACTORIES.put("high_mirror", () -> new HighMirror(Block.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.GLASS).strength(1f)));
        BLOCK_FACTORIES.put("radiator_right_side", () -> new Radiator(Block.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.METAL).strength(1.8f).requiresCorrectToolForDrops()));
        BLOCK_FACTORIES.put("radiator_left_side", () -> new Radiator(Block.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.METAL).strength(1.8f).requiresCorrectToolForDrops()));
        BLOCK_FACTORIES.put("square_shelf", () -> new SquareShelf(Block.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.WOOD).strength(1.45f)));
        BLOCK_FACTORIES.put("sink", () -> new Sink(Block.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops().noOcclusion()));
        BLOCK_FACTORIES.put("socket", () -> new Socket(Block.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.METAL).strength(1.1f)));
        BLOCK_FACTORIES.put("side_pipes", () -> new SidePipes(Block.Properties.of().mapColor(MapColor.METAL).sound(SoundType.METAL).strength(1.8f).requiresCorrectToolForDrops()));
        BLOCK_FACTORIES.put("ceiling_pipes", () -> new CeilingPipes(Block.Properties.of().mapColor(MapColor.METAL).sound(SoundType.METAL).strength(1.8f).requiresCorrectToolForDrops()));
        BLOCK_FACTORIES.put("pedestal_altea", () -> new PedestalAltea(Block.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.WOOD).strength(2f)));
        BLOCK_FACTORIES.put("metal_closet", () -> new MetalCloset(Block.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.METAL).strength(3.75f).requiresCorrectToolForDrops().noOcclusion()));
        BLOCK_FACTORIES.put("white_bookshelf", () -> new WhiteBookshelf(Block.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.WOOD).strength(1.45f)));
        BLOCK_FACTORIES.put("calendar", () -> new Calendar(Block.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.STONE).strength(0.5f)));
        BLOCK_FACTORIES.put("decorative_fence", () -> new DecorativeFence(Block.Properties.of().mapColor(MapColor.STONE).sound(SoundType.METAL).strength(0.65f)));
        BLOCK_FACTORIES.put("practical_fence", () -> new PracticalFence(Block.Properties.of().mapColor(MapColor.METAL).sound(SoundType.METAL).strength(2.5f).requiresCorrectToolForDrops()));
        BLOCK_FACTORIES.put("plastic_chair", () -> new PlasticChair(Block.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.STONE).strength(2.2f)));
        BLOCK_FACTORIES.put("vertical_washing_machine", () -> new VerticalWashingMachine(Block.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).sound(SoundType.METAL).strength(3.85f).requiresCorrectToolForDrops()));
        BLOCK_FACTORIES.put("bedside_table", () -> new BedsideTable(Block.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.WOOD).strength(2.2f)));
        BLOCK_FACTORIES.put("globe", () -> new Globe(Block.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).sound(SoundType.WOOD).strength(0.5f)));
        BLOCK_FACTORIES.put("aquarium", () -> new Aquarium(Block.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.GLASS).strength(1.25f).noOcclusion()));
        BLOCK_FACTORIES.put("mre", () -> new MRE(Block.Properties.of().mapColor(MapColor.COLOR_GREEN).sound(SoundType.BAMBOO).strength(0.5f)));
        BLOCK_FACTORIES.put("bottle", () -> new Bottle(Block.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.GLASS).strength(0.5f)));
        BLOCK_FACTORIES.put("safe", () -> new Safe(Block.Properties.of().mapColor(MapColor.METAL).sound(SoundType.METAL).strength(5.5f).requiresCorrectToolForDrops()));
        BLOCK_FACTORIES.put("ceiling_grid", () -> new CeilingGrid(Block.Properties.of().mapColor(MapColor.STONE).sound(SoundType.METAL).strength(2f).requiresCorrectToolForDrops().noOcclusion()));
        BLOCK_FACTORIES.put("side_storage_shelves", () -> new SideStorageShelves(Block.Properties.of().mapColor(MapColor.STONE).sound(SoundType.METAL).strength(2f).requiresCorrectToolForDrops().noOcclusion()));
        BLOCK_FACTORIES.put("side_beams", () -> new SideBeams(Block.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.METAL).strength(2f).requiresCorrectToolForDrops().noOcclusion()));
        BLOCK_FACTORIES.put("computer", () -> new Computer(Block.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.METAL).strength(1.5f).requiresCorrectToolForDrops()));
        BLOCK_FACTORIES.put("old_safe", () -> new OldSafe(Block.Properties.of().mapColor(MapColor.STONE).sound(SoundType.METAL).strength(-1.0F, 3600000.0F).noLootTable()));
        BLOCK_FACTORIES.put("vertical_boiler", () -> new VerticalBoiler(Block.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.METAL).strength(3.75f).requiresCorrectToolForDrops()));
        BLOCK_FACTORIES.put("horizontal_boiler", () -> new HorizontalBoiler(Block.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.METAL).strength(3.75f).requiresCorrectToolForDrops()));
        BLOCK_FACTORIES.put("organizer", () -> new Organizer(Block.Properties.of().mapColor(MapColor.STONE).sound(SoundType.WOOD).strength(0.5f)));
        BLOCK_FACTORIES.put("empty_organizer", () -> new Organizer(Block.Properties.of().mapColor(MapColor.STONE).sound(SoundType.WOOD).strength(0.5f)));
        BLOCK_FACTORIES.put("small_wooden_barricades", () -> new WoodenBarricades(Block.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).strength(1.0f).requiresCorrectToolForDrops().noOcclusion()));
        BLOCK_FACTORIES.put("medium_wooden_barricades", () -> new WoodenBarricades(Block.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).strength(1.5f).requiresCorrectToolForDrops().noOcclusion()));
        BLOCK_FACTORIES.put("large_wooden_barricades", () -> new WoodenBarricades(Block.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).strength(2.0f).requiresCorrectToolForDrops().noOcclusion()));
        BLOCK_FACTORIES.put("mud_on_floor", () -> new GarbageOnFloor(Block.Properties.of().noOcclusion().noCollission().noLootTable().noOcclusion()));
        BLOCK_FACTORIES.put("garbage_on_floor", () -> new GarbageOnFloor(Block.Properties.of().replaceable().noCollission().noLootTable().noOcclusion()));
        BLOCK_FACTORIES.put("concrete_crumbs_on_floor", () -> new GarbageOnFloor(Block.Properties.of().replaceable().noCollission().noLootTable().noOcclusion()));
        BLOCK_FACTORIES.put("water_strider_egg", () -> new WaterStriderEgg(Block.Properties.of().mapColor(MapColor.COLOR_GREEN).sound(SoundType.SLIME_BLOCK).strength(0.5f).noOcclusion()));
        BLOCK_FACTORIES.put("water_strider_egg_middle", () -> new WaterStriderEgg(Block.Properties.of().mapColor(MapColor.COLOR_GREEN).sound(SoundType.SLIME_BLOCK).strength(0.5f).noOcclusion()));
        BLOCK_FACTORIES.put("water_strider_egg_large", () -> new WaterStriderEgg(Block.Properties.of().mapColor(MapColor.COLOR_GREEN).sound(SoundType.SLIME_BLOCK).strength(0.5f).noOcclusion()));
        BLOCK_FACTORIES.put("water_strider_egg_huge", () -> new WaterStriderEgg(Block.Properties.of().mapColor(MapColor.COLOR_GREEN).sound(SoundType.SLIME_BLOCK).strength(0.5f).noOcclusion()));
        BLOCK_FACTORIES.put("painting_horizontal_forest", () -> new PaintingHorizontal(Block.Properties.of().sound(SoundType.HANGING_SIGN).strength(1f)));
        BLOCK_FACTORIES.put("painting_horizontal_sunset", () -> new PaintingHorizontal(Block.Properties.of().sound(SoundType.HANGING_SIGN).strength(1f)));
        BLOCK_FACTORIES.put("painting_horizontal_emptiness", () -> new PaintingHorizontal(Block.Properties.of().sound(SoundType.HANGING_SIGN).strength(1f)));
        BLOCK_FACTORIES.put("painting_vertical_lake", () -> new PaintingVertical(Block.Properties.of().sound(SoundType.HANGING_SIGN).strength(1f)));
        BLOCK_FACTORIES.put("painting_vertical_city", () -> new PaintingVertical(Block.Properties.of().sound(SoundType.HANGING_SIGN).strength(1f)));
        BLOCK_FACTORIES.put("painting_vertical_pipes", () -> new PaintingVertical(Block.Properties.of().sound(SoundType.HANGING_SIGN).strength(1f)));
        BLOCK_FACTORIES.put("filing_cabinet_closed", () -> new FilingCabinet(Block.Properties.of().sound(SoundType.WOOD).strength(1.5f).requiresCorrectToolForDrops()));
        BLOCK_FACTORIES.put("filing_cabinet_open_up", () -> new FilingCabinet(Block.Properties.of().sound(SoundType.WOOD).strength(1.5f).requiresCorrectToolForDrops()));
        BLOCK_FACTORIES.put("filing_cabinet_open_down", () -> new FilingCabinet(Block.Properties.of().sound(SoundType.WOOD).strength(1.5f).requiresCorrectToolForDrops()));
        BLOCK_FACTORIES.put("showcase_with_dishes", () -> new ShowcaseWithDishes(Block.Properties.of().mapColor(MapColor.STONE).sound(SoundType.METAL).strength(2f).requiresCorrectToolForDrops().noOcclusion()));

        // Не имеют лута и крафта
        BLOCK_FACTORIES.put("vending_machine", () -> new VendingMachine(Block.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.METAL).strength(4f).requiresCorrectToolForDrops().noOcclusion()));
        BLOCK_FACTORIES.put("grid_vertical", () -> new GridPropVertical(Block.Properties.of().mapColor(MapColor.COLOR_GRAY).sound(SoundType.METAL).strength(2f).requiresCorrectToolForDrops().noOcclusion()));
        BLOCK_FACTORIES.put("storage_shelves", () -> new StorageShelves(Block.Properties.of().mapColor(MapColor.COLOR_GRAY).sound(SoundType.METAL).strength(2.3f).requiresCorrectToolForDrops()));
        BLOCK_FACTORIES.put("bath", () -> new Bath(Block.Properties.of().mapColor(MapColor.COLOR_GRAY).sound(SoundType.METAL).strength(2f).requiresCorrectToolForDrops().noOcclusion()));

        // Светяшки и всё что даёт хоть какой-то свет
        BLOCK_FACTORIES.put("lamp", () -> new LampProp(Block.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.METAL).strength(0.5f).noOcclusion() .lightLevel(state -> state.getValue(LampProp.LIT) ? 10 : 0)));
        BLOCK_FACTORIES.put("lamp_high", () -> new LampHighProp(Block.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.METAL).strength(1.2f).noOcclusion() .lightLevel(state -> state.getValue(LampHighProp.LIT) ? 14 : 0)));
        BLOCK_FACTORIES.put("lamp_led", () -> new LampLED(Block.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.METAL).strength(2f).noOcclusion() .lightLevel(state -> state.getValue(LampLED.LIT) ? 14 : 0)));
        BLOCK_FACTORIES.put("lampshade", () -> new Lampshade(Block.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.METAL).strength(1f).noOcclusion() .lightLevel(state -> state.getValue(Lampshade.LIT) ? 9 : 0)));
        BLOCK_FACTORIES.put("light_bulb", () -> new LightBulb(Block.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.METAL).strength(1f).noOcclusion() .lightLevel(state -> state.getValue(LightBulb.LIT) ? 11 : 0)));
        BLOCK_FACTORIES.put("lamp_wall", () -> new LampWall(Block.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.METAL).strength(2f).noOcclusion() .lightLevel(state -> state.getValue(LampWall.LIT) ? 11 : 0)));
        BLOCK_FACTORIES.put("hanging_lamp", () -> new HangingLamp(Block.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.METAL).strength(3f).noOcclusion() .lightLevel(state -> state.getValue(HangingLamp.LIT) ? 14 : 0)));

    }

    private static BlockState getBaseBlockState(String blockName) {
        return BuiltInRegistries.BLOCK
                .get(ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, blockName))
                .defaultBlockState();
    }

    @FunctionalInterface
    public interface BlockRegisterer {
        Supplier<Block> register(String name, Supplier<Block> blockFactory);
    }

    @FunctionalInterface
    public interface ModItemRegisterer {
        void register(String name, Supplier<Item> itemFactory);
    }

    public static void registerBlocks(BlockRegisterer blockRegistry, ModItemRegisterer itemRegistry) {
        BLOCK_FACTORIES.forEach((name, blockFactory) -> {
            Supplier<Block> registeredBlockSupplier = blockRegistry.register(name, blockFactory);
            itemRegistry.register(name, () -> new BlockItem(registeredBlockSupplier.get(), new Item.Properties()));
        });
    }
}