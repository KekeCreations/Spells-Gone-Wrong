package com.kekecreations.spells_gone_wrong.core.mixin;

import com.kekecreations.spells_gone_wrong.core.config.SpellsGoneWrongCommonConfig;
import io.redspace.ironsspellbooks.entity.spells.void_tentacle.VoidTentacle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VoidTentacle.class)
public class VoidTentacleMixin {

    @Inject(method = "tick", at = @At(value = "TAIL"))
    public void spells_gone_wrong_tick(CallbackInfo ci) {
        VoidTentacle voidTentacle = (VoidTentacle) (Object) this;
        if (!(voidTentacle.getOwner() instanceof VoidTentacle) && SpellsGoneWrongCommonConfig.SCULK_TENTACLES_SPELL_CAN_HURT_OWNER.get()) {
            voidTentacle.setOwner(voidTentacle);
        }
    }
}
