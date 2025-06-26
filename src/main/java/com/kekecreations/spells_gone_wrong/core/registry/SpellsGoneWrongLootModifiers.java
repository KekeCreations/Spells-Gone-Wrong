package com.kekecreations.spells_gone_wrong.core.registry;

import com.kekecreations.spells_gone_wrong.SpellsGoneWrong;
import com.kekecreations.spells_gone_wrong.common.modifier.ManaShardItemModifier;
import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class SpellsGoneWrongLootModifiers {

    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, SpellsGoneWrong.MODID);

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> MANA_SHARD_MODIFIER =
            LOOT_MODIFIER_SERIALIZERS.register("mana_shard", ManaShardItemModifier.CODEC);


    public static void register(IEventBus eventBus) {
        LOOT_MODIFIER_SERIALIZERS.register(eventBus);
    }
}
