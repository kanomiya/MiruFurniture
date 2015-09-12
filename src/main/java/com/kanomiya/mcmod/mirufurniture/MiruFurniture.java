package com.kanomiya.mcmod.mirufurniture;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import org.apache.logging.log4j.Logger;

import com.kanomiya.mcmod.mirufurniture.gui.GuiHandler;

@Mod(modid = MiruFurniture.MODID, name = MiruFurniture.MODID, version = MiruFurniture.VERSION)
public class MiruFurniture {
	public static final String MODID = "mirufurniture";
	public static final String VERSION = "0.10";


	@Mod.Instance("mirufurniture")
	public static MiruFurniture instance;

	public static final MFCreativeTab tabMF = new MFCreativeTab();

	public static final int GUIID_STORAGESHELF = 0;

	public static Logger logger;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();

		MFBlocks.init(event.getSide().isClient());
		MFRecipes.init();

		// MinecraftForge.EVENT_BUS.register(this);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		MFConfig.init();

	}


}




