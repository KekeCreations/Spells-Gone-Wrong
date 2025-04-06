package com.kekecreations.spells_gone_wrong.core.mixin;

import com.kekecreations.spells_gone_wrong.core.config.SpellsGoneWrongCommonConfig;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.flag.FeatureElement;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.alchemy.Potion;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Potion.class)
public class PotionMixin implements FeatureElement {


    public FeatureFlagSet requiredFeatures() {
        return null;
    }

    @Override
    public boolean isEnabled(FeatureFlagSet flagSet) {
        if (SpellsGoneWrongCommonConfig.MANA_SHARD.get()) {
            Potion $this = (Potion) (Object) this;
            if ($this.getEffects().contains(new MobEffectInstance(MobEffectRegistry.INSTANT_MANA))) {
                return false;
            } else if ($this.getEffects().contains(new MobEffectInstance(MobEffectRegistry.INSTANT_MANA, 0, 1))) {
                return false;
            } else if ($this.getEffects().contains(new MobEffectInstance(MobEffectRegistry.INSTANT_MANA, 0, 2))) {
                return false;
            } else if ($this.getEffects().contains(new MobEffectInstance(MobEffectRegistry.INSTANT_MANA, 0, 3))) {
                return false;
            }
        }
        return true;
    }
}
