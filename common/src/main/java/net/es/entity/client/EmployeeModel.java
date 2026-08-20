package net.es.entity.client;

import net.es.EndlessStoreMod;
import net.es.entity.custom.EmployeeEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EmployeeModel extends GeoModel<EmployeeEntity> {
    @Override
    public ResourceLocation getModelResource(EmployeeEntity object) {
        return ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "geo/employee.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EmployeeEntity object) {
        return ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "textures/entity/employee.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EmployeeEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "animations/employee.animation.json");
    }
}