package net.es.endless_store_mod.entity.client;

import net.es.endless_store_mod.EndlessStoreMod;
import net.es.endless_store_mod.entity.custom.JackEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class JackRenderer extends GeoEntityRenderer<JackEntity> {
    public JackRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new JackModel());
        this.shadowRadius = 0.4f;
    }

    @Override
    public Identifier getTextureResource(JackEntity istance) {
        return new Identifier(EndlessStoreMod.MOD_ID, "textures/entity/jack.png");
    }

    @Override
    public RenderLayer getRenderType(JackEntity animatable, float partialTick, MatrixStack poseStack, VertexConsumerProvider bufferSource, VertexConsumer buffer, int packedLight, Identifier texture) {
        return super.getRenderType(animatable, partialTick, poseStack, bufferSource, buffer, packedLight, texture);
    }
}
