package emcapi.bxlib.mixin;

import emcapi.bxlib.proxy.CommonProxy;
import net.langball.coffee.villager.CoffeeVillagerHouse;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraftforge.fml.common.Loader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

import static emcapi.bxlib.proxy.CommonProxy.modids;

/**
 *  @Author Biggest_Xuan
 *  2023/01/11
 */

@Mixin(value = CoffeeVillagerHouse.class,remap = false)
public abstract class MixinCoffeeHouse {
    @Inject(method = "func_74875_a",at=@At("HEAD"),cancellable = true)
    public void spawn(World world, Random rand, StructureBoundingBox box, CallbackInfoReturnable<Boolean> cir){
        cir.setReturnValue(false);
    }
}
