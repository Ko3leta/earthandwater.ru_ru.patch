package potatowolfie.earth_and_water.entity.bore;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.SnifferAnimations;
import net.minecraft.client.render.entity.model.EntityModel;
import potatowolfie.earth_and_water.animation.BoreAnimations;

// Made with Blockbench 4.12.4

@Environment(EnvType.CLIENT)
public class BoreEntityModel extends EntityModel<BoreEntityRenderState> {
	private final ModelPart head;
	private final ModelPart eyes;
	private final ModelPart rods_top;
	private final ModelPart rods_bottom;

	public BoreEntityModel(ModelPart modelPart) {
		super(modelPart);
		this.head = modelPart.getChild("head");
		this.eyes = this.head.getChild("eyes");
		this.rods_top = modelPart.getChild("rods_top");
		this.rods_bottom = modelPart.getChild("rods_bottom");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();

		ModelPartData head = modelPartData.addChild("head",
				ModelPartBuilder.create().uv(0, 0)
						.cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)),
				ModelTransform.pivot(0.0F, 4.0F, 0.0F));

		head.addChild("eyes",
				ModelPartBuilder.create().uv(0, 0)
						.cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)),
				ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r1 = head.addChild("cube_r1",
				ModelPartBuilder.create().uv(0, 9).cuboid(0.0F, -6.0F, -3.5F, 0.0F, 6.0F, 7.0F, new Dilation(0.0F)),
				ModelTransform.of(0.0F, -8.0F, 0.0F, 0.0F, -2.3562F, 0.0F));

		ModelPartData cube_r2 = head.addChild("cube_r2",
				ModelPartBuilder.create().uv(0, 9).cuboid(0.0F, -6.0F, -3.5F, 0.0F, 6.0F, 7.0F, new Dilation(0.0F)),
				ModelTransform.of(0.0F, -8.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		ModelPartData rods_top = modelPartData.addChild("rods_top",
				ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 7.0F, 0.0F));

		rods_top.addChild("rod1",
				ModelPartBuilder.create().uv(14, 16)
						.cuboid(-1.0F, -4.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)),
				ModelTransform.pivot(-5.0F, 0.0F, -5.0F));

		rods_top.addChild("rod2",
				ModelPartBuilder.create().uv(14, 16)
						.cuboid(-1.0F, -4.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)),
				ModelTransform.pivot(5.0F, 0.0F, -5.0F));

		rods_top.addChild("rod3",
				ModelPartBuilder.create().uv(14, 16)
						.cuboid(-1.0F, -4.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)),
				ModelTransform.pivot(5.0F, 0.0F, 5.0F));

		rods_top.addChild("rod4",
				ModelPartBuilder.create().uv(14, 16)
						.cuboid(-1.0F, -4.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)),
				ModelTransform.pivot(-5.0F, 0.0F, 5.0F));

		ModelPartData rods_bottom = modelPartData.addChild("rods_bottom",
				ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 11.0F, 0.0F));

		rods_bottom.addChild("rod_bottom1",
				ModelPartBuilder.create().uv(0, 0)
						.cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F))
						.uv(0, 22).mirrored()
						.cuboid(-2.0F, 6.0F, 0.0F, 3.0F, 4.0F, 0.0F, new Dilation(0.0F)).mirrored(false),
				ModelTransform.of(2.5F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1309F));

		rods_bottom.addChild("rod_bottom2",
				ModelPartBuilder.create().uv(0, 0)
						.cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F))
						.uv(0, 22)
						.cuboid(-1.0F, 6.0F, 0.0F, 3.0F, 4.0F, 0.0F, new Dilation(0.0F)),
				ModelTransform.of(-2.5F, 0.0F, 0.0F, -0.0078F, -0.0231F, -0.1285F));

		return TexturedModelData.of(modelData, 32, 32);
	}

	public void setAngles(BoreEntityRenderState boreEntityRenderState) {
		super.setAngles(boreEntityRenderState);

		this.animate(boreEntityRenderState.idleAnimationState, BoreAnimations.BORE_IDLE, boreEntityRenderState.age);
		this.animate(boreEntityRenderState.shootingAnimationState, BoreAnimations.BORE_SHOOTING, boreEntityRenderState.age);
		this.animate(boreEntityRenderState.burrowingAnimationState, BoreAnimations.BORE_BURROWING, boreEntityRenderState.age);
		this.animate(boreEntityRenderState.unburrowingAnimationState, BoreAnimations.BORE_UNBURROWING, boreEntityRenderState.age);
		this.animate(boreEntityRenderState.whileburrowingAnimationState, BoreAnimations.BURROWING, boreEntityRenderState.age);
	}

	public ModelPart getHead() {
		return this.head;
	}

	public ModelPart getEyes() {
		return this.eyes;
	}

	public ModelPart getRodsTop() {
		return this.rods_top;
	}

	public ModelPart getRodsBottom() {
		return this.rods_bottom;
	}
}