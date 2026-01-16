package net.es.endless_store_mod.entity.client.armor;

import net.es.endless_store_mod.item.custom.HandmadeArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class HandmadeArmorRenderer extends GeoArmorRenderer<HandmadeArmorItem> {
    public HandmadeArmorRenderer() {
        super(new HandmadeArmorModel());
    }
}