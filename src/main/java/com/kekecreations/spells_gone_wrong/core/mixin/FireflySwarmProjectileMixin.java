package com.kekecreations.spells_gone_wrong.core.mixin;

import com.kekecreations.spells_gone_wrong.core.config.SpellsGoneWrongCommonConfigs;
import io.redspace.ironsspellbooks.entity.spells.firefly_swarm.FireflySwarmProjectile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FireflySwarmProjectile.class)
public class FireflySwarmProjectileMixin {

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "net/minecraft/world/entity/PathfinderMob.tick ()V"))
    public void spells_gone_wrong_tick(CallbackInfo ci) {
        FireflySwarmProjectile fireflySwarmProjectile = FireflySwarmProjectile.class.cast(this);
        if (!(fireflySwarmProjectile.getOwner() instanceof FireflySwarmProjectile) && SpellsGoneWrongCommonConfigs.FIREFLY_SWARM_SPELL_CAN_HURT_OWNER.get()) {
            fireflySwarmProjectile.setOwner(fireflySwarmProjectile);
        }
    }
}
