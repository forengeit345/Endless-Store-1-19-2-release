package net.es.endless_store_mod.entity.client;

import net.es.endless_store_mod.EndlessStoreMod;
import net.es.endless_store_mod.entity.custom.SecurityEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SecurityModel extends AnimatedGeoModel<SecurityEntity> {
    @Override
    public Identifier getModelResource(SecurityEntity object) {
        return new Identifier(EndlessStoreMod.MOD_ID, "geo/security.geo.json");
    }

    @Override
    public Identifier getTextureResource(SecurityEntity object) {
        return new Identifier(EndlessStoreMod.MOD_ID, "textures/entity/security.png");
    }

    @Override
    public Identifier getAnimationResource(SecurityEntity animatable) {
        return new Identifier(EndlessStoreMod.MOD_ID, "animations/security.animation.json");
    }
}
