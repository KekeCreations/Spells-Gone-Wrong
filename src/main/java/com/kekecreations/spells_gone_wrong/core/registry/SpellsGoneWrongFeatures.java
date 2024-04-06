package com.kekecreations.spells_gone_wrong.core.registry;

import com.kekecreations.spells_gone_wrong.SpellsGoneWrong;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class SpellsGoneWrongFeatures {


    public static class ConfiguredFeatures {

        public static final ResourceKey<ConfiguredFeature<?, ?>> ICE_PATCH = createKey("ice_patch");

        public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
            return ResourceKey.create(Registries.CONFIGURED_FEATURE, SpellsGoneWrong.id(name));
        }
    }
}
