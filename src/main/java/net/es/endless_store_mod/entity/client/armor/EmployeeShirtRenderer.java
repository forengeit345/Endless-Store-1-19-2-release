package net.es.endless_store_mod.entity.client.armor;

import net.es.endless_store_mod.item.custom.EmployeeShirtArmorItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class EmployeeShirtRenderer extends GeoArmorRenderer<EmployeeShirtArmorItem> {
    public EmployeeShirtRenderer() {
        super(new EmployeeShirtModel());

        this.headBone = "armorHead";
        this.bodyBone = "armorBody";
        this.rightArmBone = "armorRightArm";
        this.leftArmBone = "armorLeftArm";
        this.rightLegBone = "armorLeftLeg";
        this.leftLegBone = "armorRightLeg";
        this.rightBootBone = "armorLeftBoot";
        this.leftBootBone = "armorRightBoot";
    }
}
