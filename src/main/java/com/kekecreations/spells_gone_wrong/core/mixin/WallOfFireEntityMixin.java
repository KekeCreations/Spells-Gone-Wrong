package com.kekecreations.spells_gone_wrong.core.mixin;

import com.kekecreations.spells_gone_wrong.core.config.SpellsGoneWrongCommonConfig;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.damage.SpellDamageSource;
import io.redspace.ironsspellbooks.entity.spells.ShieldPart;
import io.redspace.ironsspellbooks.entity.spells.wall_of_fire.WallOfFireEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.entity.PartEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(WallOfFireEntity.class)
public class WallOfFireEntityMixin {

    @Shadow(remap = false)
    protected ShieldPart[] subEntities;
    @Shadow(remap = false)
    protected List<Vec3> partPositions;
    @Shadow(remap = false)
    protected List<Vec3> anchorPoints;
    @Shadow(remap = false)
    protected float damage;

    @Inject(method = "tick", at = @At(value = "TAIL"))
    public void spells_gone_wrong$tick(CallbackInfo ci) {
        WallOfFireEntity wallOfFire = (WallOfFireEntity) (Object) this;
        if (wallOfFire.getOwner() instanceof Player player && SpellsGoneWrongCommonConfig.WALL_OF_FIRE_SPELL_CAN_HURT_OWNER.get()) {
            if (this.anchorPoints.size() > 1 && this.subEntities.length > 1) {
                int i = 0;

                for (int subEntitiesLength = this.subEntities.length; i < subEntitiesLength; ++i) {
                    PartEntity<?> subEntity = this.subEntities[i];
                    Vec3 pos = (Vec3) this.partPositions.get(i);
                    subEntity.setPos(pos);
                    subEntity.xo = pos.x;
                    subEntity.yo = pos.y;
                    subEntity.zo = pos.z;
                    subEntity.xOld = pos.x;
                    subEntity.yOld = pos.y;
                    subEntity.zOld = pos.z;
                    if (wallOfFire.level().isClientSide && i < subEntitiesLength - 1) {
                    } else {
                        for (LivingEntity livingentity : wallOfFire.level().getEntitiesOfClass(LivingEntity.class, subEntity.getBoundingBox().inflate(0.2, (double) 0.0F, 0.2))) {
                            if (livingentity == wallOfFire.getOwner()) {
                                float baseAmount = this.damage;
                                SpellDamageSource spellDamageSource = SpellRegistry.WALL_OF_FIRE_SPELL.get().getDamageSource(wallOfFire, wallOfFire.getOwner());
                                player.hurt(spellDamageSource, baseAmount);
                            }
                        }
                    }
                }
            }
        }
    }
}
