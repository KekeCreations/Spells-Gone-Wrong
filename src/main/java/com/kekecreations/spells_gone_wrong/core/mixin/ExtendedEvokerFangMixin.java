package com.kekecreations.spells_gone_wrong.core.mixin;

import com.kekecreations.spells_gone_wrong.core.config.SpellsGoneWrongCommonConfigs;
import io.redspace.ironsspellbooks.entity.spells.ExtendedEvokerFang;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ExtendedEvokerFang.class)
public class ExtendedEvokerFangMixin {

    @Inject(method = "tick", at = @At(value = "HEAD"))
    public void spells_gone_wrong_tick(CallbackInfo ci) {
        ExtendedEvokerFang extendedEvokerFang = (ExtendedEvokerFang) (Object) this;
        if ((extendedEvokerFang.getOwner() != null) && SpellsGoneWrongCommonConfigs.FANG_STRIKE_SPELL_AND_FANG_WARD_SPELL_CAN_HURT_OWNER.get()) {
            extendedEvokerFang.setOwner(null);
        }
    }
}
