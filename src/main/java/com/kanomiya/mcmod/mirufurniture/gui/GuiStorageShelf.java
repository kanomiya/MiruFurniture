package com.kanomiya.mcmod.mirufurniture.gui;

import org.lwjgl.opengl.GL11;

import com.kanomiya.mcmod.mirufurniture.MiruFurniture;
import com.kanomiya.mcmod.mirufurniture.inventory.ContainerStorageShelf;
import com.kanomiya.mcmod.mirufurniture.tileentity.TileEntityStorageShelf;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public class GuiStorageShelf extends GuiContainer {
	private static ResourceLocation background = new ResourceLocation(MiruFurniture.MODID + ":textures/gui/guiStorageShelf.png");

	public GuiStorageShelf(InventoryPlayer inventoryPlayer, TileEntityStorageShelf tileEntity) {
		super(new ContainerStorageShelf(inventoryPlayer, tileEntity));
		ySize = 176;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2) {

		fontRendererObj.drawString("Bookshelf", 8, 6, 4210752);
		//draws "Inventory" or your regional equivalent
		fontRendererObj.drawString(I18n.format("container.inventory"), 8, ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(background);
		int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
	}

}
