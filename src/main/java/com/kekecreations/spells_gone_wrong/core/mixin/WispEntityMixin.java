package com.kekecreations.spells_gone_wrong.core.mixin;

import com.kekecreations.spells_gone_wrong.core.config.SpellsGoneWrongCommonConfigs;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.wisp.WispEntity;
import net.minecraft.world.entity.MobType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = WispEntity.class, remap = false)
public class WispEntityMixin {

    @Shadow
    private float damageAmount;

    @Inject(method = "tick()V", at = @At(value = "INVOKE", target = "io/redspace/ironsspellbooks/api/spells/AbstractSpell.getDamageSource (Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/entity/Entity;)Lio/redspace/ironsspellbooks/damage/SpellDamageSource;"))
    private void spells_gone_wrong_tick(CallbackInfo ci) {
        if (SpellsGoneWrongCommonConfigs.HOLY_SPELLS_DO_EXTRA_DAMAGE_TO_UNDEAD_MOBS.get()) {
            WispEntity $this = (WispEntity) (Object) this;
            if ($this.getTarget() != null) {
                if ($this.getTarget().getMobType() == MobType.UNDEAD) {
                    DamageSources.applyDamage($this.getTarget(), damageAmount, $this.getTarget().damageSources().onFire());
                }
            }
        }
    }
}
