package com.kanomiya.mcmod.mirufurniture.event;

import java.util.Set;

import net.minecraftforge.fml.common.eventhandler.Event;

/**
 * @author Kanomiya
 *
 */
public class BookItemInitEvent extends Event {
	public final Set<String> bookNames;

	public BookItemInitEvent(Set<String> parBookNames) {
		bookNames = parBookNames;
	}

}
