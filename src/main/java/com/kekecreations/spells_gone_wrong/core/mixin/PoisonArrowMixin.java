package com.kekecreations.spells_gone_wrong.core.mixin;

import com.kekecreations.spells_gone_wrong.core.config.SpellsGoneWrongCommonConfigs;
import io.redspace.ironsspellbooks.entity.spells.poison_arrow.PoisonArrow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PoisonArrow.class)
public class PoisonArrowMixin {

    @Inject(method = "tick", at = @At(value = "TAIL"))
    public void spells_gone_wrong_tick(CallbackInfo ci) {
        PoisonArrow poisonArrow = PoisonArrow.class.cast(this);
        if (!(poisonArrow.getOwner() instanceof PoisonArrow) && SpellsGoneWrongCommonConfigs.POISON_ARROW_SPELL_CAN_HURT_OWNER.get()) {
            poisonArrow.setOwner(poisonArrow);
        }
    }
}
