package potatowolfie.earth_and_water.entity.client.spiked_shield;

import com.mojang.datafixers.util.Pair;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BannerBlockEntity;
import net.minecraft.block.entity.BannerPattern;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BannerBlockEntityRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import potatowolfie.earth_and_water.EarthWaterClient;

import java.util.List;

@Environment(EnvType.CLIENT)
public class SpikedShieldRenderer {

    private final SpikedShieldEntityModel model;
    private BannerBlockEntity banner;

    public SpikedShieldRenderer(SpikedShieldEntityModel model) {
        this.model = model;
    }

    public void render(ItemStack stack, ModelTransformationMode mode, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light, int overlay) {
        NbtCompound nbt = BlockItem.getBlockEntityNbt(stack);
        boolean hasPattern = nbt != null;
        boolean hasGlint = stack.hasGlint();

        matrices.push();
        matrices.scale(1.0F, -1.0F, -1.0F);

        VertexConsumer vertexConsumer = (hasPattern ?
                EarthWaterClient.SPIKED_SHIELD_BASE :
                EarthWaterClient.SPIKED_SHIELD_BASE_NO_PATTERN)
                .getVertexConsumer(vertexConsumers, RenderLayer::getEntityCutoutNoCull);

        this.model.getHandle().render(matrices, vertexConsumer, light, overlay);

        if (hasPattern) {
            this.model.getPlate().render(matrices, vertexConsumer, light, overlay);

            DyeColor baseColor = DyeColor.WHITE;
            if (nbt != null && nbt.contains("Base")) {
                baseColor = DyeColor.byId(nbt.getInt("Base"));
            }

            if (banner == null) {
                banner = new BannerBlockEntity(BlockPos.ORIGIN, Blocks.WHITE_BANNER.getDefaultState());
            }
            banner.readFrom(stack, baseColor);

            List<Pair<RegistryEntry<BannerPattern>, DyeColor>> patterns = banner.getPatterns();

            for (int i = 0; i < 17 && i < patterns.size(); i++) {
                Pair<RegistryEntry<BannerPattern>, DyeColor> pair = patterns.get(i);
                float[] colors = pair.getSecond().getColorComponents();

                pair.getFirst().getKey().map(TexturedRenderLayers::getShieldPatternTextureId
                ).ifPresent(sprite -> {
                    VertexConsumer patternConsumer = sprite.getVertexConsumer(vertexConsumers, RenderLayer::getEntityNoOutline);
                    this.model.getPlate().render(matrices, patternConsumer, light, overlay, colors[0], colors[1], colors[2], 1.0F);
                });
            }

            if (hasGlint) {
                this.model.getPlate().render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityGlint()), light, overlay);
            }
        } else {
            this.model.getPlate().render(matrices, vertexConsumer, light, overlay);
            if (hasGlint) {
                this.model.getPlate().render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityGlint()), light, overlay);
            }
        }

        // Render spikes with their own vertex consumer
        VertexConsumer spikeConsumer = EarthWaterClient.SPIKED_SHIELD_BASE
                .getVertexConsumer(vertexConsumers, RenderLayer::getEntityCutoutNoCull);
        this.model.getSpikes().render(matrices, spikeConsumer, light, overlay);

        matrices.pop();
    }
}