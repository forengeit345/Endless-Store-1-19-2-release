package net.es.endless_store_mod.entity.client.armor;

import net.es.endless_store_mod.EndlessStoreMod;
import net.es.endless_store_mod.item.custom.SecurityArmorItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class SecurityArmorModel extends GeoModel<SecurityArmorItem> {
    @Override
    public Identifier getModelResource(SecurityArmorItem object) {
        return new Identifier(EndlessStoreMod.MOD_ID, "geo/security_armor.geo.json");
    }

    @Override
    public Identifier getTextureResource(SecurityArmorItem object) {
        return new Identifier(EndlessStoreMod.MOD_ID, "textures/armor/security_armor.png");
    }

    @Override
    public Identifier getAnimationResource(SecurityArmorItem animatable) {
        return new Identifier(EndlessStoreMod.MOD_ID, "animations/security_armor.animation.json");
    }
}