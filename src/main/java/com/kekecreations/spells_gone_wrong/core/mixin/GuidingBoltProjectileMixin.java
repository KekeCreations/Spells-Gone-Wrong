package com.kekecreations.spells_gone_wrong.core.mixin;

import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.guiding_bolt.GuidingBoltProjectile;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.phys.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = GuidingBoltProjectile.class)
public class GuidingBoltProjectileMixin {
    @Inject(method = "onHitEntity(Lnet/minecraft/world/phys/EntityHitResult;)V", at = @At(value = "HEAD"))
    private void cheese(EntityHitResult entityHitResult, CallbackInfo ci) {
        GuidingBoltProjectile $this = (GuidingBoltProjectile) (Object) this;
        if (entityHitResult.getEntity() instanceof LivingEntity livingEntity) {
            if (livingEntity.getMobType() == MobType.UNDEAD) {
                if (DamageSources.applyDamage(entityHitResult.getEntity(), ($this.getDamage() * 2.5F), ((AbstractSpell) SpellRegistry.GUIDING_BOLT_SPELL.get()).getDamageSource($this, $this.getOwner()))) {
                    livingEntity.addEffect(new MobEffectInstance((MobEffect) MobEffectRegistry.GUIDING_BOLT.get(), 500));
                    $this.discard();
                }
            }
        }
    }

}
