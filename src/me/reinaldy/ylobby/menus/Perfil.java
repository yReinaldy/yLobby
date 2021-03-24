package me.reinaldy.ylobby.menus;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import me.reinaldy.ylobby.Main;
import me.reinaldy.ylobby.api.Items;
import me.reinaldy.ylobby.groups.Grupos;

public final class Perfil implements Listener {
	
	public static void ContaMenu(Player p) {
		Inventory menu = Bukkit.createInventory(p, 27, "§bPerfil §f- §b" + p.getName());
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), () -> {

		menu.setItem(13, Items.newHead("§b" + p.getName(), p.getName()));
			
		}, 0, 20);
		p.openInventory(menu);
	}
	
	public static void ContaHeader(Player p) {
		Inventory menu = Bukkit.createInventory(p, 27, "§b" + p.getName());
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), () -> {
			
		menu.setItem(13, Items.newItem(Material.PAPER, "§7Perfil: §b" + p.getName(),
				new String[] {"", "§b§l● §7Grupo:" + Grupos.GetGrupo(p), "§b§l● §7Nick: §b" + p.getName(), "§b§l● §7Uuid: §b" + p.getUniqueId(), "", "§b§l● §7Ip: §b" + p.getAddress(), "" ,}, 1,
				(byte) 0));
		menu.setItem(18, Items.newItem(Material.ARROW, "§7Voltar",
				new String[] {}, 1,
				(byte) 0));
		}, 0, 20);
		p.openInventory(menu);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void ClicarMenus(InventoryClickEvent e) {
		Player p = (Player)e.getWhoClicked();
		if ((e.getInventory().getTitle().equalsIgnoreCase("§bPerfil §f- §b" + p.getName())) && 
				(e.getCurrentItem() != null) && (e.getCurrentItem().getTypeId() != 0)) {
			e.setCancelled(true);
			if (e.getCurrentItem().getType() == Material.SKULL_ITEM) {
				p.playSound(p.getLocation(), Sound.NOTE_PLING,  2.0F, 1.0F);
				ContaHeader(p);
				return;
			}
		}
	}
	
		@SuppressWarnings("deprecation")
		@EventHandler
		public void ClicarMenusHeader(InventoryClickEvent e) {
			Player p = (Player)e.getWhoClicked();
			if ((e.getInventory().getTitle().equalsIgnoreCase("§b" + p.getName())) && 
					(e.getCurrentItem() != null) && (e.getCurrentItem().getTypeId() != 0)) {
				e.setCancelled(true);
				if (e.getCurrentItem().getType() == Material.PAPER) {
					p.playSound(p.getLocation(), Sound.NOTE_PLING,  2.0F, 1.0F);
					e.setCancelled(true);
					return;
				}
				if (e.getCurrentItem().getType() == Material.ARROW) {
					p.playSound(p.getLocation(), Sound.NOTE_PLING,  2.0F, 1.0F);
					ContaMenu(p);
					return;
				}
			}
		}
}
