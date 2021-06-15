package me.blume.invisspeedrunner;

import java.util.HashSet;
import java.util.UUID;

import org.bukkit.plugin.java.JavaPlugin;

import me.blume.invisspeedrunner.commands.AddCloak;
import me.blume.invisspeedrunner.commands.RemoveCloak;
import me.blume.invisspeedrunner.listeners.CantDrop;
import me.blume.invisspeedrunner.listeners.ClearDrops;
import me.blume.invisspeedrunner.listeners.StopInvis;
import me.blume.invisspeedrunner.listeners.TurnInvis;

public class Main extends JavaPlugin{
	HashSet<UUID> cloakplayer = new HashSet<UUID>();
	@Override
	public void onEnable() {

		getCommand("addinvis").setExecutor(new AddCloak(this));
		getCommand("removeinvis").setExecutor(new RemoveCloak(this));
		getServer().getPluginManager().registerEvents(new TurnInvis(this), this);
		getServer().getPluginManager().registerEvents(new ClearDrops(this), this);
		getServer().getPluginManager().registerEvents(new CantDrop(this), this);
		getServer().getPluginManager().registerEvents(new StopInvis(this), this);
		loadConfig();
		
		
	}
	public void onDisable() {
		
	}
	
	public void addplayer(UUID player) {
		cloakplayer.add(player);
	}
	public void removeplayer(UUID player) {
		cloakplayer.remove(player);
	}
	public HashSet<UUID> getcloakplayer() {
		return this.cloakplayer;
	}
	public void loadConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
}