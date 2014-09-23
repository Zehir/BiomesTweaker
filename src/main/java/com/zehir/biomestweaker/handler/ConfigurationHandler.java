package com.zehir.biomestweaker.handler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.zehir.biomestweaker.reference.R;
import com.zehir.biomestweaker.utility.LogHelper;
import com.zehir.biomestweaker.utility.TweakRule;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;

public class ConfigurationHandler {

	public static Configuration		configuration;
	public static boolean			debug		= false;
	public static List<TweakRule>	tweakRules	= new ArrayList<TweakRule>();

	public static void init(File configFile) {
		// Create the configuration object from the given configuration file
		if (configuration == null) {
			configuration = new Configuration(configFile);
			syncConfig();
		}

	}

	@SubscribeEvent
	public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.modID.equalsIgnoreCase(R.MOD_ID)) {
			// Resync configs
			syncConfig();
		}
	}

	private static void syncConfig() {
		// Read in properties from configuration file
		// Load debug mode
		debug = configuration.getBoolean("debug", R.CATEGORY_GENERAL, false, "Debug mode");

		// TODO Remove, this is just for dev
		debug = true;

		initSampleRule();
		loadRules();
		// TODO order rules by name and example at the end
		// configuration.setCategoryPropertyOrder(category, propOrder)

		// Save the configuration file
		if (configuration.hasChanged()) {
			configuration.save();
		}
	}

	private static void loadRules() {
		for (ConfigCategory rule : configuration.getCategory(R.CATEGORY_RULES).getChildren()) {
			// Do nothing if is example rule
			if (rule.getName().equalsIgnoreCase("example"))
				break;

			// Create new rule
			if (debug)
				LogHelper.info("Creating rule: " + rule.getName());
			TweakRule currentRule = new TweakRule(rule.getName());

			// Set affected biomes
			LogHelper.info("Set affected biomes");
			currentRule.setAffectedBiomes(rule.get(R.CONFIG_AFFECTED_BIOMES).getStringList());

			currentRule.saveErros(rule);

		}

	}

	private static void initSampleRule() {
		// Delete example rule
		configuration.removeCategory(configuration.getCategory(R.CATEGORY_RULES_EXAMPLE));
		// Create example rule entry for affected biomes
		configuration.getStringList(R.CONFIG_AFFECTED_BIOMES, R.CATEGORY_RULES_EXAMPLE,
				new String[] { "1", "3-5" }, R.CONFIG_AFFECTED_BIOMES_COMMENT + "\n\t");
		// Create example rule entry for name
		configuration.getString(R.CONFIG_BIOME_NAME, R.CATEGORY_RULES_EXAMPLE, "Sample Name",
				R.CONFIG_BIOME_NAME_COMMENT);
		// Create example rule entry for temperature
		configuration.getFloat(R.CONFIG_TEMPERATURE, R.CATEGORY_RULES_EXAMPLE, 0.8F, 2.0F, -2.0F,
				R.CONFIG_TEMPERATURE_COMMENT);
		// Create example rule entry for rainfall
		configuration.getFloat(R.CONFIG_RAINFALL, R.CATEGORY_RULES_EXAMPLE, 0.4F, 0.0F, 1.0F,
				R.CONFIG_RAINFALL_COMMENT);
		// Create example rule entry for disable rain
		configuration.getBoolean(R.CONFIG_DISABLE_RAIN, R.CATEGORY_RULES_EXAMPLE, false,
				R.CONFIG_DISABLE_RAIN_COMMENT);
		// Create example rule entry for enable snow
		configuration.getBoolean(R.CONFIG_ENABLE_SNOW, R.CATEGORY_RULES_EXAMPLE, false,
				R.CONFIG_ENABLE_SNOW_COMMENT);
		// Set comment for example category
		configuration
				.setCategoryComment(R.CATEGORY_RULES_EXAMPLE, R.CATEGORY_RULES_EXAMPLE_COMMENT);
	}
}
