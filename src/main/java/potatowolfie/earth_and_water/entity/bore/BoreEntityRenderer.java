package potatowolfie.earth_and_water.entity.bore;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import potatowolfie.earth_and_water.EarthWater;
import potatowolfie.earth_and_water.entity.client.ModEntityModelLayers;

@Environment(EnvType.CLIENT)
public class BoreEntityRenderer extends MobEntityRenderer<BoreEntity, BoreEntityRenderState, BoreEntityModel> {
    private static final Identifier TEXTURE = Identifier.of(EarthWater.MOD_ID, "textures/entity/bore/bore.png");
    private static final Identifier DARK_TEXTURE = Identifier.of(EarthWater.MOD_ID, "textures/entity/bore/dark_bore.png");

    public BoreEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new BoreEntityModel(context.getPart(ModEntityModelLayers.BORE)), 0.3f);
        this.addFeature(new BoreEntityEyesFeatureRenderer(this));
    }


    @Override
    public BoreEntityRenderState createRenderState() {
        return new BoreEntityRenderState();
    }

    public static BoreEntityModel updatePartVisibility(BoreEntityModel model, ModelPart... modelParts) {
        model.getHead().visible = false;
        model.getEyes().visible = false;
        model.getRodsTop().visible = false;
        model.getRodsBottom().visible = false;
        ModelPart[] var2 = modelParts;
        int var3 = modelParts.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            ModelPart modelPart = var2[var4];
            modelPart.visible = true;
        }

        return model;
    }

    @Override
    public void updateRenderState(BoreEntity entity, BoreEntityRenderState state, float tickDelta) {
        super.updateRenderState(entity, state, tickDelta);

        state.idleAnimationState.copyFrom(entity.idleAnimationState);
        state.shootingAnimationState.copyFrom(entity.shootingAnimationState);
        state.burrowingAnimationState.copyFrom(entity.burrowingAnimationState);
        state.unburrowingAnimationState.copyFrom(entity.unburrowingAnimationState);
        state.whileburrowingAnimationState.copyFrom(entity.whileburrowingAnimationState);

        state.variant = entity.getVariant();
    }

    @Override
    public Identifier getTexture(BoreEntityRenderState boreEntityRenderState) {
        return switch (boreEntityRenderState.variant) {
            case NORMAL -> TEXTURE;
            case DARK -> DARK_TEXTURE;
        };
    }
}