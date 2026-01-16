package net.es.endless_store_mod.entity.client.armor;

import net.es.endless_store_mod.EndlessStoreMod;
import net.es.endless_store_mod.item.custom.HandmadeIronArmorItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class HandmadeIronArmorModel extends GeoModel<HandmadeIronArmorItem> {
    @Override
    public Identifier getModelResource(HandmadeIronArmorItem object) {
        return new Identifier(EndlessStoreMod.MOD_ID, "geo/handmade_iron_armor.geo.json");
    }

    @Override
    public Identifier getTextureResource(HandmadeIronArmorItem object) {
        return new Identifier(EndlessStoreMod.MOD_ID, "textures/armor/handmade_iron_armor.png");
    }

    @Override
    public Identifier getAnimationResource(HandmadeIronArmorItem animatable) {
        return new Identifier(EndlessStoreMod.MOD_ID, "animations/handmade_iron_armor.animation.json");
    }
}