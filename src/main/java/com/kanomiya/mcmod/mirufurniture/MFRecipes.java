
package com.kanomiya.mcmod.mirufurniture;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.kanomiya.mcmod.kanomiyacore.KanomiyaCore;

/**
 * @author Kanomiya
 *
 */
public class MFRecipes {

	public static void preInit(FMLPreInitializationEvent event, KanomiyaCore core) {
		GameRegistry.addRecipe(new ItemStack(MFBlocks.blockOnetimeGlassCase_cube),
				"CCC",
				"C C",
				"CCC",
				'C',Blocks.HARDENED_CLAY
		);

		GameRegistry.addRecipe(new ItemStack(MFBlocks.blockOnetimeGlassCase_cube),
				"CCC",
				"C C",
				"CCC",
				'C',Blocks.STAINED_HARDENED_CLAY
		);

		GameRegistry.addRecipe(new ItemStack(MFBlocks.blockOnetimeGlassCase_cube),
				"CCC",
				"CGC",
				"CCC",
				'C',Blocks.HARDENED_CLAY,
				'G',Blocks.GLASS_PANE
		);

		GameRegistry.addRecipe(new ItemStack(MFBlocks.blockOnetimeGlassCase_cube),
				"CCC",
				"CGC",
				"CCC",
				'C',Blocks.STAINED_HARDENED_CLAY,
				'G',Blocks.GLASS_PANE
		);

		GameRegistry.addShapelessRecipe(new ItemStack(MFBlocks.blockOnetimeGlassCase_cube, 1, 0),
				new ItemStack(MFBlocks.blockOnetimeGlassCase_cube, 1, 1),
				Blocks.GLASS_PANE
		);

		GameRegistry.addRecipe(new ItemStack(MFBlocks.blockStorageShelf),
				"PPP",
				"HHH",
				"PPP",
				'P',Blocks.PLANKS,
				'H',Blocks.WOODEN_SLAB
		);

	}

	public static void init(FMLInitializationEvent event, KanomiyaCore core) {  }
	public static void postInit(FMLPostInitializationEvent event, KanomiyaCore core) {  }


}
