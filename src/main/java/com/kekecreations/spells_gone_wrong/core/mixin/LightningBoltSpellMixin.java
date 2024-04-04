package com.kekecreations.spells_gone_wrong.core.mixin;

import com.kekecreations.spells_gone_wrong.core.config.SpellsGoneWrongCommonConfigs;
import com.llamalad7.mixinextras.sugar.Local;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.spells.lightning.LightningBoltSpell;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LightningBoltSpell.class)
public class LightningBoltSpellMixin {

    @Inject(method = "onCast", at = @At(value = "TAIL"), remap = false)
    public void spells_gone_wrong_onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData, CallbackInfo ci, @Local(name="pos") Vec3 pos, @Local(name="radius") float radius, @Local(name="damage") float damage, @Local(name="lightningBolt") LightningBolt lightningBolt) {
        if (SpellsGoneWrongCommonConfigs.LIGHTNING_BOLT_SPELL_CAN_HURT_OWNER.get()) {
            LightningBoltSpell $this = LightningBoltSpell.class.cast(this);
            double playerDistance = 0;

            for (Player target : level.getEntitiesOfClass(Player.class, AABB.ofSize(pos, (double) (radius * 2.0F), (double) (radius * 2.0F), (double) (radius * 2.0F)))) {
                float finalDamage = (float) ((double) damage * (1.0 - playerDistance / (double) (radius * radius)));
                DamageSources.applyDamage(target, finalDamage, $this.getDamageSource(lightningBolt, entity));
            }
        }
    }
}
