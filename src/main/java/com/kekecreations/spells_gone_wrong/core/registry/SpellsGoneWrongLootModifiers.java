package com.kekecreations.spells_gone_wrong.core.registry;

import com.kekecreations.spells_gone_wrong.common.modifier.ManaShardItemModifier;
import com.mojang.serialization.MapCodec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class SpellsGoneWrongLootModifiers {

    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS;
    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<ManaShardItemModifier>> MANA_SHARD_MODIFIER;

    public SpellsGoneWrongLootModifiers() {
    }

    public static void register(IEventBus eventBus) {
        LOOT_MODIFIER_SERIALIZERS.register(eventBus);
    }

    static {
        LOOT_MODIFIER_SERIALIZERS = DeferredRegister.create(NeoForgeRegistries.GLOBAL_LOOT_MODIFIER_SERIALIZERS, "spells_gone_wrong");
        MANA_SHARD_MODIFIER = LOOT_MODIFIER_SERIALIZERS.register("mana_shard", ManaShardItemModifier.CODEC);
    }
}
