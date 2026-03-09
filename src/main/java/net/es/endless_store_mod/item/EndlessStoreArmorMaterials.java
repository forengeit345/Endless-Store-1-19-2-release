package net.es.endless_store_mod.item;

import net.es.endless_store_mod.EndlessStoreMod;
import net.minecraft.Util;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.function.Supplier;

public enum EndlessStoreArmorMaterials implements ArmorMaterial {
    EMPLOYEE("employee", 5, Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266652_) -> {
        p_266652_.put(ArmorItem.Type.BOOTS, 1);
        p_266652_.put(ArmorItem.Type.LEGGINGS, 2);
        p_266652_.put(ArmorItem.Type.CHESTPLATE, 3);
        p_266652_.put(ArmorItem.Type.HELMET, 2);
    }), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> {
        return Ingredient.of(EndlessStoreItems.COARSE_FABRIC.get());
    }),

    HANDMADE("handmade", 5, Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266652_) -> {
        p_266652_.put(ArmorItem.Type.BOOTS, 1);
        p_266652_.put(ArmorItem.Type.LEGGINGS, 2);
        p_266652_.put(ArmorItem.Type.CHESTPLATE, 2);
        p_266652_.put(ArmorItem.Type.HELMET, 1);
    }), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> {
        return Ingredient.of(EndlessStoreItems.PIECE_OF_CARDBOARD.get());
    }),

    HANDMADE_IRON("handmade_iron", 5, Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266652_) -> {
        p_266652_.put(ArmorItem.Type.BOOTS, 2);
        p_266652_.put(ArmorItem.Type.LEGGINGS, 4);
        p_266652_.put(ArmorItem.Type.CHESTPLATE, 5);
        p_266652_.put(ArmorItem.Type.HELMET, 3);
    }), 15, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.2F, () -> {
        return Ingredient.of(EndlessStoreItems.SCRAP.get());
    }),

    SECURITY("security", 5, Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266652_) -> {
        p_266652_.put(ArmorItem.Type.BOOTS, 1);
        p_266652_.put(ArmorItem.Type.LEGGINGS, 4);
        p_266652_.put(ArmorItem.Type.CHESTPLATE, 5);
        p_266652_.put(ArmorItem.Type.HELMET, 3);
    }), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> {
        return Ingredient.of(EndlessStoreItems.COARSE_FABRIC.get());
    });

    private static final EnumMap<ArmorItem.Type, Integer> HEALTH_FUNCTION_FOR_TYPE = Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266653_) -> {
        p_266653_.put(ArmorItem.Type.BOOTS, 13);
        p_266653_.put(ArmorItem.Type.LEGGINGS, 15);
        p_266653_.put(ArmorItem.Type.CHESTPLATE, 16);
        p_266653_.put(ArmorItem.Type.HELMET, 11);
    });
    private final String name;
    private final int durabilityMultiplier;
    private final EnumMap<ArmorItem.Type, Integer> protectionFunctionForType;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    EndlessStoreArmorMaterials(String pName, int pDurabilityMultiplier, EnumMap<ArmorItem.Type, Integer> pProtectionFunctionForType, int pEnchantmentValue, SoundEvent pSound, float pToughness, float pKnockbackResistance, Supplier<Ingredient> pRepairIngredient) {
        this.name = pName;
        this.durabilityMultiplier = pDurabilityMultiplier;
        this.protectionFunctionForType = pProtectionFunctionForType;
        this.enchantmentValue = pEnchantmentValue;
        this.sound = pSound;
        this.toughness = pToughness;
        this.knockbackResistance = pKnockbackResistance;
        this.repairIngredient = new LazyLoadedValue<>(pRepairIngredient);
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type pType) {
        return HEALTH_FUNCTION_FOR_TYPE.get(pType) * this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type pType) {
        return this.protectionFunctionForType.get(pType);
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public SoundEvent getEquipSound() {
        return this.sound;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    public String getName() {
        return EndlessStoreMod.MOD_ID + ":" + this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}