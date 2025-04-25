package emcapi.bxlib;

import emcapi.bxlib.gui.GuiModRisk;
import emcapi.bxlib.proxy.CommonProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static emcapi.bxlib.proxy.CommonProxy.modids;

@Mod(modid = BXLib.MODID, name = BXLib.NAME, version = BXLib.VERSION,dependencies = "required-after:mantle;required-after:conarm;required-after:tconstruct;required-after:coffeework")
public class BXLib
{
    public static final String MODID = "bxlib";
    public static final String NAME = "BX Lib";
    public static final String VERSION = "1.0.3";

    @SidedProxy(modId = MODID,clientSide = "emcapi.bxlib.proxy.ClientProxy",serverSide = "emcapi.bxlib.proxy.CommonProxy")
    public static CommonProxy commonProxy;
    public static boolean token = false;

    public BXLib(){
        MinecraftForge.EVENT_BUS.register(this);
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
       commonProxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        commonProxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        commonProxy.postInit(event);
    }

    @SubscribeEvent
    public void onDisplayGui(GuiOpenEvent event){
        if(!token && event.getGui() instanceof GuiMainMenu){
            if(modids.stream().noneMatch(Loader::isModLoaded)){
                token = true;
                return;
            }
            event.setGui(new GuiModRisk());
        }
    }
}
