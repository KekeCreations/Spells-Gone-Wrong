package com.kekecreations.spells_gone_wrong.core.mixin;

import com.kekecreations.spells_gone_wrong.core.config.SpellsGoneWrongCommonConfig;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractSpell.class)
public class AbstractSpellMixin {

    @Inject(method = "isEnabled", at = @At(value = "HEAD"), cancellable = true, remap = false)
    public void spells_gone_wrong$isEnabled(CallbackInfoReturnable<Boolean> cir) {
        AbstractSpell spell = (AbstractSpell) (Object) this;
        if (spell == SpellRegistry.SCULK_TENTACLES_SPELL.get()) {
            cir.setReturnValue(!SpellsGoneWrongCommonConfig.DISABLE_SCULK_TENTACLES.get());
        }
        if (spell == SpellRegistry.SONIC_BOOM_SPELL.get()) {
            cir.setReturnValue(!SpellsGoneWrongCommonConfig.DISABLE_SONIC_BOOM.get());
        }
        if (spell == SpellRegistry.ELDRITCH_BLAST_SPELL.get()) {
            cir.setReturnValue(!SpellsGoneWrongCommonConfig.DISABLE_ELDRITCH_BLAST.get());
        }
        if (spell == SpellRegistry.PLANAR_SIGHT_SPELL.get()) {
            cir.setReturnValue(!SpellsGoneWrongCommonConfig.DISABLE_PLANAR_SIGHT.get());
        }
        if (spell == SpellRegistry.ABYSSAL_SHROUD_SPELL.get()) {
            cir.setReturnValue(!SpellsGoneWrongCommonConfig.DISABLE_ABYSSAL_SHROUD.get());
        }
        if (spell == SpellRegistry.TELEKINESIS_SPELL.get()) {
            cir.setReturnValue(!SpellsGoneWrongCommonConfig.DISABLE_TELEKINESIS.get());
        }
        if (spell == SpellRegistry.BLACK_HOLE_SPELL.get()) {
            cir.setReturnValue(!SpellsGoneWrongCommonConfig.DISABLE_BLACKHOLE.get());
        }
    }
}
