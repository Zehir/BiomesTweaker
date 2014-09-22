package com.zehir.biomestweaker.reference;

public class R {
	public static final String MOD_ID = "BiomesTweaker";
	public static final String MOD_NAME = "Biomes Tweaker";
	public static final String VERSION = "@VERSION@";
	public static final String FINGERPRINT = "@FINGERPRINT@";

	public static final String SERVER_PROXY_CLASS = "com.zehir.biomestweaker.proxy.ServerProxy";
	public static final String CLIENT_PROXY_CLASS = "com.zehir.biomestweaker.proxy.ServerProxy";
	public static final String GUI_FACTORY_CLASS = "com.zehir.biomestweaker.client.gui.GuiFactory";

	public static final String CATEGORY_GENERAL = "general";
	public static final String CATEGORY_RULES = "rules";

	public static final String CONFIG_AFFECTED_BIOMES = "affectedBiomes";
	public static final String CONFIG_COMMENT_AFFECTED_BIOMES = "Can be class (net.minecraft.world.biome.BiomeGenDesert), id (2) or inclusive range (1-6)";

	public static final String CONFIG_BIOME_NAME = "biomeName";
	public static final String CONFIG_COMMENT_BIOME_NAME = "Biome Name Ex: Desert";

	public static final String CONFIG_TEMPERATURE = "temperature";
	public static final String CONFIG_COMMENT_TEMPERATURE = "Temperature Ex: Desert = 2.0, Cold Taiga = -0.5 ";

	public static final String CONFIG_RAINFALL = "rainfall";
	public static final String CONFIG_COMMENT_RAINFALL = "RainFall Ex: Desert = 0.0, Cold Taiga = 0.4";

	public static final String CONFIG_DISABLE_RAIN = "disableRain";
	public static final String CONFIG_COMMENT_DISABLE_RAIN = "Disable rain";

	public static final String CONFIG_ENABLE_SNOW = "enableSnow";
	public static final String CONFIG_COMMENT_ENABLE_SNOW = "Enable snow (Set Temperature below 0.1 if above), not compatible with disable rain";
}
