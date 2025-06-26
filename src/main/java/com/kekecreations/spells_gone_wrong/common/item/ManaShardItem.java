package com.kekecreations.spells_gone_wrong.common.item;

import com.kekecreations.spells_gone_wrong.core.config.SpellsGoneWrongCommonConfig;
import com.kekecreations.spells_gone_wrong.core.registry.SpellsGoneWrongSounds;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.joml.Vector3f;

public class ManaShardItem extends Item {
    public ManaShardItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public boolean isEnabled(FeatureFlagSet p_249172_) {
        return SpellsGoneWrongCommonConfig.MANA_SHARD.get();
    }

    @Override
    public InteractionResult useOn(UseOnContext ctx) {
        Level level = ctx.getLevel();
        Player player = ctx.getPlayer();
        BlockPos blockPos = ctx.getClickedPos();
        ItemStack stack = ctx.getItemInHand();
        if (!level.isClientSide()) {
            if (level.getFluidState(blockPos.above()).isEmpty()) {
                if (player != null) {
                    RandomSource random = player.getRandom();
                    double diceRoll = random.nextDouble();
                    level.playSound(null, blockPos, SpellsGoneWrongSounds.SHARD_BREAK.get(), SoundSource.BLOCKS, 0.5F, random.nextFloat() * 0.2F + 0.9F);
                    MagicManager.spawnParticles(level, new BlastwaveParticleOptions(new Vector3f(0.0F, 1.0F, 1.0F), 6.0F), blockPos.getX() + 0.5F, blockPos.getY() + 1.2F, blockPos.getZ() + 0.5F, 0, (double) 0.0F, (double) 0.0F, (double) 0.0F, (double) 0.0F, false);
                    MagicManager.spawnParticles(level, new BlastwaveParticleOptions(new Vector3f(0.0F, 1.0F, 1.0F), 2.0F), blockPos.getX() + 0.5F, blockPos.getY() + 1.2F, blockPos.getZ() + 0.5F, 0, (double) 0.0F, (double) 0.0F, (double) 0.0F, (double) 0.0F, false);


                    if (diceRoll <= SpellsGoneWrongCommonConfig.MANA_SHARD_AMPLIFIER_3_CHANCE.get()) {
                        player.addEffect(new MobEffectInstance(MobEffectRegistry.INSTANT_MANA.get(), 1, 3));
                    } else if (diceRoll <= SpellsGoneWrongCommonConfig.MANA_SHARD_AMPLIFIER_2_CHANCE.get()) {
                        player.addEffect(new MobEffectInstance(MobEffectRegistry.INSTANT_MANA.get(), 1, 2));
                    } else if (diceRoll <= SpellsGoneWrongCommonConfig.MANA_SHARD_AMPLIFIER_1_CHANCE.get()) {
                        player.addEffect(new MobEffectInstance(MobEffectRegistry.INSTANT_MANA.get(), 1, 1));
                    } else {
                        player.addEffect(new MobEffectInstance(MobEffectRegistry.INSTANT_MANA.get(), 1, 0));
                    }
                    stack.shrink(1);
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return super.useOn(ctx);
    }
}