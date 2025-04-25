package emcapi.bxlib.tconstruct.traits;

import net.minecraft.client.resources.I18n;
import slimeknights.tconstruct.library.traits.AbstractTrait;

/**
 *  @Author Biggest_Xuan
 *  2023/01/12
 */

public abstract class BXLibTrait extends AbstractTrait {
    private final String name;

    private BXLibTrait(String identifier, int color) {
        super(identifier, color);
        name = identifier;
    }

    protected BXLibTrait(String name){
        this(name,0x800000);
    }

    @Override
    public String getLocalizedName() {
        return I18n.format("bxlib.trait."+name+".name");
    }

    @Override
    public String getLocalizedDesc() {
        return I18n.format("bxlib.trait."+name+".desc");
    }
}
