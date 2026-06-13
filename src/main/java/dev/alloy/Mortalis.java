package dev.alloy;

import dev.alloy.item.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mortalis implements ModInitializer {

	public static final String MOD_ID = "mortalis";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Mortalis loading...");

		ModItems.register();

		LOGGER.info("Mortalis loaded successfully.");
	}
}