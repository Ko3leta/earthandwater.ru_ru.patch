package potatowolfie.earth_and_water.sound;

import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;

public class ModBlockSoundGroup {

    public static final BlockSoundGroup TUFF_BRICKS = new BlockSoundGroup(
            1.0F,
            1.0F,
            ModSounds.BLOCK_TUFF_BRICKS_BREAK,
            ModSounds.BLOCK_TUFF_BRICKS_STEP,
            ModSounds.BLOCK_TUFF_BRICKS_PLACE,
            ModSounds.BLOCK_TUFF_BRICKS_HIT,
            ModSounds.BLOCK_TUFF_BRICKS_FALL
    );

    public static final BlockSoundGroup TRIAL_SPAWNER = new BlockSoundGroup(
            1.0F,
            1.0F,
            ModSounds.BLOCK_TRIAL_SPAWNER_BREAK,
            ModSounds.BLOCK_TRIAL_SPAWNER_STEP,
            ModSounds.BLOCK_TRIAL_SPAWNER_PLACE,
            ModSounds.BLOCK_TRIAL_SPAWNER_HIT,
            ModSounds.BLOCK_TRIAL_SPAWNER_FALL
    );
}