package net.es.entity.client.armor;

import net.es.item.custom.SecurityArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class SecurityArmorRenderer extends GeoArmorRenderer<SecurityArmorItem> {
    public SecurityArmorRenderer() {
        super(new SecurityArmorModel());
    }
}