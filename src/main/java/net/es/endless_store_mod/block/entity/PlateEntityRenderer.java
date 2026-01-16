package net.es.endless_store_mod.block.entity;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RotationAxis;

public class PlateEntityRenderer implements BlockEntityRenderer<PlateEntity> {
    private final ItemRenderer itemRenderer;

    public PlateEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        this.itemRenderer = ctx.getItemRenderer();
    }

    @Override
    public void render(PlateEntity entity, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light, int overlay) {

        ItemStack stack = entity.getHeldItem();
        if (stack.isEmpty()) return;

        matrices.push();

        // Позиционирование предмета в центре тарелки
        matrices.translate(0.5, 0.03125, 0.5); // 1/16 от высоты блока

        // Поворачиваем предмет, чтобы он лежал плашмя (параллельно тарелке)
        // Сначала поворачиваем на 90 градусов вокруг оси X
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90f));

        // Затем применяем вращение по оси Y (8 направлений как у рамки)
        float rotation = entity.getRotation() * 45f; // 45 градусов на каждый шаг
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(rotation));

        // Масштабируем предмет
        float scale = getScaleForItem(stack);
        matrices.scale(scale, scale, scale);

        // Небольшое покачивание для оживления (опционально)
        if (entity.getWorld() != null) {
            float time = (entity.getWorld().getTime() + tickDelta) * 0.5f;
            float bounce = (float) Math.sin(time * 0.05f) * 0.01f;
            matrices.translate(0, bounce, 0);
        }

        // Рендеринг предмета
        itemRenderer.renderItem(
                stack,
                ModelTransformationMode.FIXED,
                light,
                OverlayTexture.DEFAULT_UV,
                matrices,
                vertexConsumers,
                entity.getWorld(),
                0
        );

        matrices.pop();
    }

    private float getScaleForItem(ItemStack stack) {
        // Разный масштаб для разных типов предметов
        if (stack.getItem() instanceof BlockItem) {
            return 0.3f; // Блоки меньше
        }
        return 0.4f; // Стандартный масштаб
    }
}
