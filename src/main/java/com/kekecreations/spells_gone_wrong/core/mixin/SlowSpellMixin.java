package com.kekecreations.spells_gone_wrong.core.mixin;

import com.kekecreations.spells_gone_wrong.core.config.SpellsGoneWrongCommonConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.api.spells.ICastData;
import io.redspace.ironsspellbooks.capabilities.magic.TargetEntityCastData;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import io.redspace.ironsspellbooks.spells.evocation.SlowSpell;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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

    @Inject(method = "onCast(Lnet/minecraft/world/level/Level;ILnet/minecraft/world/entity/LivingEntity;Lio/redspace/ironsspellbooks/api/spells/CastSource;Lio/redspace/ironsspellbooks/api/magic/MagicData;)V", at = @At(value = "TAIL"))
    public void spells_Gone_Wrong$onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData, CallbackInfo ci) {
        if (SpellsGoneWrongCommonConfig.SLOW_SPELL_CAN_HURT_OWNER.get()) {
            ICastData data = playerMagicData.getAdditionalCastData();
            if (data instanceof TargetEntityCastData targetData) {
                LivingEntity targetEntity = targetData.getTarget((ServerLevel)world);
                if (targetEntity != null) {
                    if (entity.distanceToSqr(targetEntity) < 20D && entity instanceof Player player && !player.isCreative() && !player.isSpectator()) {
                        entity.addEffect(new MobEffectInstance(MobEffectRegistry.SLOWED.get(), this.getDuration(spellLevel, entity), this.getAmplifier(spellLevel, entity)));
                    }
                }
            }

        }
    }
}
