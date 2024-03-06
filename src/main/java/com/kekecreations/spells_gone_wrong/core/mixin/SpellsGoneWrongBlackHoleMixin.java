package com.kekecreations.spells_gone_wrong.core.mixin;

import io.redspace.ironsspellbooks.entity.spells.black_hole.BlackHole;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlackHole.class)
public class SpellsGoneWrongBlackHoleMixin {


    @Inject(method = "tick", at = @At(value = "INVOKE", target = "io/redspace/ironsspellbooks/entity/spells/black_hole/BlackHole.getRadius ()F"))
    public void tick(CallbackInfo ci) {
        BlackHole blackHole = BlackHole.class.cast(this);
        if (!(blackHole.getOwner() instanceof BlackHole)) {
            blackHole.setOwner(blackHole);
            System.out.println("set owner");
            System.out.println(blackHole.getOwner());
        }

    }
}
