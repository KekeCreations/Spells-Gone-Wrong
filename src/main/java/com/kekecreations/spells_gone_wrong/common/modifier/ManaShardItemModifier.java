package com.kekecreations.spells_gone_wrong.common.modifier;

import com.google.common.base.Suppliers;
import com.kekecreations.spells_gone_wrong.core.config.SpellsGoneWrongCommonConfig;
import com.kekecreations.spells_gone_wrong.core.registry.SpellsGoneWrongItems;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class ManaShardItemModifier extends LootModifier {
    public static final Supplier<Codec<ManaShardItemModifier>> CODEC = Suppliers.memoize(()
            -> RecordCodecBuilder.create(inst -> codecStart(inst).and(ForgeRegistries.ITEMS.getCodec()
            .fieldOf("item").forGetter(m -> m.item)).apply(inst, ManaShardItemModifier::new)));

    private final Item item;

    public ManaShardItemModifier(LootItemCondition[] conditionsIn, Item item) {
        super(conditionsIn);
        this.item = item;
    }

    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        for(LootItemCondition condition : this.conditions) {
            if (!condition.test(context)) {
                return generatedLoot;
            }
        }
        if (SpellsGoneWrongCommonConfig.MANA_SHARD.get()) {
            if (context.getLevel().getRandom().nextDouble() < 0.1) {
                generatedLoot.clear();
                generatedLoot.add(new ItemStack(this.item));

                return generatedLoot;
            }
        }

        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}
