package com.kanomiya.mcmod.mirufurniture.tileentity;

import com.kanomiya.mcmod.core.tileentity.ITileEntityWithInventory;
import com.kanomiya.mcmod.mirufurniture.MFConfig;

import net.minecraft.item.ItemStack;

public class TileEntityStorageShelf extends ITileEntityWithInventory {

	public TileEntityStorageShelf() {
		super();
	}



	public float getEnchantPowerBonus() {
		int count = 0;

		for (int i=0, i_=getSizeInventory(); i<i_; i++) {
			ItemStack stack = getStackInSlot(i);
			if (stack != null) {
				count += stack.stackSize;
			}
		}

		return (3 <= count) ? 1 : 0;
	}


	// --*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--
	// --*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--
	// IInventory's Methods
	// --*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--
	// --*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--]

	@Override public String getName() { return hasCustomName() ? customName : "container.storageshelf"; }

	@Override public int getSizeInventory() { return 14; }
	@Override public int getInventoryStackLimit() { return 1; }

	@Override public boolean isItemValidForSlot(int index, ItemStack stack) { return MFConfig.isBookItem(stack); }





}
