package me.reinaldy.ylobby.groups.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import me.reinaldy.ylobby.Main;
import net.minecraft.server.v1_7_R4.PacketPlayOutScoreboardTeam;

public final class NametagAPI implements Listener {
	
	public static final String DEV = "A";
	public static final String CEO = "B";
	public static final String GERENTE = "C";
	public static final String ADMIN = "D";
	public static final String MOD = "E";
	public static final String AJD = "F";
	public static final String YT = "G";
	public static final String MVPLUS = "H";
	public static final String MVP = "I";
	public static final String VIP = "J";
	public static final String MEMBRO = "K";

	public static void setTagDev(String name, String prefix, String suffix) {
		newTag(name, DEV, prefix, suffix);
	}
	
	public static void setTagCeo(String name, String prefix, String suffix) {
		newTag(name, CEO, prefix, suffix);
	}
	public static void setTagGerente(String name, String prefix, String suffix) {
		newTag(name, GERENTE, prefix, suffix);
	}
	public static void setTagAdmin(String name, String prefix, String suffix) {
		newTag(name, ADMIN, prefix, suffix);
	}
	public static void setTagMod(String name, String prefix, String suffix) {
		newTag(name, MOD, prefix, suffix);
	}
	public static void setTagAjd(String name, String prefix, String suffix) {
		newTag(name, AJD, prefix, suffix);
	}
	public static void setTagYt(String name, String prefix, String suffix) {
		newTag(name, YT, prefix, suffix);
	}
	public static void setTagMvpPlus(String name, String prefix, String suffix) {
		newTag(name, MVPLUS, prefix, suffix);
	}
	public static void setTagMvp(String name, String prefix, String suffix) {
		newTag(name, MVP, prefix, suffix);
	}
	public static void setTagVip(String name, String prefix, String suffix) {
		newTag(name, VIP, prefix, suffix);
	}
	public static void setTagMembro(String name, String prefix, String suffix) {
		newTag(name, MEMBRO, prefix, suffix);
	}

	@SuppressWarnings("deprecation")
	public static void removeNametag(String playerName) {
		TeamInfo oldTeam = getPlayerTeam(playerName);
		if (oldTeam != null) {
			oldTeam.getPlayers().remove(playerName);
			PacketInfo packetInfo = new PacketInfo(oldTeam.getName(), Collections.singleton(playerName), 4);
			for (Player player : Bukkit.getOnlinePlayers()) {
				packetInfo.sendToPlayer(player);
			}

			checkPlayerTeam(oldTeam);
		}
	}

	@SuppressWarnings("deprecation")
	private static TeamInfo getPlayerTeam(String teamName, String prefix, String suffix) {
		for (TeamInfo team : team_list) {
			if (team.getPrefix().equals(prefix) && team.getSuffix().equals(suffix)) {
				return team;
			}
		}

		TeamInfo teamInfo = new TeamInfo(teamName + count++);
		teamInfo.setPrefix(prefix);
		teamInfo.setSuffix(suffix);
		team_list.add(teamInfo);

		PacketInfo packetInfo = new PacketInfo(teamInfo.getName(), prefix, suffix, teamInfo.getPlayers(), 0);
		for (Player player : Bukkit.getOnlinePlayers()) {
			packetInfo.sendToPlayer(player);
		}

		return teamInfo;
	}

	@SuppressWarnings("deprecation")
	private static void checkPlayerTeam(TeamInfo teamInfo) {
		if (teamInfo.getPlayers().isEmpty()) {
			PacketInfo packetInfo = new PacketInfo(teamInfo.getName(), null, null, null, 1);
			for (Player player : Bukkit.getOnlinePlayers()) {
				packetInfo.sendToPlayer(player);
			}
			team_list.remove(teamInfo);
		}
	}

	private static TeamInfo getPlayerTeam(String player) {
		for (TeamInfo team : team_list) {
			if (team.getPlayers().contains(player)) {
				return team;
			}
		}
		return null;
	}

	public static void addPlayerToTeam(Player player) {
		for (TeamInfo teamInfo : team_list) {
			PacketInfo packetInfo = new PacketInfo(teamInfo.getName(), teamInfo.getPrefix(), teamInfo.getSuffix(),
					teamInfo.getPlayers(), 0);
			packetInfo.sendToPlayer(player);
		}
	}

	public static void newTag(final String name, final String teamName, final String prefix, final String suffix) {
		new BukkitRunnable() {
			@SuppressWarnings("deprecation")
			public void run() {
				removeNametag(name);

				TeamInfo teamInfo = getPlayerTeam(teamName, prefix, suffix);
				if (teamInfo.getPlayers().contains(name))
					return;
				teamInfo.getPlayers().add(name);

				PacketInfo packetInfo = new PacketInfo(teamInfo.getName(), Collections.singleton(name), 3);
				for (Player player : Bukkit.getOnlinePlayers()) {
					packetInfo.sendToPlayer(player);
				}
			}
		}.runTask(Main.getPlugin(Main.class));
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		addPlayerToTeam(event.getPlayer());
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		removeNametag(event.getPlayer().getName());
	}

	public static class PacketInfo {

		private final PacketPlayOutScoreboardTeam packet;

		public PacketInfo(String name, String prefix, String suffix, Collection<String> players, int updateType) {
			packet = new PacketPlayOutScoreboardTeam();
			ReflectionUtils.setValue("a", packet, name);
			ReflectionUtils.setValue("f", packet, updateType);

			if (updateType == 0 || updateType == 2) {
				ReflectionUtils.setValue("b", packet, name);
				ReflectionUtils.setValue("c", packet, prefix);
				ReflectionUtils.setValue("d", packet, suffix);
				ReflectionUtils.setValue("g", packet, 1);
			}

			if (updateType == 0) {
				addAll(players);
			}
		}

		public PacketInfo(String name, Collection<String> players, int updateType) {
			packet = new PacketPlayOutScoreboardTeam();

			if (updateType != 3 && updateType != 4) {
				throw new IllegalArgumentException("O metodo precisa ter o jogador como construtor");
			}

			if (players == null || players.isEmpty()) {
				players = new ArrayList<String>();
			}

			ReflectionUtils.setValue("a", packet, name);
			ReflectionUtils.setValue("f", packet, updateType);
			addAll(players);
		}

		public void sendToPlayer(Player bukkitPlayer) {
			((CraftPlayer) bukkitPlayer).getHandle().playerConnection.sendPacket(packet);
		}

		@SuppressWarnings("all")
		private void addAll(Collection<String> col) {
			((Collection<String>) ReflectionUtils.getValue("e", packet)).addAll(col);
		}
	}

	public static final HashSet<TeamInfo> team_list = new HashSet<TeamInfo>();
	private static int count;

	static class TeamInfo {
		private final String name;
		private String prefix;
		private String suffix;
		private Set<String> players;

		TeamInfo(String name) {
			this.name = name;
			this.players = new HashSet<String>();
		}

		public String getPrefix() {
			return prefix;
		}

		public void setPrefix(String prefix) {
			this.prefix = prefix;
		}

		public String getSuffix() {
			return suffix;
		}

		public void setSuffix(String suffix) {
			this.suffix = suffix;
		}

		public String getName() {
			return name;
		}

		public Set<String> getPlayers() {
			return players;
		}
	}
}
