package net.es.entity.client;

import net.es.EndlessStoreMod;
import net.es.entity.custom.JackEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class JackModel extends GeoModel<JackEntity> {
    @Override
    public ResourceLocation getModelResource(JackEntity object) {
        return ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "geo/jack.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(JackEntity object) {
        return ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "textures/entity/jack.png");
    }

    @Override
    public ResourceLocation getAnimationResource(JackEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "animations/jack.animation.json");
    }
}
