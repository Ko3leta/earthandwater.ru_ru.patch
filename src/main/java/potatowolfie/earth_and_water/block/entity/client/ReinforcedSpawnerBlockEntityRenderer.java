package potatowolfie.earth_and_water.block.entity.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.block.entity.MobSpawnerBlockEntityRenderer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import potatowolfie.earth_and_water.block.entity.custom.ReinforcedSpawnerBlockEntity;

@Environment(EnvType.CLIENT)
public class ReinforcedSpawnerBlockEntityRenderer implements BlockEntityRenderer<ReinforcedSpawnerBlockEntity> {
    private final EntityRenderDispatcher entityRenderDispatcher;

    public ReinforcedSpawnerBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        this.entityRenderDispatcher = ctx.getEntityRenderDispatcher();
    }

    @Override
    public void render(ReinforcedSpawnerBlockEntity blockEntity, float tickProgress, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light, int overlay, Vec3d cameraPos) {
        World world = blockEntity.getWorld();
        if (world != null) {
            Entity entity = blockEntity.getDisplayEntity(world);
            if (entity != null) {
                MobSpawnerBlockEntityRenderer.render(
                        tickProgress,
                        matrices,
                        vertexConsumers,
                        light,
                        entity,
                        this.entityRenderDispatcher,
                        blockEntity.getLastRotation(),
                        blockEntity.getRotation()
                );
            }
        }
    }
}