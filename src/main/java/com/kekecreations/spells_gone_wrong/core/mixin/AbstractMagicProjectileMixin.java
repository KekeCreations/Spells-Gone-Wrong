package com.kekecreations.spells_gone_wrong.core.mixin;

import com.kekecreations.spells_gone_wrong.core.config.SpellsGoneWrongCommonConfigs;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
import io.redspace.ironsspellbooks.entity.spells.lightning_lance.LightningLanceProjectile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractMagicProjectile.class)
public class AbstractMagicProjectileMixin {

    @Inject(method = "tick", at = @At(value = "HEAD"))
    public void spells_gone_wrong_tick(CallbackInfo ci) {
        AbstractMagicProjectile abstractMagicProjectile = AbstractMagicProjectile.class.cast(this);
        //Lightning Lance Spell
        if (abstractMagicProjectile instanceof LightningLanceProjectile && !(abstractMagicProjectile.getOwner() instanceof LightningLanceProjectile) && SpellsGoneWrongCommonConfigs.LIGHTNING_LANCE_SPELL_CAN_HURT_OWNER.get()) {
            abstractMagicProjectile.setOwner(abstractMagicProjectile);
        }
    }
}
