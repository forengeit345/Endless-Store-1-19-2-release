package net.es.endless_store_mod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.es.endless_store_mod.EndlessStoreMod;
import net.es.endless_store_mod.entity.custom.EmployeeEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class EmployeeRenderer extends GeoEntityRenderer<EmployeeEntity> {
    public EmployeeRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new EmployeeModel());
    }

    @Override
    public ResourceLocation getTextureLocation(EmployeeEntity animatable) {
        return new ResourceLocation(EndlessStoreMod.MOD_ID, "textures/entity/employee.png");
    }

    @Override
    public void render(EmployeeEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
