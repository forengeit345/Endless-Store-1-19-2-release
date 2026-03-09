package net.es.endless_store_mod.entity.client;

import net.es.endless_store_mod.EndlessStoreMod;
import net.es.endless_store_mod.entity.custom.SecurityEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import com.mojang.blaze3d.vertex.PoseStack;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SecurityRenderer extends GeoEntityRenderer<SecurityEntity> {
    public SecurityRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SecurityModel());
        this.shadowRadius = 0.4f;
    }

    @Override
    public ResourceLocation getTextureLocation(SecurityEntity animatable) {
        return new ResourceLocation(EndlessStoreMod.MOD_ID, "textures/entity/security.png");
    }

    @Override
    public void render(SecurityEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
