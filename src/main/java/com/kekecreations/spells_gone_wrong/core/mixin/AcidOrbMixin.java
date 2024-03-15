package com.kekecreations.spells_gone_wrong.core.mixin;

import com.kekecreations.spells_gone_wrong.core.config.SpellsGoneWrongCommonConfigs;
import io.redspace.ironsspellbooks.entity.spells.acid_orb.AcidOrb;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AcidOrb.class)
public class AcidOrbMixin {

    @Inject(method = "onHit", at = @At(value = "INVOKE", target = "io/redspace/ironsspellbooks/entity/spells/acid_orb/AcidOrb.getBoundingBox ()Lnet/minecraft/world/phys/AABB;"))
    public void spells_gone_wrong_onHit(CallbackInfo ci) {
        AcidOrb acidOrb = AcidOrb.class.cast(this);
        if (!(acidOrb.getOwner() instanceof AcidOrb) && SpellsGoneWrongCommonConfigs.ACID_SPIT_SPELL_CAN_HURT_OWNER.get()) {
            acidOrb.setOwner(acidOrb);
        }
    }


}
