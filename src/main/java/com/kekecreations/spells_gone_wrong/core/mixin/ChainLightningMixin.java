package com.kekecreations.spells_gone_wrong.core.mixin;

import com.kekecreations.spells_gone_wrong.core.config.SpellsGoneWrongCommonConfigs;
import io.redspace.ironsspellbooks.entity.spells.ChainLightning;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChainLightning.class)
public class ChainLightningMixin {

    @Inject(method = "tick", at = @At(value = "TAIL"))
    public void spells_gone_wrong_tick(CallbackInfo ci) {
        ChainLightning chainLightning = ChainLightning.class.cast(this);
        if (!(chainLightning.getOwner() instanceof ChainLightning) && SpellsGoneWrongCommonConfigs.CHAIN_LIGHTNING_SPELL_CAN_HURT_OWNER.get()) {
            chainLightning.setOwner(chainLightning);
        }
    }
}
