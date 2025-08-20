package com.kekecreations.spells_gone_wrong.core.mixin;

import com.kekecreations.spells_gone_wrong.core.config.SpellsGoneWrongCommonConfig;
import io.redspace.ironsspellbooks.api.events.SpellDamageEvent;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.damage.SpellDamageSource;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
import io.redspace.ironsspellbooks.entity.spells.lightning_lance.LightningLanceProjectile;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LightningLanceProjectile.class)
public class LightningLanceProjectileMixin {


    @Inject(method = "onHitEntity", at = @At(value = "HEAD"))
    protected void onHitEntity(EntityHitResult entityHitResult, CallbackInfo ci) {
        AbstractMagicProjectile abstractMagicProjectile = (AbstractMagicProjectile) (Object) this;
        if (entityHitResult.getEntity() instanceof Player player && SpellsGoneWrongCommonConfig.LIGHTNING_LANCE_SPELL_CAN_HURT_OWNER.get()) {
            float baseAmount = abstractMagicProjectile.getDamage();
            SpellDamageSource spellDamageSource = SpellRegistry.LIGHTNING_LANCE_SPELL.get().getDamageSource(abstractMagicProjectile, abstractMagicProjectile.getOwner());
            SpellDamageEvent e = new SpellDamageEvent(player, baseAmount, spellDamageSource);

            baseAmount = e.getAmount();
            float adjustedDamage = baseAmount * DamageSources.getResist(player, SpellRegistry.LIGHTNING_LANCE_SPELL.get().getSchoolType());

            player.hurt(spellDamageSource, adjustedDamage);
        }
    }
}
