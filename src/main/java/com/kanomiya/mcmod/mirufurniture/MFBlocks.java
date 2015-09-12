
package com.kanomiya.mcmod.mirufurniture;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.kanomiya.mcmod.core.util.GameRegistryUtils;
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

	public static void init(boolean client) {
		GameRegistryUtils.registerBlock(blockOnetimeGlassCase_cube = new BlockOnetimeGlassCase(), ItemBlockOnetimeGlassCase.class, "blockOnetimeGlassCase_cube", new String[] { "", "broken" }, client);
		GameRegistryUtils.registerBlock(blockStorageShelf = new BlockStorageShelf(), "blockStorageShelf", client);

		// ItemBlock
		GameRegistry.registerTileEntity(TileEntityOnetimeGlassCase.class, "tileOnetimeGlassCase");
		GameRegistry.registerTileEntity(TileEntityStorageShelf.class, "tileStorageShelf");

		if (client) {
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntityOnetimeGlassCase.class, new TileEntityOnetimeGlassCaseRenderer());
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntityStorageShelf.class, new TileEntityStorageShelfRenderer());

		}


	}



}
