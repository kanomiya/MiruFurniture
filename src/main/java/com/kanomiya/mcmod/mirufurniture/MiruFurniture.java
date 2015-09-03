package com.kanomiya.mcmod.mirufurniture;

import org.apache.logging.log4j.Logger;

import com.kanomiya.mcmod.mirufurniture.gui.GuiHandler;
import com.kanomiya.mcmod.mirufurniture.proxy.CommonProxy;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = MiruFurniture.MODID, name = MiruFurniture.MODID, version = MiruFurniture.VERSION)
public class MiruFurniture {
	public static final String MODID = "mirufurniture";
	public static final String VERSION = "0.10";


	@Mod.Instance("mirufurniture")
	public static MiruFurniture instance;

	@SidedProxy(clientSide="com.kanomiya.mcmod.mirufurniture.proxy.ClientProxy",
				serverSide="com.kanomiya.mcmod.mirufurniture.proxy.CommonProxy")
	public static CommonProxy proxy;
	public static final MFCreativeTab tabMF = new MFCreativeTab();

	public static final int GUIID_STORAGESHELF = 0;

	public static Logger logger;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();

		MFBlocks.init(event.getSide().isClient());

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

		// MinecraftForge.EVENT_BUS.register(this);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {

		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		proxy.registerRenderer();
		MFConfig.init();

	}


}




