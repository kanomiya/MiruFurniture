package com.kanomiya.mcmod.mirufurniture.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureCompass;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSkull;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.kanomiya.mcmod.kanomiyacore.render.IExtendedTileEntitySpecialRenderer;
import com.kanomiya.mcmod.mirufurniture.MiruFurniture;
import com.kanomiya.mcmod.mirufurniture.block.BlockOnetimeGlassCase;
import com.kanomiya.mcmod.mirufurniture.block.MFBlockConsts;
import com.kanomiya.mcmod.mirufurniture.tileentity.TileEntityOnetimeGlassCase;

@SideOnly(Side.CLIENT)
public class TileEntityOnetimeGlassCaseRenderer extends IExtendedTileEntitySpecialRenderer {
	private static final ResourceLocation oneTimeGCResource = new ResourceLocation(MiruFurniture.MODID + ":textures/tileentity/OnetimeGlassCase.png");

	public final ResourceLocation resource;
	public final boolean rotateFlag;

	public TileEntityOnetimeGlassCaseRenderer() {
		resource = oneTimeGCResource;
		rotateFlag = true;
	}

	@Override public void renderTileEntityAt(TileEntity te, double posX, double posY, double posZ, float rot_rot, int p_180535_9_) {
		if (te instanceof TileEntityOnetimeGlassCase) {
			TileEntityOnetimeGlassCase tileCase = (TileEntityOnetimeGlassCase) te;
			// int rotateMeta = (te.getBlockMetadata() >> 1) & 3;
			int rotateMeta = BlockOnetimeGlassCase.BITFIELD.getValue(MFBlockConsts.NAME_FACING, te.getBlockMetadata());

			GlStateManager.pushMatrix(); // 座標保存

			GlStateManager.translate((float)posX +0.5f, (float)posY, (float)posZ +0.5f);
			if (rotateFlag) GlStateManager.rotate(metaToRotate(rotateMeta) *90f, 0f, 1f, 0f);

			GlStateManager.disableLighting();

			if (tileCase.getDisplayedItem() != null) {
				Minecraft mc = Minecraft.getMinecraft();
				RenderItem itemRenderer = mc.getRenderItem();
				Item item = tileCase.getDisplayedItem().getItem();

				TextureAtlasSprite textureatlassprite = null;

				if (item == Items.compass)
				{
					textureatlassprite = mc.getTextureMapBlocks().getAtlasSprite(TextureCompass.field_176608_l);
					mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);

					if (textureatlassprite instanceof TextureCompass)
					{
						TextureCompass texturecompass = (TextureCompass)textureatlassprite;
						double d0 = texturecompass.currentAngle;
						double d1 = texturecompass.angleDelta;
						texturecompass.currentAngle = 0.0D;
						texturecompass.angleDelta = 0.0D;
						texturecompass.updateCompass(tileCase.getWorld(), tileCase.getPos().getX(), tileCase.getPos().getZ(), 0, false, true);
						texturecompass.currentAngle = d0;
						texturecompass.angleDelta = d1;
					}
					else
					{
						textureatlassprite = null;
					}
				}

				GlStateManager.translate(0d, 0.4d, 0d);
				GlStateManager.scale(0.5f, 0.5f, 0.5f);

				if (!itemRenderer.shouldRenderItemIn3D(tileCase.getDisplayedItem()) || item instanceof ItemSkull)
				{
					GlStateManager.rotate(180.0f, 0.0F, 1.0F, 0.0F);
				}

				GlStateManager.pushAttrib();
				RenderHelper.enableStandardItemLighting();
				// itemRenderer.renderItemModel(tileCase.getDisplayedItem());
				// RenderItemFrame / TileEntityItemStackRenderer
				itemRenderer.func_181564_a(tileCase.getDisplayedItem(), ItemCameraTransforms.TransformType.FIXED);
				RenderHelper.disableStandardItemLighting();
				GlStateManager.popAttrib();

				if (textureatlassprite != null && textureatlassprite.getFrameCount() > 0)
				{
					textureatlassprite.updateAnimation();
				}
			}
			GlStateManager.enableLighting();

			// GlStateManager.scale(0.0625f, 0.0625f, 0.0625f);

			// bindTexture(resource);
			// model.renderModel(1f, ((te.getBlockMetadata() & 16) == 16));


			GlStateManager.popMatrix(); // 座標展開
		}

	}

}
