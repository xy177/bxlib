package emcapi.bxlib.tconstruct.traits;

import emcapi.bxlib.BXConfig;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 *  @Author Biggest_Xuan
 *  2023/01/08
 */

public class LeonTrait extends BXLibTrait {
    public LeonTrait() {
        super("leon");
    }

    @Override
    public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float newDamage, boolean isCritical) {
        if(player instanceof EntityPlayer){
            EntityPlayer player1 = (EntityPlayer) player;
            NBTTagCompound nbt = player1.getEntityData();
            if(nbt.hasKey("thd_attack_time")){
                int time = nbt.getInteger("thd_attack_time");
                if(time < BXConfig.TRAITS_CONFIG.LEON_TIME){
                    return BXConfig.TRAITS_CONFIG.LEON_RATIO * newDamage;
                }
            }
        }
        return newDamage;
    }
}
