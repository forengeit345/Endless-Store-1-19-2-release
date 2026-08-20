package net.es.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.es.EndlessStoreMod;
import net.es.entity.custom.EmployeeEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class EmployeeRenderer extends GeoEntityRenderer<EmployeeEntity> {
    public EmployeeRenderer(EntityRendererProvider.Context context) {
        super(context, new EmployeeModel());
        this.shadowRadius = 0.4f;
    }

    @Override
    public ResourceLocation getTextureLocation(EmployeeEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "textures/entity/employee.png");
    }

    @Override
    public void render(EmployeeEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}