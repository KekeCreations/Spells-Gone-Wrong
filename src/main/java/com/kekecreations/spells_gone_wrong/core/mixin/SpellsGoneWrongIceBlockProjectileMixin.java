package com.kekecreations.spells_gone_wrong.core.mixin;

import com.kekecreations.spells_gone_wrong.core.config.SpellsGoneWrongCommonConfigs;
import io.redspace.ironsspellbooks.entity.spells.ice_block.IceBlockProjectile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(IceBlockProjectile.class)
public class SpellsGoneWrongIceBlockProjectileMixin {

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "io/redspace/ironsspellbooks/entity/spells/ice_block/IceBlockProjectile.getXRot ()F"))
    public void tick(CallbackInfo ci) {

        IceBlockProjectile iceBlockProjectile = IceBlockProjectile.class.cast(this);
        if (!(iceBlockProjectile.getOwner() instanceof IceBlockProjectile) && SpellsGoneWrongCommonConfigs.ICE_BLOCK_SPELL_CAN_HURT_OWNER.get()) {
            iceBlockProjectile.setOwner(iceBlockProjectile);
        }

    }
}
