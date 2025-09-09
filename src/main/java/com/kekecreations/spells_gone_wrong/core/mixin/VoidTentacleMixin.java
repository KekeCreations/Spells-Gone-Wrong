package com.kekecreations.spells_gone_wrong.core.mixin;

import com.kekecreations.spells_gone_wrong.core.config.SpellsGoneWrongCommonConfig;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.damage.SpellDamageSource;
import io.redspace.ironsspellbooks.entity.spells.void_tentacle.VoidTentacle;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VoidTentacle.class)
public class VoidTentacleMixin {

    @Shadow(remap = false)
    private float damage;

    @Inject(method = "dealDamage", at = @At(value = "HEAD"), cancellable = true, remap = false)
    public void spells_gone_wrong$dealDamage(LivingEntity target, CallbackInfoReturnable<Boolean> cir) {
        VoidTentacle voidTentacle = (VoidTentacle) (Object) this;
        if (target == voidTentacle.getOwner() && voidTentacle.getOwner() instanceof Player player && SpellsGoneWrongCommonConfig.SCULK_TENTACLES_SPELL_CAN_HURT_OWNER.get()) {
            target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 100));
            float baseAmount = this.damage;
            SpellDamageSource spellDamageSource = SpellRegistry.SCULK_TENTACLES_SPELL.get().getDamageSource(voidTentacle, voidTentacle.getOwner());
            player.hurt(spellDamageSource, baseAmount);
            cir.setReturnValue(true);
        }
    }
}
