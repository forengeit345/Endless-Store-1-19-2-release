package net.es.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.es.EndlessStoreMod;
import net.es.entity.custom.WatcherEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class WatcherRenderer extends GeoEntityRenderer<WatcherEntity> {
    public WatcherRenderer(EntityRendererProvider.Context context) {
        super(context, new WatcherModel());
        this.shadowRadius = 0.4f;
    }

    @Override
    public ResourceLocation getTextureLocation(WatcherEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "textures/entity/watcher.png");
    }

    @Override
    public void render(WatcherEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
