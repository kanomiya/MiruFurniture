
package com.kanomiya.mcmod.mirufurniture;

import static com.kanomiya.mcmod.kanomiyacore.util.KCUtils.Client.*;
import static com.kanomiya.mcmod.mirufurniture.MiruFurniture.*;

import com.kanomiya.mcmod.mirufurniture.block.BlockOnetimeGlassCase;
import com.kanomiya.mcmod.mirufurniture.block.BlockStorageShelf;
import com.kanomiya.mcmod.mirufurniture.item.ItemBlockOnetimeGlassCase;
import com.kanomiya.mcmod.mirufurniture.render.TileEntityOnetimeGlassCaseRenderer;
import com.kanomiya.mcmod.mirufurniture.render.TileEntityStorageShelfRenderer;
import com.kanomiya.mcmod.mirufurniture.tileentity.TileEntityOnetimeGlassCase;
import com.kanomiya.mcmod.mirufurniture.tileentity.TileEntityStorageShelf;

import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * @author Kanomiya
 *
 */
public class MFBlocks {

    public static BlockOnetimeGlassCase blockOnetimeGlassCase_cube = new BlockOnetimeGlassCase();
    public static BlockStorageShelf blockStorageShelf = new BlockStorageShelf();
    // public static Block block;


    public static void preInit(FMLPreInitializationEvent event)
    {
        GameRegistry.register(blockOnetimeGlassCase_cube, new ResourceLocation(MODID, "blockOnetimeGlassCase_cube"));
        GameRegistry.register(blockStorageShelf, new ResourceLocation(MODID, "blockStorageShelf"));

        GameRegistry.register(new ItemBlockOnetimeGlassCase(blockOnetimeGlassCase_cube), blockOnetimeGlassCase_cube.getRegistryName());
        GameRegistry.register(new ItemBlock(blockStorageShelf), blockStorageShelf.getRegistryName());

        // TileEntity
        GameRegistry.registerTileEntity(TileEntityOnetimeGlassCase.class, MODID+":tileOnetimeGlassCase");
        GameRegistry.registerTileEntity(TileEntityStorageShelf.class, MODID+":tileStorageShelf");

        if (event.getSide().isClient())
        {
            arrayRegister.accept(Item.getItemFromBlock(blockOnetimeGlassCase_cube), new String[] { "", "broken" });
            simpleRegister.accept(Item.getItemFromBlock(blockStorageShelf));


            ClientRegistry.bindTileEntitySpecialRenderer(TileEntityOnetimeGlassCase.class, new TileEntityOnetimeGlassCaseRenderer());
            ClientRegistry.bindTileEntitySpecialRenderer(TileEntityStorageShelf.class, new TileEntityStorageShelfRenderer());

        }

    }



}
