package potatowolfie.earth_and_water.entity.client.spiked_shield;

import com.mojang.serialization.MapCodec;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.LoadedEntityModels;
import net.minecraft.client.render.item.model.special.SpecialModelRenderer;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BannerPatternsComponent;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DyeColor;
import org.jetbrains.annotations.Nullable;
import potatowolfie.earth_and_water.EarthWaterClient;

import java.util.List;
import java.util.Objects;

@Environment(EnvType.CLIENT)
public class SpikedShieldRenderer implements SpecialModelRenderer {
    private final SpikedShieldEntityModel model;

    public SpikedShieldRenderer(SpikedShieldEntityModel model) {
        this.model = model;
    }

    @Override
    public void render(@Nullable Object data, ItemDisplayContext displayContext, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, boolean glint) {
        if (!(data instanceof ItemStack stack)) return;

        BannerPatternsComponent bannerPatternsComponent = stack.getOrDefault(
                DataComponentTypes.BANNER_PATTERNS,
                BannerPatternsComponent.DEFAULT
        );
        DyeColor dyeColor = stack.get(DataComponentTypes.BASE_COLOR);
        boolean hasPattern = !bannerPatternsComponent.layers().isEmpty() || dyeColor != null;

        matrices.push();
        matrices.scale(1.0F, -1.0F, -1.0F);

        SpriteIdentifier spriteIdentifier = hasPattern ?
                EarthWaterClient.SPIKED_SHIELD_BASE :
                EarthWaterClient.SPIKED_SHIELD_BASE_NO_PATTERN;

        VertexConsumer vertexConsumer = spriteIdentifier.getVertexConsumer(
                vertexConsumers,
                RenderLayer::getEntityCutoutNoCull
        );

        this.model.getHandle().render(matrices, vertexConsumer, light, overlay);

        if (hasPattern) {
            this.model.getPlate().render(matrices, vertexConsumer, light, overlay);

            DyeColor baseColor = Objects.requireNonNullElse(dyeColor, DyeColor.WHITE);
            List<BannerPatternsComponent.Layer> layers = bannerPatternsComponent.layers();

            VertexConsumer baseConsumer = TexturedRenderLayers.SHIELD_BASE.getVertexConsumer(
                    vertexConsumers, RenderLayer::getEntityNoOutline);
            this.model.getPlate().render(matrices, baseConsumer, light, overlay, baseColor.getEntityColor());

            for (int i = 0; i < 17 && i < layers.size(); i++) {
                BannerPatternsComponent.Layer layer = layers.get(i);
                SpriteIdentifier patternSprite = TexturedRenderLayers.getShieldPatternTextureId(layer.pattern());
                VertexConsumer patternConsumer = patternSprite.getVertexConsumer(
                        vertexConsumers, RenderLayer::getEntityNoOutline);
                this.model.getPlate().render(matrices, patternConsumer, light, overlay, layer.color().getEntityColor());
            }

            if (glint) {
                VertexConsumer glintConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityGlint());
                this.model.getPlate().render(matrices, glintConsumer, light, overlay);
            }
        } else {
            this.model.getPlate().render(matrices, vertexConsumer, light, overlay);

            if (glint) {
                VertexConsumer glintConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityGlint());
                this.model.getPlate().render(matrices, glintConsumer, light, overlay);
            }
        }
        this.model.getSpikes().render(matrices, vertexConsumer, light, overlay);

        matrices.pop();
    }

    @Nullable
    @Override
    public Object getData(ItemStack stack) {
        return stack;
    }

    public record Unbaked() implements SpecialModelRenderer.Unbaked {
        public static final MapCodec<Unbaked> CODEC = MapCodec.unit(new Unbaked());

        @Override
        public MapCodec<? extends SpecialModelRenderer.Unbaked> getCodec() {
            return CODEC;
        }

        @Override
        public SpecialModelRenderer bake(LoadedEntityModels entityModels) {
            SpikedShieldEntityModel model = new SpikedShieldEntityModel(
                    entityModels.getModelPart(EarthWaterClient.SPIKED_SHIELD_MODEL_LAYER)
            );
            return new SpikedShieldRenderer(model);
        }
    }
}