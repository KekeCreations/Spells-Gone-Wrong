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
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;

@AutoSpellConfig
public class NucreeperStrikeSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(SpellsGoneWrong.MOD_ID, "nucreeper_strike");
    private final DefaultConfig defaultConfig;

    public NucreeperStrikeSpell() {
        this.defaultConfig = (new DefaultConfig()).setMinRarity(SpellRarity.RARE).setSchoolResource(SchoolRegistry.EVOCATION_RESOURCE).setMaxLevel(10).setCooldownSeconds((double)2.0F).build();
        this.manaCostPerLevel = 12;
        this.baseSpellPower = 1;
        this.spellPowerPerLevel = 0;
        this.castTime = 18;
        this.baseManaCost = 75;
    }

    @Override
    public ResourceLocation getSpellResource() {
        return this.spellId;
    }

    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(Component.translatable("ui.irons_spellbooks.damage", new Object[]{Utils.stringTruncation((double)this.getDamage(spellLevel), 1)}), Component.translatable("ui.irons_spellbooks.projectile_count", new Object[]{this.getCount(spellLevel)}));
    }

    private int getCount(float spellLevel) {
        if (spellLevel < 6) {
            return 7;
        } else if(spellLevel >= 6 && spellLevel < 8) {
            return 11;
        } else {
            return 17;
        }
    }

    @Override
    public DefaultConfig getDefaultConfig() {
        return this.defaultConfig;
    }

    @Override
    public CastType getCastType() {
        return CastType.LONG;
    }

    private float getDamage(int spellLevel) {
        if (spellLevel <= 3) {
            return 1F + ((float) spellLevel / 2);
        }
        return 0.8F + ((float) spellLevel / 2);
    }

    @Override
    public boolean isEnabled() {
        return SpellsGoneWrongCommonConfig.NUCREEPER_STRIKE_SPELL.get();
    }

    @Override
    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        float speed = (float)(8) * 0.1F;
        float speed2 = (float)(8) * 0.1F;
        float damage = this.getDamage(spellLevel);
        Vec3 spawn = entity.getEyePosition().add(entity.getForward());
        RandomSource random = entity.getRandom();
        //PROJECTILES
        CreeperHeadProjectile head = new CreeperHeadProjectile(entity, level, speed + random.nextFloat(), damage);
        CreeperHeadProjectile head2 = new CreeperHeadProjectile(entity, level, speed2+ random.nextFloat(), damage);
        CreeperHeadProjectile head3 = new CreeperHeadProjectile(entity, level, speed2+ random.nextFloat(), damage);
        CreeperHeadProjectile head4 = new CreeperHeadProjectile(entity, level, speed2+ random.nextFloat(), damage);
        CreeperHeadProjectile head5 = new CreeperHeadProjectile(entity, level, speed2+ random.nextFloat(), damage);
        CreeperHeadProjectile head6 = new CreeperHeadProjectile(entity, level, speed2+ random.nextFloat(), damage);
        CreeperHeadProjectile head7 = new CreeperHeadProjectile(entity, level, speed2+ random.nextFloat(), damage);

        //HEAD POSITION
        head.moveTo(spawn.x, spawn.y - head.getBoundingBox().getYsize() / (double)2.0F, spawn.z, entity.getYRot() + 180.0F, entity.getXRot());
        head2.moveTo(spawn.x + 0.8D, spawn.y - head.getBoundingBox().getYsize() / (double)2.0F, spawn.z, entity.getYRot() + 180.0F, entity.getXRot());
        head3.moveTo(spawn.x - 0.8D, spawn.y - head.getBoundingBox().getYsize() / (double)2.0F, spawn.z, entity.getYRot() + 180.0F, entity.getXRot());
        head4.moveTo(spawn.x, spawn.y - head.getBoundingBox().getYsize() / (double)2.0F, spawn.z + 0.8D, entity.getYRot() + 180.0F, entity.getXRot());
        head5.moveTo(spawn.x, spawn.y - head.getBoundingBox().getYsize() / (double)2.0F, spawn.z - 0.8D, entity.getYRot() + 180.0F, entity.getXRot());
        head6.moveTo(spawn.x - 0.8D, spawn.y - head.getBoundingBox().getYsize() / (double)2.0F, spawn.z - 0.8D, entity.getYRot() + 180.0F, entity.getXRot());
        head7.moveTo(spawn.x + 0.8D, spawn.y - head.getBoundingBox().getYsize() / (double)2.0F, spawn.z + 0.8D, entity.getYRot() + 180.0F, entity.getXRot());

        level.addFreshEntity(head);
        level.addFreshEntity(head2);
        level.addFreshEntity(head3);
        level.addFreshEntity(head4);
        level.addFreshEntity(head5);
        level.addFreshEntity(head6);
        level.addFreshEntity(head7);

        if (spellLevel >= 6) {
            CreeperHeadProjectile head8 = new CreeperHeadProjectile(entity, level, speed2+ random.nextFloat(), damage);
            CreeperHeadProjectile head9 = new CreeperHeadProjectile(entity, level, speed2+ random.nextFloat(), damage);
            CreeperHeadProjectile head10 = new CreeperHeadProjectile(entity, level, speed2+ random.nextFloat(), damage);
            CreeperHeadProjectile head11 = new CreeperHeadProjectile(entity, level, speed2+ random.nextFloat(), damage);

            head8.moveTo(spawn.x + 1.5D, spawn.y - head.getBoundingBox().getYsize() / (double)2.0F, spawn.z, entity.getYRot() + 180.0F, entity.getXRot());
            head9.moveTo(spawn.x - 1.5D, spawn.y - head.getBoundingBox().getYsize() / (double)2.0F, spawn.z, entity.getYRot() + 180.0F, entity.getXRot());
            head10.moveTo(spawn.x, spawn.y - head.getBoundingBox().getYsize() / (double)2.0F, spawn.z + 1.5D, entity.getYRot() + 180.0F, entity.getXRot());
            head11.moveTo(spawn.x, spawn.y - head.getBoundingBox().getYsize() / (double)2.0F, spawn.z - 1.5D, entity.getYRot() + 180.0F, entity.getXRot());

            level.addFreshEntity(head8);
            level.addFreshEntity(head9);
            level.addFreshEntity(head10);
            level.addFreshEntity(head11);
        }


        if (spellLevel >= 8) {
            CreeperHeadProjectile head12 = new CreeperHeadProjectile(entity, level, speed2+ random.nextFloat(), damage);
            CreeperHeadProjectile head13 = new CreeperHeadProjectile(entity, level, speed2+ random.nextFloat(), damage);
            CreeperHeadProjectile head14 = new CreeperHeadProjectile(entity, level, speed2+ random.nextFloat(), damage);
            CreeperHeadProjectile head15 = new CreeperHeadProjectile(entity, level, speed2+ random.nextFloat(), damage);
            CreeperHeadProjectile head16 = new CreeperHeadProjectile(entity, level, speed2+ random.nextFloat(), damage);
            CreeperHeadProjectile head17 = new CreeperHeadProjectile(entity, level, speed2+ random.nextFloat(), damage);

            head12.moveTo(spawn.x + 2.3D, spawn.y - head.getBoundingBox().getYsize() / (double)2.0F, spawn.z, entity.getYRot() + 180.0F, entity.getXRot());
            head13.moveTo(spawn.x - 2.3D, spawn.y - head.getBoundingBox().getYsize() / (double)2.0F, spawn.z, entity.getYRot() + 180.0F, entity.getXRot());
            head14.moveTo(spawn.x, spawn.y - head.getBoundingBox().getYsize() / (double)2.0F, spawn.z + 2.3D, entity.getYRot() + 180.0F, entity.getXRot());
            head15.moveTo(spawn.x, spawn.y - head.getBoundingBox().getYsize() / (double)2.0F, spawn.z - 2.3D, entity.getYRot() + 180.0F, entity.getXRot());
            head16.moveTo(spawn.x + 1.5D, spawn.y - head.getBoundingBox().getYsize() / (double)2.0F, spawn.z + 1.5D, entity.getYRot() + 180.0F, entity.getXRot());
            head17.moveTo(spawn.x - 1.5D, spawn.y - head.getBoundingBox().getYsize() / (double)2.0F, spawn.z + 1.5D, entity.getYRot() + 180.0F, entity.getXRot());

            level.addFreshEntity(head12);
            level.addFreshEntity(head13);
            level.addFreshEntity(head14);
            level.addFreshEntity(head15);
            level.addFreshEntity(head16);
            level.addFreshEntity(head17);
        }
        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }
}
