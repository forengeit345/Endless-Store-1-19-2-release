package net.es.endless_store_mod.entity.client;

import net.es.endless_store_mod.EndlessStoreMod;
import net.es.endless_store_mod.entity.custom.WatcherEntity;
import net.es.endless_store_mod.entity.custom.WaterStriderEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class WaterStriderRenderer extends GeoEntityRenderer<WaterStriderEntity> {
    public WaterStriderRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new WaterStriderModel());
        this.shadowRadius = 0.4f;
    }

    @Override
    public Identifier getTextureLocation(WaterStriderEntity animatable) {
        return new Identifier(EndlessStoreMod.MOD_ID, "textures/entity/water_strider.png");
    }

    @Override
    public void render(WaterStriderEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                       VertexConsumerProvider bufferSource, int packedLight) {

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
