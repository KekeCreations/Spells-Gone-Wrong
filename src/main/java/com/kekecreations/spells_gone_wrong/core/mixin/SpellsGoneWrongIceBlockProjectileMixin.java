package com.kekecreations.spells_gone_wrong.core.mixin;

import com.kekecreations.spells_gone_wrong.core.config.SpellsGoneWrongCommonConfigs;
import com.kekecreations.spells_gone_wrong.core.registry.SpellsGoneWrongFeatures;
import io.redspace.ironsspellbooks.entity.spells.ice_block.IceBlockProjectile;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
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







        if (SpellsGoneWrongCommonConfigs.ICE_BLOCK_SPELL_CAN_CAUSE_ICE_PATCHES.get()) {
            Level pLevel = iceBlockProjectile.getLevel();
            RandomSource pRandom = pLevel.getRandom();
            BlockPos pPos = new BlockPos(iceBlockProjectile.getBlockX(), iceBlockProjectile.getBlockY(), iceBlockProjectile.getBlockZ());
            MinecraftServer serverLevel = pLevel.getServer();
            if (pLevel.getFluidState(pPos.below()).is(FluidTags.WATER) || iceBlockProjectile.isOnGround()) {
                if (serverLevel != null && !pLevel.getFluidState(pPos).is(FluidTags.WATER)) {
                    ChunkGenerator pGenerator = serverLevel.overworld().getChunkSource().getGenerator();
                    Holder<? extends ConfiguredFeature<?, ?>> holder = SpellsGoneWrongFeatures.ConfiguredFeatures.ICE_PATCH;
                    net.minecraftforge.event.level.SaplingGrowTreeEvent event = net.minecraftforge.event.ForgeEventFactory.blockGrowFeature(pLevel, pRandom, pPos, holder);
                    ConfiguredFeature<?, ?> configuredfeature = event.getFeature().value();
                    configuredfeature.place((WorldGenLevel) pLevel, pGenerator, pRandom, pPos);
                }
            }
        }
    }
}
