package net.es.entity.client.armor;

import net.es.EndlessStoreMod;
import net.es.item.custom.EmployeeShirtArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EmployeeShirtModel extends GeoModel<EmployeeShirtArmorItem> {

    @Override
    public ResourceLocation getModelResource(EmployeeShirtArmorItem object) {
        return ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "geo/employee_shirt.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EmployeeShirtArmorItem object) {
        return ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "textures/armor/employees_shirt.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EmployeeShirtArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "animations/employee_shirt.animation.json");
    }
}