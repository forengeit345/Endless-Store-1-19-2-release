package net.es.entity.client.armor;

import net.es.item.custom.HandmadeArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class HandmadeArmorRenderer extends GeoArmorRenderer<HandmadeArmorItem> {
    public HandmadeArmorRenderer() {
        super(new HandmadeArmorModel());
    }
}