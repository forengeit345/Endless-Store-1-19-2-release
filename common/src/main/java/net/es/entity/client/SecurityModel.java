package net.es.entity.client;

import net.es.EndlessStoreMod;
import net.es.entity.custom.SecurityEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SecurityModel extends GeoModel<SecurityEntity> {
    @Override
    public ResourceLocation getModelResource(SecurityEntity object) {
        return ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "geo/security.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SecurityEntity object) {
        return ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "textures/entity/security.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SecurityEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "animations/security.animation.json");
    }
}
