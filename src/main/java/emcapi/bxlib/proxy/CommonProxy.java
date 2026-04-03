package emcapi.bxlib.proxy;

import emcapi.bxlib.init.ItemInit;
import emcapi.bxlib.tconstruct.tools.TinkerNunchaku;
import emcapi.bxlib.tconstruct.traits.*;
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
        TinkerRegistry.addTrait(TinkerNunchaku.COMBO_TRAIT);
        TinkerRegistry.addTrait(new LeonTrait());
        TinkerRegistry.addTrait(new GanbjTrait());
        TinkerRegistry.addTrait(new EMCTrait());
        TinkerRegistry.addTrait(new EMCTrait.EMCArmorTrait());
        TinkerRegistry.addTrait(new DistanceTrait());
        TinkerRegistry.addTrait(new DistanceTrait.DistanceArmorTrait());
    }


    public void postInit(FMLPostInitializationEvent event) {
    }


    public void init(FMLInitializationEvent event) {
        // ★ 注册到工具站（Tool Station）
        TinkerRegistry.registerToolCrafting(ItemInit.TinkerNunchaku);
        // 若希望仅在工具锻造台（Tool Forge）合成，改用：
        // TinkerRegistry.registerToolForgeCrafting(ItemInit.TinkerNunchaku);
    }
}


