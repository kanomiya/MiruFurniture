package com.kanomiya.mcmod.mirufurniture.proxy;

import com.kanomiya.mcmod.mirufurniture.render.TileEntityOnetimeGlassCaseRenderer;
import com.kanomiya.mcmod.mirufurniture.render.TileEntityStorageShelfRenderer;
import com.kanomiya.mcmod.mirufurniture.tileentity.TileEntityOnetimeGlassCase;
import com.kanomiya.mcmod.mirufurniture.tileentity.TileEntityStorageShelf;

import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {

	@Override public void registerRenderer() {
		super.registerRenderer();

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityOnetimeGlassCase.class, new TileEntityOnetimeGlassCaseRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityStorageShelf.class, new TileEntityStorageShelfRenderer());


	}


}
