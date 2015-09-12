
package com.kanomiya.mcmod.mirufurniture;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * @author Kanomiya
 *
 */
public class MFRecipes {

	public static void preInit(FMLPreInitializationEvent event) {
		GameRegistry.addRecipe(new ItemStack(MFBlocks.blockOnetimeGlassCase_cube),
				"CCC",
				"C C",
				"CCC",
				'C',Blocks.hardened_clay
		);

		GameRegistry.addRecipe(new ItemStack(MFBlocks.blockOnetimeGlassCase_cube),
				"CCC",
				"C C",
				"CCC",
				'C',Blocks.stained_hardened_clay
		);

		GameRegistry.addRecipe(new ItemStack(MFBlocks.blockOnetimeGlassCase_cube),
				"CCC",
				"CGC",
				"CCC",
				'C',Blocks.hardened_clay,
				'G',Blocks.glass_pane
		);

		GameRegistry.addRecipe(new ItemStack(MFBlocks.blockOnetimeGlassCase_cube),
				"CCC",
				"CGC",
				"CCC",
				'C',Blocks.stained_hardened_clay,
				'G',Blocks.glass_pane
		);

		GameRegistry.addShapelessRecipe(new ItemStack(MFBlocks.blockOnetimeGlassCase_cube, 1, 0),
				new ItemStack(MFBlocks.blockOnetimeGlassCase_cube, 1, 1),
				Blocks.glass_pane
		);

		GameRegistry.addRecipe(new ItemStack(MFBlocks.blockStorageShelf),
				"PPP",
				"HHH",
				"PPP",
				'P',Blocks.planks,
				'H',Blocks.wooden_slab
		);

	}

	public static void init(FMLInitializationEvent event) {  }
	public static void postInit(FMLPostInitializationEvent event) {  }


}
