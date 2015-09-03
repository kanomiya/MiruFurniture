package com.kanomiya.mcmod.mirufurniture.tileentity;

import net.minecraft.item.ItemStack;

/**
 * @author Kanomiya
 *
 */
public class TileEntityOnetimeGlassCase extends ITileEntityWithInventory {

	public TileEntityOnetimeGlassCase() {
		super();

	}

	public void setDisplayedItem(ItemStack stackIn) {
		setInventorySlotContents(0, stackIn);
	}

	public ItemStack getDisplayedItem() { return getStackInSlot(0); }



	/**
	* @inheritDoc
	*/
	@Override public String getName() { return "tileEntityOnetimeGlassCase"; }

	/**
	* @inheritDoc
	*/
	@Override public int getSizeInventory() { return 1; }

	/**
	* @inheritDoc
	*/
	@Override public int getInventoryStackLimit() { return 1; }

	/**
	* @inheritDoc
	*/
	@Override public boolean isItemValidForSlot(int index, ItemStack stack) { return true; }



}
