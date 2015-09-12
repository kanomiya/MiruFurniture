package com.kanomiya.mcmod.mirufurniture;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.apache.logging.log4j.Logger;

import com.kanomiya.mcmod.mirufurniture.gui.GuiHandler;

@Mod(modid = MiruFurniture.MODID, name = MiruFurniture.MODID, version = MiruFurniture.VERSION)
public class MiruFurniture {
	public static final String MODID = "mirufurniture";
	public static final String VERSION = "0.10";


	@Mod.Instance(MODID)
	public static MiruFurniture instance;

	public static final CreativeTabs tabMF = new CreativeTabs(MODID) {
		@Override @SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return Item.getItemFromBlock(MFBlocks.blockOnetimeGlassCase_cube);
		}
	};

	public static final int GUIID_STORAGESHELF = 0;

	public static Logger logger;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();

		MFBlocks.preInit(event);
		MFRecipes.preInit(event);
		MFConfig.preInit(event);

		// MinecraftForge.EVENT_BUS.register(this);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		MFBlocks.init(event);
		MFRecipes.init(event);

		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		MFBlocks.postInit(event);
		MFRecipes.postInit(event);

	}

}




