package emcapi.bxlib.proxy;

import emcapi.bxlib.init.ItemInit;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 *  @Author Biggest_Xuan
 *  2023/01/11
 */

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);

        net.minecraftforge.common.MinecraftForge.EVENT_BUS.register(this);

        // ★ 注册客户端双节棍处理器（仅客户端）
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.register(
                new emcapi.bxlib.client.NunchakuClientHandler()
        );
    }
    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);

        // 注册双节棍的工具站 GUI 槽位坐标
        // 三个槽位对应：[0]杆1, [1]杆2, [2]绑定结
        // 坐标参考工具站 UI 布局（中心约在 x=52, y=48），下方为示例值，可调整
        if (ItemInit.TinkerNunchaku != null) {
            slimeknights.tconstruct.library.client.ToolBuildGuiInfo info =
                    new slimeknights.tconstruct.library.client.ToolBuildGuiInfo(ItemInit.TinkerNunchaku);
            info.addSlotPosition(16, 32); // 杆1
            info.addSlotPosition(16, 56); // 杆2
            info.addSlotPosition(48, 44); // 绑定结（居中）
            slimeknights.tconstruct.library.TinkerRegistryClient.addToolBuilding(info);
        }
    }

    @SubscribeEvent
    public void registerModels(ModelRegistryEvent event) {
        if (ItemInit.TinkerNunchaku != null) {
            // 同时注册两个变体，确保匠魂都烘焙它们
            /*AI的注册方式有很大问题，之前能跑很神奇...
            net.minecraftforge.client.model.ModelLoader.registerItemVariants(
                    ItemInit.TinkerNunchaku,
                    new net.minecraft.util.ResourceLocation("bxlib", "tools/tinkernunchaku.tcon"),
                    new net.minecraft.util.ResourceLocation("bxlib", "tools/tinkernunchaku_spinning.tcon")
            );

             */
            slimeknights.tconstruct.common.ModelRegisterUtil.registerToolModel(ItemInit.TinkerNunchaku);
        }
    }

    /*
    @SubscribeEvent
    public void onModelBake(ModelBakeEvent event) {
        if (ItemInit.TinkerNunchaku == null) return;

        net.minecraft.client.renderer.block.model.ModelResourceLocation mrlNormal =
                new net.minecraft.client.renderer.block.model.ModelResourceLocation(
                        new net.minecraft.util.ResourceLocation("bxlib", "tools/tinkernunchaku.tcon"), "inventory");

        net.minecraft.client.renderer.block.model.ModelResourceLocation mrlSpinning =
                new net.minecraft.client.renderer.block.model.ModelResourceLocation(
                        new net.minecraft.util.ResourceLocation("bxlib", "tools/tinkernunchaku_spinning.tcon"), "inventory");

        net.minecraft.client.renderer.block.model.IBakedModel normalModel =
                event.getModelRegistry().getObject(mrlNormal);
        net.minecraft.client.renderer.block.model.IBakedModel spinningModel =
                event.getModelRegistry().getObject(mrlSpinning);

        // 两个都烘焙成功才替换，否则保持原样（不会崩溃）
        if (normalModel != null && spinningModel != null) {
            event.getModelRegistry().putObject(mrlNormal,
                    new emcapi.bxlib.client.NunchakuBakedModel(normalModel, spinningModel));
        }
    }

     */

}
