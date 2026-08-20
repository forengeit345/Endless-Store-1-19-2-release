package net.es.entity.client.armor;

import net.es.EndlessStoreMod;
import net.es.item.custom.SecurityArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SecurityArmorModel extends GeoModel<SecurityArmorItem> {
    @Override
    public ResourceLocation getModelResource(SecurityArmorItem object) {
        return ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "geo/security_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SecurityArmorItem object) {
        return ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "textures/armor/security_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SecurityArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "animations/security_armor.animation.json");
    }
}