package me.reinaldy.ylobby.score;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public final class ScoreboardAPI {

	private Scoreboard score;
	private Objective obj;

	public ScoreboardAPI(final Player bp, final String name) {
		score = Bukkit.getScoreboardManager().getNewScoreboard();
		obj = score.registerNewObjective("score", "dummy");

		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.setDisplayName(name);

		for (int t = 1; t < 16; t++) {
			@SuppressWarnings("unused")
			Team team = score.registerNewTeam("line-" + t);
		}
	}

	public Scoreboard getSoreboard() {
		return score;
	}

	public static final String convertOff(final int line) {
		if (line == 1) {
			return "§1";
		}
		if (line == 2) {
			return "§2";
		}
		if (line == 3) {
			return "§3";
		}
		if (line == 4) {
			return "§4";
		}
		if (line == 5) {
			return "§5";
		}
		if (line == 6) {
			return "§6";
		}
		if (line == 7) {
			return "§7";
		}
		if (line == 8) {
			return "§8";
		}
		if (line == 9) {
			return "§9";
		}
		if (line == 10) {
			return "§a";
		}
		if (line == 11) {
			return "§b";
		}
		if (line == 12) {
			return "§c";
		}
		if (line == 13) {
			return "§d";
		}
		return "§0";
	}

	@SuppressWarnings("deprecation")
	public final void add(String prefix, String suffix, final int line) {
		Team t = score.getTeam("line-" + line);
		final FakeOfflinePlayer of = new FakeOfflinePlayer(convertOff(line));
		obj.getScore(of).setScore(line);
		t.addPlayer(of);

		if (prefix.length() > 16) {
			prefix = prefix.substring(0, 16);
		}
		if (suffix.length() > 16) {
			suffix = suffix.substring(0, 16);
		}
		t.setPrefix(prefix);
		t.setSuffix(suffix);
	}

	public final void updateLine(String prefix, String suffix, final int line) {
		Team t = score.getTeam("line-" + line);
		if (prefix.length() > 16) {
			prefix = prefix.substring(0, 16);
		}
		if (suffix.length() > 16) {
			suffix = suffix.substring(0, 16);
		}
		t.setPrefix(prefix);
		t.setSuffix(suffix);
	}
}

