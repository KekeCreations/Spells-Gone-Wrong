package com.kekecreations.spells_gone_wrong.core.registry;

import com.kekecreations.spells_gone_wrong.SpellsGoneWrong;
import com.kekecreations.spells_gone_wrong.common.spell.ShotgunCreeperSpell;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class SpellsGoneWrongSpells {
    public static final DeferredRegister<AbstractSpell> SPELLS;

    public static final Supplier<AbstractSpell> CREEPER_SHOTGUN;

    public static Supplier<AbstractSpell> registerSpell(AbstractSpell spell) {
        return SPELLS.register(spell.getSpellName(), () -> spell);
    }


    public static void register(IEventBus eventBus) {
        SPELLS.register(eventBus);
    }

    static {
        SPELLS = DeferredRegister.create(SpellRegistry.SPELL_REGISTRY_KEY, SpellsGoneWrong.MOD_ID);
        CREEPER_SHOTGUN = registerSpell(new ShotgunCreeperSpell());
    }
}
