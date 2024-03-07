package com.kekecreations.spells_gone_wrong.core.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class SpellsGoneWrongCommonConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> BLACK_HOLE_SPELL_CAN_HURT_OWNER;

    public static final ForgeConfigSpec.ConfigValue<Boolean> BLACK_HOLE_SPELL_CAN_CAUSE_EXPLOSIONS;

    public static final ForgeConfigSpec.ConfigValue<Boolean> ICE_BLOCK_SPELL_CAN_HURT_OWNER;


    static {
        BUILDER.push("Spells Gone Wrong Config");

        BUILDER.comment("Enable some bonus features for spells!");
        BUILDER.push("Spell Additions Settings");

        BLACK_HOLE_SPELL_CAN_CAUSE_EXPLOSIONS = BUILDER
                .comment("When enabled the Black Hole Spell will become unstable just before the spell expires and cause random explosions around the black hole. When enabled the Black Hole Spell will also display explosion particles (Don't cause damage) in the middle of the black hole when the spell expires")
                .define("Black Hole Spell Can Cause Explosions Just Before The Spell Expires", true);

        BUILDER.pop();

        BUILDER.comment("Settings for letting a spell hurt the player who casted it");
        BUILDER.push("Spell Can Hurt Caster Settings");

        BLACK_HOLE_SPELL_CAN_HURT_OWNER = BUILDER
                .comment("Should the Black Hole Spell hurt the player who casted it?")
                .define("Black Hole Spell Can Hurt Owner", true);

        ICE_BLOCK_SPELL_CAN_HURT_OWNER = BUILDER
                .comment("Should the Ice Block Spell hurt the player who casted it?")
                .define("Ice Block Spell Can Hurt Owner", false);

        BUILDER.pop();







        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
