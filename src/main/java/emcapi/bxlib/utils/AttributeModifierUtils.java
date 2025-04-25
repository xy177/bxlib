package emcapi.bxlib.utils;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

import java.util.UUID;

/**
 *  @Author Biggest_Xuan
 *  2023/01/09
 */

public class AttributeModifierUtils {
    public static final UUID THD_MODIFY_HEALTH_ID = UUID.fromString("a80fcb74-82f3-4e06-8df7-7d8031e8976e");
    public static final String THD_MODIFY_HEALTH_NAME = "THD.HealthModifier";
    public static final String THD_HEALTH_MAP = "THD.health_map";

    public static void setPlayerAdditionHealth(EntityPlayer player,float value){
        IAttributeInstance instance = player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
        AttributeModifier modifier = instance.getModifier(THD_MODIFY_HEALTH_ID);
        if(modifier != null){
            instance.removeModifier(THD_MODIFY_HEALTH_ID);
        }
        instance.applyModifier(new AttributeModifier(THD_MODIFY_HEALTH_ID,THD_MODIFY_HEALTH_NAME,value,0));
    }
    public static void setPlayerAdditionHealth(EntityPlayer player, String key, float value){
        IAttributeInstance instance = player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
        AttributeModifier modifier = instance.getModifier(THD_MODIFY_HEALTH_ID);
        if(modifier != null){
            instance.removeModifier(THD_MODIFY_HEALTH_ID);
        }
        NBTTagCompound map = player.getEntityData().getCompoundTag(THD_HEALTH_MAP);
        map.setFloat(key,value);
        float result = 0;
        for (String entry:map.getKeySet()) {
            result += map.getFloat(entry);
        }
        player.getEntityData().setTag(THD_HEALTH_MAP,map);
        instance.applyModifier(new AttributeModifier(THD_MODIFY_HEALTH_ID,THD_MODIFY_HEALTH_NAME,result,0));
    }
}
