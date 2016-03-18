package com.kanomiya.mcmod.mirufurniture;

import net.minecraft.item.Item;
import net.minecraft.item.ItemBook;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemWritableBook;
import net.minecraft.item.ItemWrittenBook;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import com.kanomiya.mcmod.kanomiyacore.KanomiyaCore;

/**
 * @author Kanomiya
 *
 */
public class MFConfig {
	// public static final Set<String> bookNames = Sets.newConcurrentHashSet();


	public static void preInit(FMLPreInitializationEvent event, KanomiyaCore core) {

	}



	public static boolean isBookItem(ItemStack stack) {
		if (stack == null) return false;

		Item item = stack.getItem();

		if (item instanceof ItemBook || item instanceof ItemWritableBook || item instanceof ItemWrittenBook || item instanceof ItemEnchantedBook) {
			return true;
		}

		String name = stack.getUnlocalizedName().toLowerCase();

		// Block
		if (name.contains("shelf")) return false; // 本棚 (bookshelf) 除外
		if (name.contains("book")) return true;

		// TODO: 本アイテムの判定
		/*
		for (String bookName: bookNames) {
			if (name.matches(bookName + "\\z")) return true;
		}
		*/

		return false;
	}

}
