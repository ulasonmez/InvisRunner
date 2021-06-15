package me.blume.invisspeedrunner.cloak;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class InvisCloak {

	public static ItemStack cloak= new ItemStack(Material.LIME_DYE);
	public static ItemStack StopCloak = new ItemStack(Material.GRAY_DYE);
	public ItemStack getCloak() {
		ItemMeta meta = cloak.getItemMeta();
		meta.setDisplayName(ChatColor.LIGHT_PURPLE+"Turn Invis");
		meta.addEnchant(Enchantment.DURABILITY, 150, true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		cloak.setItemMeta(meta);
		return cloak;
	}
	public void removeCloak(Player player) {
		player.getInventory().remove(InvisCloak.cloak);
	}
	public ItemStack stopCloak() {
		ItemMeta meta = StopCloak.getItemMeta();
		meta.setDisplayName(ChatColor.AQUA+"Turn Visible");
		meta.addEnchant(Enchantment.DURABILITY, 161, true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		StopCloak.setItemMeta(meta);
		return StopCloak;
	}
	public void removeStopCloak(Player player) {
		player.getInventory().remove(InvisCloak.StopCloak);
	}
}
