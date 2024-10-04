package com.kekecreations.spells_gone_wrong.core.mixin;

import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.spells.holy.DivineSmiteSpell;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(value = DivineSmiteSpell.class, remap = false)
public class DivineSmiteSpellMixin {

    @Shadow
    private float getDamage(int spellLevel, LivingEntity entity) {
        return 0F;
    }

    @Inject(method = "onCast(Lnet/minecraft/world/level/Level;ILnet/minecraft/world/entity/LivingEntity;Lio/redspace/ironsspellbooks/api/spells/CastSource;Lio/redspace/ironsspellbooks/api/magic/MagicData;)V", at = @At(value = "INVOKE", target = "io/redspace/ironsspellbooks/spells/holy/DivineSmiteSpell.getDamageSource (Lnet/minecraft/world/entity/Entity;)Lio/redspace/ironsspellbooks/damage/SpellDamageSource;"))
    public void spells_gone_wrong_onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData, CallbackInfo ci) {
        float radius = 2.2F;
        float range = 1.7F;
        Vec3 smiteLocation = Utils.raycastForBlock(level, entity.getEyePosition(), entity.getEyePosition().add(entity.getForward().multiply((double)range, 0.0, (double)range)), ClipContext.Fluid.NONE).getLocation();
        List<Entity> entities = level.getEntities(entity, AABB.ofSize(smiteLocation, (double)(radius * 2.0F), (double)(radius * 4.0F), (double)(radius * 2.0F)));
        for (Entity targetEntity : entities) {
            if (targetEntity instanceof LivingEntity livingTarget) {
                if (livingTarget.getMobType() == MobType.UNDEAD) {
                    DamageSources.applyDamage(targetEntity, getDamage(spellLevel, entity), livingTarget.damageSources().onFire());
                }
            }
        }
    }
}
