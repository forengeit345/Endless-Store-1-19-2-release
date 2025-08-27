package net.es.endless_store_mod.entity.client.armor;

import net.es.endless_store_mod.item.custom.EmployeeShirtArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class EmployeeShirtRender extends GeoArmorRenderer<EmployeeShirtArmorItem> {
    public EmployeeShirtRender() {
        super(new EmployeeShirtModel());
    }
}
