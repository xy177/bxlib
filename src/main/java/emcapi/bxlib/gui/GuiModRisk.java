package emcapi.bxlib.gui;

import emcapi.bxlib.BXLib;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.client.GuiModsMissing;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class GuiModRisk extends GuiScreen {
    public GuiModRisk(){
        super();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        for (int i = 0; i < 4; i++) {
            drawCenteredString(fontRenderer,I18n.format("bxlib.gui.extra_mod" + (i+1)),width / 2,height / 3 + 10*i,0xffffff);
        }
    }

    @Override
    public void initGui() {
        int id = 0;
        addButton(new GuiButton(id++,this.width / 2 - 180,this.height - 38,150,20, I18n.format("bxlib.gui.extra_mod_sure")){
            @Override
            public void mouseReleased(int mouseX, int mouseY) {
                GuiModRisk.this.sure();
            }
        });
        addButton(new GuiButton(id++,this.width / 2 + 30,this.height - 38,50,20,I18n.format("bxlib.gui.extra_mod_deny")){
            @Override
            public void mouseReleased(int mouseX, int mouseY) {
                GuiModRisk.this.deny();
            }
        });
    }

    private void deny() {
        FMLCommonHandler.instance().exitJava(0,false);
    }

    private void sure() {
        BXLib.token = true;
        Minecraft.getMinecraft().displayGuiScreen(null);
    }
}
