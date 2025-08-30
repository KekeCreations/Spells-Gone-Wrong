package com.kekecreations.spells_gone_wrong.core.mixin;

import io.redspace.ironsspellbooks.item.EldritchManuscript;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

import static com.kekecreations.spells_gone_wrong.core.config.SpellsGoneWrongCommonConfig.*;

@Mixin(EldritchManuscript.class)
public class EldritchManuscriptMixin extends Item {

    public EldritchManuscriptMixin(Properties pProperties) {
        super(pProperties);
    }

    @Override //To disable item if all the Eldritch spells are turned off
    public boolean isEnabled(@NotNull FeatureFlagSet pEnabledFeatures) {
        if (DISABLE_SCULK_TENTACLES.get() && DISABLE_SONIC_BOOM.get() && DISABLE_ELDRITCH_BLAST.get() && DISABLE_PLANAR_SIGHT.get() && DISABLE_ABYSSAL_SHROUD.get() && DISABLE_TELEKINESIS.get()) {
            return false;
        }
        return true;
    }
}
