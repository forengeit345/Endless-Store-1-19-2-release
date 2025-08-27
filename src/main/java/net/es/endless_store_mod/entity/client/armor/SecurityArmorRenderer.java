package net.es.endless_store_mod.entity.client.armor;

import net.es.endless_store_mod.item.custom.SecurityArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class SecurityArmorRenderer extends GeoArmorRenderer<SecurityArmorItem> {
    public SecurityArmorRenderer() {
        super(new SecurityArmorModel());
    }
}