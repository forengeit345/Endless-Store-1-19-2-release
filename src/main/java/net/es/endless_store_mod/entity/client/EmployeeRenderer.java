package net.es.endless_store_mod.entity.client;

import net.es.endless_store_mod.EndlessStoreMod;
import net.es.endless_store_mod.entity.custom.EmployeeEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class EmployeeRenderer extends GeoEntityRenderer<EmployeeEntity> {
    public EmployeeRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new EmployeeModel());
    }

    @Override
    public Identifier getTextureLocation(EmployeeEntity animatable) {
        return new Identifier(EndlessStoreMod.MOD_ID, "textures/entity/employee.png");
    }

    @Override
    public void render(EmployeeEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                       VertexConsumerProvider bufferSource, int packedLight) {

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
