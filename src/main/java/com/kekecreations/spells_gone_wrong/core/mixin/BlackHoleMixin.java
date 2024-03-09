package com.kekecreations.spells_gone_wrong.core.mixin;

import com.kekecreations.spells_gone_wrong.core.config.SpellsGoneWrongCommonConfigs;
import io.redspace.ironsspellbooks.entity.spells.black_hole.BlackHole;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlackHole.class)
public class BlackHoleMixin {


    @Inject(method = "tick", at = @At(value = "INVOKE", target = "io/redspace/ironsspellbooks/entity/spells/black_hole/BlackHole.getRadius ()F"))
    public void spells_gone_wrong_tick(CallbackInfo ci) {
        BlackHole blackHole = BlackHole.class.cast(this);
        if (!(blackHole.getOwner() instanceof BlackHole) && SpellsGoneWrongCommonConfigs.BLACK_HOLE_SPELL_CAN_HURT_OWNER.get()) {
            blackHole.setOwner(blackHole);
        }
        if (blackHole.tickCount > 500 && blackHole.tickCount < 610 && SpellsGoneWrongCommonConfigs.BLACK_HOLE_SPELL_CAN_CAUSE_EXPLOSIONS.get()) {
            if ((blackHole.tickCount) % 8 == 0 && !blackHole.level.isClientSide) {
                BlockPos blockPos = new BlockPos(blackHole.getRandomX(0.3), (blackHole.getRandomY() - 10) + (double)blackHole.getRadius(), blackHole.getRandomZ(0.3));
                Block block = blackHole.level.getBlockState(blockPos).getBlock();

                if (block == Blocks.AIR || block == Blocks.VOID_AIR || block == Blocks.CAVE_AIR) {
                    blackHole.level.explode(blackHole, blockPos.getX(), blockPos.getY(), blockPos.getZ(), 4.0F, Explosion.BlockInteraction.BREAK);
                }
            }
        }
        if (blackHole.tickCount > 610 && SpellsGoneWrongCommonConfigs.BLACK_HOLE_SPELL_CAN_CAUSE_EXPLOSIONS.get()) {
            blackHole.getLevel().addParticle(ParticleTypes.EXPLOSION_EMITTER, true, blackHole.getX(), blackHole.getY() + (double)blackHole.getRadius(), blackHole.getZ(), 0, 0, 0);
        }
    }
}
