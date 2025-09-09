package com.kekecreations.spells_gone_wrong.core.mixin;

import com.kekecreations.spells_gone_wrong.core.config.SpellsGoneWrongCommonConfig;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.damage.SpellDamageSource;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
import io.redspace.ironsspellbooks.entity.spells.blood_needle.BloodNeedle;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BloodNeedle.class)
public class BloodNeedleMixin {

    @Inject(method = "onHitEntity", at = @At(value = "HEAD"))
    protected void spells_gone_wrong$onHitEntity(EntityHitResult entityHitResult, CallbackInfo ci) {
        AbstractMagicProjectile abstractMagicProjectile = (AbstractMagicProjectile) (Object) this;
        if (entityHitResult.getEntity() instanceof Player player && entityHitResult.getEntity() == abstractMagicProjectile.getOwner() && SpellsGoneWrongCommonConfig.BLOOD_NEEDLES_SPELL_AND_ACUPUNCTURE_SPELL_CAN_HURT_OWNER.get()) {
            float baseAmount = abstractMagicProjectile.getDamage();
            SpellDamageSource spellDamageSource = SpellRegistry.BLOOD_NEEDLES_SPELL.get().getDamageSource(abstractMagicProjectile, abstractMagicProjectile.getOwner());
            player.hurt(spellDamageSource, baseAmount);
            player.invulnerableTime = 0;
        }
    }


}
