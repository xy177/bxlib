package emcapi.bxlib.tconstruct.traits;

import c4.conarm.lib.traits.AbstractArmorTrait;
import net.minecraft.client.resources.I18n;

/**
 *  @Author Biggest_Xuan
 *  2023/01/13
 */

public abstract class BXLibArmorTrait extends AbstractArmorTrait {
    private final String name;

    private BXLibArmorTrait(String identifier, int color) {
        super(identifier, color);
        name = identifier;
    }

    protected BXLibArmorTrait(String name){
        this(name,0x800000);
    }

    @Override
    public String getLocalizedName() {
        return I18n.format("bxlib.armor_trait."+name+".name");
    }

    @Override
    public String getLocalizedDesc() {
        return I18n.format("bxlib.armor_trait."+name+".desc");
    }
}
