package net.es.endless_store_mod.entity.client;

import net.es.endless_store_mod.EndlessStoreMod;
import net.es.endless_store_mod.entity.custom.JackEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class JackModel extends GeoModel<JackEntity> {
    @Override
    public Identifier getModelResource(JackEntity object) {
        return new Identifier(EndlessStoreMod.MOD_ID, "geo/jack.geo.json");
    }

    @Override
    public Identifier getTextureResource(JackEntity object) {
        return new Identifier(EndlessStoreMod.MOD_ID, "textures/entity/jack.png");
    }

    @Override
    public Identifier getAnimationResource(JackEntity animatable) {
        return new Identifier(EndlessStoreMod.MOD_ID, "animations/jack.animation.json");
    }
}
