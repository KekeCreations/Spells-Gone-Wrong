package com.kekecreations.spells_gone_wrong.core.mixin;

import com.kekecreations.spells_gone_wrong.core.config.SpellsGoneWrongCommonConfigs;
import io.redspace.ironsspellbooks.entity.spells.black_hole.BlackHole;
import io.redspace.ironsspellbooks.entity.spells.comet.Comet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Comet.class)
public class CometMixin {


    @Inject(method = "onHit", at = @At(value = "INVOKE", target = "io/redspace/ironsspellbooks/entity/spells/comet/Comet.getImpactSound ()Ljava/util/Optional;"))
    public void spells_gone_wrong_onHit(CallbackInfo ci) {
        Comet comet = Comet.class.cast(this);
        if (!(comet.getOwner() instanceof Comet) && SpellsGoneWrongCommonConfigs.STARFALL_SPELL_CAN_HURT_OWNER.get()) {
            comet.setOwner(comet);
        }
    }
}
