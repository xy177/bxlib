package emcapi.bxlib.tconstruct.traits;

import emcapi.bxlib.BXConfig;
import emcapi.bxlib.utils.MathUtils;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

/**
 *  @Author Biggest_Xuan
 *  2023/01/12
 */

public class DistanceTrait extends BXLibTrait {
    private static final String name = "emc_distance";
    public DistanceTrait(){
        super(name);
    }

    @Override
    public float damage(ItemStack tool, EntityLivingBase attacker, EntityLivingBase target, float damage, float newDamage, boolean isCritical) {
        double distance = MathUtils.twoEntityDistance(attacker,target);
        float rate = BXConfig.TRAITS_CONFIG.DISTANCE_UPPER_RATIO;
        if(distance > BXConfig.TRAITS_CONFIG.DISTANCE_NEAR_DISTANCE){
            if(distance > BXConfig.TRAITS_CONFIG.DISTANCE_FAR_DISTANCE){
                rate = BXConfig.TRAITS_CONFIG.DISTANCE_LOWER_RATIO;
            }else {
                distance -= BXConfig.TRAITS_CONFIG.DISTANCE_NEAR_DISTANCE;
                float length = BXConfig.TRAITS_CONFIG.DISTANCE_FAR_DISTANCE - BXConfig.TRAITS_CONFIG.DISTANCE_NEAR_DISTANCE;
                float ratio = BXConfig.TRAITS_CONFIG.DISTANCE_UPPER_RATIO - BXConfig.TRAITS_CONFIG.DISTANCE_LOWER_RATIO;
                rate -= (distance / length) * ratio;
            }
        }
        return Math.max(newDamage * rate,0);
    }
    @Override
    public String getLocalizedName() {
        if(BXConfig.TRAITS_CONFIG.DISTANCE_LOWER_RATIO > BXConfig.TRAITS_CONFIG.DISTANCE_UPPER_RATIO){
            return I18n.format("bxlib.trait."+ name +".far.name");
        }
        return super.getLocalizedName();
    }
    @Override
    public String getLocalizedDesc() {
        if(BXConfig.TRAITS_CONFIG.DISTANCE_LOWER_RATIO > BXConfig.TRAITS_CONFIG.DISTANCE_UPPER_RATIO){
            return I18n.format("bxlib.trait."+ name +".far.desc");
        }
        return super.getLocalizedDesc();
    }

    public static class DistanceArmorTrait extends BXLibArmorTrait {

        public DistanceArmorTrait() {
            super(name);
        }
        @Override
        public String getLocalizedName() {
            if(BXConfig.TRAITS_CONFIG.DISTANCE_RESIST_NEAR > BXConfig.TRAITS_CONFIG.DISTANCE_RESIST_FAR){
                return I18n.format("bxlib.armor_trait."+ name +".far.name");
            }
            return super.getLocalizedName();
        }
        @Override
        public String getLocalizedDesc() {
            if(BXConfig.TRAITS_CONFIG.DISTANCE_RESIST_NEAR > BXConfig.TRAITS_CONFIG.DISTANCE_RESIST_FAR){
                return I18n.format("bxlib.armor_trait."+ name +".far.desc");
            }
            return super.getLocalizedDesc();
        }
        @Override
        public float onHurt(ItemStack armor, EntityPlayer player, DamageSource source, float damage, float newDamage, LivingHurtEvent evt) {
            Entity attacker = source.getImmediateSource();
            if(attacker != null){
                double distance = MathUtils.twoEntityDistance(player,attacker);
                float rate = distance <= BXConfig.TRAITS_CONFIG.DISTANCE_CHANGE_LIMIT ?
                        BXConfig.TRAITS_CONFIG.DISTANCE_RESIST_NEAR:
                        BXConfig.TRAITS_CONFIG.DISTANCE_RESIST_FAR;
                return newDamage * rate;
            }
            return newDamage;
        }
    }
}
