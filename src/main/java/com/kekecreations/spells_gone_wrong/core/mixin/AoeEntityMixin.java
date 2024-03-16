package com.kekecreations.spells_gone_wrong.core.mixin;

import com.kekecreations.spells_gone_wrong.core.config.SpellsGoneWrongCommonConfigs;
import io.redspace.ironsspellbooks.entity.spells.AoeEntity;
import io.redspace.ironsspellbooks.entity.spells.EarthquakeAoe;
import io.redspace.ironsspellbooks.entity.spells.poison_cloud.PoisonCloud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AoeEntity.class)
public class AoeEntityMixin {

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "net/minecraft/world/entity/projectile/Projectile.tick ()V"))
    public void spells_gone_wrong_tick(CallbackInfo ci) {
        AoeEntity aoeEntity = AoeEntity.class.cast(this);
        //Poison Cloud from Poison Arrow Spell
        if (aoeEntity instanceof PoisonCloud && !(aoeEntity.getOwner() instanceof PoisonCloud) && SpellsGoneWrongCommonConfigs.POISON_ARROW_SPELL_CAN_HURT_OWNER.get()) {
            aoeEntity.setOwner(aoeEntity);
        }
        //Earthquake Spell
        if (aoeEntity instanceof EarthquakeAoe && !(aoeEntity.getOwner() instanceof EarthquakeAoe) && SpellsGoneWrongCommonConfigs.EARTHQUAKE_SPELL_CAN_HURT_OWNER.get()) {
            aoeEntity.setOwner(aoeEntity);
        }
    }
}
