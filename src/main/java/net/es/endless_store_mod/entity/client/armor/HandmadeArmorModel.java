package net.es.endless_store_mod.entity.client.armor;

import net.es.endless_store_mod.EndlessStoreMod;
import net.es.endless_store_mod.item.custom.HandmadeArmorItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class HandmadeArmorModel extends GeoModel<HandmadeArmorItem> {
    @Override
    public Identifier getModelResource(HandmadeArmorItem object) {
        return new Identifier(EndlessStoreMod.MOD_ID, "geo/handmade_armor.geo.json");
    }

    @Override
    public Identifier getTextureResource(HandmadeArmorItem object) {
        return new Identifier(EndlessStoreMod.MOD_ID, "textures/armor/handmade_armor.png");
    }

    @Override
    public Identifier getAnimationResource(HandmadeArmorItem animatable) {
        return new Identifier(EndlessStoreMod.MOD_ID, "animations/handmade_armor.animation.json");
    }
}