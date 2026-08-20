package net.es.entity.client.armor;

import net.es.EndlessStoreMod;
import net.es.item.custom.HandmadeIronArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class HandmadeIronArmorModel extends GeoModel<HandmadeIronArmorItem> {
    @Override
    public ResourceLocation getModelResource(HandmadeIronArmorItem object) {
        return ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "geo/handmade_iron_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(HandmadeIronArmorItem object) {
        return ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "textures/armor/handmade_iron_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(HandmadeIronArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "animations/handmade_iron_armor.animation.json");
    }
}