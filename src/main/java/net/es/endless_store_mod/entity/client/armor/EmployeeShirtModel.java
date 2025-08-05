package net.es.endless_store_mod.entity.client.armor;

import net.es.endless_store_mod.EndlessStoreMod;
import net.es.endless_store_mod.item.custom.EmployeeShirtArmorItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class EmployeeShirtModel extends AnimatedGeoModel<EmployeeShirtArmorItem> {
    @Override
    public Identifier getModelResource(EmployeeShirtArmorItem object) {
        return new Identifier(EndlessStoreMod.MOD_ID, "geo/employee_shirt.geo.json");
    }

    @Override
    public Identifier getTextureResource(EmployeeShirtArmorItem object) {
        return new Identifier(EndlessStoreMod.MOD_ID, "textures/armor/employees_shirt.png");
    }

    @Override
    public Identifier getAnimationResource(EmployeeShirtArmorItem animatable) {
        return new Identifier(EndlessStoreMod.MOD_ID, "animations/employee_shirt.animation.json");
    }
}
