package net.es.endless_store_mod.block.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class PlateEntityRenderer implements BlockEntityRenderer<PlateEntity> {
    private final ItemRenderer itemRenderer;

    public PlateEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(PlateEntity entity, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight, int packedOverlay) {

        ItemStack stack = entity.getHeldItem();
        if (stack.isEmpty()) return;

        poseStack.pushPose();

        // Позиционирование предмета в центре тарелки
        poseStack.translate(0.5, 0.03125, 0.5); // 1/16 от высоты блока

        // Поворачиваем предмет, чтобы он лежал плашмя (параллельно тарелке)
        poseStack.mulPose(com.mojang.math.Axis.XP.rotationDegrees(90f));

        // Применяем вращение по оси Y (8 направлений как у рамки)
        float rotation = entity.getRotation() * 45f;
        poseStack.mulPose(com.mojang.math.Axis.ZP.rotationDegrees(rotation));

        // Масштабируем предмет
        float scale = getScaleForItem(stack);
        poseStack.scale(scale, scale, scale);

        // Небольшое покачивание для оживления (опционально)
        if (entity.getLevel() != null) {
            float time = (entity.getLevel().getGameTime() + partialTick) * 0.5f;
            float bounce = (float) Math.sin(time * 0.05f) * 0.01f;
            poseStack.translate(0, bounce, 0);
        }

        // Рендеринг предмета
        itemRenderer.renderStatic(
                stack,
                ItemDisplayContext.FIXED,
                packedLight,
                OverlayTexture.NO_OVERLAY,
                poseStack,
                bufferSource,
                entity.getLevel(),
                0
        );

        poseStack.popPose();
    }

    private float getScaleForItem(ItemStack stack) {
        // Разный масштаб для разных типов предметов
        if (stack.getItem() instanceof BlockItem) {
            return 0.3f; // Блоки меньше
        }
        return 0.4f; // Стандартный масштаб
    }
}