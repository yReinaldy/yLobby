package me.reinaldy.ylobby;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.reinaldy.ylobby.command.FlyCommand;
import me.reinaldy.ylobby.event.Listeners;
import me.reinaldy.ylobby.groups.manager.MainAPI;
import me.reinaldy.ylobby.groups.manager.NametagAPI;
import me.reinaldy.ylobby.menus.Perfil;
import me.reinaldy.ylobby.menus.Servidores;
import me.reinaldy.ylobby.score.Score;

public final class Main extends JavaPlugin {
	
	public static final MainAPI tag = new MainAPI();
	
	public static Plugin plugin;
	private static Main instance;
	
	public static Plugin getPlugin() {
		return plugin;
	}

	public static Main getInstance() {
		return instance;
	}
	
	@SuppressWarnings("static-access")
	private void setInstance(Main instance) {
		this.instance = instance;
	}
	
	void eventos() {
		PluginManager yLobby = Bukkit.getPluginManager();
		yLobby.registerEvents(new Listeners(), this);
		yLobby.registerEvents(new Servidores(), this);
		yLobby.registerEvents(new Perfil(), this);
		yLobby.registerEvents(new NametagAPI(), this);
	}
	
	void Comandos() {
		getCommand("fly").setExecutor(new FlyCommand());
	}
	
	public static boolean getVersion(Player player) {

		((CraftPlayer) player).getHandle().playerConnection.networkManager.getVersion();
		return false;

	}
	
	public void onEnable() {
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage("§2Iniciando plugin: §byLobby");
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage("§bInformações: §6Plugin publico");
		Bukkit.getConsoleSender().sendMessage("§bAuthor: §6[ yReinaldy_ ]");
		Bukkit.getConsoleSender().sendMessage("§bVersion: §60.1");
		Bukkit.getConsoleSender().sendMessage("");
		plugin = this;
		setInstance(this);
		Score.updateAllScoreboards();
		this.eventos();
		this.Comandos();
        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();
        this.saveConfig();
        this.reloadConfig();
	}
	
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage("§cFinalizando plugin: §byLobby");
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage("§bInformações: §6Plugin publico");
		Bukkit.getConsoleSender().sendMessage("§bAuthor: §6[ yReinaldy_ ]");
		Bukkit.getConsoleSender().sendMessage("§bVersion: §60.1");
		Bukkit.getConsoleSender().sendMessage("");
	}
}
