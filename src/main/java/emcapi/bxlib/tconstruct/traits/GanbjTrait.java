package emcapi.bxlib.tconstruct.traits;

import emcapi.bxlib.BXConfig;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

/**
 *  @Author Biggest_Xuan
 *  2023/01/12
 */

public class GanbjTrait extends BXLibTrait {
    public GanbjTrait() {
        super("ganbj");
    }

    @Override
    public void onHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, boolean isCritical) {
        Potion bleed = Potion.REGISTRY.getObject(new ResourceLocation("astralsorcery","potionbleed"));
        target.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS,200,0));
        if(bleed != null){
            target.addPotionEffect(new PotionEffect(bleed,100,0));
        }
    }

    @Override
    public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float newDamage, boolean isCritical) {
        player.heal(2f);
        return newDamage * (player.getHealth() >= BXConfig.TRAITS_CONFIG.GANBJ_LIMIT ? ((player.getHealth() - BXConfig.TRAITS_CONFIG.GANBJ_LIMIT)  * BXConfig.TRAITS_CONFIG.GANBJ_RATIO) + 1 : 1);
    }
}
