package potatowolfie.earth_and_water.entity.client.spiked_shield;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ModelTransformationMode;

@Environment(EnvType.CLIENT)
public class SpikedShieldItemRenderer implements BuiltinItemRendererRegistry.DynamicItemRenderer {
    private final SpikedShieldRenderer renderer;

    public SpikedShieldItemRenderer(SpikedShieldRenderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void render(ItemStack itemStack, ModelTransformationMode var2, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i, int i1) {
        this.renderer.render(itemStack, var2, matrixStack, vertexConsumerProvider, i, i1);
    }
}