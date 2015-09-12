package com.kanomiya.mcmod.core.util;

import net.minecraft.block.Block;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.kanomiya.mcmod.mirufurniture.MiruFurniture;

/**
 * @author Kanomiya
 *
 */
public class GameRegistryUtils {
	public static final String MODID = MiruFurniture.MODID;

	public static void registerModel(Item item, String name, String infix, String[] metaNames) {

	}

	public static void registerBlock(Block block, Class<? extends ItemBlock> itemclass, String name, String infix, String[] metaNames, boolean registerModel) {

		block.setUnlocalizedName(name);
		GameRegistry.registerBlock(block, itemclass, name);

		if (registerModel) {
			String keyName = MODID + ":" + name;
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


	public static void registerBlock(Block block, Class<? extends ItemBlock> itemclass, String name, String[] metaNames, boolean registerModel) {
		registerBlock(block, itemclass, name, "_", metaNames, registerModel);
	}

	public static void registerBlock(Block block, Class<? extends ItemBlock> itemclass, String name, int metaSize, boolean registerModel) {
		registerBlock(block, itemclass, name, "_", new String[metaSize], registerModel);
	}

	public static void registerBlock(Block block, Class<? extends ItemBlock> itemclass, String name, boolean registerModel) {
		registerBlock(block, itemclass, name, null, null, registerModel);
	}



	public static void registerBlock(Block block, String name, String[] metaNames, boolean registerModel) {
		registerBlock(block, ItemBlock.class, name, "_", metaNames, registerModel);
	}

	public static void registerBlock(Block block, String name, int metaSize, boolean registerModel) {
		registerBlock(block, ItemBlock.class, name, "_", new String[metaSize], registerModel);
	}

	public static void registerBlock(Block block, String name, boolean registerModel) {
		registerBlock(block, ItemBlock.class, name, null, null, registerModel);
	}


}
