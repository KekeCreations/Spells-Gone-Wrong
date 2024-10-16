package com.kekecreations.spells_gone_wrong.core.mixin;

import com.kekecreations.spells_gone_wrong.core.config.SpellsGoneWrongCommonConfig;
import com.kekecreations.spells_gone_wrong.core.registry.SpellsGoneWrongFeatures;
import io.redspace.ironsspellbooks.entity.spells.ice_block.IceBlockProjectile;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.neoforged.neoforge.event.level.BlockGrowFeatureEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(IceBlockProjectile.class)
public class IceBlockProjectileMixin {

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "io/redspace/ironsspellbooks/entity/spells/ice_block/IceBlockProjectile.getXRot ()F"))
    public void spell_gone_wrong_tick(CallbackInfo ci) {
        IceBlockProjectile iceBlockProjectile = (IceBlockProjectile) (Object) this;
        if (!(iceBlockProjectile.getOwner() instanceof IceBlockProjectile) && SpellsGoneWrongCommonConfig.ICE_BLOCK_SPELL_CAN_HURT_OWNER.get()) {
            iceBlockProjectile.setOwner(iceBlockProjectile);
        }

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
                    //BlockGrowFeatureEvent event = net.neoforged.neoforge.event.ForgeEventFactory.blockGrowFeature(pLevel, pRandom, pPos, holder);
                    BlockGrowFeatureEvent event = new BlockGrowFeatureEvent(pLevel, pRandom, pPos, holder);
                    ConfiguredFeature<?, ?> configuredfeature = event.getFeature().value();
                    configuredfeature.place((WorldGenLevel) pLevel, pGenerator, pRandom, pPos);
                }
            }
        }
    }
}
