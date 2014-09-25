package com.zehir.biomestweaker.utility;

import java.util.ArrayList;
import java.util.List;

import sun.org.mozilla.javascript.internal.Undefined;

import com.zehir.biomestweaker.handler.ConfigurationHandler;
import com.zehir.biomestweaker.reference.R;

import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.config.ConfigCategory;

public class TweakRule {
	private String id = null;
	private List<Integer> affectedBiomesID = new ArrayList<Integer>();
	private String biomeName = "";
	private float temperature = -5;
	private float rainfall = -5;
	private boolean disableRain = false;
	private boolean enableSnow = false;
	private List<String> errors = new ArrayList<String>();

	/**
	 * Creates a new rule
	 * 
	 * @param id
	 *            Rule id
	 */
	public TweakRule(String id) {
		this.id = id;
	}

	/**
	 * Add affected biome of rule.
	 * 
	 * @param biome
	 *            The biome id
	 */
	public void addAffectedBiome(int biome) {
		// Cant use BiomeDictionary.isBiomeRegistered(biome) here to check if
		// biome exist because at this point only vanilla
		// biomes are registred
		this.affectedBiomesID.add(biome);
	}

	/**
	 * Define affected biomes of rule.
	 * 
	 * @param biomes
	 *            The biomes id
	 */
	public void setAffectedBiomes(String[] biomes) {
		// For each biomes run method addAffectedBiome
		checking: for (String biome : biomes) {
			// If the param is just an integer call addAffectedBiome
			// Skip new ligne in config
			if (biome.length() == 0)
				continue checking;
			if (Common.isInteger(biome)) {
				// Check if number is a valid biome id (between 0 and 255)
				if (!(Integer.valueOf(biome) >= 0 && Integer.valueOf(biome) <= 255)) {
					// Adding error message
					this.errors.add(R.CONFIG_AFFECTED_BIOMES + " -> \"" + biome
							+ "\" is not an valid biome id");
					// Stop routine testing but continues other
					continue checking;
				}
				addAffectedBiome(Integer.valueOf(biome));
				// If the param is an range
			} else if (biome.length() >= 3 && biome.contains("-")) {
				// Only split on first dash
				String[] parts = biome.split("-", 2);
				// Check if range is valid
				// Check if start and end of range is integer
				for (int i = 0; i <= 1; i++) {
					if (!Common.isInteger(parts[i])) {
						// Adding error message
						this.errors.add(R.CONFIG_AFFECTED_BIOMES + " -> \""
								+ biome + "\"; \"" + parts[i]
								+ "\" is not an valid integer");
						// Stop routine testing but continues other
						continue checking;
					}
				}
				// Check if start is before end
				if (Integer.valueOf(parts[0]) > Integer.valueOf(parts[1])) {
					// Adding error message
					this.errors.add(R.CONFIG_AFFECTED_BIOMES + " -> \"" + biome
							+ "\"; \"" + parts[0] + "\" is greather than \""
							+ parts[1] + "\"");
					// Stop routine testing but continues other
					continue checking;
				}

				// For each integer in range call addAffectedBiome
				for (int i = Integer.valueOf(parts[0]); i <= Integer
						.valueOf(parts[1]); i++) {
					this.addAffectedBiome(i);
				}

			} else {
				// Adding error message
				this.errors.add(R.CONFIG_AFFECTED_BIOMES + " -> \"" + biome
						+ "\" is not an valid integer or range");
				// Stop routine testing but continues other
				continue checking;
			}

		}
	}

	/**
	 * Define new biome name of rule.
	 * 
	 * @param name
	 *            The biomes name
	 */
	public void setName(String name) {
		if (!name.isEmpty())
			this.biomeName = name;
	}

	public void logErros(ConfigCategory rule) {
		if (this.haveErrors()) {
			// If have errors write to log
			LogHelper.error("Error when parsing rule " + this.id);
			for (String error : this.errors) {
				LogHelper.error(error);
			}
			// Errors data not needed anymore
			this.errors.clear();
		}
	}

	/**
	 * 
	 * @return Have errors when parsing config
	 */
	public boolean haveErrors() {
		return !this.errors.isEmpty();
	}

	/**
	 * Print in log info all data of selected rule
	 */
	public void debug() {
		LogHelper.info("Debug Rule ID: " + this.id);
		if (!this.affectedBiomesID.isEmpty()) {
			String biomeList = "";
			for (Integer biomeId : this.affectedBiomesID) {
				biomeList += (biomeId + ", ");
			}
			LogHelper.info("affectedBiomes: "
					+ biomeList.substring(0, biomeList.length() - 2));
		}
		if (!this.biomeName.isEmpty())
			LogHelper.info("biomeName: \"" + this.biomeName + "\"");
		if (this.disableRain)
			LogHelper.info("disableRain: true");
		if (this.enableSnow)
			LogHelper.info("enableSnow: true");
		if (this.rainfall != -5)
			LogHelper.info("rainfall: " + this.rainfall);
		if (this.temperature != -5)
			LogHelper.info("temperature: " + this.temperature);
	}

	/**
	 * Disable rain
	 */
	public void disableRain() {
		this.disableRain = true;
		this.rainfall = 0;
	}

	/**
	 * Enable snow and set temperature below 0.1 if above
	 */
	public void enableSnow() {
		this.enableSnow = true;
		if (this.temperature > 0.1F)
			this.temperature = 0.1F;
	}

	/**
	 * Set Rainfall value
	 * 
	 * @param rainfall
	 *            Rainfall value
	 */
	public void setRainfall(float rainfall) {
		if (rainfall > R.RAIN_MAX) {
			this.rainfall = R.RAIN_MAX;
		} else if (rainfall < R.RAIN_MIN) {
			this.rainfall = R.RAIN_MIN;
		} else {
			this.rainfall = rainfall;
		}
	}

	/**
	 * Set Rainfall value
	 * 
	 * @param rainfall
	 *            Rainfall value
	 */
	public void setRainfall(String rainfall) {
		if (Common.isFloat(rainfall))
			this.setRainfall(Float.valueOf(rainfall));
	}

	/**
	 * Set temperature
	 * 
	 * @param temperature
	 *            Temperature
	 */
	public void setTemperature(float temperature) {
		if (temperature > R.TEMP_MAX) {
			this.temperature = R.TEMP_MAX;
		} else if (temperature < R.TEMP_MIN) {
			this.temperature = R.TEMP_MIN;
		} else {
			this.temperature = temperature;
		}
	}

	/**
	 * Set temperature
	 * 
	 * @param temperature
	 *            Temperature
	 */
	public void setTemperature(String temperature) {
		if (Common.isFloat(temperature))
			this.setTemperature(Float.valueOf(temperature));
	}

	/**
	 * Get the biome name
	 * 
	 * @return Biome Name
	 */
	public String getBiomeName() {
		return this.biomeName;
	}

	/**
	 * Get if the disable Rain option is set
	 * 
	 * @return true or false
	 */
	public boolean getdisableRain() {
		return this.disableRain;
	}

	/**
	 * Get if the enable Snow option is set
	 * 
	 * @return true or false
	 */
	public boolean getEnableSnow() {
		return this.enableSnow;
	}

	/**
	 * Return rainfall value
	 * 
	 * @return rainfall
	 */
	public float getRainfall() {
		return this.rainfall;
	}

	/**
	 * Return temperature value
	 * 
	 * @return temperature
	 */
	public float getTemperature() {
		return this.temperature;
	}

	/**
	 * Apply rules to the biomes
	 */
	public void applyTweakRule() {
		if (ConfigurationHandler.debug)
			LogHelper.info("Apply rule " + this.id);
		for (Integer biomeID : this.affectedBiomesID) {
			if (ConfigurationHandler.debug)
				LogHelper.info("Apply rule " + this.id + " on biome id "
						+ biomeID);
			// Check if biome is registered
			if (BiomeDictionary.isBiomeRegistered(biomeID)) {
				// Get biome
				BiomeGenBase biome = BiomeGenBase.getBiome(biomeID);

				// Define biomeName
				if (!this.getBiomeName().isEmpty()) {
					biome.setBiomeName(this.getBiomeName());
					if (ConfigurationHandler.debug)
						LogHelper.info("Apply rule " + this.id
								+ " on biome id " + biomeID + " set name:"
								+ this.getBiomeName());
				}

				// Define temperature and rainfall
				if ((this.getTemperature() != R.TEMP_DEF)
						&& (this.getRainfall() != R.RAIN_DEF)) {
					// If option temperature and railfall are set
					biome.setTemperatureRainfall(this.getTemperature(),
							this.getRainfall());
					if (ConfigurationHandler.debug)
						LogHelper.info("Apply rule " + this.id
								+ " on biome id " + biomeID
								+ " set remperature:" + this.getTemperature()
								+ " and rainfall:" + this.getRainfall());
				} else if (this.getTemperature() != R.TEMP_DEF) {
					// Only if temperature is set
					biome.setTemperatureRainfall(this.getTemperature(),
							biome.rainfall);
					if (ConfigurationHandler.debug)
						LogHelper.info("Apply rule " + this.id
								+ " on biome id " + biomeID
								+ " set remperature:" + this.getTemperature());
				} else if (this.getRainfall() != R.RAIN_DEF) {
					// Only if rainfall is set
					biome.setTemperatureRainfall(biome.temperature,
							this.getRainfall());
					if (ConfigurationHandler.debug)
						LogHelper.info("Apply rule " + this.id
								+ " on biome id " + biomeID + " set rainfall:"
								+ this.getRainfall());
				}

				// Disable rain
				if (this.getdisableRain()) {
					biome.setDisableRain();
					if (ConfigurationHandler.debug)
						LogHelper.info("Apply rule " + this.id
								+ " on biome id " + biomeID + " disable rain");
				}

				// Enable snow
				if (this.getEnableSnow()) {
					biome.setEnableSnow();
					if (ConfigurationHandler.debug)
						LogHelper.info("Apply rule " + this.id
								+ " on biome id " + biomeID + " enable snow");
				}
			} else if (ConfigurationHandler.debug) {
				LogHelper.warn("The biome with id " + biomeID
						+ " is not registred (rule: " + this.id
						+ ") This warning can be ignored");
			}
		}
	}
}
