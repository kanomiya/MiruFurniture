package com.kanomiya.mcmod.mirufurniture;

import java.util.Set;

import net.minecraft.item.Item;
import net.minecraft.item.ItemBook;
import net.minecraft.item.ItemEditableBook;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemWritableBook;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import com.google.common.collect.Sets;
import com.kanomiya.mcmod.mirufurniture.event.BookItemInitEvent;

/**
 * @author Kanomiya
 *
 */
public class MFConfig {
	public static final Set<String> bookNames = Sets.newConcurrentHashSet();


	public static void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.post(new BookItemInitEvent(bookNames));

	}



	public static boolean isBookItem(ItemStack stack) {
		if (stack == null) return false;

		Item item = stack.getItem();

		if (item instanceof ItemBook || item instanceof ItemEditableBook || item instanceof ItemWritableBook || item instanceof ItemEnchantedBook) {
			return true;
		}

		String name = stack.getUnlocalizedName();
		if (name.toLowerCase().contains("book")) return true;

		for (String bookName: bookNames) {
			if (name.matches(bookName + "\\z")) return true;
		}

		return false;
	}

}
