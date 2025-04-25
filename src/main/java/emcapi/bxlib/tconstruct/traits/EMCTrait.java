package emcapi.bxlib.tconstruct.traits;

import emcapi.bxlib.BXConfig;
import emcapi.bxlib.event.CommonEvent;
import emcapi.bxlib.utils.MathUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

/**
 *  @Author Biggest_Xuan
 *  2023/01/12
 */

public class EMCTrait extends BXLibTrait {
    private static final String name = "emcworld";
    private static final String id = CommonEvent.id_3;

    public EMCTrait() {
        super(name);
    }

    @Override
    public float damage(ItemStack tool, EntityLivingBase attacker, EntityLivingBase target, float damage, float newDamage, boolean isCritical) {
        float amt = newDamage;
        if(attacker instanceof EntityPlayer){
            EntityPlayer player = (EntityPlayer) attacker;
            NBTTagCompound nbt = player.getEntityData();
            if(!nbt.hasKey(id)){
                return amt;
            }
            float filled = MathUtils.nbtGetValue(player,id);
            filled /= BXConfig.TRAITS_CONFIG.EMC_LIMIT;
            amt *= BXConfig.TRAITS_CONFIG.EMC_LOWER_RATIO +
                    (BXConfig.TRAITS_CONFIG.EMC_UPPER_RATIO - BXConfig.TRAITS_CONFIG.EMC_LOWER_RATIO)
                            * filled;
            MathUtils.nbtModify(player,id,(int)amt,0,BXConfig.TRAITS_CONFIG.EMC_LIMIT);
        }
        return amt;
    }

    public static class EMCArmorTrait extends BXLibArmorTrait {
        public EMCArmorTrait(){
            super(name);
        }

        @Override
        public float onHurt(ItemStack armor, EntityPlayer player, DamageSource source, float damage, float newDamage, LivingHurtEvent evt){
            int emc = (int) (MathUtils.nbtGetValue(player,id) * BXConfig.TRAITS_CONFIG.EMC_SHIELD_RATIO);
            if(emc > 0){
                MathUtils.nbtModify(player,id,Math.negateExact(emc));
                return Math.max(0,newDamage-emc * BXConfig.TRAITS_CONFIG.EMC_SHIELD_REDUCE_RATIO);
            }
            return newDamage;
        }
    }
}
