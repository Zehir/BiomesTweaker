package com.zehir.biomestweaker.reference;

import net.minecraftforge.common.config.Configuration;

public class R {
	// Mod info
	public static final String		MOD_ID							= "BiomesTweaker";
	public static final String		MOD_NAME						= "Biomes Tweaker";
	public static final String		VERSION							= "@VERSION@";
	public static final String		FINGERPRINT						= "@FINGERPRINT@";

	// Class list
	public static final String		PACKAGE							= "com.zehir.biomestweaker";
	public static final String		SERVER_PROXY_CLASS				= R.PACKAGE
																			+ ".proxy.ServerProxy";
	public static final String		CLIENT_PROXY_CLASS				= R.PACKAGE
																			+ ".proxy.ClientProxy";
	public static final String		GUI_FACTORY_CLASS				= R.PACKAGE
																			+ ".client.gui.GuiFactory";

	// Options
	public static final float		TEMP_MAX						= 2F;
	public static final float		TEMP_MIN						= -2F;
	public static final float		TEMP_DEF						= 0.8F;
	public static final float		RAIN_MAX						= 1F;
	public static final float		RAIN_MIN						= 0F;
	public static final float		RAIN_DEF						= 0.4F;
	public static final String[]	AFFECTED_BIOMES_DEF				= new String[] { "1", "3-5" };

	// Config
	public static final String		CATEGORY_GENERAL				= "general";
	public static final String		CATEGORY_RULES					= "rules";
	public static final String		CATEGORY_RULES_EXAMPLE			= CATEGORY_RULES + ".example";

	public static final String		CATEGORY_RULES_EXAMPLE_COMMENT	= "This is an example of rule."
																			+ Configuration.NEW_LINE
																			+ "You can add rules but if the name is \"example\" it will be ignored."
																			+ Configuration.NEW_LINE
																			+ "If an tweak option is not set it wiil not use the default value of example."
																			+ Configuration.NEW_LINE
																			+ "If this sample is deleted or edited it will be re-created.";

	public static final String		CONFIG_AFFECTED_BIOMES			= "affectedBiomes";
	public static final String		CONFIG_AFFECTED_BIOMES_COMMENT	= "Can be biome id or range of biome id (1-4)"
																			+ Configuration.NEW_LINE
																			+ "If biome is not found it will just ignored";

	public static final String		CONFIG_BIOME_NAME				= "biomeName";
	public static final String		CONFIG_BIOME_NAME_COMMENT		= "Biome Name Ex: Desert";

	public static final String		CONFIG_TEMPERATURE				= "temperature";
	public static final String		CONFIG_TEMPERATURE_COMMENT		= "Temperature"
																			+ Configuration.NEW_LINE
																			+ "Ex: Desert = 2.0, Cold Taiga = -0.5"
																			+ Configuration.NEW_LINE
																			+ "Please avoid temperatures in the range 0.1 - 0.2 because of snow"
																			+ Configuration.NEW_LINE;

	public static final String		CONFIG_RAINFALL					= "rainfall";
	public static final String		CONFIG_RAINFALL_COMMENT			= "RainFall Ex: Desert = 0.0, Cold Taiga = 0.4";

	public static final String		CONFIG_DISABLE_RAIN				= "disableRain";
	public static final String		CONFIG_DISABLE_RAIN_COMMENT		= "Disable rain";

	public static final String		CONFIG_ENABLE_SNOW				= "enableSnow";
	public static final String		CONFIG_ENABLE_SNOW_COMMENT		= "Enable snow (Set Temperature below 0.1 if above), not compatible with disable rain";
}
