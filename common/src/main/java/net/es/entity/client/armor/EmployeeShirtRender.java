package net.es.entity.client.armor;

import net.es.item.custom.EmployeeShirtArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class EmployeeShirtRender extends GeoArmorRenderer<EmployeeShirtArmorItem> {
    public EmployeeShirtRender() {
        super(new EmployeeShirtModel());
    }
}