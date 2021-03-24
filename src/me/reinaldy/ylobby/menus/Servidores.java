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

public final class Servidores implements Listener {
	
	public static void ServidoresMenu(Player p) {
		Inventory menu = Bukkit.createInventory(p, 27, "§bServidores");
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), () -> {

		menu.setItem(12, Items.newItem(Material.DIAMOND_SWORD, "§bServidor: §7KitPvP",
				new String[] {"", "§b§l● §f(Clique para conectar)"}, 1,
				(byte) 0));
		menu.setItem(14, Items.newItem(Material.IRON_FENCE, "§bServidor: §7Gladiator",
				new String[] {"", "§b§l● §f(Clique para conectar)"}, 1,
				(byte) 0));
		}, 0, 20);
		p.openInventory(menu);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void ConectarServidor(InventoryClickEvent e) {
		Player p = (Player)e.getWhoClicked();
		if ((e.getInventory().getTitle().equalsIgnoreCase("§bServidores")) && 
				(e.getCurrentItem() != null) && (e.getCurrentItem().getTypeId() != 0)) {
			e.setCancelled(true);
			if (e.getCurrentItem().getType() == Material.DIAMOND_SWORD) {
				p.closeInventory();
				p.sendMessage(Main.getPlugin().getConfig().getString("1.nome_do_server").replace("&", "§") + "§cO sistema de redirecionamento ainda não foi finalizado, aguarde até uma nova atualização...");
				p.playSound(p.getLocation(), Sound.NOTE_PLING,  2.0F, 1.0F);
				e.setCancelled(true);
				return;
			}
			if (e.getCurrentItem().getType() == Material.IRON_FENCE) {
				p.closeInventory();
				p.sendMessage(Main.getPlugin().getConfig().getString("1.nome_do_server").replace("&", "§") + "§cO sistema de redirecionamento ainda não foi finalizado, aguarde até uma nova atualização...");
				p.playSound(p.getLocation(), Sound.NOTE_PLING,  2.0F, 1.0F);
				e.setCancelled(true);
				return;
			}
		}
	}
}

