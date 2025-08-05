package net.es.endless_store_mod.item;

import net.es.endless_store_mod.EndlessStoreMod;
import net.es.endless_store_mod.entity.EndlessStoreEntities;
import net.es.endless_store_mod.item.custom.EmployeeShirtArmorItem;
import net.es.endless_store_mod.item.custom.SecurityArmorItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class EndlessStoreItems {
    public static final Item WOODEN_BOARD = registerItem("wooden_board", new Item(new FabricItemSettings().group(EndlessStoreItemGroup.ENDLESS_MOD).maxCount(16)));
    public static final Item CONCRETE_CRUMBS = registerItem("concrete_crumbs", new Item(new FabricItemSettings().group(EndlessStoreItemGroup.ENDLESS_MOD).maxCount(16)));
    public static final Item PIECE_OF_CARDBOARD = registerItem("piece_of_cardboard", new Item(new FabricItemSettings().group(EndlessStoreItemGroup.ENDLESS_MOD).maxCount(32)));
    public static final Item METAL_PIPES = registerItem("metal_pipes", new Item(new FabricItemSettings().group(EndlessStoreItemGroup.ENDLESS_MOD).maxCount(8)));
    public static final Item NAILS = registerItem("nails", new Item(new FabricItemSettings().group(EndlessStoreItemGroup.ENDLESS_MOD).maxCount(32)));
    public static final Item BOLTS = registerItem("bolts", new Item(new FabricItemSettings().group(EndlessStoreItemGroup.ENDLESS_MOD).maxCount(32)));
    public static final Item METAL_PLATES = registerItem("metal_plates", new Item(new FabricItemSettings().group(EndlessStoreItemGroup.ENDLESS_MOD).maxCount(4)));
    public static final Item CONCRETE_BRICK = registerItem("concrete_brick", new Item(new FabricItemSettings().group(EndlessStoreItemGroup.ENDLESS_MOD).maxCount(16)));
    public static final Item SCRAP = registerItem("scrap", new Item(new FabricItemSettings().group(EndlessStoreItemGroup.ENDLESS_MOD).maxCount(32)));
    public static final Item MOLTEN_SCRAP = registerItem("molten_scrap", new Item(new FabricItemSettings().group(EndlessStoreItemGroup.ENDLESS_MOD).maxCount(1)));
    public static final Item PLASTIC_BUCKET = registerItem("plastic_bucket", new CustomBucketItem(Fluids.EMPTY, (new Item.Settings()).maxCount(16).group(EndlessStoreItemGroup.ENDLESS_MOD)));
    public static final Item TRASH_PLASTIC = registerItem("trash_plastic", new Item(new FabricItemSettings().group(EndlessStoreItemGroup.ENDLESS_MOD).maxCount(32)));
    public static final Item BROKEN_GLASS = registerItem("broken_glass", new Item(new FabricItemSettings().group(EndlessStoreItemGroup.ENDLESS_MOD)));
    public static final Item PIECE_OF_LINOLEUM = registerItem("pieces_of_linoleum", new Item(new FabricItemSettings().group(EndlessStoreItemGroup.ENDLESS_MOD).maxCount(16)));
    public static final Item WIRES = registerItem("wires", new Item(new FabricItemSettings().group(EndlessStoreItemGroup.ENDLESS_MOD).maxCount(32)));
    public static final Item PIECE_OF_WALLPAPER = registerItem("pieces_of_wallpaper", new Item(new FabricItemSettings().group(EndlessStoreItemGroup.ENDLESS_MOD).maxCount(16)));
    public static final Item SAWDUST = registerItem("sawdust", new Item(new FabricItemSettings().group(EndlessStoreItemGroup.ENDLESS_MOD).maxCount(64)));
    public static final Item COARSE_FABRIC = registerItem("coarse_fabric", new Item(new FabricItemSettings().group(EndlessStoreItemGroup.ENDLESS_MOD).maxCount(16)));

    public static final Item BREPSI = registerItem("brepsi", new Item(new FabricItemSettings().food(new FoodComponent.Builder().hunger(5).saturationModifier(0.3f).build()).group(EndlessStoreItemGroup.ENDLESS_MOD).maxCount(4)));
    public static final Item WET_WALLPAPER = registerItem("wet_wallpaper", new Item(new FabricItemSettings().food(new FoodComponent.Builder().hunger(2).saturationModifier(0.2f).build()).group(EndlessStoreItemGroup.ENDLESS_MOD).maxCount(16)));
    public static final Item SAWDUST_SOUP = registerItem("sawdust_soup", new StewItem(new FabricItemSettings().food(new FoodComponent.Builder().hunger(7).saturationModifier(0.45f).build()).group(EndlessStoreItemGroup.ENDLESS_MOD).maxCount(1)));
    public static final Item HARDTACK = registerItem("hardtack", new Item(new FabricItemSettings().food(new FoodComponent.Builder().hunger(4).saturationModifier(0.3f).build()).group(EndlessStoreItemGroup.ENDLESS_MOD).maxCount(32)));
    public static final Item CRISP = registerItem("crisp", new Item(new FabricItemSettings().food(new FoodComponent.Builder().hunger(4).saturationModifier(0.2f).build()).group(EndlessStoreItemGroup.ENDLESS_MOD).maxCount(16)));

    public static final Item EMPLOYEE_SPAWN_EGG = registerItem("employee_spawn_egg", new SpawnEggItem(EndlessStoreEntities.EMPLOYEE,0x23b341, 0x12732e, new FabricItemSettings().group(EndlessStoreItemGroup.ENDLESS_MOD)));
    public static final Item JACK_SPAWN_EGG = registerItem("jack_spawn_egg", new SpawnEggItem(EndlessStoreEntities.JACK,0x83b314, 0x4a732e, new FabricItemSettings().group(EndlessStoreItemGroup.ENDLESS_MOD)));
    public static final Item SECURITY_SPAWN_EGG = registerItem("security_spawn_egg", new SpawnEggItem(EndlessStoreEntities.SECURITY,0x0ebe14, 0x0a3411d, new FabricItemSettings().group(EndlessStoreItemGroup.ENDLESS_MOD)));

    public static final Item FLIMSY_PICKAXE = registerItem("flimsy_pickaxe", new PickaxeItem(ToolMaterials.WOOD, 1, -2.8F, new FabricItemSettings().group(EndlessStoreItemGroup.ENDLESS_MOD).maxCount(1)));
    public static final Item HANDMADE_PICKAXE = registerItem("handmade_pickaxe", new PickaxeItem(ToolMaterials.STONE, 1, -2.8F, new FabricItemSettings().group(EndlessStoreItemGroup.ENDLESS_MOD).maxCount(1)));
    public static final Item DURABLE_PICKAXE = registerItem("durable_pickaxe", new PickaxeItem(ToolMaterials.IRON, 1, -2.8F, new FabricItemSettings().group(EndlessStoreItemGroup.ENDLESS_MOD).maxCount(1)));
    public static final Item PROFESSIONAL_PICKAXE = registerItem("professional_pickaxe", new PickaxeItem(ToolMaterials.DIAMOND, 1, -2.8F, new FabricItemSettings().group(EndlessStoreItemGroup.ENDLESS_MOD).maxCount(1)));

    public static final Item HANDMADE_AXE = registerItem("handmade_axe", new AxeItem(ToolMaterials.STONE, 5F, -3.2F, new FabricItemSettings().group(EndlessStoreItemGroup.ENDLESS_MOD).maxCount(1)));

    public static final Item HANDMADE_SHOVEL = registerItem("handmade_shovel", new ShovelItem(ToolMaterials.STONE, 1.5F, -3.0F, new FabricItemSettings().group(EndlessStoreItemGroup.ENDLESS_MOD).maxCount(1)));

    public static final Item KNIFE = registerItem("knife", new SwordItem(ToolMaterials.WOOD, 5, -2.35F, new FabricItemSettings().group(EndlessStoreItemGroup.ENDLESS_MOD).maxCount(1)));
    public static final Item FRYING_PAN = registerItem("frying_pan", new SwordItem(ToolMaterials.IRON, 6, -2.8F, new FabricItemSettings().group(EndlessStoreItemGroup.ENDLESS_MOD).maxCount(1)));
    public static final Item BATON = registerItem("baton", new SwordItem(ToolMaterials.IRON, 6, -2.8F, new FabricItemSettings().group(EndlessStoreItemGroup.ENDLESS_MOD).maxCount(1)));

    public static final Item EMPLOYEES_SHIRT = registerItem("employees_shirt", new EmployeeShirtArmorItem(EndlessStoreArmorMaterials.EMPLOYEE, EquipmentSlot.CHEST, new FabricItemSettings().group(EndlessStoreItemGroup.ENDLESS_MOD).maxCount(1)));
    public static final Item SECURITY_CAP = registerItem("security_cap", new SecurityArmorItem(EndlessStoreArmorMaterials.SECURITY, EquipmentSlot.HEAD, new FabricItemSettings().group(EndlessStoreItemGroup.ENDLESS_MOD).maxCount(1)));
    public static final Item SECURITY_SHIRT = registerItem("security_shirt", new SecurityArmorItem(EndlessStoreArmorMaterials.SECURITY, EquipmentSlot.CHEST, new FabricItemSettings().group(EndlessStoreItemGroup.ENDLESS_MOD).maxCount(1)));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(EndlessStoreMod.MOD_ID, name), item);
    }
    public static void registerModItems() {
        EndlessStoreMod.LOGGER.debug("Регистрация ModItems для" + EndlessStoreMod.MOD_ID);
    }
}
