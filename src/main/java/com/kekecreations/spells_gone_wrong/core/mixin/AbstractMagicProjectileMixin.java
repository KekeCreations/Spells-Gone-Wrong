package com.kekecreations.spells_gone_wrong.core.mixin;

import com.kekecreations.spells_gone_wrong.core.config.SpellsGoneWrongCommonConfig;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
import io.redspace.ironsspellbooks.entity.spells.blood_needle.BloodNeedle;
import io.redspace.ironsspellbooks.entity.spells.comet.Comet;
import io.redspace.ironsspellbooks.entity.spells.lightning_lance.LightningLanceProjectile;
import io.redspace.ironsspellbooks.entity.spells.magma_ball.FireBomb;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractMagicProjectile.class)
public class AbstractMagicProjectileMixin {

    @Inject(method = "tick", at = @At(value = "HEAD"))
    public void spells_gone_wrong_tick(CallbackInfo ci) {
        AbstractMagicProjectile abstractMagicProjectile = (AbstractMagicProjectile) (Object) this;
        //Lightning Lance Spell
        if (abstractMagicProjectile instanceof LightningLanceProjectile && !(abstractMagicProjectile.getOwner() instanceof LightningLanceProjectile) && SpellsGoneWrongCommonConfig.LIGHTNING_LANCE_SPELL_CAN_HURT_OWNER.get()) {
            abstractMagicProjectile.setOwner(abstractMagicProjectile);
        }
        //Blood Needles Spell
        if (abstractMagicProjectile instanceof BloodNeedle && !(abstractMagicProjectile.getOwner() instanceof BloodNeedle) && SpellsGoneWrongCommonConfig.BLOOD_NEEDLES_SPELL_AND_ACUPUNCTURE_SPELL_CAN_HURT_OWNER.get()) {
            abstractMagicProjectile.setOwner(abstractMagicProjectile);
        }
        //Magma Bomb Spell
        if (abstractMagicProjectile instanceof FireBomb && !(abstractMagicProjectile.getOwner() instanceof FireBomb) && SpellsGoneWrongCommonConfig.MAGMA_BOMB_SPELL_CAN_HURT_OWNER.get()) {
            abstractMagicProjectile.setOwner(abstractMagicProjectile);
        }
        //StarFall Spell
        if (abstractMagicProjectile instanceof Comet && !(abstractMagicProjectile.getOwner() instanceof Comet) && SpellsGoneWrongCommonConfig.STARFALL_SPELL_CAN_HURT_OWNER.get()) {
            abstractMagicProjectile.setOwner(abstractMagicProjectile);
        }
    }
}
