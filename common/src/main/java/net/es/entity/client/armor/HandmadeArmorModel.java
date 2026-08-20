package net.es.entity.client.armor;

import net.es.EndlessStoreMod;
import net.es.item.custom.HandmadeArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class HandmadeArmorModel extends GeoModel<HandmadeArmorItem> {
    @Override
    public ResourceLocation getModelResource(HandmadeArmorItem object) {
        return ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "geo/handmade_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(HandmadeArmorItem object) {
        return ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "textures/armor/handmade_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(HandmadeArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "animations/handmade_armor.animation.json");
    }
}