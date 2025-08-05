package net.es.endless_store_mod.entity.client;

import net.es.endless_store_mod.EndlessStoreMod;
import net.es.endless_store_mod.entity.custom.SecurityEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SecurityRenderer extends GeoEntityRenderer<SecurityEntity> {
    public SecurityRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new SecurityModel());
        this.shadowRadius = 0.4f;
    }

    @Override
    public Identifier getTextureResource(SecurityEntity istance) {
        return new Identifier(EndlessStoreMod.MOD_ID, "textures/entity/security.png");
    }

    @Override
    public RenderLayer getRenderType(SecurityEntity animatable, float partialTick, MatrixStack poseStack, VertexConsumerProvider bufferSource, VertexConsumer buffer, int packedLight, Identifier texture) {
        return super.getRenderType(animatable, partialTick, poseStack, bufferSource, buffer, packedLight, texture);
    }
}
