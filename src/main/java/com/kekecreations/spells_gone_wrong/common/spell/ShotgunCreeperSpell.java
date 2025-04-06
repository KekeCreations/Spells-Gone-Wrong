package com.kekecreations.spells_gone_wrong.common.spell;

import com.kekecreations.spells_gone_wrong.SpellsGoneWrong;
import com.kekecreations.spells_gone_wrong.core.config.SpellsGoneWrongCommonConfig;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.entity.spells.creeper_head.CreeperHeadProjectile;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;

@AutoSpellConfig
public class ShotgunCreeperSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(SpellsGoneWrong.MOD_ID, "shotgun_creeper");
    private final DefaultConfig defaultConfig;

    public ShotgunCreeperSpell() {
        this.defaultConfig = (new DefaultConfig()).setMinRarity(SpellRarity.RARE).setSchoolResource(SchoolRegistry.EVOCATION_RESOURCE).setMaxLevel(10).setCooldownSeconds((double)2.0F).build();
        this.manaCostPerLevel = 10;
        this.baseSpellPower = 4;
        this.spellPowerPerLevel = 0;
        this.castTime = 2;
        this.baseManaCost = 40;
    }

    @Override
    public ResourceLocation getSpellResource() {
        return this.spellId;
    }

    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(Component.translatable("ui.irons_spellbooks.damage", new Object[]{Utils.stringTruncation((double)this.getDamage(spellLevel), 1)}), Component.translatable("ui.irons_spellbooks.projectile_count", new Object[]{this.getCount()}));
    }

    private int getCount() {
        return 3;
    }

    @Override
    public DefaultConfig getDefaultConfig() {
        return this.defaultConfig;
    }

    @Override
    public CastType getCastType() {
        return CastType.INSTANT;
    }

    private float getDamage(int spellLevel) {
        return 1F + spellLevel;
    }

    @Override
    public boolean isEnabled() {
        return SpellsGoneWrongCommonConfig.SHOTGUN_CREEPER_SPELL.get();
    }

    @Override
    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        float speed = (float)(8) * 0.1F;
        float speed2 = (float)(8) * 0.1F;
        float damage = this.getDamage(spellLevel);
        CreeperHeadProjectile head = new CreeperHeadProjectile(entity, level, speed, damage);
        CreeperHeadProjectile head2 = new CreeperHeadProjectile(entity, level, speed2, damage);
        CreeperHeadProjectile head3 = new CreeperHeadProjectile(entity, level, speed2, damage);
        Vec3 spawn = entity.getEyePosition().add(entity.getForward());
        head.moveTo(spawn.x, spawn.y - head.getBoundingBox().getYsize() / (double)2.0F, spawn.z, entity.getYRot() + 180.0F, entity.getXRot());

        if (entity.getDirection() == Direction.NORTH || entity.getDirection() == Direction.SOUTH) {
            head2.moveTo(spawn.x + 0.8D, spawn.y - head.getBoundingBox().getYsize() / (double)2.0F, spawn.z, entity.getYRot() + 180.0F, entity.getXRot());
            head3.moveTo(spawn.x - 0.8D, spawn.y - head.getBoundingBox().getYsize() / (double)2.0F, spawn.z, entity.getYRot() + 180.0F, entity.getXRot());
        }
        if (entity.getDirection() == Direction.WEST || entity.getDirection() == Direction.EAST) {
            head2.moveTo(spawn.x, spawn.y - head.getBoundingBox().getYsize() / (double)2.0F, spawn.z + 0.8D, entity.getYRot() + 180.0F, entity.getXRot());
            head3.moveTo(spawn.x, spawn.y - head.getBoundingBox().getYsize() / (double)2.0F, spawn.z - 0.8D, entity.getYRot() + 180.0F, entity.getXRot());
        }
        level.addFreshEntity(head);
        level.addFreshEntity(head2);
        level.addFreshEntity(head3);
        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }
}
