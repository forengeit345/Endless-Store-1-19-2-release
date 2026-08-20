package net.es.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.es.EndlessStoreMod;
import net.es.entity.custom.JackEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class JackRenderer extends GeoEntityRenderer<JackEntity> {
    public JackRenderer(EntityRendererProvider.Context context) {
        super(context, new JackModel());
        this.shadowRadius = 0.4f;
    }

    @Override
    public ResourceLocation getTextureLocation(JackEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "textures/entity/jack.png");
    }

    @Override
    public void render(JackEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
