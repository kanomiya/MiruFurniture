package com.kanomiya.mcmod.mirufurniture.inventory;

import com.kanomiya.mcmod.mirufurniture.MFConfig;
import com.kanomiya.mcmod.mirufurniture.tileentity.TileEntityStorageShelf;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

// ContainerFurnace
// ContainerEnchantment
public class ContainerStorageShelf extends Container {

	protected TileEntityStorageShelf tileEntity;
	int slotNum = 0;

	public ContainerStorageShelf(InventoryPlayer inventoryPlayer, TileEntityStorageShelf te) {
		tileEntity = te;

		for (int i = 0; i < 2; ++i) {
			for (int j = 0; j < 7; ++j) {
				addSlotToContainer(new FixedSlot(tileEntity, slotNum, 24 + j * 18, 30 + i * 18));
				++slotNum;
			}
		}

		bindPlayerInventory(inventoryPlayer);

		tileEntity.openInventory(inventoryPlayer.player);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return tileEntity.isUseableByPlayer(player);
	}


	protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {

		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 94 + i * 18));
			}
		}

		for (int i = 0; i < 9; ++i) {
			addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 152));
		}
	}

	/**
	 * シフトクリック時
	 */
	@Override public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int slotNum) {
		ItemStack stack = null;
		Slot slot = inventorySlots.get(slotNum);

		if (slot == null || ! slot.getHasStack()) return stack;

		ItemStack slotStack = slot.getStack();
		stack = slotStack.copy();

		if (slotNum < tileEntity.getSizeInventory())
		{
			if (! mergeItemStack(stack, tileEntity.getSizeInventory(), inventorySlots.size(), true))
			{
				return null;
			}
		}
		else if(MFConfig.isBookItem(slotStack))
		{
			for (int i=0, i_=tileEntity.getSizeInventory(); i<i_; i++) {
				ItemStack target = tileEntity.getStackInSlot(i);

				if (target == null) {
					stack.stackSize = 1;
					tileEntity.setInventorySlotContents(i, stack);

		            slotStack.stackSize --;
		            if (slotStack.stackSize == 0) slot.putStack((ItemStack) null);

					return slotStack;
				}

			}

			return null;
		}
		else if (! mergeItemStack(stack, 0, tileEntity.getSizeInventory(), false))
		{
			return null;
		}

		if (stack.stackSize == 0) slot.putStack((ItemStack) null);
		else slot.putStack(stack);

		return stack;
	}


	@Override
	public void onContainerClosed(EntityPlayer p_75134_1_) {
		super.onContainerClosed(p_75134_1_);
		tileEntity.closeInventory(p_75134_1_);
	}

}
