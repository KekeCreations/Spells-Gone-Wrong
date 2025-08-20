package com.kekecreations.spells_gone_wrong.core.mixin;

import com.kekecreations.spells_gone_wrong.core.config.SpellsGoneWrongCommonConfig;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
import io.redspace.ironsspellbooks.entity.spells.AoeEntity;
import io.redspace.ironsspellbooks.entity.spells.EarthquakeAoe;
import io.redspace.ironsspellbooks.entity.spells.blood_needle.BloodNeedle;
import io.redspace.ironsspellbooks.entity.spells.comet.Comet;
import io.redspace.ironsspellbooks.entity.spells.lightning_lance.LightningLanceProjectile;
import io.redspace.ironsspellbooks.entity.spells.magma_ball.FireBomb;
import io.redspace.ironsspellbooks.entity.spells.magma_ball.FireField;
import io.redspace.ironsspellbooks.entity.spells.poison_arrow.PoisonArrow;
import io.redspace.ironsspellbooks.entity.spells.poison_cloud.PoisonCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractMagicProjectile.class)
public class AbstractMagicProjectileMixin {

    @Unique
    protected boolean spells_Gone_Wrong$canHitEntity(Entity pTarget) {
        AbstractMagicProjectile projectile = (AbstractMagicProjectile) (Object) this;
        if (!pTarget.canBeHitByProjectile()) {
            return false;
        } else {
            Entity entity = projectile.getOwner();
            return entity == null  || entity == pTarget  || !entity.isPassengerOfSameVehicle(pTarget);
        }
    }

    @Inject(method = "canHitEntity", at = @At(value = "RETURN"), cancellable = true)
    void canHitEntity(Entity pTarget, CallbackInfoReturnable<Boolean> cir) {
        AbstractMagicProjectile abstractMagicProjectile = (AbstractMagicProjectile) (Object) this;
        //Lightning Lance Spell - REPLACE BY ITS MIXIN
        if (abstractMagicProjectile instanceof LightningLanceProjectile && abstractMagicProjectile.getOwner() instanceof Player && SpellsGoneWrongCommonConfig.LIGHTNING_LANCE_SPELL_CAN_HURT_OWNER.get()) {
            cir.setReturnValue(spells_Gone_Wrong$canHitEntity(pTarget));
        }
        //Blood Needles Spell - MIXIN TODO
        if (abstractMagicProjectile instanceof BloodNeedle && abstractMagicProjectile.getOwner() instanceof Player && SpellsGoneWrongCommonConfig.BLOOD_NEEDLES_SPELL_AND_ACUPUNCTURE_SPELL_CAN_HURT_OWNER.get()) {
            cir.setReturnValue(spells_Gone_Wrong$canHitEntity(pTarget));
        }
        //Magma Bomb Spell - MIXIN TODO
        if (abstractMagicProjectile instanceof FireBomb && abstractMagicProjectile.getOwner() instanceof Player  && SpellsGoneWrongCommonConfig.MAGMA_BOMB_SPELL_CAN_HURT_OWNER.get()) {
            cir.setReturnValue(spells_Gone_Wrong$canHitEntity(pTarget));
        }
        //StarFall Spell - MIXIN TODO
        if (abstractMagicProjectile instanceof Comet && abstractMagicProjectile.getOwner() instanceof Player && SpellsGoneWrongCommonConfig.STARFALL_SPELL_CAN_HURT_OWNER.get()) {
            cir.setReturnValue(spells_Gone_Wrong$canHitEntity(pTarget));
        }
    }

    /*
    @Inject(method = "tick", at = @At(value = "HEAD"))
    public void spells_gone_wrong_tick(CallbackInfo ci) {
        AbstractMagicProjectile abstractMagicProjectile = (AbstractMagicProjectile) (Object) this;
        //Lightning Lance Spell
        if (abstractMagicProjectile instanceof LightningLanceProjectile && abstractMagicProjectile.getOwner() instanceof Player && SpellsGoneWrongCommonConfig.LIGHTNING_LANCE_SPELL_CAN_HURT_OWNER.get()) {
            abstractMagicProjectile.setOwner(abstractMagicProjectile);
        }
        //Blood Needles Spell
        if (abstractMagicProjectile instanceof BloodNeedle && abstractMagicProjectile.getOwner() instanceof Player && SpellsGoneWrongCommonConfig.BLOOD_NEEDLES_SPELL_AND_ACUPUNCTURE_SPELL_CAN_HURT_OWNER.get()) {
            abstractMagicProjectile.setOwner(abstractMagicProjectile);
        }
        //Magma Bomb Spell
        if (abstractMagicProjectile instanceof FireBomb && abstractMagicProjectile.getOwner() instanceof Player  && SpellsGoneWrongCommonConfig.MAGMA_BOMB_SPELL_CAN_HURT_OWNER.get()) {
            abstractMagicProjectile.setOwner(abstractMagicProjectile);
        }
        //StarFall Spell
        if (abstractMagicProjectile instanceof Comet && abstractMagicProjectile.getOwner() instanceof Player && SpellsGoneWrongCommonConfig.STARFALL_SPELL_CAN_HURT_OWNER.get()) {
            abstractMagicProjectile.setOwner(abstractMagicProjectile);
        }
    }

     */
}
