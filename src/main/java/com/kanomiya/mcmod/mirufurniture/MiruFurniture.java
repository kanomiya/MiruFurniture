package com.kanomiya.mcmod.mirufurniture;

import org.apache.logging.log4j.Logger;

import com.kanomiya.mcmod.mirufurniture.gui.GuiHandler;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Modクラス
 *
 * @author Kanomiya [2016]
 *
 */
@Mod(modid = MiruFurniture.MODID, name = "Miru Furniture", version = "@VERSION@")
public class MiruFurniture {
	public static final String MODID = "com.kanomiya.mcmod.mirufurniture";

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

		// MinecraftForge.EVENT_BUS.register(this);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {

		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

	}

}




