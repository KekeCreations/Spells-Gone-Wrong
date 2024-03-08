package com.kekecreations.spells_gone_wrong.core.registry;

import com.kekecreations.spells_gone_wrong.common.util.SpellsGoneWrongTags;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedBlockStateProvider;

import java.util.List;

public class SpellsGoneWrongFeatures {


    public static class ConfiguredFeatures {

        public static final Holder<ConfiguredFeature<DiskConfiguration, ?>> ICE_PATCH = FeatureUtils.register("ice_patch", Feature.DISK, new DiskConfiguration(new RuleBasedBlockStateProvider(BlockStateProvider.simple(Blocks.ICE), List.of(new RuleBasedBlockStateProvider.Rule(BlockPredicate.matchesBlocks(Blocks.ICE), BlockStateProvider.simple(Blocks.PACKED_ICE)))), BlockPredicate.matchesTag(SpellsGoneWrongTags.ICE_PATCH_REPLACEABLES), UniformInt.of(2, 3), 1));

    }
}
