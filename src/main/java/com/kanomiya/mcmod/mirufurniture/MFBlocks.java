
package com.kanomiya.mcmod.mirufurniture;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.kanomiya.mcmod.kanomiyacore.KanomiyaCore;
import com.kanomiya.mcmod.kanomiyacore.util.GameRegistryUtils;
import com.kanomiya.mcmod.mirufurniture.block.BlockOnetimeGlassCase;
import com.kanomiya.mcmod.mirufurniture.block.BlockStorageShelf;
import com.kanomiya.mcmod.mirufurniture.item.ItemBlockOnetimeGlassCase;
import com.kanomiya.mcmod.mirufurniture.render.TileEntityOnetimeGlassCaseRenderer;
import com.kanomiya.mcmod.mirufurniture.render.TileEntityStorageShelfRenderer;
import com.kanomiya.mcmod.mirufurniture.tileentity.TileEntityOnetimeGlassCase;
import com.kanomiya.mcmod.mirufurniture.tileentity.TileEntityStorageShelf;

/**
 * @author Kanomiya
 *
 */
public class MFBlocks {

	public static BlockOnetimeGlassCase blockOnetimeGlassCase_cube;
	public static BlockStorageShelf blockStorageShelf;
	// public static Block block;


	public static void preInit(FMLPreInitializationEvent event, KanomiyaCore core) {
		final boolean client = event.getSide().isClient();

		GameRegistryUtils utils = core.getGameRegistryUtils();

		utils.registerBlock(blockOnetimeGlassCase_cube = new BlockOnetimeGlassCase(), ItemBlockOnetimeGlassCase.class, "blockOnetimeGlassCase_cube", new String[] { "", "broken" }, client);
		utils.registerBlock(blockStorageShelf = new BlockStorageShelf(), "blockStorageShelf", client);
		// GameRegistryUtils.registerBlock(block = new Block(), "block", client);

		// TileEntity
		GameRegistry.registerTileEntity(TileEntityOnetimeGlassCase.class, "tileOnetimeGlassCase");
		GameRegistry.registerTileEntity(TileEntityStorageShelf.class, "tileStorageShelf");

		if (client) {
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntityOnetimeGlassCase.class, new TileEntityOnetimeGlassCaseRenderer());
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntityStorageShelf.class, new TileEntityStorageShelfRenderer());

		}
	}

	public static void init(FMLInitializationEvent event, KanomiyaCore core) {  }
	public static void postInit(FMLPostInitializationEvent event, KanomiyaCore core) {  }



}
