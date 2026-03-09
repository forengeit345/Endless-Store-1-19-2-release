package net.es.endless_store_mod.entity.client;

import net.es.endless_store_mod.EndlessStoreMod;
import net.es.endless_store_mod.entity.custom.JackEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class JackModel extends GeoModel<JackEntity> {
    @Override
    public ResourceLocation getModelResource(JackEntity object) {
        return new ResourceLocation(EndlessStoreMod.MOD_ID, "geo/jack.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(JackEntity object) {
        return new ResourceLocation(EndlessStoreMod.MOD_ID, "textures/entity/jack.png");
    }

    @Override
    public ResourceLocation getAnimationResource(JackEntity animatable) {
        return new ResourceLocation(EndlessStoreMod.MOD_ID, "animations/jack.animation.json");
    }
}
