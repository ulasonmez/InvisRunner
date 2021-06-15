package me.blume.invisspeedrunner.listeners;

import java.util.List;
import java.util.ListIterator;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import me.blume.invisspeedrunner.Main;
import me.blume.invisspeedrunner.cloak.InvisCloak;

public class ClearDrops implements Listener {
	private Main plugin;
	public ClearDrops(Main plugin) {
		this.plugin=plugin;
	}
	InvisCloak invcloak = new InvisCloak();
	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		if(plugin.getcloakplayer().contains(event.getEntity().getUniqueId())) {
			List<ItemStack> drops = event.getDrops(); 
			ListIterator<ItemStack> litr = drops.listIterator();	  
			Player player= (Player) event.getEntity();
			while( litr.hasNext() )
			{
				ItemStack stack = litr.next();

				if(stack.isSimilar(invcloak.getCloak()) || stack.isSimilar(invcloak.stopCloak()) )
				{
					player.getInventory().remove(invcloak.stopCloak());
					player.getInventory().remove(invcloak.getCloak());
					litr.remove();
				}
			}
			TurnInvis.task.cancel();
		}
		else {
			Player killer = event.getEntity().getKiller();
			if(event.getEntity() instanceof Player && !(plugin.getcloakplayer().contains(event.getEntity().getUniqueId()))) {
				if(plugin.getcloakplayer().contains(killer.getUniqueId())) {
					plugin.getConfig().set("InvisTime", plugin.getConfig().getInt("InvisTime")+30);
					plugin.saveConfig();
				}
			}
		}
	}
	
	

}
