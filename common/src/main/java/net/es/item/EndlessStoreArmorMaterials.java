package net.es.item;

import net.es.EndlessStoreMod;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class EndlessStoreArmorMaterials {
    public static final Holder<ArmorMaterial> EMPLOYEE;
    public static final Holder<ArmorMaterial> SECURITY;
    public static final Holder<ArmorMaterial> HANDMADE;
    public static final Holder<ArmorMaterial> HANDMADE_IRON;


    static {
        EMPLOYEE = register("employee",
                4,                              // durabilityMultiplier (базовая прочность умножается на это число)
                createDefenseMap(2, 2, 3, 1),   // helmet, chestplate, leggings, boots (у вас в EnumMap было: BOOTS=1, LEGGINGS=2, CHESTPLATE=3, HELMET=2)
                15,                             // enchantability
                SoundEvents.ARMOR_EQUIP_LEATHER,
                0.0F,                           // toughness
                0.0F,                           // knockbackResistance
                () -> Ingredient.of(EndlessStoreItems.getItem(EndlessStoreItems.COARSE_FABRIC))
        );
        SECURITY = register("security",
                5,                              // durabilityMultiplier (базовая прочность умножается на это число)
                createDefenseMap(1, 4, 5, 3),   // helmet, chestplate, leggings, boots (у вас в EnumMap было: BOOTS=1, LEGGINGS=2, CHESTPLATE=3, HELMET=2)
                15,                             // enchantability
                SoundEvents.ARMOR_EQUIP_LEATHER,
                0.0F,                           // toughness
                1.0F,                           // knockbackResistance
                () -> Ingredient.of(EndlessStoreItems.getItem(EndlessStoreItems.COARSE_FABRIC))
        );
        HANDMADE = register("handmade",
                3,                              // durabilityMultiplier (базовая прочность умножается на это число)
                createDefenseMap(1, 2, 2, 1),   // helmet, chestplate, leggings, boots (у вас в EnumMap было: BOOTS=1, LEGGINGS=2, CHESTPLATE=3, HELMET=2)
                15,                             // enchantability
                SoundEvents.ARMOR_EQUIP_LEATHER,
                0.0F,                           // toughness
                1.0F,                           // knockbackResistance
                () -> Ingredient.of(EndlessStoreItems.getItem(EndlessStoreItems.PIECE_OF_CARDBOARD))
        );
        HANDMADE_IRON = register("handmade_iron",
                6,                              // durabilityMultiplier (базовая прочность умножается на это число)
                createDefenseMap(2, 4, 5, 3),   // helmet, chestplate, leggings, boots (у вас в EnumMap было: BOOTS=1, LEGGINGS=2, CHESTPLATE=3, HELMET=2)
                15,                             // enchantability
                SoundEvents.ARMOR_EQUIP_CHAIN,
                0.0F,                           // toughness
                0.0F,                           // knockbackResistance
                () -> Ingredient.of(EndlessStoreItems.getItem(EndlessStoreItems.SCRAP))
        );
    }

    /**
     * Регистрирует материал брони.
     *
     * @param name               имя материала (используется в слое и идентификаторе)
     * @param durabilityMultiplier множитель прочности (базовая прочность = BASE_DURABILITY.get(type) * multiplier)
     * @param defensePoints      карта защиты по слотам (helmet, chestplate, leggings, boots, body)
     * @param enchantability     шанс получить хорошие чары
     * @param equipSound         звук надевания
     * @param toughness          прочность брони
     * @param knockbackResistance сопротивление отбрасыванию
     * @param repairIngredient   ингредиент для ремонта
     * @return Holder<ArmorMaterial>
     */

    private static Holder<ArmorMaterial> register(String name,
                                                  int durabilityMultiplier,
                                                  EnumMap<ArmorItem.Type, Integer> defensePoints,
                                                  int enchantability,
                                                  Holder<SoundEvent> equipSound,
                                                  float toughness,
                                                  float knockbackResistance,
                                                  Supplier<Ingredient> repairIngredient) {
        // Создаём полную карту прочности (как в ванильном ArmorMaterials)
        EnumMap<ArmorItem.Type, Integer> durabilityMap = new EnumMap<>(ArmorItem.Type.class);
        for (ArmorItem.Type type : ArmorItem.Type.values()) {
            int base = switch (type) {
                case BOOTS -> 13;
                case LEGGINGS -> 15;
                case CHESTPLATE -> 16;
                case HELMET -> 11;
                case BODY -> 16; // тело как нагрудник
            };
            durabilityMap.put(type, base * durabilityMultiplier);
        }

        // Слои текстур (один слой без окрашивания)
        List<ArmorMaterial.Layer> layers = List.of(
                new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, name), "", false)
        );

        ArmorMaterial material = new ArmorMaterial(
                defensePoints,
                enchantability,
                equipSound,
                repairIngredient,
                layers,
                toughness,
                knockbackResistance
        );

        return Registry.registerForHolder(
                BuiltInRegistries.ARMOR_MATERIAL,
                ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, name),
                material
        );
    }

    /**
     * Быстрый способ задать защиту для всех слотов.
     * Порядок: helmet, chestplate, leggings, boots (body = 0).
     */
    private static EnumMap<ArmorItem.Type, Integer> createDefenseMap(int helmet, int chestplate, int leggings, int boots) {
        EnumMap<ArmorItem.Type, Integer> map = new EnumMap<>(ArmorItem.Type.class);
        map.put(ArmorItem.Type.HELMET, helmet);
        map.put(ArmorItem.Type.CHESTPLATE, chestplate);
        map.put(ArmorItem.Type.LEGGINGS, leggings);
        map.put(ArmorItem.Type.BOOTS, boots);
        map.put(ArmorItem.Type.BODY, 0); // тело не защищено
        return map;
    }
}