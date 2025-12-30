package potatowolfie.earth_and_water;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.particle.EndRodParticle;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.item.model.special.SpecialModelTypes;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;
import potatowolfie.earth_and_water.block.ModBlocks;
import potatowolfie.earth_and_water.block.entity.ModBlockEntities;
import potatowolfie.earth_and_water.block.entity.client.ReinforcedSpawnerBlockEntityRenderer;
import potatowolfie.earth_and_water.entity.ModEntities;
import potatowolfie.earth_and_water.entity.bore.BoreEntityModel;
import potatowolfie.earth_and_water.entity.bore.BoreEntityRenderer;
import potatowolfie.earth_and_water.entity.brine.BrineEntityModel;
import potatowolfie.earth_and_water.entity.brine.BrineEntityRenderer;
import potatowolfie.earth_and_water.entity.client.*;
import potatowolfie.earth_and_water.entity.client.spiked_shield.SpikedShieldEntityModel;
import potatowolfie.earth_and_water.entity.client.spiked_shield.SpikedShieldRenderer;
import potatowolfie.earth_and_water.entity.earth_charge.EarthChargeProjectileModel;
import potatowolfie.earth_and_water.entity.earth_charge.EarthChargeProjectileRenderer;
import potatowolfie.earth_and_water.entity.water_charge.WaterChargeProjectileModel;
import potatowolfie.earth_and_water.entity.water_charge.WaterChargeProjectileRenderer;
import potatowolfie.earth_and_water.particle.ReinforcedSpawnerDetectionParticle;
import potatowolfie.earth_and_water.particle.ReinforcedSpawnerDetectionParticleInner;
import potatowolfie.earth_and_water.particle.ReinforcedSpawnerOutwardParticle;

public class EarthWaterClient implements ClientModInitializer {

    public static final Identifier SPIKED_BANNER_SHIELD_TYPE =
            Identifier.of(EarthWater.MOD_ID, "spiked_banner_shield");

    public static final EntityModelLayer SPIKED_SHIELD_MODEL_LAYER = new EntityModelLayer(
            Identifier.of(EarthWater.MOD_ID, "spiked_shield"), "main"
    );

    public static final SpriteIdentifier SPIKED_SHIELD_BASE = new SpriteIdentifier(
            TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE,
            Identifier.of(EarthWater.MOD_ID, "entity/shield/spiked_shield_base")
    );

    public static final SpriteIdentifier SPIKED_SHIELD_BASE_NO_PATTERN = new SpriteIdentifier(
            TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE,
            Identifier.of(EarthWater.MOD_ID, "entity/shield/spiked_shield_base_nopattern")
    );

    public static SpikedShieldEntityModel spikedShieldModel;

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.putBlock(ModBlocks.OXYGEN_BUBBLE, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.REINFORCED_SPAWNER, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.POINTED_DARK_DRIPSTONE, BlockRenderLayer.CUTOUT);
        ParticleFactoryRegistry.getInstance().register(EarthWater.LIGHT_UP, EndRodParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(EarthWater.REINFORCED_SPAWNER_DETECTION, ReinforcedSpawnerDetectionParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(EarthWater.REINFORCED_SPAWNER_DETECTION_OUTWARD, ReinforcedSpawnerOutwardParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(EarthWater.REINFORCED_SPAWNER_DETECTION_INNER, ReinforcedSpawnerDetectionParticleInner.Factory::new);

        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.EARTH_CHARGE, EarthChargeProjectileModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.EARTH_CHARGE, EarthChargeProjectileRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.WATER_CHARGE, WaterChargeProjectileModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.WATER_CHARGE, WaterChargeProjectileRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.BORE, BoreEntityModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.BORE, BoreEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.BRINE, BrineEntityModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.BRINE, BrineEntityRenderer::new);

        BlockEntityRendererFactories.register(
                ModBlockEntities.REINFORCED_SPAWNER_BLOCK_ENTITY,
                ReinforcedSpawnerBlockEntityRenderer::new
        );

        SpecialModelTypes.ID_MAPPER.put(
                Identifier.of(EarthWater.MOD_ID, "spiked_banner_shield"),
                SpikedShieldRenderer.Unbaked.CODEC
        );

        EntityModelLayerRegistry.registerModelLayer(SPIKED_SHIELD_MODEL_LAYER, SpikedShieldEntityModel::getTexturedModelData);
    }

    public static SpikedShieldEntityModel getSpikedShieldModel() {
        if (spikedShieldModel == null) {
            spikedShieldModel = new SpikedShieldEntityModel(MinecraftClient.getInstance()
                    .getLoadedEntityModels()
                    .getModelPart(SPIKED_SHIELD_MODEL_LAYER));
        }
        return spikedShieldModel;
    }
}