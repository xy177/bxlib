package emcapi.bxlib.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import slimeknights.tconstruct.library.utils.ToolHelper;

/**
 *  @Author Biggest_Xuan
 *  2023/01/13
 */

@Mixin(value = ToolHelper.class,remap = false)
public abstract class MixinToolHelper {

    @Inject(method = "calcCutoffDamage",at = @At("HEAD"), cancellable = true)
    private static void calcDamage(float damage, float cutoff, CallbackInfoReturnable<Float> cir){
        cir.setReturnValue(damage);
        cir.cancel();
    }
}
