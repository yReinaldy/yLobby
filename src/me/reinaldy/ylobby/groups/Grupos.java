package me.reinaldy.ylobby.groups;

import org.bukkit.entity.Player;

import me.reinaldy.ylobby.Main;

public class Grupos {
	
    public static String GetGrupo(final Player p) {
        if (p.getName().contains("yReinaldy_")) {
            return " §3§o[DEV]";
        }
        if (p.hasPermission(Main.getPlugin().getConfig().getString("4.ceo_perms").replace("&", "§"))) {
            return Main.getPlugin().getConfig().getString("3.ceo").replace("&", "§");
        }
        if (p.hasPermission(Main.getPlugin().getConfig().getString("4.gerente_perms").replace("&", "§"))) {
            return Main.getPlugin().getConfig().getString("3.gerente").replace("&", "§");
        }
        if (p.hasPermission(Main.getPlugin().getConfig().getString("4.admin_perms").replace("&", "§"))) {
            return Main.getPlugin().getConfig().getString("3.admin").replace("&", "§");
        }
        if (p.hasPermission(Main.getPlugin().getConfig().getString("4.mod_perms").replace("&", "§"))) {
            return Main.getPlugin().getConfig().getString("3.mod").replace("&", "§");
        }
        if (p.hasPermission(Main.getPlugin().getConfig().getString("4.ajd_perms").replace("&", "§"))) {
            return Main.getPlugin().getConfig().getString("3.ajd").replace("&", "§");
        }
        if (p.hasPermission(Main.getPlugin().getConfig().getString("4.yt_perms").replace("&", "§"))) {
            return Main.getPlugin().getConfig().getString("3.yt").replace("&", "§");
        }
        if (p.hasPermission(Main.getPlugin().getConfig().getString("4.mvpplus_perms").replace("&", "§"))) {
            return Main.getPlugin().getConfig().getString("3.mvpplus").replace("&", "§");
        }
        if (p.hasPermission(Main.getPlugin().getConfig().getString("4.mvp_perms").replace("&", "§"))) {
            return Main.getPlugin().getConfig().getString("3.mvp").replace("&", "§");
        }
        if (p.hasPermission(Main.getPlugin().getConfig().getString("4.vip_perms").replace("&", "§"))) {
            return Main.getPlugin().getConfig().getString("3.vip").replace("&", "§");
        }
        if (p.hasPermission(Main.getPlugin().getConfig().getString("4.membro_perms").replace("&", "§"))) {
            return Main.getPlugin().getConfig().getString("3.membro").replace("&", "§");
        }
        return Main.getPlugin().getConfig().getString("3.membro").replace("&", "§");
    }
}

