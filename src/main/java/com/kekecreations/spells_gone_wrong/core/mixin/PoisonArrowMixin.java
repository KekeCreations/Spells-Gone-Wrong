package com.kekecreations.spells_gone_wrong.core.mixin;

import com.kekecreations.spells_gone_wrong.core.config.SpellsGoneWrongCommonConfig;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.damage.SpellDamageSource;
import io.redspace.ironsspellbooks.entity.spells.poison_arrow.PoisonArrow;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PoisonArrow.class)
public class PoisonArrowMixin {


    @Inject(method = "onHitEntity", at = @At(value = "TAIL"))
    public void spells_gone_wrong$onHitEntity(EntityHitResult entityHitResult, CallbackInfo ci) {
        PoisonArrow poisonArrow = (PoisonArrow) (Object) this;

        if (!poisonArrow.level().isClientSide) {
            Entity entity = entityHitResult.getEntity();
            if (poisonArrow.getOwner() instanceof Player player && entity == poisonArrow.getOwner() && SpellsGoneWrongCommonConfig.POISON_ARROW_SPELL_CAN_HURT_OWNER.get()) {
                float baseAmount = poisonArrow.getDamage();
                SpellDamageSource spellDamageSource = SpellRegistry.FIREBALL_SPELL.get().getDamageSource(poisonArrow, poisonArrow.getOwner());
                player.hurt(spellDamageSource, baseAmount);
            }
        }
    }


}
