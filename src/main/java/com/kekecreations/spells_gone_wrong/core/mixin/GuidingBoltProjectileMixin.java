package com.kekecreations.spells_gone_wrong.core.mixin;

import com.kekecreations.spells_gone_wrong.core.config.SpellsGoneWrongCommonConfigs;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.guiding_bolt.GuidingBoltProjectile;
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
    private void spells_gone_wrong_onHitEntity(EntityHitResult entityHitResult, CallbackInfo ci) {
        if (SpellsGoneWrongCommonConfigs.HOLY_SPELLS_DO_EXTRA_DAMAGE_TO_UNDEAD_MOBS.get()) {
            GuidingBoltProjectile $this = (GuidingBoltProjectile) (Object) this;
            if (entityHitResult.getEntity() instanceof LivingEntity livingEntity) {
                if (livingEntity.getMobType() == MobType.UNDEAD) {
                    DamageSources.applyDamage(entityHitResult.getEntity(), $this.getDamage(), livingEntity.damageSources().onFire());
                }
            }
        }
    }

}
