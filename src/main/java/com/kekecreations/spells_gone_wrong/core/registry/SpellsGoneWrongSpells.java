package com.kekecreations.spells_gone_wrong.core.registry;

import com.kekecreations.spells_gone_wrong.SpellsGoneWrong;
import com.kekecreations.spells_gone_wrong.common.spell.NucreeperStrikeSpell;
import com.kekecreations.spells_gone_wrong.common.spell.ShotgunCreeperSpell;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class SpellsGoneWrongSpells {
    public static final DeferredRegister<AbstractSpell> SPELLS;

    public static final Supplier<AbstractSpell> CREEPER_SHOTGUN;
    public static final Supplier<AbstractSpell> NUCREEPER_STRIKE;

    public static Supplier<AbstractSpell> registerSpell(AbstractSpell spell) {
        return SPELLS.register(spell.getSpellName(), () -> spell);
    }


    public static void register(IEventBus eventBus) {
        SPELLS.register(eventBus);
    }

    static {
        SPELLS = DeferredRegister.create(SpellRegistry.SPELL_REGISTRY_KEY, SpellsGoneWrong.MODID);
        CREEPER_SHOTGUN = registerSpell(new ShotgunCreeperSpell());
        NUCREEPER_STRIKE = registerSpell(new NucreeperStrikeSpell());
    }
}
