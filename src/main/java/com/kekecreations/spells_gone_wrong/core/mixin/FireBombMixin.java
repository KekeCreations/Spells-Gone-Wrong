package com.kekecreations.spells_gone_wrong.core.mixin;

import com.kekecreations.spells_gone_wrong.core.config.SpellsGoneWrongCommonConfig;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.damage.SpellDamageSource;
import io.redspace.ironsspellbooks.entity.spells.magma_ball.FireBomb;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FireBomb.class)
public class FireBombMixin {

    @Inject(method = "onHit", at = @At(value = "HEAD"))
    protected void spells_gone_wrong$onHit(HitResult hitResult, CallbackInfo ci) {
        FireBomb comet = (FireBomb) (Object) this;
        if (!comet.level().isClientSide) {
            float explosionRadius = comet.getExplosionRadius();
            for (Entity entity : comet.level().getEntities(comet, comet.getBoundingBox().inflate((double) explosionRadius))) {
                double distance = entity.distanceToSqr(hitResult.getLocation());
                if (distance < (double) (explosionRadius * explosionRadius) && entity == comet.getOwner() && entity instanceof Player player && SpellsGoneWrongCommonConfig.STARFALL_SPELL_CAN_HURT_OWNER.get() && Utils.hasLineOfSight(comet.level(), hitResult.getLocation(), entity.position().add((double)0.0F, (double)(entity.getEyeHeight() * 0.5F), (double)0.0F), true)) {
                    float baseAmount = comet.getDamage();
                    SpellDamageSource spellDamageSource = SpellRegistry.FIREBALL_SPELL.get().getDamageSource(comet, comet.getOwner());
                    player.hurt(spellDamageSource, baseAmount);
                }
            }
        }
    }
}
