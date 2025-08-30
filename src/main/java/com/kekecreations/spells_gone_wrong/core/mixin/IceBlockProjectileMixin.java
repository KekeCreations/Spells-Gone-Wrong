package com.kekecreations.spells_gone_wrong.core.mixin;

import com.kekecreations.spells_gone_wrong.core.config.SpellsGoneWrongCommonConfig;
import com.kekecreations.spells_gone_wrong.core.registry.SpellsGoneWrongFeatures;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.damage.SpellDamageSource;
import io.redspace.ironsspellbooks.entity.spells.ice_block.IceBlockProjectile;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(IceBlockProjectile.class)
public class IceBlockProjectileMixin {

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "io/redspace/ironsspellbooks/entity/spells/ice_block/IceBlockProjectile.getXRot ()F"))
    public void spells_gone_wrong$tick(CallbackInfo ci) {
        IceBlockProjectile iceBlockProjectile = (IceBlockProjectile) (Object) this;

        if (SpellsGoneWrongCommonConfig.ICE_BLOCK_SPELL_CAN_CAUSE_ICE_PATCHES.get()) {
            Level pLevel = iceBlockProjectile.level();
            RandomSource pRandom = pLevel.getRandom();
            BlockPos pPos = new BlockPos(iceBlockProjectile.getBlockX(), iceBlockProjectile.getBlockY(), iceBlockProjectile.getBlockZ());
            MinecraftServer serverLevel = pLevel.getServer();
            if (pLevel.getFluidState(pPos.below()).is(FluidTags.WATER) || iceBlockProjectile.onGround()) {
                if (serverLevel != null && !pLevel.getFluidState(pPos).is(FluidTags.WATER)) {
                    ChunkGenerator pGenerator = serverLevel.overworld().getChunkSource().getGenerator();
                    ResourceKey<ConfiguredFeature<?, ?>> getTreeFeature = SpellsGoneWrongFeatures.ConfiguredFeatures.ICE_PATCH;
                    Holder<ConfiguredFeature<?, ?>> holder = pLevel.registryAccess().registryOrThrow(Registries.CONFIGURED_FEATURE).getHolder(getTreeFeature).orElse((Holder.Reference<ConfiguredFeature<?, ?>>)null);
                    net.minecraftforge.event.level.SaplingGrowTreeEvent event = net.minecraftforge.event.ForgeEventFactory.blockGrowFeature(pLevel, pRandom, pPos, holder);
                    ConfiguredFeature<?, ?> configuredfeature = event.getFeature().value();
                    configuredfeature.place((WorldGenLevel) pLevel, pGenerator, pRandom, pPos);
                }
            }
        }
    }


    @Inject(method = "doImpactDamage", at = @At(value = "RETURN"), remap = false)
    public void spell_gone_wrong$doImpactDamage(CallbackInfo ci) {
        IceBlockProjectile iceBlockProjectile = (IceBlockProjectile) (Object) this;
        if (SpellsGoneWrongCommonConfig.ICE_BLOCK_SPELL_CAN_HURT_OWNER.get()) {
            float explosionRadius = 3.5F;
            iceBlockProjectile.level().getEntities(iceBlockProjectile, iceBlockProjectile.getBoundingBox().inflate((double)explosionRadius)).forEach((entity) -> {
                if (entity == iceBlockProjectile.getOwner() && iceBlockProjectile.getOwner() instanceof Player player) {
                    double distance = entity.distanceToSqr(iceBlockProjectile.position());
                    if (distance < (double) (explosionRadius * explosionRadius)) {
                        double p = (double) 1.0F - Math.pow(Math.sqrt(distance) / (double) explosionRadius, (double) 3.0F);
                        float damage = (float) ((double) iceBlockProjectile.getDamage() * p);
                        SpellDamageSource spellDamageSource = SpellRegistry.ICE_BLOCK_SPELL.get().getDamageSource(iceBlockProjectile, iceBlockProjectile.getOwner());
                        player.hurt(spellDamageSource, damage);
                    }
                }
            });
        }
    }
}
