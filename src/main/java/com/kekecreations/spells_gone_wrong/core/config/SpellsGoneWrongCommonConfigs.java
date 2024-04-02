package com.kekecreations.spells_gone_wrong.core.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class SpellsGoneWrongCommonConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> BLACK_HOLE_SPELL_CAN_HURT_OWNER;

    public static final ForgeConfigSpec.ConfigValue<Boolean> BLACK_HOLE_SPELL_CAN_CAUSE_EXPLOSIONS;

    public static final ForgeConfigSpec.ConfigValue<Boolean> ICE_BLOCK_SPELL_CAN_HURT_OWNER;
    public static final ForgeConfigSpec.ConfigValue<Boolean> ICE_BLOCK_SPELL_CAN_CAUSE_ICE_PATCHES;
    public static final ForgeConfigSpec.ConfigValue<Boolean> STARFALL_SPELL_CAN_HURT_OWNER;
    public static final ForgeConfigSpec.ConfigValue<Boolean> ACID_SPIT_SPELL_CAN_HURT_OWNER;
    public static final ForgeConfigSpec.ConfigValue<Boolean> POISON_ARROW_SPELL_CAN_HURT_OWNER;
    public static final ForgeConfigSpec.ConfigValue<Boolean> FIREFLY_SWARM_SPELL_CAN_HURT_OWNER;
    public static final ForgeConfigSpec.ConfigValue<Boolean> FIREFLY_SWARM_CAN_GIVE_GLOWING_EFFECT;
    public static final ForgeConfigSpec.ConfigValue<Boolean> EARTHQUAKE_SPELL_CAN_HURT_OWNER;
    public static final ForgeConfigSpec.ConfigValue<Boolean> MAGMA_BOMB_SPELL_CAN_HURT_OWNER;
    public static final ForgeConfigSpec.ConfigValue<Boolean> WALL_OF_FIRE_SPELL_CAN_HURT_OWNER;


    static {
        BUILDER.push("Spells Gone Wrong Config");

        BUILDER.comment("Enable some bonus features for spells!");
        BUILDER.push("Spell Additions Settings");

        BLACK_HOLE_SPELL_CAN_CAUSE_EXPLOSIONS = BUILDER
                .comment("When enabled the Black Hole Spell will become unstable just before the spell expires and cause random explosions around the black hole. When enabled the Black Hole Spell will also display explosion particles (Don't cause damage) in the middle of the black hole when the spell expires")
                .define("Black Hole Spell Can Cause Explosions Just Before The Spell Expires", true);

        ICE_BLOCK_SPELL_CAN_CAUSE_ICE_PATCHES = BUILDER
                .comment("When enabled the Ice Block Spell can cause Ice Patches when hitting the ground or when hitting water. Ice Patches will place Ice on blocks with the #spells_gone_wrong:ice_patch_replaceable tag and will turn Ice into Packed Ice.")
                .define("Ice Block Spell Can Cause Ice Patches", true);

        FIREFLY_SWARM_CAN_GIVE_GLOWING_EFFECT = BUILDER
                .comment("When enabled the Firefly Swarm Spell will give the glowing effect to nearby enemies")
                .define("Firefly Swarm Spell Can Give Glowing Effect", true);

        BUILDER.pop();

        BUILDER.comment("Settings for letting a spell hurt the player who casted it");
        BUILDER.push("Spell Can Hurt Caster Settings");

        BLACK_HOLE_SPELL_CAN_HURT_OWNER = BUILDER
                .comment("Should the Black Hole Spell hurt the player who casted it?")
                .define("Black Hole Spell Can Hurt Owner", true);

        ICE_BLOCK_SPELL_CAN_HURT_OWNER = BUILDER
                .comment("Should the Ice Block Spell hurt the player who casted it?")
                .define("Ice Block Spell Can Hurt Owner", true);

        STARFALL_SPELL_CAN_HURT_OWNER = BUILDER
                .comment("Should the Comets spawned by the Starfall Spell hurt the player who casted it?")
                .define("Starfall Spell Can Hurt Owner", true);

        ACID_SPIT_SPELL_CAN_HURT_OWNER = BUILDER
                .comment("Should the Acid Orbs spawned by the Acid Spit Spell hurt the player who casted it?")
                .define("Acid Spit Spell Can Hurt Owner", true);

        POISON_ARROW_SPELL_CAN_HURT_OWNER = BUILDER
                .comment("Should the Poison Arrow and the Poison Clouds spawned by the Poison Arrow Spell hurt the player who casted it?")
                .define("Poison Arrow Spell Can Hurt Owner", true);

        FIREFLY_SWARM_SPELL_CAN_HURT_OWNER = BUILDER
                .comment("Should the Fireflies spawned by the Firefly Swarm Spell hurt the player who casted it?")
                .define("Firefly Swarm Spell Can Hurt Owner", true);

        EARTHQUAKE_SPELL_CAN_HURT_OWNER = BUILDER
                .comment("Should the Earthquake Spell hurt the player who casted it?")
                .define("Earthquake Spell Can Hurt Owner", true);

        MAGMA_BOMB_SPELL_CAN_HURT_OWNER = BUILDER
                .comment("Should the Magma Bomb and the Fire Field spawned by the Magma Bomb Spell hurt the player who casted it?")
                .define("Magma Bomb Spell Can Hurt Owner", true);

        WALL_OF_FIRE_SPELL_CAN_HURT_OWNER = BUILDER
                .comment("Should the Wall Of Fire Spell hurt the player who casted it?")
                .define("Wall Of Fire Spell Can Hurt Owner", true);

        BUILDER.pop();







        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
