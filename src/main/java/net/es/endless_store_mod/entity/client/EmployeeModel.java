package net.es.endless_store_mod.entity.client;

import net.es.endless_store_mod.EndlessStoreMod;
import net.es.endless_store_mod.entity.custom.EmployeeEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EmployeeModel extends GeoModel<EmployeeEntity> {
    @Override
    public ResourceLocation getModelResource(EmployeeEntity object) {
        return new ResourceLocation(EndlessStoreMod.MOD_ID, "geo/employee.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EmployeeEntity object) {
        return new ResourceLocation(EndlessStoreMod.MOD_ID, "textures/entity/employee.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EmployeeEntity animatable) {
        return new ResourceLocation(EndlessStoreMod.MOD_ID, "animations/employee.animation.json");
    }
}
