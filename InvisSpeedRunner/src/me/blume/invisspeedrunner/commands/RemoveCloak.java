package me.blume.invisspeedrunner.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.blume.invisspeedrunner.Main;
import me.blume.invisspeedrunner.cloak.InvisCloak;



public class RemoveCloak implements CommandExecutor{
	@SuppressWarnings("unused")
	private Main plugin;
	public RemoveCloak(Main plugin) {
		this.plugin=plugin;
	}
	InvisCloak invcloak = new InvisCloak();
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(args.length==0) {
				if(plugin.getcloakplayer().contains(player.getUniqueId())) {
					if(label.equals("removeinvis")) {
						player.sendMessage(ChatColor.AQUA+"Invisibility removed");
						invcloak.removeCloak(player);
						plugin.removeplayer(player.getUniqueId());
						plugin.getConfig().set("InvisTime", 0);
						plugin.saveConfig();
					}
				}
			}
		}
		return false;
	}

}
