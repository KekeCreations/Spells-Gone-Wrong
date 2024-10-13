package com.kekecreations.spells_gone_wrong.core.mixin;

import com.kekecreations.spells_gone_wrong.core.config.SpellsGoneWrongCommonConfig;
import io.redspace.ironsspellbooks.entity.spells.firefly_swarm.FireflySwarmProjectile;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = FireflySwarmProjectile.class)
public abstract class FireflySwarmProjectileMixin {


    @Inject(method = "tick", at = @At(value = "INVOKE", target = "net/minecraft/world/entity/PathfinderMob.tick ()V"))
    public void spells_gone_wrong_tick(CallbackInfo ci) {
        FireflySwarmProjectile fireflySwarmProjectile = (FireflySwarmProjectile) (Object) this;
        if (!(fireflySwarmProjectile.getOwner() instanceof FireflySwarmProjectile) && SpellsGoneWrongCommonConfig.FIREFLY_SWARM_SPELL_CAN_HURT_OWNER.get()) {
            fireflySwarmProjectile.setOwner(fireflySwarmProjectile);
        }
    }


    @Inject(method = "customServerAiStep", at = @At(value = "INVOKE", target = "net/minecraft/world/level/Level.getEntities (Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/phys/AABB;Ljava/util/function/Predicate;)Ljava/util/List;"))
    public void spells_gone_wrong_customServerAiStep(CallbackInfo ci) {
        FireflySwarmProjectile fireflySwarmProjectile = (FireflySwarmProjectile) (Object) this;
        if (SpellsGoneWrongCommonConfig.FIREFLY_SWARM_CAN_GIVE_GLOWING_EFFECT.get()) {
            double inflate = 2.0F - fireflySwarmProjectile.getBbWidth() * 0.5F;
            fireflySwarmProjectile.level().getEntities(fireflySwarmProjectile, fireflySwarmProjectile.getBoundingBox().inflate((double) inflate), Entity::isAlive).forEach((entity) -> {
                if (!entity.isSpectator() && entity.isAlive() && entity.isPickable() && entity instanceof LivingEntity livingEntity) {
                    if (livingEntity instanceof Player player) {
                        if (!player.isCreative() && !player.isSpectator()) {
                            int randDuration = fireflySwarmProjectile.getRandom().nextInt(40);
                            livingEntity.addEffect(new MobEffectInstance(MobEffects.GLOWING, randDuration, 0));
                        }
                    } else {
                        int randDuration = fireflySwarmProjectile.getRandom().nextInt(40);
                        livingEntity.addEffect(new MobEffectInstance(MobEffects.GLOWING, randDuration, 0));
                    }
                }
            });
        }
    }
}
