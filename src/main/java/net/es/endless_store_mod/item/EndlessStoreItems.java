package net.es.endless_store_mod.item;

import net.es.endless_store_mod.EndlessStoreMod;
import net.es.endless_store_mod.entity.EndlessStoreEntities;
import net.es.endless_store_mod.item.custom.EmployeeShirtArmorItem;
import net.es.endless_store_mod.item.custom.HandmadeArmorItem;
import net.es.endless_store_mod.item.custom.HandmadeIronArmorItem;
import net.es.endless_store_mod.item.custom.SecurityArmorItem;
import net.es.endless_store_mod.registry.EndlessStoreFuel;
import net.minecraft.world.item.*;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EndlessStoreItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, EndlessStoreMod.MOD_ID);

    public static final RegistryObject<Item> WOODEN_BOARD = ITEMS.register("wooden_board", () -> new EndlessStoreFuel(new Item.Properties().stacksTo(16), 200));
    public static final RegistryObject<Item> CONCRETE_CRUMBS = ITEMS.register("concrete_crumbs", () -> new Item(new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> PIECE_OF_CARDBOARD = ITEMS.register("piece_of_cardboard", () -> new EndlessStoreFuel(new Item.Properties().stacksTo(32), 55));
    public static final RegistryObject<Item> METAL_PIPES = ITEMS.register("metal_pipes", () -> new Item(new Item.Properties().stacksTo(8)));
    public static final RegistryObject<Item> NAILS = ITEMS.register("nails", () -> new Item(new Item.Properties().stacksTo(32)));
    public static final RegistryObject<Item> BOLTS = ITEMS.register("bolts", () -> new Item(new Item.Properties().stacksTo(32)));
    public static final RegistryObject<Item> METAL_PLATES = ITEMS.register("metal_plates", () -> new Item(new Item.Properties().stacksTo(4)));
    public static final RegistryObject<Item> CONCRETE_BRICK = ITEMS.register("concrete_brick", () -> new Item(new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> SCRAP = ITEMS.register("scrap", () -> new Item(new Item.Properties().stacksTo(32)));
    public static final RegistryObject<Item> MOLTEN_SCRAP = ITEMS.register("molten_scrap", () -> new Item(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> PLASTIC_BUCKET = ITEMS.register("plastic_bucket", () -> new CustomBucketItem(Fluids.EMPTY, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> TRASH_PLASTIC = ITEMS.register("trash_plastic", () -> new Item(new Item.Properties().stacksTo(32)));
    public static final RegistryObject<Item> BROKEN_GLASS = ITEMS.register("broken_glass", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PIECE_OF_LINOLEUM = ITEMS.register("pieces_of_linoleum", () -> new EndlessStoreFuel(new Item.Properties().stacksTo(16), 75));
    public static final RegistryObject<Item> WIRES = ITEMS.register("wires", () -> new Item(new Item.Properties().stacksTo(32)));
    public static final RegistryObject<Item> PIECE_OF_WALLPAPER = ITEMS.register("pieces_of_wallpaper", () -> new EndlessStoreFuel(new Item.Properties().stacksTo(16), 55));
    public static final RegistryObject<Item> SAWDUST = ITEMS.register("sawdust", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COARSE_FABRIC = ITEMS.register("coarse_fabric", () -> new Item(new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> CANISTER = ITEMS.register("canister", () -> new Item(new Item.Properties().stacksTo(4)));
    public static final RegistryObject<Item> EMPTY_CAN = ITEMS.register("empty_can", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ELECTRONIC_BOARD = ITEMS.register("electronic_board", () -> new Item(new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> MICROCHIP = ITEMS.register("microchip", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COIL_COPPER_WIRES = ITEMS.register("coil_copper_wires", () -> new Item(new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> TRANSISTOR = ITEMS.register("transistor", () -> new Item(new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> BREPSI = ITEMS.register("brepsi", () -> new Item(new Item.Properties().food(EndlessStoreFoods.BREPSI).stacksTo(4)));
    public static final RegistryObject<Item> WET_WALLPAPER = ITEMS.register("wet_wallpaper", () -> new Item(new Item.Properties().food(EndlessStoreFoods.WET_WALLPAPER).stacksTo(16)));
    public static final RegistryObject<Item> SAWDUST_SOUP = ITEMS.register("sawdust_soup", () -> new BowlFoodItem(new Item.Properties().food(EndlessStoreFoods.SAWDUST_SOUP).stacksTo(1)));
    public static final RegistryObject<Item> HARDTACK = ITEMS.register("hardtack", () -> new Item(new Item.Properties().food(EndlessStoreFoods.HARDTACK).stacksTo(32)));
    public static final RegistryObject<Item> CRISP = ITEMS.register("crisp", () -> new Item(new Item.Properties().food(EndlessStoreFoods.CRISP).stacksTo(16)));
    public static final RegistryObject<Item> HOTDOG = ITEMS.register("hotdog", () -> new Item(new Item.Properties().food(EndlessStoreFoods.HOTDOG).stacksTo(6)));
    public static final RegistryObject<Item> PANCAKE = ITEMS.register("pancake", () -> new Item(new Item.Properties().food(EndlessStoreFoods.PANCAKE).stacksTo(16)));
    public static final RegistryObject<Item> SANDWICH = ITEMS.register("sandwich", () -> new Item(new Item.Properties().food(EndlessStoreFoods.SANDWICH).stacksTo(3)));
    public static final RegistryObject<Item> WATER_STRIDER_EGGS = ITEMS.register("water_strider_eggs", () -> new Item(new Item.Properties().food(EndlessStoreFoods.WATER_STRIDER_EGGS).stacksTo(16)));

    public static final RegistryObject<Item> EMPLOYEE_SPAWN_EGG = ITEMS.register("employee_spawn_egg",
            () -> new ForgeSpawnEggItem(EndlessStoreEntities.EMPLOYEE, 0x23b341, 0x12732e,
                    new Item.Properties()));

    public static final RegistryObject<Item> JACK_SPAWN_EGG = ITEMS.register("jack_spawn_egg",
            () -> new ForgeSpawnEggItem(EndlessStoreEntities.JACK, 0x83b314, 0x4a732e,
                    new Item.Properties()));

    public static final RegistryObject<Item> SECURITY_SPAWN_EGG = ITEMS.register("security_spawn_egg",
            () -> new ForgeSpawnEggItem(EndlessStoreEntities.SECURITY, 0x0ebe14, 0x0a3411d,
                    new Item.Properties()));

    public static final RegistryObject<Item> WATCHER_SPAWN_EGG = ITEMS.register("watcher_spawn_egg",
            () -> new ForgeSpawnEggItem(EndlessStoreEntities.WATCHER, 0x1dd214, 0x4c6d2ad,
                    new Item.Properties()));

    public static final RegistryObject<Item> WATER_STRIDER_EGG = ITEMS.register("water_strider_spawn_egg",
            () -> new ForgeSpawnEggItem(EndlessStoreEntities.WATER_STRIDER, 0xade714, 0xcc612ad,
                    new Item.Properties()));

    public static final RegistryObject<Item> FLIMSY_PICKAXE = ITEMS.register("flimsy_pickaxe", () -> new PickaxeItem(Tiers.WOOD, 1, -2.8F, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> HANDMADE_PICKAXE = ITEMS.register("handmade_pickaxe", () -> new PickaxeItem(Tiers.STONE, 1, -2.8F, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> DURABLE_PICKAXE = ITEMS.register("durable_pickaxe", () -> new PickaxeItem(Tiers.IRON, 1, -2.8F, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> PROFESSIONAL_PICKAXE = ITEMS.register("professional_pickaxe", () -> new PickaxeItem(Tiers.DIAMOND, 1, -2.8F, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> HANDMADE_AXE = ITEMS.register("handmade_axe", () -> new AxeItem(Tiers.STONE, 5F, -3.2F, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> HANDMADE_SHOVEL = ITEMS.register("handmade_shovel", () -> new ShovelItem(Tiers.STONE, 1.5F, -3.0F, new Item.Properties()));

    public static final RegistryObject<Item> KNIFE = ITEMS.register("knife", () -> new SwordItem(Tiers.WOOD, 5, -2.35F, new Item.Properties().stacksTo(1)));;
    public static final RegistryObject<Item> FRYING_PAN = ITEMS.register("frying_pan", () -> new SwordItem(Tiers.IRON, 6, -2.8F, new Item.Properties().stacksTo(1)));;
    public static final RegistryObject<Item> BATON = ITEMS.register("baton", () -> new SwordItem(Tiers.IRON, 6, -2.8F, new Item.Properties().stacksTo(1)));;
    public static final RegistryObject<Item> EXTINGUISHER = ITEMS.register("extinguisher", () -> new SwordItem(Tiers.STONE, 8, -3.0F, new Item.Properties().stacksTo(1)));;

    public static final RegistryObject<Item> EMPLOYEES_SHIRT = ITEMS.register("employees_shirt", () -> new EmployeeShirtArmorItem(EndlessStoreArmorMaterials.EMPLOYEE, ArmorItem.Type.CHESTPLATE,  new Item.Properties().stacksTo(1)));;
    public static final RegistryObject<Item> SECURITY_CAP = ITEMS.register("security_cap", () -> new SecurityArmorItem(EndlessStoreArmorMaterials.SECURITY, ArmorItem.Type.HELMET,  new Item.Properties().stacksTo(1)));;
    public static final RegistryObject<Item> CARDBOARD_HELMET = ITEMS.register("cardboard_helmet", () -> new HandmadeArmorItem(EndlessStoreArmorMaterials.HANDMADE, ArmorItem.Type.HELMET,  new Item.Properties().stacksTo(1)));;
    public static final RegistryObject<Item> CARDBOARD_TROUSERS = ITEMS.register("cardboard_trousers", () -> new HandmadeArmorItem(EndlessStoreArmorMaterials.HANDMADE, ArmorItem.Type.LEGGINGS,  new Item.Properties().stacksTo(1)));;
    public static final RegistryObject<Item> HANDMADE_CHAIN_MAIL = ITEMS.register("handmade_chain_mail", () -> new HandmadeIronArmorItem(EndlessStoreArmorMaterials.HANDMADE_IRON, ArmorItem.Type.CHESTPLATE,  new Item.Properties().stacksTo(1)));;
    public static final RegistryObject<Item> HANDMADE_CHAIN_MAIL_TROUSERS = ITEMS.register("handmade_chain_mail_trousers", () -> new HandmadeIronArmorItem(EndlessStoreArmorMaterials.HANDMADE_IRON, ArmorItem.Type.LEGGINGS,  new Item.Properties().stacksTo(1)));;
    public static final RegistryObject<Item> SECURITY_SHIRT = ITEMS.register("security_shirt", () -> new SecurityArmorItem(EndlessStoreArmorMaterials.SECURITY, ArmorItem.Type.CHESTPLATE,  new Item.Properties().stacksTo(1)));;


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
