package me.reinaldy.ylobby.groups.manager;

import org.bukkit.entity.Player;

import me.reinaldy.ylobby.Main;

public final class MainAPI {

	public MainAPI() {

	}

	public void EntrarComTag(Player p) {
		if (p.getName().contains("yReinaldy_")) {
			setDev(p);
			return;
		} else if (p.hasPermission(Main.getPlugin().getConfig().getString("4.ceo_perms").replace("&", "§"))) {
			setCeo(p);
			return;
		} else if (p.hasPermission(Main.getPlugin().getConfig().getString("4.gerente_perms").replace("&", "§"))) {
			setGerente(p);
			return;
		} else if (p.hasPermission(Main.getPlugin().getConfig().getString("4.admin_perms").replace("&", "§"))) {
			setAdmin(p);
			return;			
		} else if (p.hasPermission(Main.getPlugin().getConfig().getString("4.mod_perms").replace("&", "§"))) {
			setMod(p);
			return;
		} else if (p.hasPermission(Main.getPlugin().getConfig().getString("4.ajd_perms").replace("&", "§"))) {
			setAjd(p);
			return;
		} else if (p.hasPermission(Main.getPlugin().getConfig().getString("4.yt_perms").replace("&", "§"))) {
			setYt(p);
			return;			
		} else if (p.hasPermission(Main.getPlugin().getConfig().getString("4.mvpplus_perms").replace("&", "§"))) {
			setMvpplus(p);
			return;
		} else if (p.hasPermission(Main.getPlugin().getConfig().getString("4.mvp_perms").replace("&", "§"))) {
			setMvp(p);
			return;
		} else if (p.hasPermission(Main.getPlugin().getConfig().getString("4.vip_perms").replace("&", "§"))) {
			setVip(p);
			return;			
		} else {
			setMembro(p);
			return;
		}
	}

	public void setDev(Player p) {
		p.setDisplayName("§3§o[DEV] §3§o" + p.getName());
		NametagAPI.setTagDev(p.getName(), "§3§o[DEV] §3§o", "");
	}
	
	public void setCeo(Player p) {
		p.setDisplayName(Main.getPlugin().getConfig().getString("3.ceo").replace("&", "§") + p.getName());
		NametagAPI.setTagCeo(p.getName(), Main.getPlugin().getConfig().getString("3.ceo").replace("&", "§"), "");
	}
	
	public void setGerente(Player p) {
		p.setDisplayName(Main.getPlugin().getConfig().getString("3.gerente").replace("&", "§") + p.getName());
		NametagAPI.setTagGerente(p.getName(), Main.getPlugin().getConfig().getString("3.gerente").replace("&", "§"), "");
	}
	
	public void setAdmin(Player p) {
		p.setDisplayName(Main.getPlugin().getConfig().getString("3.admin").replace("&", "§") + p.getName());
		NametagAPI.setTagAdmin(p.getName(), Main.getPlugin().getConfig().getString("3.admin").replace("&", "§"), "");
	}	
	
	public void setMod(Player p) {
		p.setDisplayName(Main.getPlugin().getConfig().getString("3.mod").replace("&", "§") + p.getName());
		NametagAPI.setTagMod(p.getName(), Main.getPlugin().getConfig().getString("3.mod").replace("&", "§"), "");
	}

	public void setAjd(Player p) {
		p.setDisplayName(Main.getPlugin().getConfig().getString("3.ajd").replace("&", "§") + p.getName());
		NametagAPI.setTagAjd(p.getName(), Main.getPlugin().getConfig().getString("3.ajd").replace("&", "§"), "");
	}
	
	public void setYt(Player p) {
		p.setDisplayName(Main.getPlugin().getConfig().getString("3.yt").replace("&", "§") + p.getName());
		NametagAPI.setTagYt(p.getName(), Main.getPlugin().getConfig().getString("3.yt").replace("&", "§"), "");
	}	
	
	public void setMvpplus(Player p) {
		p.setDisplayName(Main.getPlugin().getConfig().getString("3.mvpplus").replace("&", "§") + p.getName());
		NametagAPI.setTagMvpPlus(p.getName(), Main.getPlugin().getConfig().getString("3.mvpplus").replace("&", "§"), "");
	}

	public void setMvp(Player p) {
		p.setDisplayName(Main.getPlugin().getConfig().getString("3.mvp").replace("&", "§") + p.getName());
		NametagAPI.setTagMvp(p.getName(), Main.getPlugin().getConfig().getString("3.mvp").replace("&", "§"), "");
	}

	public void setVip(Player p) {
		p.setDisplayName(Main.getPlugin().getConfig().getString("3.vip").replace("&", "§") + p.getName());
		NametagAPI.setTagVip(p.getName(), Main.getPlugin().getConfig().getString("3.vip").replace("&", "§"), "");
	}
	public void setMembro(Player p) {
		p.setDisplayName(Main.getPlugin().getConfig().getString("3.membro").replace("&", "§") + p.getName());
		NametagAPI.setTagMembro(p.getName(), Main.getPlugin().getConfig().getString("3.membro").replace("&", "§"), "");
	}
}
