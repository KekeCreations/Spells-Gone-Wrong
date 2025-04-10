package com.kekecreations.spells_gone_wrong.core.registry;

import com.kekecreations.jinxedlib.core.util.JinxedRegistryHelper;
import com.kekecreations.spells_gone_wrong.SpellsGoneWrong;
import com.kekecreations.spells_gone_wrong.common.item.ManaShardItem;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class SpellsGoneWrongItems {

    public static final Supplier<Item> MANA_SHARD = registerItem("mana_shard", () -> new ManaShardItem(new Item.Properties().stacksTo(16)));


    public static Supplier<Item> registerItem(String id, Supplier<Item> supplier) {
        return JinxedRegistryHelper.registerItem(SpellsGoneWrong.MOD_ID, id, supplier);
    }

    public static void register() {}
}
