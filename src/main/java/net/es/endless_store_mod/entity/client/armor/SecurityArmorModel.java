package net.es.endless_store_mod.entity.client.armor;

import net.es.endless_store_mod.EndlessStoreMod;
import net.es.endless_store_mod.item.custom.SecurityArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SecurityArmorModel extends GeoModel<SecurityArmorItem> {
    @Override
    public ResourceLocation getModelResource(SecurityArmorItem object) {
        return new ResourceLocation (EndlessStoreMod.MOD_ID, "geo/security_armor.geo.json");
    }

    @Override
    public ResourceLocation  getTextureResource(SecurityArmorItem object) {
        return new ResourceLocation (EndlessStoreMod.MOD_ID, "textures/armor/security_armor.png");
    }

    @Override
    public ResourceLocation  getAnimationResource(SecurityArmorItem animatable) {
        return new ResourceLocation (EndlessStoreMod.MOD_ID, "animations/security_armor.animation.json");
    }
}
