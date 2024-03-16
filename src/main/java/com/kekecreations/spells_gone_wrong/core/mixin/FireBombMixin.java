package com.kekecreations.spells_gone_wrong.core.mixin;

import com.kekecreations.spells_gone_wrong.core.config.SpellsGoneWrongCommonConfigs;
import io.redspace.ironsspellbooks.entity.spells.magma_ball.FireBomb;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FireBomb.class)
public class FireBombMixin {

    @Inject(method = "onHit", at = @At(value = "INVOKE", target = "io/redspace/ironsspellbooks/entity/spells/magma_ball/FireBomb.getExplosionRadius ()F"))
    public void spells_gone_wrong_onHit(CallbackInfo ci) {
        FireBomb fireBomb = FireBomb.class.cast(this);
        if (!(fireBomb.getOwner() instanceof FireBomb) && SpellsGoneWrongCommonConfigs.MAGMA_BOMB_SPELL_CAN_HURT_OWNER.get()) {
            fireBomb.setOwner(fireBomb);
        }
    }
}
