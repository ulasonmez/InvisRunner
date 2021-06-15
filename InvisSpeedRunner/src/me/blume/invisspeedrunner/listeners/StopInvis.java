package me.blume.invisspeedrunner.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

import me.blume.invisspeedrunner.Main;
import me.blume.invisspeedrunner.cloak.InvisCloak;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class StopInvis implements Listener{
	private Main plugin;
	public StopInvis(Main plugin) {
		this.plugin=plugin;
	}
	public static BukkitTask task2;
	public static boolean giveGreenDye=false;
	InvisCloak invcloak = new InvisCloak();
	@EventHandler
	public void onRightClicktoGray(PlayerInteractEvent event) {
		
		ItemStack item = event.getItem();
		Action action = event.getAction();
		EquipmentSlot e = event.getHand();
		Player player = event.getPlayer();
		if(item!=null && item.getItemMeta().getDisplayName().equals(ChatColor.AQUA+"Turn Visible")) {
			if(action.equals(Action.RIGHT_CLICK_AIR) ){
				if(e.equals(EquipmentSlot.OFF_HAND)) return;
				if(event.getHand().equals(EquipmentSlot.HAND)) {
					TurnInvis.slot1 = -1;
					for (int i = 0; i < player.getInventory().getSize(); i++) {
						if(player.getInventory().getItem(i)==null) continue;
						if (!(player.getInventory().getItem(i).isSimilar(invcloak.stopCloak()))) continue;
						TurnInvis.slot1 = i;
						break;
					}
					if (TurnInvis.slot1 == -1) return;
					
					invcloak.removeStopCloak(player);
					
					if(plugin.getConfig().getInt("InvisTime")<0) {
						invcloak.removeStopCloak(player);
						player.getInventory().setItem(TurnInvis.slot1, invcloak.getCloak());
						return;
					}
					player.getInventory().setItem(TurnInvis.slot1, invcloak.getCloak());
					player.removePotionEffect(PotionEffectType.INVISIBILITY);

					player.sendMessage(ChatColor.AQUA+"You turned visible");
					TurnInvis.task.cancel();
					task2=Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {
						@Override
						public void run() {
							player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.AQUA+"Invis Time: "+ChatColor.WHITE+plugin.getConfig().getInt("InvisTime")));

						}
					},0,20L);
					TurnInvis.isUsed = true;
					
				}
			}
			
		}
		
	}
	
	
}
