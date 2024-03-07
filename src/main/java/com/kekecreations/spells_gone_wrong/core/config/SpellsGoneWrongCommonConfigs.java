package com.kekecreations.spells_gone_wrong.core.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class SpellsGoneWrongCommonConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> BLACK_HOLE_CAN_HURT_OWNER;

    public static final ForgeConfigSpec.ConfigValue<Boolean> BLACK_HOLE_CAN_CAUSE_EXPLOSIONS;


    static {
        BUILDER.push("Spells Gone Wrong Config");

        BUILDER.comment("Settings for letting a spell hurt the player who casted it");
        BUILDER.push("Spell Can Hurt Caster Settings");

        BLACK_HOLE_CAN_HURT_OWNER = BUILDER
                .comment("Should the Black Hole Spell hurt the player who casted it?")
                .define("Black Hole Can Hurt Owner", false);

        BUILDER.pop();

        BUILDER.comment("Enable some bonus features for spells!");
        BUILDER.push("Spell Additions Settings");

        BLACK_HOLE_CAN_CAUSE_EXPLOSIONS = BUILDER
                .comment("When enabled Black Holes will become unstable just before the spell expires and cause random explosions around it. When enabled Black Holes will also display explosion particles (Don't cause damage) when the spell expires")
                .define("Black Hole Can Cause Explosions Just Before The Spell Expires", true);

        BUILDER.pop();





        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
