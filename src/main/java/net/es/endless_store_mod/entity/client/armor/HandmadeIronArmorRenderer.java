package net.es.endless_store_mod.entity.client.armor;

import net.es.endless_store_mod.item.custom.HandmadeIronArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class HandmadeIronArmorRenderer extends GeoArmorRenderer<HandmadeIronArmorItem> {
    public HandmadeIronArmorRenderer() {
        super(new HandmadeIronArmorModel());
    }
}