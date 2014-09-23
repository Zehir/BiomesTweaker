package com.zehir.biomestweaker.handler;

import java.io.File;

import com.zehir.biomestweaker.reference.R;
import com.zehir.biomestweaker.utility.LogHelper;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.world.biome.BiomeGenBase;
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

		createSampleRule();
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

	private static void createSampleRule() {
		configuration.getStringList(R.CONFIG_AFFECTED_BIOMES, R.CATEGORY_RULES
				+ ".example",
				new String[] { "net.minecraft.world.biome.BiomeGenDesert", "net.minecraft.world.biome.BiomeGenPlains" },
				R.CONFIG_AFFECTED_BIOMES_COMMENT + "\n\t");

		configuration.getString(R.CONFIG_BIOME_NAME, R.CATEGORY_RULES
				+ ".example", "SampleName", R.CONFIG_BIOME_NAME_COMMENT);

		configuration.getFloat(R.CONFIG_TEMPERATURE, R.CATEGORY_RULES
				+ ".example", 1.0F, 2.0F, -2.0F, R.CONFIG_TEMPERATURE_COMMENT);
		/*
		configuration.getFloat(R.CONFIG_RAINFALL,
				R.CATEGORY_RULES + ".example", 0.5F, 0.0F, 1.0F,
				R.CONFIG_RAINFALL_COMMENT);
		*/
		configuration.getFloat(R.CONFIG_RAINFALL,
				R.CATEGORY_RULES + ".example", 0, 0, 0, null);


		configuration.getBoolean(R.CONFIG_DISABLE_RAIN, R.CATEGORY_RULES
				+ ".example", false, R.CONFIG_DISABLE_RAIN_COMMENT);

		configuration.getBoolean(R.CONFIG_ENABLE_SNOW, R.CATEGORY_RULES
				+ ".example", false, R.CONFIG_ENABLE_SNOW_COMMENT);
		
	}
}
