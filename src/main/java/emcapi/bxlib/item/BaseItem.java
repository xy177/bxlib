package emcapi.bxlib.item;

import emcapi.bxlib.BXLib;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

/**
 *  @Author Biggest_Xuan
 *  2023/01/11
 */

public abstract class BaseItem extends Item {
    public BaseItem(String name){
        setUnlocalizedName(BXLib.MODID + ":" + name);
        setRegistryName(BXLib.MODID,name);
        setCreativeTab(CreativeTabs.MISC);
    }
}
