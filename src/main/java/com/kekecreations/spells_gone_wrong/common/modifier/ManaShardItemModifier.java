package com.kekecreations.spells_gone_wrong.common.modifier;

import com.google.common.base.Suppliers;
import com.kekecreations.spells_gone_wrong.core.config.SpellsGoneWrongCommonConfig;
import com.kekecreations.spells_gone_wrong.core.registry.SpellsGoneWrongItems;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.redspace.ironsspellbooks.registries.ItemRegistry;
import io.redspace.ironsspellbooks.registries.PotionRegistry;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.function.Supplier;

public class ManaShardItemModifier extends LootModifier {
    public static final Supplier<MapCodec<ManaShardItemModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.mapCodec((inst) -> codecStart(inst).and(BuiltInRegistries.ITEM.byNameCodec().fieldOf("item").forGetter((m) -> m.item)).apply(inst, ManaShardItemModifier::new)));
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
        if (SpellsGoneWrongCommonConfig.MANA_SHARD.get() && !SpellsGoneWrongCommonConfig.DISABLE_MANA_POTIONS.get()) {
            if (context.getLevel().getRandom().nextDouble() < 0.1) {
                if (context.getLevel().getRandom().nextDouble() < 0.5) {
                    generatedLoot.clear();
                    generatedLoot.add(new ItemStack(this.item));
                } else {
                    generatedLoot.clear();
                    generatedLoot.add(PotionContents.createItemStack(Items.POTION, PotionRegistry.INSTANT_MANA_ONE));
                }
                return generatedLoot;
            }
        }
        for (Iterator<ItemStack> it = generatedLoot.stream().iterator(); it.hasNext(); ) {
            ItemStack itemStack = it.next();
            if (itemStack.has(DataComponents.POTION_CONTENTS)) {
                if (itemStack.get(DataComponents.POTION_CONTENTS).potion().isPresent()) {
                    if (!itemStack.get(DataComponents.POTION_CONTENTS).potion().get().value().isEnabled(FeatureFlagSet.of())) {
                        generatedLoot.remove(itemStack);
                        if (SpellsGoneWrongCommonConfig.MANA_SHARD.get()) {
                            generatedLoot.add(new ItemStack(this.item));
                        }
                    }
                }
            }
        }

        return generatedLoot;
    }

    public MapCodec<? extends IGlobalLootModifier> codec() {
        return (MapCodec)CODEC.get();
    }
}
