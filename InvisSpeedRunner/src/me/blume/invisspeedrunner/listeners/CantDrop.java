package me.blume.invisspeedrunner.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import me.blume.invisspeedrunner.Main;
import me.blume.invisspeedrunner.cloak.InvisCloak;

public class CantDrop implements Listener{
	private Main plugin;
	public CantDrop(Main plugin) {
		this.plugin=plugin;
	}

	InvisCloak invcloak = new InvisCloak();
	@EventHandler
	public void elytraDrops(PlayerDropItemEvent event) {
		if(plugin.getcloakplayer().contains(event.getPlayer().getUniqueId())) {
			if(event.getItemDrop().getItemStack().isSimilar(invcloak.getCloak())) {
				event.setCancelled(true);
				return;
			}
		}
	}
	@EventHandler
	public void stopCloakDrops(PlayerDropItemEvent event) {
		if(plugin.getcloakplayer().contains(event.getPlayer().getUniqueId())) {
			if(event.getItemDrop().getItemStack().isSimilar(invcloak.stopCloak())) {
				event.setCancelled(true);
				return;
			}
		}
	}
}
