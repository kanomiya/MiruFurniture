
package com.kanomiya.mcmod.mirufurniture;

import com.kanomiya.mcmod.mirufurniture.block.BlockOnetimeGlassCase;
import com.kanomiya.mcmod.mirufurniture.block.BlockStorageShelf;
import com.kanomiya.mcmod.mirufurniture.item.ItemBlockOnetimeGlassCase;
import com.kanomiya.mcmod.mirufurniture.tileentity.TileEntityOnetimeGlassCase;
import com.kanomiya.mcmod.mirufurniture.tileentity.TileEntityStorageShelf;

import net.minecraft.block.Block;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * @author Kanomiya
 *
 */
public class MFBlocks {

	public static BlockOnetimeGlassCase blockOnetimeGlassCase_cube;
	public static BlockStorageShelf blockStorageShelf;

	public static void init(boolean client) {
		register(blockOnetimeGlassCase_cube = new BlockOnetimeGlassCase(), ItemBlockOnetimeGlassCase.class, "blockOnetimeGlassCase_cube", false);
		register(blockStorageShelf = new BlockStorageShelf(), "blockStorageShelf", true);

		// ItemBlock
		GameRegistry.registerTileEntity(TileEntityOnetimeGlassCase.class, "tileOnetimeGlassCase");
		GameRegistry.registerTileEntity(TileEntityStorageShelf.class, "tileStorageShelf");

		if (client) {
			Item itemBlock;
			String key;

			itemBlock = Item.getItemFromBlock(blockOnetimeGlassCase_cube);
			key = MiruFurniture.MODID + ":blockOnetimeGlassCase_cube";

			ModelBakery.addVariantName(itemBlock, key, key + "_broken");
			ModelLoader.setCustomModelResourceLocation(itemBlock, 0, new ModelResourceLocation(key, "inventory"));
			ModelLoader.setCustomModelResourceLocation(itemBlock, 1, new ModelResourceLocation(key + "_broken", "inventory"));

		}


	}



	public static void register(Block block, Class<? extends ItemBlock> itemclass, String name, String infix, String[] metaNames, boolean modelRegistFlag) {

		block.setUnlocalizedName(name);
		GameRegistry.registerBlock(block, itemclass, name);

		if (modelRegistFlag) {
			String keyName = MiruFurniture.MODID + ":" + name;
			Item itemBlock = Item.getItemFromBlock(block);

			if (metaNames != null && 0 < metaNames.length) {
				for (int index=0; index<metaNames.length; index++) {
					boolean flag = (metaNames[index] != null && ! metaNames[index].equals(""));

					String key = keyName + ((flag) ? infix + metaNames[index] : "");
					ModelResourceLocation location = new ModelResourceLocation(key, "inventory");

					ModelBakery.addVariantName(itemBlock, key);
					ModelLoader.setCustomModelResourceLocation(itemBlock, index, location);

				}
			} else {
				ModelLoader.setCustomModelResourceLocation(itemBlock, 0, new ModelResourceLocation(keyName, "inventory"));
			}
		}

	}


	public static void register(Block block, Class<? extends ItemBlock> itemclass, String name, String[] metaNames, boolean modelRegistFlag) {
		register(block, itemclass, name, "_", metaNames, modelRegistFlag);
	}

	public static void register(Block block, Class<? extends ItemBlock> itemclass, String name, int metaSize, boolean modelRegistFlag) {
		register(block, itemclass, name, "_", new String[metaSize], modelRegistFlag);
	}

	public static void register(Block block, Class<? extends ItemBlock> itemclass, String name, boolean modelRegistFlag) {
		register(block, itemclass, name, null, null, modelRegistFlag);
	}



	public static void register(Block block, String name, String[] metaNames, boolean modelRegistFlag) {
		register(block, ItemBlock.class, name, "_", metaNames, modelRegistFlag);
	}

	public static void register(Block block, String name, int metaSize, boolean modelRegistFlag) {
		register(block, ItemBlock.class, name, "_", new String[metaSize], modelRegistFlag);
	}

	public static void register(Block block, String name, boolean modelRegistFlag) {
		register(block, ItemBlock.class, name, null, null, modelRegistFlag);
	}





}
