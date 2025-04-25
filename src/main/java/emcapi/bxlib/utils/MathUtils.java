package emcapi.bxlib.utils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nonnull;

/**
 *  @Author Biggest_Xuan
 *  2023/01/12
 */

public class MathUtils {
    public static double twoEntityDistance(Entity entity1,Entity entity2){
        BlockPos pos1 = entity1.getPosition();
        BlockPos pos2 = entity2.getPosition();
        return Math.sqrt(Math.pow(pos2.getX()-pos1.getX(),2)+Math.pow(pos2.getY()-pos1.getY(),2)+Math.pow(pos2.getZ()-pos1.getZ(),2));
    }

    public static int nbtGetValue(@Nonnull EntityPlayer player, String name){
        NBTTagCompound nbt = player.getEntityData();
        return nbt.hasKey(name) ? nbt.getInteger(name) : 0;
    }

    public static void nbtModify(@Nonnull EntityPlayer player,String name,int value,int min,int max){
        NBTTagCompound nbt = player.getEntityData();
        if(nbt.hasKey(name)){
            int f = nbt.getInteger(name) + value;
            nbt.setInteger(name,Math.min(Math.max(min,f),max));
            player.writeEntityToNBT(nbt);
        }
    }

    public static void nbtModify(@Nonnull EntityPlayer player,String name,int value){
        nbtModify(player,name,value,0,Integer.MAX_VALUE);
    }
}
