package me.blume.invisspeedrunner.listeners;



import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

import me.blume.invisspeedrunner.Main;
import me.blume.invisspeedrunner.cloak.InvisCloak;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_16_R3.PacketPlayOutEntityEquipment;

public class TurnInvis implements Listener{

	int id1,id2,a,c,d,changeItem=0,changeItem2=0;
	public static int slot1;
	int timeLeft;
	public static boolean isUsed = false,giveGrayDye=false;

	public static BukkitTask task;


	private Main plugin;
	public TurnInvis(Main plugin) {
		this.plugin=plugin;
	}
	InvisCloak invcloak = new InvisCloak();


	@EventHandler
	public void rightClickCloak(PlayerInteractEvent event) {
		ItemStack item = event.getItem();
		Action action = event.getAction();
		EquipmentSlot e = event.getHand();
		Player player = event.getPlayer();
		if(plugin.getcloakplayer().contains(event.getPlayer().getUniqueId())) {

			if(item!=null && item.getItemMeta().getDisplayName().equals(ChatColor.LIGHT_PURPLE+"Turn Invis")) {
				if(action.equals(Action.RIGHT_CLICK_AIR)){
					if(e.equals(EquipmentSlot.OFF_HAND)) return;


					if(plugin.getConfig().getInt("InvisTime")<0) {
						return;
					}
					slot1 = -1;

					for (int i = 0; i < player.getInventory().getSize(); i++) {
						if(player.getInventory().getItem(i)==null) continue;
						if (!(player.getInventory().getItem(i).isSimilar(invcloak.getCloak()))) continue;
						slot1 = i;
						break;
					}
					if (slot1 == -1) return;
					invcloak.removeCloak(player);
					player.getInventory().setItem(slot1, invcloak.stopCloak());
					player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,plugin.getConfig().getInt("InvisTime")*20,1));
					
							
							
						}
					}
					
					
					if(isUsed==true) {
						StopInvis.task2.cancel();
					}
					task=Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {
						@Override
						public void run() {
							timeLeft = plugin.getConfig().getInt("InvisTime");
							if(timeLeft<0) {
								task.cancel();
							}
							else {
								player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.AQUA+"Invis Time: "+ChatColor.WHITE+timeLeft));
								timeLeft--;
								plugin.getConfig().set("InvisTime", timeLeft);
								plugin.saveConfig();
							}
						}
					},0,20L);
				}

			}


	}

