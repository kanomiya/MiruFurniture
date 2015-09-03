package com.kanomiya.mcmod.mirufurniture;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MFCreativeTab extends CreativeTabs {
	public MFCreativeTab() {
		super("mirufurniture");
	}

	@Override @SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return Item.getItemFromBlock(MFBlocks.blockOnetimeGlassCase_cube);
	}

}
