package com.kanomiya.mcmod.mirufurniture.render;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.kanomiya.mcmod.kanomiyacore.render.IExtendedTileEntitySpecialRenderer;
import com.kanomiya.mcmod.mirufurniture.MiruFurniture;
import com.kanomiya.mcmod.mirufurniture.tileentity.TileEntityStorageShelf;

// TileEntityEnchantmentTableRenderer
@SideOnly(Side.CLIENT)
public class TileEntityStorageShelfRenderer extends IExtendedTileEntitySpecialRenderer {
	private static final ResourceLocation bookResource = new ResourceLocation(MiruFurniture.MODID + ":textures/blocks/blockStorageShelf_book.png");
	private ModelBook bookmodel = new ModelBook();

	@Override public void renderTileEntityAt(TileEntity te, double posX, double posY, double posZ, float rot_rot, int p_180535_9_) {

		if (te instanceof TileEntityStorageShelf) {
			TileEntityStorageShelf tileShelf = (TileEntityStorageShelf) te;

			GlStateManager.pushMatrix(); // 座標保存

			GlStateManager.translate((float)posX +0.5f, (float)posY -0.5f, (float)posZ +0.5f);
			GlStateManager.scale(0.0625f, 0.0625f, 0.0625f);
			GlStateManager.rotate(metaToRotate(tileShelf.getBlockMetadata()) *90f, 0f, 1f, 0f);
			GlStateManager.enableCull(); // 背面描画の省略?

			GlStateManager.translate(5f, 17f, -7f);

			bindTexture(bookResource);

			for (int i=0; i<tileShelf.getSizeInventory(); i++) {
				if (tileShelf.getStackInSlot(i) != null) {
					bookmodel.renderBook(1f);
				}

				GlStateManager.translate(-2f, 0f, 0f);
				if (i == tileShelf.getSizeInventory()/2 -1) { GlStateManager.translate(2f* tileShelf.getSizeInventory()/2, -8f, 0f); }
			}

			GlStateManager.popMatrix(); // 座標展開

		}
	}

}
