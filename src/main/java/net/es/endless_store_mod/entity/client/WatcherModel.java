package net.es.endless_store_mod.entity.client;

import net.es.endless_store_mod.EndlessStoreMod;
import net.es.endless_store_mod.entity.custom.WatcherEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class WatcherModel extends GeoModel<WatcherEntity> {
    @Override
    public ResourceLocation getModelResource(WatcherEntity object) {
        return new ResourceLocation(EndlessStoreMod.MOD_ID, "geo/watcher.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WatcherEntity object) {
        return new ResourceLocation(EndlessStoreMod.MOD_ID, "textures/entity/watcher.png");
    }

    @Override
    public ResourceLocation getAnimationResource(WatcherEntity animatable) {
        return new ResourceLocation(EndlessStoreMod.MOD_ID, "animations/watcher.animation.json");
    }
}
