package net.es.entity.client.armor;

import net.es.item.custom.HandmadeIronArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class HandmadeIronArmorRenderer extends GeoArmorRenderer<HandmadeIronArmorItem> {
    public HandmadeIronArmorRenderer() {
        super(new HandmadeIronArmorModel());
    }
}