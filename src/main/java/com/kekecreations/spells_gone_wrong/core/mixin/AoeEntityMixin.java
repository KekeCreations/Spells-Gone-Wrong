package com.kekecreations.spells_gone_wrong.core.mixin;

import com.kekecreations.spells_gone_wrong.core.config.SpellsGoneWrongCommonConfig;
import io.redspace.ironsspellbooks.entity.spells.AoeEntity;
import io.redspace.ironsspellbooks.entity.spells.EarthquakeAoe;
import io.redspace.ironsspellbooks.entity.spells.magma_ball.FireField;
import io.redspace.ironsspellbooks.entity.spells.poison_cloud.PoisonCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AoeEntity.class)
public class AoeEntityMixin {

    @Shadow
    protected Vec3 getInflation() {
        return Vec3.ZERO;
    }

    @Unique
    protected boolean spells_Gone_Wrong$canHitEntity(Entity pTarget) {
        AoeEntity aoeEntity = (AoeEntity) (Object) this;
        if (!pTarget.canBeHitByProjectile()) {
            return false;
        } else {
            Entity entity = aoeEntity.getOwner();
            return entity == null || entity == pTarget || !entity.isPassengerOfSameVehicle(pTarget);
        }
    }

    @Inject(method = "canHitEntity", at = @At(value = "RETURN"), cancellable = true)
    void canHitEntity(Entity pTarget, CallbackInfoReturnable<Boolean> cir) {
        AoeEntity aoeEntity = (AoeEntity) (Object) this;
        //Poison Cloud from Poison Arrow Spell
        if (aoeEntity instanceof PoisonCloud && aoeEntity.getOwner() instanceof Player && SpellsGoneWrongCommonConfig.POISON_ARROW_SPELL_CAN_HURT_OWNER.get()) {
            cir.setReturnValue(spells_Gone_Wrong$canHitEntity(pTarget));
        }
        //Earthquake Spell
        if (aoeEntity instanceof EarthquakeAoe && aoeEntity.getOwner() instanceof Player && SpellsGoneWrongCommonConfig.EARTHQUAKE_SPELL_CAN_HURT_OWNER.get()) {
            cir.setReturnValue(spells_Gone_Wrong$canHitEntity(pTarget));
        }
        //Fire Field from Magma Bomb Spell
        if (aoeEntity instanceof FireField && aoeEntity.getOwner() instanceof Player && SpellsGoneWrongCommonConfig.MAGMA_BOMB_SPELL_CAN_HURT_OWNER.get()) {
            cir.setReturnValue(spells_Gone_Wrong$canHitEntity(pTarget));
        }
    }

    /*
    @Inject(method = "tick", at = @At(value = "INVOKE", target = "net/minecraft/world/entity/projectile/Projectile.tick ()V"))
    public void spells_gone_wrong_tick(CallbackInfo ci) {
        AoeEntity aoeEntity = (AoeEntity) (Object) this;
        //Poison Cloud from Poison Arrow Spell
        if (aoeEntity instanceof PoisonCloud && aoeEntity.getOwner() instanceof Player && SpellsGoneWrongCommonConfig.POISON_ARROW_SPELL_CAN_HURT_OWNER.get()) {
            aoeEntity.setOwner(aoeEntity);
        }
        //Earthquake Spell
        if (aoeEntity instanceof EarthquakeAoe && aoeEntity.getOwner() instanceof Player && SpellsGoneWrongCommonConfig.EARTHQUAKE_SPELL_CAN_HURT_OWNER.get()) {
            aoeEntity.setOwner(aoeEntity);
        }
        //Fire Field from Magma Bomb Spell
        if (aoeEntity instanceof FireField && aoeEntity.getOwner() instanceof Player && SpellsGoneWrongCommonConfig.MAGMA_BOMB_SPELL_CAN_HURT_OWNER.get()) {
            aoeEntity.setOwner(aoeEntity);
        }
    }

     */
}
