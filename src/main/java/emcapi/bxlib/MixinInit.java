package emcapi.bxlib;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.asm.mixin.transformer.meta.MixinInner;
import org.spongepowered.asm.mixin.transformer.meta.MixinProxy;
import zone.rong.mixinbooter.MixinLoader;

/**
 *  @Author Biggest_Xuan
 *  2023/01/11
 */
@MixinLoader
public class MixinInit {
    public MixinInit(){
        Mixins.addConfiguration("mixins.bxlib.json");
    }
}
