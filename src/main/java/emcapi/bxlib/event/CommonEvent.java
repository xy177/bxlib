package emcapi.bxlib.event;

import emcapi.bxlib.BXConfig;
import emcapi.bxlib.BXLib;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentBase;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *  @Author Biggest_Xuan
 *  2023/01/07
 */

@Mod.EventBusSubscriber(modid = BXLib.MODID)
public class CommonEvent {
    private static final String id = "thd_attack_time";
    private static final String id_1 = "shooter_count";
    private static final String id_2 = "laser_count";
    public static final String id_3 = "emcworld";
    public static int[] numbers;
    static {
        numbers = new int[BXConfig.ENCHANTMENT_CONFIG.BANNED_ID.length];
        for (int index = 0; index < numbers.length; index++) {
            try{
                numbers[index] = Integer.parseInt(BXConfig.ENCHANTMENT_CONFIG.BANNED_ID[index]);
            }catch (NumberFormatException exception){
                System.out.println("No number:" + BXConfig.ENCHANTMENT_CONFIG.BANNED_ID[index]);
                exception.printStackTrace();
            }
        }
    }
    @SubscribeEvent
    public static void rightClickEvent(PlayerInteractEvent.RightClickItem event){
        EntityPlayer player = event.getEntityPlayer();
        if(player.world.isRemote) return;
        ItemStack stack = event.getItemStack();
        ResourceLocation rl = stack.getItem().getRegistryName();
        playerUseCD(
                player,rl,
                new ResourceLocation("tinkersaether","dart_shooter"),
                BXConfig.DART_SHOOTER_CONFIG.COUNT,
                id_1,
                BXConfig.DART_SHOOTER_CONFIG.INTERVAL
        );
        playerUseCD(
                player,rl,
                new ResourceLocation("plustic","laser_gun"),
                BXConfig.LASER_CONFIG.COUNT,
                id_2,
                BXConfig.LASER_CONFIG.INTERVAL
        );
    }

    @SubscribeEvent
    public static void playerTick(TickEvent.PlayerTickEvent event){
        EntityPlayer player = event.player;
        if(player == null || player.world.isRemote || event.phase == TickEvent.Phase.END) return;
        NBTTagCompound nbt = player.getEntityData();
        for(String s : new String[]{id,id_1,id_2,id_3}){
            if(!nbt.hasKey(s)){
                nbt.setInteger(s,0);
            }
        }
        int time = nbt.getInteger(id);
        nbt.setInteger(id,time+1);
        int emc = nbt.getInteger(id_3);
        if(emc > 0){
            nbt.setInteger(id_3,emc-1);
            TextComponentBase base = new TextComponentString("EMC:"+ (emc - 1));
            player.sendStatusMessage(base.setStyle(new Style().setColor(TextFormatting.GREEN)),true);
        }
        //player.writeEntityToNBT(nbt);//屑轩记住，这函数是用来序列化的，写入用readFrom...
        for(ItemStack s : getAllItems(new LinkedList<>(),player)){
            if(!s.getItem().equals(Items.ENCHANTED_BOOK) && s.isItemEnchanted()){
                Iterator<NBTBase> baseIterator = s.getEnchantmentTagList().iterator();
                while (baseIterator.hasNext()){
                    NBTTagCompound compound = (NBTTagCompound) baseIterator.next();
                    int id = compound.getShort("id");
                    for (int id_2:numbers) {
                        if(id == id_2){
                            baseIterator.remove();
                            break;
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void playerAttack(LivingDamageEvent event){
        DamageSource source = event.getSource();
        if(source.getImmediateSource() instanceof EntityPlayer){
            EntityPlayer player = (EntityPlayer) source.getImmediateSource();
            if(player == null || player.world.isRemote) return;
            NBTTagCompound nbt = player.getEntityData();
            if(nbt.hasKey(id)){
                nbt.setInteger(id,0);
                player.writeEntityToNBT(nbt);
            }
        }
    }
    private static void playerUseCD(EntityPlayer player, @Nullable ResourceLocation eventRL, @Nonnull ResourceLocation stackRL, int countLimit, String countID, int cd){
        NBTTagCompound nbt = player.getEntityData();
        if(eventRL != null && eventRL.equals(stackRL)){
            Item item = Item.getByNameOrId(stackRL.toString());
            int count = nbt.getInteger(countID);
            if(item == null) return;
            if(!player.getCooldownTracker().hasCooldown(item)){
                count++;
                if(count >= countLimit){
                    player.getCooldownTracker().setCooldown(item,cd);
                    count = 0;
                    if(countID.equals(id_2)){
                        player.sendMessage(new TextComponentString("\u67aa\u7ba1\u6563\u70ed"));
                    }
                }
                nbt.setInteger(countID,count);
            }
        player.writeEntityToNBT(nbt);
        }
    }
    private static <T extends List<ItemStack>> T getAllItems(T list,EntityPlayer player){
        list.addAll(player.inventory.mainInventory);
        list.addAll(player.inventory.armorInventory);
        list.addAll(player.inventory.offHandInventory);
        return list;
    }
    private static NonNullList<ItemStack> getAllItems(EntityPlayer player){
        NonNullList<ItemStack> list = NonNullList.create();
        list.addAll(player.inventory.mainInventory);
        list.addAll(player.inventory.armorInventory);
        list.addAll(player.inventory.offHandInventory);
        return list;
    }
}
