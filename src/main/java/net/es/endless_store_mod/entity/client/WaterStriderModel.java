package net.es.endless_store_mod.entity.client;

import net.es.endless_store_mod.EndlessStoreMod;
import net.es.endless_store_mod.entity.custom.WaterStriderEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class WaterStriderModel extends GeoModel<WaterStriderEntity> {
    @Override
    public ResourceLocation getModelResource(WaterStriderEntity object) {
        return new ResourceLocation(EndlessStoreMod.MOD_ID, "geo/water_strider.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WaterStriderEntity object) {
        return new ResourceLocation(EndlessStoreMod.MOD_ID, "textures/entity/water_strider.png");
    }

    @Override
    public ResourceLocation getAnimationResource(WaterStriderEntity animatable) {
        return new ResourceLocation(EndlessStoreMod.MOD_ID, "animations/water_strider.animation.json");
    }
}
