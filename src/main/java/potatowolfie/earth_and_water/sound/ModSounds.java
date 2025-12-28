package potatowolfie.earth_and_water.sound;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import potatowolfie.earth_and_water.EarthWater;

public class ModSounds {
    public static final SoundEvent BATTLE_AXE_DASH = registerSoundEvent("battle_axe_dash");
    public static final SoundEvent BORE_AMBIENT = registerSoundEvent("bore_ambient");
    public static final SoundEvent BORE_HURT = registerSoundEvent("bore_hurt");
    public static final SoundEvent BORE_DEATH = registerSoundEvent("bore_death");
    public static final SoundEvent BRINE_AMBIENT = registerSoundEvent("brine_ambient");
    public static final SoundEvent BRINE_UNDERWATER_AMBIENT = registerSoundEvent("brine_underwater_ambient");
    public static final SoundEvent BRINE_DEATH = registerSoundEvent("brine_death");
    public static final SoundEvent BRINE_UNDERWATER_DEATH = registerSoundEvent("brine_underwater_death");

    public static final SoundEvent BLOCK_TUFF_BRICKS_BREAK = registerSoundEvent("block.tuff_bricks.break");
    public static final SoundEvent BLOCK_TUFF_BRICKS_STEP = registerSoundEvent("block.tuff_bricks.step");
    public static final SoundEvent BLOCK_TUFF_BRICKS_PLACE = registerSoundEvent("block.tuff_bricks.place");
    public static final SoundEvent BLOCK_TUFF_BRICKS_HIT = registerSoundEvent("block.tuff_bricks.hit");
    public static final SoundEvent BLOCK_TUFF_BRICKS_FALL = registerSoundEvent("block.tuff_bricks.fall");

    public static final SoundEvent BLOCK_TRIAL_SPAWNER_BREAK = registerSoundEvent("block.trial_spawner.break");
    public static final SoundEvent BLOCK_TRIAL_SPAWNER_STEP = registerSoundEvent("block.trial_spawner.step");
    public static final SoundEvent BLOCK_TRIAL_SPAWNER_PLACE = registerSoundEvent("block.trial_spawner.place");
    public static final SoundEvent BLOCK_TRIAL_SPAWNER_HIT = registerSoundEvent("block.trial_spawner.hit");
    public static final SoundEvent BLOCK_TRIAL_SPAWNER_FALL = registerSoundEvent("block.trial_spawner.fall");

    public static final SoundEvent TRIAL_SPAWNER_ABOUT_TO_SPAWN_ITEM = registerSoundEvent("block.trial_spawner.about_to_spawn_item");
    public static final SoundEvent TRIAL_SPAWNER_AMBIENT = registerSoundEvent("block.trial_spawner.ambient");
    public static final SoundEvent TRIAL_SPAWNER_AMBIENT_OMINOUS = registerSoundEvent("block.trial_spawner.ambient_ominous");
    public static final SoundEvent TRIAL_SPAWNER_CLOSE_SHUTTER = registerSoundEvent("block.trial_spawner.close_shutter");
    public static final SoundEvent TRIAL_SPAWNER_DETECT_PLAYER = registerSoundEvent("block.trial_spawner.detect_player");
    public static final SoundEvent TRIAL_SPAWNER_EJECT_ITEM = registerSoundEvent("block.trial_spawner.eject_item");
    public static final SoundEvent TRIAL_SPAWNER_OMINOUS_ACTIVATE = registerSoundEvent("block.trial_spawner.ominous_activate");
    public static final SoundEvent TRIAL_SPAWNER_OPEN_SHUTTER = registerSoundEvent("block.trial_spawner.open_shutter");
    public static final SoundEvent TRIAL_SPAWNER_SPAWN_MOB = registerSoundEvent("block.trial_spawner.spawn_mob");
    public static final SoundEvent TRIAL_SPAWNER_SPAWN_ITEM = registerSoundEvent("block.trial_spawner.spawn_item");
    public static final SoundEvent TRIAL_SPAWNER_SPAWN_ITEM_BEGIN = registerSoundEvent("block.trial_spawner.spawn_item_begin");

    public static final SoundEvent BLOCK_VAULT_ACTIVATE = registerSoundEvent("block.vault.activate");
    public static final SoundEvent BLOCK_VAULT_AMBIENT = registerSoundEvent("block.vault.ambient");
    public static final SoundEvent BLOCK_VAULT_BREAK = registerSoundEvent("block.vault.break");
    public static final SoundEvent BLOCK_VAULT_DEACTIVATE = registerSoundEvent("block.vault.deactivate");
    public static final SoundEvent BLOCK_VAULT_EJECT_ITEM = registerSoundEvent("block.vault.eject_item");
    public static final SoundEvent BLOCK_VAULT_INSERT_ITEM = registerSoundEvent("block.vault.insert_item");
    public static final SoundEvent BLOCK_VAULT_INSERT_ITEM_FAIL = registerSoundEvent("block.vault.insert_item_fail");
    public static final SoundEvent BLOCK_VAULT_OPEN_SHUTTER = registerSoundEvent("block.vault.open_shutter");
    public static final SoundEvent BLOCK_VAULT_PLACE = registerSoundEvent("block.vault.place");
    public static final SoundEvent BLOCK_VAULT_STEP = registerSoundEvent("block.vault.step");
    public static final SoundEvent BLOCK_VAULT_HIT = registerSoundEvent("block.vault.hit");
    public static final SoundEvent BLOCK_VAULT_FALL = registerSoundEvent("block.vault.fall");
    public static final SoundEvent BLOCK_VAULT_REJECT_REWARDED_PLAYER = registerSoundEvent("block.vault.reject_rewarded_player");

    public static final SoundEvent ENTITY_WIND_CHARGE_THROW = registerSoundEvent("entity.wind_charge.throw");

    private static SoundEvent registerSoundEvent(String name) {
        return Registry.register(Registries.SOUND_EVENT, Identifier.of(EarthWater.MOD_ID, name),
                SoundEvent.of(Identifier.of(EarthWater.MOD_ID, name)));
    }

    public static void registerSounds() {
        EarthWater.LOGGER.info("Registering Mod Sounds for " + EarthWater.MOD_ID);
    }
}