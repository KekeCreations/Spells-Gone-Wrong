package com.kekecreations.spells_gone_wrong.core.mixin;

import com.kekecreations.spells_gone_wrong.core.config.SpellsGoneWrongCommonConfig;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.damage.SpellDamageSource;
import io.redspace.ironsspellbooks.entity.spells.black_hole.BlackHole;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(BlackHole.class)
public class BlackHoleMixin {

    @Shadow(remap = false)
    List<Entity> trackingEntities;

    @Inject(method = "tick", at = @At(value = "HEAD"))
    public void spells_gone_wrong$tick(CallbackInfo ci) {
        BlackHole blackHole = (BlackHole) (Object) this;
        if (SpellsGoneWrongCommonConfig.BLACK_HOLE_SPELL_CAN_HURT_OWNER.get()) {
            AABB bb = blackHole.getBoundingBox();
            float radius = (float)bb.getXsize();
            boolean hitTick = blackHole.tickCount % 10 == 0;
            for(Entity entity : this.trackingEntities) {
                if (entity == blackHole.getOwner() && blackHole.getOwner() instanceof Player player) {
                    Vec3 center = bb.getCenter();
                    float distance = (float)center.distanceTo(entity.position());
                    if (!(distance > radius)) {
                        float f = 1.0F - distance / radius;
                        float scale = f * f * f * f * 0.25F;
                        Vec3 diff = center.subtract(entity.position()).scale((double)scale);
                        entity.push(diff.x, diff.y, diff.z);
                        if (hitTick && distance < 9.0F) {
                            float baseAmount = blackHole.getDamage();
                            SpellDamageSource spellDamageSource = SpellRegistry.BLACK_HOLE_SPELL.get().getDamageSource(blackHole, blackHole.getOwner());
                            player.hurt(spellDamageSource, baseAmount);
                        }

                        entity.fallDistance = 0.0F;
                    }
                }
            }
        }

        if (blackHole.tickCount > 500 && blackHole.tickCount < 610 && SpellsGoneWrongCommonConfig.BLACK_HOLE_SPELL_CAN_CAUSE_EXPLOSIONS.get()) {
            if ((blackHole.tickCount) % 8 == 0 && !blackHole.level().isClientSide) {
                BlockPos blockPos = new BlockPos((int) blackHole.getRandomX(0.3), (int) (blackHole.getRandomY() - 10) + (int) blackHole.getRadius(), (int) blackHole.getRandomZ(0.3));
                Block block = blackHole.level().getBlockState(blockPos).getBlock();

                if (block == Blocks.AIR || block == Blocks.VOID_AIR || block == Blocks.CAVE_AIR) {
                    blackHole.level().explode(blackHole, blockPos.getX(), blockPos.getY(), blockPos.getZ(), 4.0F, Level.ExplosionInteraction.TNT);
                }
            }
        }
        if (blackHole.tickCount > 610 && SpellsGoneWrongCommonConfig.BLACK_HOLE_SPELL_CAN_CAUSE_EXPLOSIONS.get()) {
            blackHole.level().addParticle(ParticleTypes.EXPLOSION_EMITTER, true, blackHole.getX(), blackHole.getY() + (double)blackHole.getRadius(), blackHole.getZ(), 0, 0, 0);
        }
    }
}
