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

import com.kanomiya.mcmod.kanomiyacore.KanomiyaCore;
import com.kanomiya.mcmod.mirufurniture.gui.GuiHandler;

@Mod(modid = MiruFurniture.MODID)
public class MiruFurniture {
	public static final String MODID = "mirufurniture"; // BlockPumpkin

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

	public static KanomiyaCore core;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		core = new KanomiyaCore(MODID, instance);

		MFBlocks.preInit(event, core);
		MFRecipes.preInit(event, core);
		MFConfig.preInit(event, core);

		// MinecraftForge.EVENT_BUS.register(this);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		MFBlocks.init(event, core);
		MFRecipes.init(event, core);

		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		MFBlocks.postInit(event, core);
		MFRecipes.postInit(event, core);

	}

}




