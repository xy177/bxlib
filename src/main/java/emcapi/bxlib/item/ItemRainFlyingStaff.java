package emcapi.bxlib.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldInfo;

import javax.annotation.Nonnull;
import java.util.Random;


public class ItemRainFlyingStaff extends BaseItem{
    public ItemRainFlyingStaff(){
        super("rain_flying_staff");
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World worldIn, @Nonnull EntityPlayer playerIn,@Nonnull EnumHand handIn) {

        int i = (300 + (new Random()).nextInt(600)) * 20;
        WorldInfo info = worldIn.getWorldInfo();

        if(worldIn.isThundering()){
            info.setCleanWeatherTime(i);
            info.setRainTime(0);
            info.setThunderTime(0);
            info.setRaining(false);
            info.setThundering(false);
        }else if(worldIn.isRaining()){
            info.setCleanWeatherTime(0);
            info.setRainTime(i);
            info.setThunderTime(i);
            info.setRaining(true);
            info.setThundering(true);
        }else {
            info.setCleanWeatherTime(0);
            info.setRainTime(i);
            info.setThunderTime(i);
            info.setRaining(true);
            info.setThundering(false);
        }
        playerIn.getCooldownTracker().setCooldown(this,1000);
        return ActionResult.newResult(EnumActionResult.SUCCESS,playerIn.getHeldItem(handIn));
    }
}
