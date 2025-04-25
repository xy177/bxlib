package emcapi.bxlib.proxy;

import emcapi.bxlib.tconstruct.traits.DistanceTrait;
import emcapi.bxlib.tconstruct.traits.EMCTrait;
import emcapi.bxlib.tconstruct.traits.GanbjTrait;
import emcapi.bxlib.tconstruct.traits.LeonTrait;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import slimeknights.tconstruct.library.TinkerRegistry;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 *  @Author Biggest_Xuan
 *  2023/01/11
 */

public class CommonProxy {
    public static final List<String> modids = Arrays.asList("torcherino","projecte");
    public void preInit(FMLPreInitializationEvent event) {
        TinkerRegistry.addTrait(new LeonTrait());
        TinkerRegistry.addTrait(new GanbjTrait());
        TinkerRegistry.addTrait(new EMCTrait());
        TinkerRegistry.addTrait(new EMCTrait.EMCArmorTrait());
        TinkerRegistry.addTrait(new DistanceTrait());
        TinkerRegistry.addTrait(new DistanceTrait.DistanceArmorTrait());
    }

    public void init(FMLInitializationEvent event) {

    }

    public void postInit(FMLPostInitializationEvent event) {
    }
}
