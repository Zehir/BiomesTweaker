package com.zehir.biomestweaker.client.gui;

import java.util.List;

import com.zehir.biomestweaker.handler.ConfigurationHandler;
import com.zehir.biomestweaker.reference.R;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;

public class ModGuiConfig extends GuiConfig {

	public ModGuiConfig(GuiScreen guiScreen) {
		super(guiScreen, new ConfigElement(
				ConfigurationHandler.configuration.getCategory(R.CATEGORY_GENERAL))
				.getChildElements(), R.MOD_ID, false, false, GuiConfig
				.getAbridgedConfigPath(ConfigurationHandler.configuration.toString()));
	}

}
