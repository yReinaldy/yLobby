package me.reinaldy.ylobby.command;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.reinaldy.ylobby.Main;

public class FlyCommand implements CommandExecutor {
	
	public static final ArrayList<Player> hasFly = new ArrayList<>();
	
	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
		Player p = (Player)sender;
		if (cmd.getName().equalsIgnoreCase("fly")) {
			if (!p.hasPermission("cmd.fly")) {
				p.sendMessage(Main.getPlugin().getConfig().getString("1.nome_do_server").replace("&", "§") + " §cVocê não tem permissão.");
				return true;
			}
			if (args.length >= 0) {
				if(hasFly.contains(p)) {
					hasFly.remove(p);
					p.setAllowFlight(false);
					p.setFlying(false);
					p.sendMessage(Main.getPlugin().getConfig().getString("1.nome_do_server").replace("&", "§") + " §cFly desabilitado");
					return true;
				} else {
					hasFly.add(p);
					p.setAllowFlight(true);
					p.setFlying(true);
					p.sendMessage(Main.getPlugin().getConfig().getString("1.nome_do_server").replace("&", "§") + " §aFly habilitado");
					return true;
				}
			}
		}
		return false;
	}
}
