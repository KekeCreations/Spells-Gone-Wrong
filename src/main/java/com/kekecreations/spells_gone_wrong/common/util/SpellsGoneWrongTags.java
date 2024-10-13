package com.kekecreations.spells_gone_wrong.common.util;

import com.kekecreations.spells_gone_wrong.SpellsGoneWrong;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class SpellsGoneWrongTags {


    public static final TagKey<Block> ICE_PATCH_REPLACEABLES
            = tag("ice_patch_replaceables");

    private static TagKey<Block> tag(String name) {
        return BlockTags.create(SpellsGoneWrong.id(name));
    }
}
