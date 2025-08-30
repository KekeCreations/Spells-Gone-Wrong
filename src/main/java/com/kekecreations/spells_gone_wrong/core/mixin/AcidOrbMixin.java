package com.kekecreations.spells_gone_wrong.core.mixin;

import com.kekecreations.spells_gone_wrong.core.config.SpellsGoneWrongCommonConfig;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.entity.spells.acid_orb.AcidOrb;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AcidOrb.class)
public class AcidOrbMixin {

    @Inject(method = "onHit", at = @At(value = "HEAD"))
    public void spells_gone_wrong$onHit(HitResult hitresult, CallbackInfo ci) {
        AcidOrb acidOrb = (AcidOrb) (Object) this;
        if (acidOrb.getOwner() instanceof Player && SpellsGoneWrongCommonConfig.ACID_SPIT_SPELL_CAN_HURT_OWNER.get()) {
            acidOrb.setOwner(acidOrb);
        }

        if (!acidOrb.level().isClientSide && SpellsGoneWrongCommonConfig.ACID_SPIT_SPELL_CAN_HURT_OWNER.get()) {
            float explosionRadius = 3.5F;

            for(Entity entity : acidOrb.level().getEntities(acidOrb, acidOrb.getBoundingBox().inflate((double)explosionRadius))) {
                double distance = entity.position().distanceTo(hitresult.getLocation());
                if (distance < (double)explosionRadius && Utils.hasLineOfSight(acidOrb.level(), hitresult.getLocation(), entity.getEyePosition(), true) && entity instanceof LivingEntity) {
                    LivingEntity livingEntity = (LivingEntity)entity;
                    if (livingEntity == acidOrb.getOwner()) {
                        livingEntity.addEffect(new MobEffectInstance((MobEffect) MobEffectRegistry.REND.get(), acidOrb.getRendDuration(), acidOrb.getRendLevel()));
                    }
                }
            }

            acidOrb.discard();
        }
    }


}
