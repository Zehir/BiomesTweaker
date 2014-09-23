package com.zehir.biomestweaker.utility;

import java.util.ArrayList;
import java.util.List;

import com.zehir.biomestweaker.reference.R;

import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.config.ConfigCategory;

public class TweakRule {
	private String			id					= null;
	private List<Integer>	affectedBiomesID	= new ArrayList<Integer>();
	private String			biomeName;
	private float			temperature;
	private float			rainfall;
	private boolean			disableRain			= false;
	private boolean			enableSnow			= false;
	private List<String>	errors				= new ArrayList<String>();

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
		// Cant use BiomeDictionary.isBiomeRegistered(biome) here to check if biome exist because at this point only vanilla
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
			LogHelper.info("Checking biome data: \"" + biome + "\"");
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
						this.errors.add(R.CONFIG_AFFECTED_BIOMES + " -> \"" + biome + "\"; \""
								+ parts[i] + "\" is not an valid integer");
						// Stop routine testing but continues other
						continue checking;
					}
				}
				// Check if start is before end
				if (Integer.valueOf(parts[0]) > Integer.valueOf(parts[1])) {
					// Adding error message
					this.errors.add(R.CONFIG_AFFECTED_BIOMES + " -> \"" + biome + "\"; \""
							+ parts[0] + "\" is greather than \"" + parts[1] + "\"");
					// Stop routine testing but continues other
					continue checking;
				}

				// For each integer in range call addAffectedBiome
				for (int i = Integer.valueOf(parts[0]); i <= Integer.valueOf(parts[1]); i++) {
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
	 * Return biome id
	 * 
	 * @param biome
	 *            The biome class or id
	 * @return Biome Id or -1 if not found
	 */
	private int getBiomeId(String biome) {
		// Check if the parameter is an integer
		if (Common.isInteger(biome)) {
			// If this biome exist return his id
			if (BiomeDictionary.isBiomeRegistered(Integer.valueOf(biome)))
				return Integer.valueOf(biome);
		} else {

		}
		return -1;
	}

	public void saveErros(ConfigCategory rule) {
		if (this.haveErrors()) {
			// If have errors write to log
			LogHelper.error("Error when parsing rule " + this.id);
			for (String error : this.errors) {
				LogHelper.error(error);
			}
			// Errors data not needed anymore
			this.errors.clear();
		}

		// rule.setComment("Erros");

	}

	/**
	 * 
	 * @return Have errors when parsing config
	 */
	public boolean haveErrors() {
		return !this.errors.isEmpty();
	}
}
