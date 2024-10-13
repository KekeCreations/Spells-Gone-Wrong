package com.kekecreations.spells_gone_wrong.core.mixin;

import com.kekecreations.spells_gone_wrong.core.config.SpellsGoneWrongCommonConfig;
import io.redspace.ironsspellbooks.entity.spells.wall_of_fire.WallOfFireEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WallOfFireEntity.class)
public class WallOfFireEntityMixin {

    @Inject(method = "tick", at = @At(value = "TAIL"))
    public void spells_gone_wrong_tick(CallbackInfo ci) {
        WallOfFireEntity wallOfFire = (WallOfFireEntity) (Object) this;
        if (!(wallOfFire.getOwner() instanceof WallOfFireEntity) && SpellsGoneWrongCommonConfig.WALL_OF_FIRE_SPELL_CAN_HURT_OWNER.get()) {
            wallOfFire.setOwner(wallOfFire);
        }
    }
}
