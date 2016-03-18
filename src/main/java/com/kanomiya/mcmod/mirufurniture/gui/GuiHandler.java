package com.kanomiya.mcmod.mirufurniture.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import com.kanomiya.mcmod.mirufurniture.MiruFurniture;
import com.kanomiya.mcmod.mirufurniture.inventory.ContainerStorageShelf;
import com.kanomiya.mcmod.mirufurniture.tileentity.TileEntityStorageShelf;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		if (id == MiruFurniture.GUIID_STORAGESHELF) {
			TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
			if (tileEntity instanceof TileEntityStorageShelf) {
				return new ContainerStorageShelf(player.inventory, (TileEntityStorageShelf) tileEntity);
			}
		}


		return null;
	}

	// returns an instance of the gui thingamabob
	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		if (id == MiruFurniture.GUIID_STORAGESHELF) {
			TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
			if (tileEntity instanceof TileEntityStorageShelf) {
				return new GuiStorageShelf(player.inventory, (TileEntityStorageShelf) tileEntity);
			}
		}

		return null;
	}

}
