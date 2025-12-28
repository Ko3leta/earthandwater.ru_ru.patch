package potatowolfie.earth_and_water.block.entity.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.MobSpawnerLogic;
import potatowolfie.earth_and_water.block.entity.custom.ReinforcedSpawnerBlockEntity;

@Environment(EnvType.CLIENT)
public class ReinforcedSpawnerBlockEntityRenderer implements BlockEntityRenderer<ReinforcedSpawnerBlockEntity> {
    private final EntityRenderDispatcher entityRenderDispatcher;

    public ReinforcedSpawnerBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        this.entityRenderDispatcher = ctx.getEntityRenderDispatcher();
    }

    @Override
    public void render(ReinforcedSpawnerBlockEntity blockEntity, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();
        matrices.translate(0.5F, 0.0F, 0.5F);

        MobSpawnerLogic spawnerLogic = blockEntity.getLogic();

        if (blockEntity.getWorld() == null) {
            matrices.pop();
            return;
        }

        Entity entity = spawnerLogic.getRenderedEntity(blockEntity.getWorld(), blockEntity.getWorld().getRandom(), blockEntity.getPos());

        if (entity != null) {
            float scale = 0.53125F;
            float maxDimension = Math.max(entity.getWidth(), entity.getHeight());
            if (maxDimension > 1.0) {
                scale /= maxDimension;
            }

            matrices.translate(0.0F, 0.4F, 0.0F);
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(
                    (float)MathHelper.lerp((double)tickDelta, spawnerLogic.getLastRotation(), spawnerLogic.getRotation()) * 10.0F
            ));
            matrices.translate(0.0F, -0.2F, 0.0F);
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-30.0F));
            matrices.scale(scale, scale, scale);

            this.entityRenderDispatcher.render(entity, 0.0, 0.0, 0.0, 0.0F, tickDelta, matrices, vertexConsumers, light);
        }

        matrices.pop();
    }
}