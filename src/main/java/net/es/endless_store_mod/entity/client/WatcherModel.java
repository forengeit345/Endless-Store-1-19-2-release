package net.es.endless_store_mod.entity.client;

import net.es.endless_store_mod.EndlessStoreMod;
import net.es.endless_store_mod.entity.custom.SecurityEntity;
import net.es.endless_store_mod.entity.custom.WatcherEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class WatcherModel extends GeoModel<WatcherEntity> {
    @Override
    public Identifier getModelResource(WatcherEntity object) {
        return new Identifier(EndlessStoreMod.MOD_ID, "geo/watcher.geo.json");
    }

    @Override
    public Identifier getTextureResource(WatcherEntity object) {
        return new Identifier(EndlessStoreMod.MOD_ID, "textures/entity/watcher.png");
    }

    @Override
    public Identifier getAnimationResource(WatcherEntity animatable) {
        return new Identifier(EndlessStoreMod.MOD_ID, "animations/watcher.animation.json");
    }
}
