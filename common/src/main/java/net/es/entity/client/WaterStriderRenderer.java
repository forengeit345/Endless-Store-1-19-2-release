package net.es.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.es.EndlessStoreMod;
import net.es.entity.custom.WaterStriderEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class WaterStriderRenderer extends GeoEntityRenderer<WaterStriderEntity> {
    public WaterStriderRenderer(EntityRendererProvider.Context context) {
        super(context, new WaterStriderModel());
        this.shadowRadius = 0.4f;
    }

    @Override
    public ResourceLocation getTextureLocation(WaterStriderEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "textures/entity/water_strider.png");
    }

    @Override
    public void render(WaterStriderEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
