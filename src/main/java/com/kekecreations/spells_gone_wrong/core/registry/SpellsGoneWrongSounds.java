package com.kekecreations.spells_gone_wrong.core.registry;

import com.kekecreations.jinxedlib.core.util.JinxedRegistryHelper;
import com.kekecreations.spells_gone_wrong.SpellsGoneWrong;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;

import java.util.function.Supplier;

public class SpellsGoneWrongSounds {

    public static final Supplier<SoundEvent> SHARD_BREAK = registerSound("item.mana_shard.break");


    private static Supplier<SoundEvent> registerSound(String name) {
        var location = SpellsGoneWrong.id(name);
        return JinxedRegistryHelper.register(BuiltInRegistries.SOUND_EVENT, SpellsGoneWrong.MOD_ID, name, () -> SoundEvent.createVariableRangeEvent(location));
    }

    public static void register() {}
}
