package emcapi.bxlib.crafttweaker;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.player.IPlayer;
import emcapi.bxlib.BXLib;
import emcapi.bxlib.utils.AttributeModifierUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static emcapi.bxlib.utils.AttributeModifierUtils.THD_HEALTH_MAP;

/**
 *  @Author Biggest_Xuan
 *  2023/01/07
 */

@ZenRegister
@ZenClass("mods.BXLib")
@SuppressWarnings("unused")
public class CrTUtils {
    @ZenMethod
    public static int getPlayerAttackTime(IPlayer player){
        EntityPlayer player1 = CraftTweakerMC.getPlayer(player);
        NBTTagCompound nbt = player1.getEntityData();
        if(nbt.hasKey("thd_attack_time")){
            return nbt.getInteger("thd_attack_time");
        }
        return 0;
    }
    @ZenMethod
    public static void addPlayerAdditionsHealth(IPlayer player,String key,float value){
        AttributeModifierUtils.setPlayerAdditionHealth(CraftTweakerMC.getPlayer(player),key,value);
    }
    @ZenMethod
    public static void addPlayerAdditionsHealth(IPlayer player,float value){
        AttributeModifierUtils.setPlayerAdditionHealth(CraftTweakerMC.getPlayer(player),"default",value);
    }
}
