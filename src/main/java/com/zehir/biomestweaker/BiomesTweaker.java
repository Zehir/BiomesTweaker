package com.zehir.biomestweaker;

import com.zehir.biomestweaker.handler.ConfigurationHandler;
import com.zehir.biomestweaker.proxy.IProxy;
import com.zehir.biomestweaker.reference.R;
import com.zehir.biomestweaker.utility.LogHelper;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBeach;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = R.MOD_ID, name = R.MOD_NAME, version = R.VERSION, guiFactory = R.GUI_FACTORY_CLASS)
public class BiomesTweaker {

	@Mod.Instance(R.MOD_ID)
	public static BiomesTweaker instance;

	@SidedProxy(modId = R.MOD_ID, clientSide = R.CLIENT_PROXY_CLASS, serverSide = R.SERVER_PROXY_CLASS)
	public static IProxy proxy;
	// TODO Sync tweaks rules between server and client

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
		LogHelper.info("Pre Initialization Complete!");
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		ConfigurationHandler.applyTweaks();
		LogHelper.info("Post Initialization Complete!");
	}
}
