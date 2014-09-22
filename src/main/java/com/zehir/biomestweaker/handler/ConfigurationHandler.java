package com.zehir.biomestweaker.handler;

import java.io.File;

import com.zehir.biomestweaker.reference.R;
import com.zehir.biomestweaker.utility.LogHelper;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

public class ConfigurationHandler {

	public static Configuration configuration;
	public static boolean configValue = false;

	public static void init(File configFile) {
		// Create the configuration object from the given configuration file
		if (configuration == null) {
			configuration = new Configuration(configFile);
			syncConfig();
		}

	}

	@SubscribeEvent
	public void onConfigurationChangedEvent(
			ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.modID.equalsIgnoreCase(R.MOD_ID)) {
			// Resync configs
			syncConfig();
		}
	}

	private static void syncConfig() {
		// Read in properties from configuration file
		configValue = configuration.getBoolean("configValue",
				R.CATEGORY_GENERAL, true,
				"This is an example config value\n with multiligne");

		configuration.getString(R.CONFIG_AFFECTED_BIOMES, R.CATEGORY_RULES
				+ ".example", "net.minecraft.world.biome.BiomeGenDesert",
				R.CONFIG_COMMENT_AFFECTED_BIOMES +"\n\t");

		/*
		 * String[] rule1 = configuration.getStringList("Name",
		 * "biomes.rules.Desert", new String[] {
		 * "net.minecraft.world.biome.BiomeGenDesert",
		 * "net.minecraft.world.biome.BiomeGenMutated" }, "Commentaire");
		 */

		// String[] desert = configuration.getStringList("Class or ID",
		// "tweaks.rules.desert", new String[]{}, "Commentaire");

		// LogHelper.info(desert.toString());

		// TMP
		configuration.save();

		// Save the configuration file
		if (configuration.hasChanged()) {
			configuration.save();
		}
	}
}
