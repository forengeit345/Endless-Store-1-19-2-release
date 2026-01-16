package net.es.endless_store_mod.entity.client;

import net.es.endless_store_mod.EndlessStoreMod;
import net.es.endless_store_mod.entity.custom.SecurityEntity;
import net.es.endless_store_mod.entity.custom.WatcherEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class WatcherRenderer extends GeoEntityRenderer<WatcherEntity> {
    public WatcherRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new WatcherModel());
        this.shadowRadius = 0.4f;
    }

    @Override
    public Identifier getTextureLocation(WatcherEntity animatable) {
        return new Identifier(EndlessStoreMod.MOD_ID, "textures/entity/watcher.png");
    }

    @Override
    public void render(WatcherEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                       VertexConsumerProvider bufferSource, int packedLight) {

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
