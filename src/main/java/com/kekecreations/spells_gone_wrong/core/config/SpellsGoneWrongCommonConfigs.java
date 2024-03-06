package com.kekecreations.spells_gone_wrong.core.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class SpellsGoneWrongCommonConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> BLACK_HOLE_CAN_HURT_OWNER;

    static {
        BUILDER.push("Spells Gone Wrong Config");

        BUILDER.comment("Settings for letting a spell hurt the player who casted it  ||  Config changes will only affect newly casted spells");
        BUILDER.push("Spell Can Hurt Caster Settings");

        BLACK_HOLE_CAN_HURT_OWNER = BUILDER
                .comment("Should the Black Hole Spell hurt the player who casted it?")
                .define("Black Hole Can Hurt Owner", false);

        BUILDER.pop();



        //BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
