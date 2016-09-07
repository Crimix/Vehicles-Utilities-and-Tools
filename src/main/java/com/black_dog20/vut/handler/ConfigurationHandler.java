package com.black_dog20.vut.handler;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import com.black_dog20.vut.reference.Reference;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ConfigurationHandler {

	public static Configuration configuration;
	public static boolean configurationServer = false;

	public static void init(File configFile) {

		// Create configuration object from the given configurations file
		if (configuration == null) {
			configuration = new Configuration(configFile);
			loadConfiguration();
		}
	}

	@SubscribeEvent
	public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {

		if (event.modID.equalsIgnoreCase(Reference.MOD_ID)) {
			loadConfiguration();
		}
	}

	public static void loadConfiguration() {

		if (configuration.hasChanged() && !configurationServer) {
			configuration.save();
		}
	}
}
