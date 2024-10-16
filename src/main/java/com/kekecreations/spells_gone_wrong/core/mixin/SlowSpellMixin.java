package com.kekecreations.spells_gone_wrong.core.mixin;

import com.kekecreations.spells_gone_wrong.core.config.SpellsGoneWrongCommonConfig;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import io.redspace.ironsspellbooks.spells.evocation.SlowSpell;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.concurrent.atomic.AtomicInteger;

@Mixin(value = SlowSpell.class, remap = false)
public class SlowSpellMixin {

    @Shadow
    public int getAmplifier(int spellLevel, LivingEntity caster) {
        return 1;
    }

    @Shadow
    public int getDuration(int spellLevel, LivingEntity caster) {
        return 1;
    }

    @Inject(method = "lambda$onCast$0", at = @At(value = "INVOKE", target = "java/util/concurrent/atomic/AtomicInteger.get ()I"))
    public void spells_gone_wrong_onCast(AtomicInteger targets, LivingEntity entity, LivingEntity targetEntity, float radius, int spellLevel, LivingEntity victim, CallbackInfo ci) {
        if (SpellsGoneWrongCommonConfig.SLOW_SPELL_CAN_HURT_OWNER.get()) {
            if (entity.distanceToSqr(targetEntity) < (double)(radius * radius) && entity instanceof Player player && !player.isCreative() && !player.isSpectator()) {
                entity.addEffect(new MobEffectInstance(MobEffectRegistry.SLOWED, this.getDuration(spellLevel, entity), this.getAmplifier(spellLevel, entity)));
                targets.incrementAndGet();
            }
        }
    }
}
