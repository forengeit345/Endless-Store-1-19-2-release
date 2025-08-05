package net.es.endless_store_mod.entity.client.armor;

import net.es.endless_store_mod.item.custom.EmployeeShirtArmorItem;
import net.es.endless_store_mod.item.custom.SecurityArmorItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class SecurityArmorRenderer extends GeoArmorRenderer<SecurityArmorItem> {
    public SecurityArmorRenderer() {
        super(new SecurityArmorModel());

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
