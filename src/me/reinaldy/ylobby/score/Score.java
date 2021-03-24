package me.reinaldy.ylobby.score;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.reinaldy.ylobby.Main;
import me.reinaldy.ylobby.groups.Grupos;

public final class Score {
	

	public static final ArrayList<Player> hasScore = new ArrayList<Player>();

	public static final HashMap<Player, ScoreboardAPI> sscore = new HashMap<>();

	public static final void updateAllScoreboards() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				for (final Player all : Bukkit.getOnlinePlayers()) {
					updateScoreboardLobby(all);
				}
			}
		}, 0, 3L);
	}
		
	@SuppressWarnings("unused")
	public static final void updateScoreboardLobby(final Player p) {
		if (hasScore.contains(p)) {

			final ScoreboardAPI sb = sscore.get(p);

			p.getScoreboard().getObjective("score").setDisplayName("" + Main.getPlugin().getConfig().getString("1.nome_do_server").replace("&", "§"));

		}
	}

	public static final void createScoreLobby(final Player p) {
		if (hasScore.contains(p)) {
			final ScoreboardAPI score = new ScoreboardAPI(p, "" + Main.getPlugin().getConfig().getString("1.nome_do_server").replace("&", "§"));

			score.add("", "", 6);
			score.add("§7Grupo:", Grupos.GetGrupo(p), 5);
			score.add("", "", 4);
			score.add("§7Lobby: §f#", Main.getPlugin().getConfig().getString("1.id_do_server").replace("&", "§"), 3);
			score.add("", "", 2);
			score.add("", Main.getPlugin().getConfig().getString("1.site_do_server").replace("&", "§"), 1);

			sscore.put(p, score);
			p.setScoreboard(score.getSoreboard());
		}
	}
}
