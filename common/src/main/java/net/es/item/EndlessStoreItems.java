package net.es.item;

import net.es.entity.EndlessStoreEntities;
import net.es.item.custom.EmployeeShirtArmorItem;
import net.es.item.custom.HandmadeArmorItem;
import net.es.item.custom.HandmadeIronArmorItem;
import net.es.item.custom.SecurityArmorItem;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluids;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

import static net.es.block.EndlessStoreBlocks.BLOCK_FACTORIES;

public class EndlessStoreItems {
    public static final Map<String, Supplier<Item>> FACTORIES = new LinkedHashMap<>();

    public static Supplier<Item> PLASTIC_BUCKET_ITEM = () -> {
        throw new IllegalStateException("Plastic bucket not registered");
    };


    // --- Идентификаторы предметов ---
    public static final String PLASTIC_BUCKET = "plastic_bucket";

    public static final String WOODEN_BOARD = "wooden_board";
    public static final String PIECE_OF_CARDBOARD = "piece_of_cardboard";
    public static final String PIECE_OF_WALLPAPER = "pieces_of_wallpaper";
    public static final String PIECE_OF_LINOLEUM = "pieces_of_linoleum";

    public static final String EMPLOYEE_SPAWN_EGG = "employee_spawn_egg";
    public static final String JACK_SPAWN_EGG = "jack_spawn_egg";
    public static final String SECURITY_SPAWN_EGG = "security_spawn_egg";
    public static final String WATCHER_SPAWN_EGG = "watcher_spawn_egg";
    public static final String WATER_STRIDER_EGG = "water_strider_spawn_egg";
    public static final String KNIFE = "knife";
    public static final String FRYING_PAN = "frying_pan";
    public static final String BATON = "baton";
    public static final String EXTINGUISHER = "extinguisher";
    public static final String COARSE_FABRIC = "coarse_fabric";
    public static final String SCRAP = "scrap";
    public static final String EMPLOYEES_SHIRT = "employees_shirt";
    public static final String SECURITY_CAP = "security_cap";
    public static final String CARDBOARD_HELMET = "cardboard_helmet";
    public static final String CARDBOARD_TROUSERS = "cardboard_trousers";
    public static final String HANDMADE_CHAIN_MAIL = "handmade_chain_mail";
    public static final String HANDMADE_CHAIN_MAIL_TROUSERS = "handmade_chain_mail_trousers";
    public static final String SECURITY_SHIRT = "security_shirt";

    private static final Tier KNIFE_TIER = new Tier() {
        @Override public int getUses() { return 59; }
        @Override public float getSpeed() { return 2.0F; }
        @Override public float getAttackDamageBonus() { return 2.0F; }
        @Override public int getEnchantmentValue() { return 15; }
        @Override public Ingredient getRepairIngredient() { return Ingredient.of(Items.OAK_PLANKS); }
        @Override public TagKey<Block> getIncorrectBlocksForDrops() {
            return BlockTags.INCORRECT_FOR_WOODEN_TOOL;  // для деревянного инструмента
        }
    };

    private static final Tier FRYING_PAN_TIER = new Tier() {
        @Override public int getUses() { return 250; }
        @Override public float getSpeed() { return 6.0F; }
        @Override public float getAttackDamageBonus() { return 3.0F; }
        @Override public int getEnchantmentValue() { return 14; }
        @Override public Ingredient getRepairIngredient() { return Ingredient.of(Items.IRON_INGOT); }
        @Override public TagKey<Block> getIncorrectBlocksForDrops() {
            return BlockTags.INCORRECT_FOR_IRON_TOOL;    // для железного инструмента
        }
    };
    private static final Tier BATON_TIER = new Tier() {
        @Override public int getUses() { return 250; }          // как IRON
        @Override public float getSpeed() { return 6.0F; }
        @Override public float getAttackDamageBonus() { return 3.0F; }  // 6 – 3 = 3
        @Override public int getEnchantmentValue() { return 14; }
        @Override public Ingredient getRepairIngredient() { return Ingredient.of(Items.IRON_INGOT); }
        @Override public TagKey<Block> getIncorrectBlocksForDrops() {
            return BlockTags.INCORRECT_FOR_IRON_TOOL;    // для железного инструмента
        }
    };

    private static final Tier EXTINGUISHER_TIER = new Tier() {
        @Override public int getUses() { return 131; }          // как STONE
        @Override public float getSpeed() { return 4.0F; }
        @Override public float getAttackDamageBonus() { return 5.0F; }  // 8 – 3 = 5
        @Override public int getEnchantmentValue() { return 5; }
        @Override public Ingredient getRepairIngredient() { return Ingredient.of(Items.STONE); }
        @Override public TagKey<Block> getIncorrectBlocksForDrops() {
            return BlockTags.INCORRECT_FOR_STONE_TOOL;    // для железного инструмента
        }
    };

    static {
        // Обычные предметы
        FACTORIES.put(WOODEN_BOARD, () -> new Item(new Item.Properties().stacksTo(16)));
        FACTORIES.put(PIECE_OF_CARDBOARD, () -> new Item(new Item.Properties().stacksTo(32)));
        FACTORIES.put(PIECE_OF_WALLPAPER, () -> new Item(new Item.Properties().stacksTo(16)));
        FACTORIES.put(PIECE_OF_LINOLEUM, () -> new Item(new Item.Properties().stacksTo(16)));


        FACTORIES.put("concrete_crumbs", () -> new Item(new Item.Properties().stacksTo(16)));
        FACTORIES.put("metal_pipes", () -> new Item(new Item.Properties().stacksTo(8)));
        FACTORIES.put("nails", () -> new Item(new Item.Properties().stacksTo(32)));
        FACTORIES.put("bolts", () -> new Item(new Item.Properties().stacksTo(32)));
        FACTORIES.put("metal_plates", () -> new Item(new Item.Properties().stacksTo(4)));
        FACTORIES.put("concrete_brick", () -> new Item(new Item.Properties().stacksTo(16)));
        FACTORIES.put("scrap", () -> new Item(new Item.Properties().stacksTo(32)));
        FACTORIES.put("molten_scrap", () -> new Item(new Item.Properties().stacksTo(1)));
        FACTORIES.put("plastic_bucket", () -> new CustomBucketItem(Fluids.EMPTY, new Item.Properties().stacksTo(16)));
        FACTORIES.put("trash_plastic", () -> new Item(new Item.Properties().stacksTo(32)));
        FACTORIES.put("broken_glass", () -> new Item(new Item.Properties()));
        FACTORIES.put("wires", () -> new Item(new Item.Properties().stacksTo(32)));
        FACTORIES.put("sawdust", () -> new Item(new Item.Properties().stacksTo(64)));
        FACTORIES.put("coarse_fabric", () -> new Item(new Item.Properties().stacksTo(16)));
        FACTORIES.put("canister", () -> new Item(new Item.Properties().stacksTo(4)));
        FACTORIES.put("empty_can", () -> new Item(new Item.Properties().stacksTo(64)));
        FACTORIES.put("electronic_board", () -> new Item(new Item.Properties().stacksTo(16)));
        FACTORIES.put("microchip", () -> new Item(new Item.Properties().stacksTo(64)));
        FACTORIES.put("coil_copper_wires", () -> new Item(new Item.Properties().stacksTo(16)));
        FACTORIES.put("transistor", () -> new Item(new Item.Properties().stacksTo(16)));

        // Еда (FoodProperties.Builder)
        FACTORIES.put("brepsi", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(5).saturationModifier(0.3f).build()).stacksTo(4)));
        FACTORIES.put("wet_wallpaper", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.2f).build()).stacksTo(16)));
        FACTORIES.put("sawdust_soup", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).usingConvertsTo(Items.BOWL).saturationModifier(0.2f).build()).stacksTo(1)));
        FACTORIES.put("hardtack", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.3f).build()).stacksTo(32)));
        FACTORIES.put("crisp", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.2f).build()).stacksTo(16)));
        FACTORIES.put("hotdog", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(6).saturationModifier(0.6F).build()).stacksTo(6)));
        FACTORIES.put("pancake", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationModifier(0.6F).build()).stacksTo(16)));
        FACTORIES.put("sandwich", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(7).saturationModifier(0.7f).build()).stacksTo(3)));
        FACTORIES.put("water_strider_eggs", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.3f).build()).stacksTo(16)));

        // Яйца призыва (SpawnEggItem)
        FACTORIES.put(EMPLOYEE_SPAWN_EGG, () -> new CustomSpawnEggItem(
                EndlessStoreEntities.EMPLOYEE.get(), 0x23b341, 0x12732e, new Item.Properties(),
                Component.translatable("tooltip.endless_store_mod.employee_spawn_egg")));
        FACTORIES.put(JACK_SPAWN_EGG, () -> new CustomSpawnEggItem(
                EndlessStoreEntities.JACK.get(), 0x83b314, 0x4a732e, new Item.Properties(),
                Component.translatable("tooltip.endless_store_mod.employee_spawn_egg")));
        FACTORIES.put(SECURITY_SPAWN_EGG, () -> new CustomSpawnEggItem(
                EndlessStoreEntities.SECURITY.get(), 0x0ebe14, 0x0a3411d, new Item.Properties(),
                Component.translatable("tooltip.endless_store_mod.employee_spawn_egg")));
        FACTORIES.put(WATCHER_SPAWN_EGG, () -> new CustomSpawnEggItem(
                EndlessStoreEntities.WATCHER.get(), 0x1dd214, 0x4c6d2ad, new Item.Properties(),
                Component.translatable("tooltip.endless_store_mod.employee_spawn_egg")));
        FACTORIES.put(WATER_STRIDER_EGG, () -> new CustomSpawnEggItem(
                EndlessStoreEntities.WATER_STRIDER.get(), 0xade714, 0xcc612ad, new Item.Properties(),
                Component.translatable("tooltip.endless_store_mod.employee_spawn_egg")));

        FACTORIES.put("flimsy_pickaxe", () -> new PickaxeItem(Tiers.WOOD, new Item.Properties().stacksTo(1).attributes(PickaxeItem.createAttributes(Tiers.WOOD, 1.0F, -2.8F))));
        FACTORIES.put("handmade_pickaxe", () -> new PickaxeItem(Tiers.STONE, new Item.Properties().stacksTo(1).attributes(PickaxeItem.createAttributes(Tiers.STONE, 1.0F, -2.8F))));
        FACTORIES.put("durable_pickaxe", () -> new PickaxeItem(Tiers.IRON, new Item.Properties().stacksTo(1).attributes(PickaxeItem.createAttributes(Tiers.IRON, 1.0F, -2.8F))));
        FACTORIES.put("professional_pickaxe", () -> new PickaxeItem(Tiers.DIAMOND, new Item.Properties().stacksTo(1).attributes(PickaxeItem.createAttributes(Tiers.DIAMOND, 1.0F, -2.8F))));
        FACTORIES.put("handmade_axe", () -> new AxeItem(Tiers.STONE, new Item.Properties().stacksTo(1).attributes(AxeItem.createAttributes(Tiers.STONE, 7.0F, -3.2F))));
        FACTORIES.put("handmade_shovel", () -> new ShovelItem(Tiers.STONE, new Item.Properties().stacksTo(1).attributes(HoeItem.createAttributes(Tiers.STONE, -1.0F, -2.0F))));

        FACTORIES.put(KNIFE, () -> new SwordItem(KNIFE_TIER, new Item.Properties()
                .stacksTo(1)
                .attributes(SwordItem.createAttributes(KNIFE_TIER, 1, -2.35F))));
        FACTORIES.put(FRYING_PAN, () -> new SwordItem(FRYING_PAN_TIER, new Item.Properties()
                .stacksTo(1)
                .attributes(SwordItem.createAttributes(FRYING_PAN_TIER, 1,-2.8F))));
        FACTORIES.put(BATON, () -> new SwordItem(BATON_TIER, new Item.Properties()
                .stacksTo(1)
                .attributes(SwordItem.createAttributes(BATON_TIER, 1,-2.8F))));
        FACTORIES.put(EXTINGUISHER, () -> new SwordItem(EXTINGUISHER_TIER, new Item.Properties()
                .stacksTo(1)
                .attributes(SwordItem.createAttributes(EXTINGUISHER_TIER, 1,-3.0F))));

        FACTORIES.put(EMPLOYEES_SHIRT, () -> new EmployeeShirtArmorItem(
                EndlessStoreArmorMaterials.EMPLOYEE, ArmorItem.Type.CHESTPLATE, new Item.Properties().stacksTo(1).durability(net.minecraft.world.item.ArmorItem.Type.HELMET.getDurability(5))));

        FACTORIES.put(SECURITY_CAP, () -> new SecurityArmorItem(
                EndlessStoreArmorMaterials.SECURITY, ArmorItem.Type.HELMET, new Item.Properties().stacksTo(1).durability(net.minecraft.world.item.ArmorItem.Type.HELMET.getDurability(15))));
        FACTORIES.put(SECURITY_SHIRT, () -> new SecurityArmorItem(
                EndlessStoreArmorMaterials.SECURITY, ArmorItem.Type.CHESTPLATE, new Item.Properties().stacksTo(1).durability(net.minecraft.world.item.ArmorItem.Type.HELMET.getDurability(15))));

        FACTORIES.put(CARDBOARD_HELMET, () -> new HandmadeArmorItem(
                EndlessStoreArmorMaterials.HANDMADE, ArmorItem.Type.HELMET, new Item.Properties().stacksTo(1).durability(net.minecraft.world.item.ArmorItem.Type.HELMET.getDurability(5))));
        FACTORIES.put(CARDBOARD_TROUSERS, () -> new HandmadeArmorItem(
                EndlessStoreArmorMaterials.HANDMADE, ArmorItem.Type.LEGGINGS, new Item.Properties().stacksTo(1).durability(net.minecraft.world.item.ArmorItem.Type.HELMET.getDurability(5))));

        FACTORIES.put(HANDMADE_CHAIN_MAIL, () -> new HandmadeIronArmorItem(
                EndlessStoreArmorMaterials.HANDMADE_IRON, ArmorItem.Type.CHESTPLATE, new Item.Properties().stacksTo(1).durability(net.minecraft.world.item.ArmorItem.Type.HELMET.getDurability(15))));
        FACTORIES.put(HANDMADE_CHAIN_MAIL_TROUSERS, () -> new HandmadeIronArmorItem(
                EndlessStoreArmorMaterials.HANDMADE_IRON, ArmorItem.Type.LEGGINGS, new Item.Properties().stacksTo(1).durability(net.minecraft.world.item.ArmorItem.Type.HELMET.getDurability(15))));

    }

    @FunctionalInterface
    public interface ItemRegistry {
        void register(String name, Supplier<Item> factory);
    }

    public static Item getItem(String name) {
        Supplier<Item> supplier = FACTORIES.get(name);
        if (supplier == null) {
            throw new IllegalStateException("Item " + name + " not found in FACTORIES");
        }
        return supplier.get();
    }

    public static void registerItems(ItemRegistry registry) {
        FACTORIES.forEach(registry::register);
    }
}