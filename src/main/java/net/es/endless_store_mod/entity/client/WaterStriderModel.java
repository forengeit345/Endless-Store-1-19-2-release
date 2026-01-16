package net.es.endless_store_mod.entity.client;

import net.es.endless_store_mod.EndlessStoreMod;
import net.es.endless_store_mod.entity.custom.WaterStriderEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class WaterStriderModel extends GeoModel<WaterStriderEntity> {
    @Override
    public Identifier getModelResource(WaterStriderEntity object) {
        return new Identifier(EndlessStoreMod.MOD_ID, "geo/water_strider.geo.json");
    }

    @Override
    public Identifier getTextureResource(WaterStriderEntity object) {
        return new Identifier(EndlessStoreMod.MOD_ID, "textures/entity/water_strider.png");
    }

    @Override
    public Identifier getAnimationResource(WaterStriderEntity animatable) {
        return new Identifier(EndlessStoreMod.MOD_ID, "animations/water_strider.animation.json");
    }
}
