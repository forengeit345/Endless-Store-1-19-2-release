package net.es.endless_store_mod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.es.endless_store_mod.EndlessStoreMod;
import net.es.endless_store_mod.entity.custom.WatcherEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class WatcherRenderer extends GeoEntityRenderer<WatcherEntity> {
    public WatcherRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new WatcherModel());
        this.shadowRadius = 0.4f;
    }

    @Override
    public ResourceLocation getTextureLocation(WatcherEntity animatable) {
        return new ResourceLocation(EndlessStoreMod.MOD_ID, "textures/entity/watcher.png");
    }

    @Override
    public void render(WatcherEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
