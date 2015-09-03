package com.kanomiya.mcmod.mirufurniture.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

/**
 * @author Kanomiya
 *
 */
public class ItemBlockOnetimeGlassCase extends ItemBlockDamaged {

	/**
	 * @param block
	 */
	public ItemBlockOnetimeGlassCase(Block block) {
		super(block);

		setHasSubtypes(true);
	}

	@Override public String getUnlocalizedName(ItemStack stack) {
		String blockName = block.getUnlocalizedName();

		if ((stack.getMetadata() & 1) == 1) blockName += "_broken";

		return blockName;
	}

}
