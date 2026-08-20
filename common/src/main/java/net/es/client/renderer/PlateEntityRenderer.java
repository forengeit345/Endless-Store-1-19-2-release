package net.es.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.es.entity.custom.PlateEntity;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class PlateEntityRenderer extends EntityRenderer<PlateEntity> {
    private final ItemRenderer itemRenderer;

    public PlateEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(PlateEntity entity, float entityYaw, float partialTicks, PoseStack matrices,
                       MultiBufferSource buffer, int packedLight) {
        ItemStack stack = entity.getItem();
        if (stack.isEmpty()) return;

        matrices.pushPose();

        matrices.mulPose(Axis.XP.rotationDegrees(90.0F));

        float rotation = entity.getRotation() * 45.0F;
        matrices.mulPose(Axis.ZP.rotationDegrees(rotation));

        // Масштаб: для блоков чуть меньше, для остальных предметов — как у рамки
        float scale = stack.getItem() instanceof BlockItem ? 0.4F : 0.225F;
        matrices.scale(scale, scale, scale);

        // Яркий фиксированный свет (полный небесный и блоковый свет)
        int light = LightTexture.pack(15, 15);
        itemRenderer.renderStatic(
                stack,
                ItemDisplayContext.FIXED,
                light,
                OverlayTexture.NO_OVERLAY,
                matrices,
                buffer,
                entity.level(),
                entity.getId()
        );

        matrices.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(PlateEntity entity) {
        return null; // не используется, т.к. рисуем только предмет
    }
}