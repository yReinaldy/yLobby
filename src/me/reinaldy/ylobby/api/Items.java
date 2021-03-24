package me.reinaldy.ylobby.api;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public final class Items implements Listener {

	public static final ItemStack newItem(final Material mat, final String name, final String[] desc) {
		final ItemStack item = new ItemStack(mat);
		final ItemMeta itemk = item.getItemMeta();
		itemk.setDisplayName(name);
		itemk.setLore(Arrays.asList(desc));
		item.setItemMeta(itemk);
		return item;
	}
	
	public static final ItemStack newItem(final Material mat, final String name, final String[] desc, final int qt, final byte bt) {
		final ItemStack item = new ItemStack(mat, qt, (byte) bt);
		final ItemMeta itemk = item.getItemMeta();
		itemk.setDisplayName(name);
		itemk.setLore(Arrays.asList(desc));
		item.setItemMeta(itemk);
		return item;
	}
	
	public static final ItemStack newItem(final Material mat, final String name, final int qt, final byte bt) {
		final ItemStack item = new ItemStack(mat, qt, (byte) bt);
		final ItemMeta itemk = item.getItemMeta();
		itemk.setDisplayName(name);
		item.setItemMeta(itemk);
		return item;
	}
	
	public static ItemStack newHead(String nome, String nomeplayer) {
		@SuppressWarnings("deprecation")
		ItemStack item = new ItemStack(Material.getMaterial(397));
		SkullMeta sm = (SkullMeta) item.getItemMeta();
		item.setDurability((short) 3);
		sm.hasOwner();
		sm.setOwner(nomeplayer);
		sm.setDisplayName(nome);
		item.setItemMeta(sm);
		return item;
	}

	public static final void EntrarComItemsLobby(final Player p) {
		p.getInventory().clear();
		p.getInventory().setItem(3, newItem(Material.COMPASS, "§bSelecionar Servidor", new String[] {null}));
		p.getInventory().setItem(5, newHead("§bPerfil §f- §b" + p.getName(), p.getName()));
		p.updateInventory();
	}
}
