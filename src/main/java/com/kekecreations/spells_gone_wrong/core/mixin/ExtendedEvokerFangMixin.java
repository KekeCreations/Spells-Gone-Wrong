package com.kekecreations.spells_gone_wrong.core.mixin;

import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.entity.spells.ExtendedEvokerFang;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ExtendedEvokerFang.class)
public class ExtendedEvokerFangMixin {

    @Inject(method = "dealDamageTo", at = @At(value = "HEAD"))
    public void spells_gone_wrong$dealDamageTo(LivingEntity pTarget, CallbackInfo ci) {
        ExtendedEvokerFang extendedEvokerFang = (ExtendedEvokerFang) (Object) this;
        LivingEntity livingentity = extendedEvokerFang.getOwner();
        if (pTarget.isAlive() && !pTarget.isInvulnerable() && pTarget == livingentity) {
            AbstractSpell spell = SpellRegistry.FANG_STRIKE_SPELL.get();
            pTarget.hurt(spell.getDamageSource(extendedEvokerFang, livingentity), extendedEvokerFang.getDamage());
        }
    }
}
