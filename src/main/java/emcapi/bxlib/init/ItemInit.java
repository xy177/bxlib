package emcapi.bxlib.init;

import emcapi.bxlib.BXLib;
import emcapi.bxlib.item.EMCWorldIngot;
import emcapi.bxlib.item.ItemRainFlyingStaff;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *  @Author Biggest_Xuan
 *  2023/01/11
 */

@Mod.EventBusSubscriber(modid = BXLib.MODID)
public class ItemInit {
    private static final List<Item> targets = new LinkedList<>();
    public static Item EMCWorldIngot = add(new EMCWorldIngot());
    public static Item RainFlyingStaff = add(new ItemRainFlyingStaff());
    private static Item add(Item target){
        targets.add(target);
        return target;
    }
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event){
        IForgeRegistry<Item> registry = event.getRegistry();
        for (Item target : targets) {
            registry.register(target);
        }
    }
    @SubscribeEvent
    public static void onAddModels(ModelRegistryEvent event){
        for (Item item:targets) {
            ModelLoader.setCustomModelResourceLocation(
                    item,
                    0,
                    new ModelResourceLocation(Objects.requireNonNull(item.getRegistryName()), "inventory")
            );

        }

    }
}
