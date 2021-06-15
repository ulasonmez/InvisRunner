package me.blume.invisspeedrunner.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.blume.invisspeedrunner.Main;
import me.blume.invisspeedrunner.cloak.InvisCloak;

public class AddCloak implements CommandExecutor{
	private Main plugin;
	public AddCloak(Main plugin) {
		this.plugin=plugin;
	}
	InvisCloak invcloak = new InvisCloak();
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(args.length==0) {
				if(label.equals("addinvis")) {
					if(!(plugin.getcloakplayer().contains(player.getUniqueId()))) {
						plugin.addplayer(player.getUniqueId());
						player.getInventory().addItem(invcloak.getCloak());
						plugin.getConfig().set("InvisTime", 100);
					}
				}
			}
		}
		return false;
	}

}
