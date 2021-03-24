package me.reinaldy.ylobby.event;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.help.HelpTopic;
import org.bukkit.util.Vector;
import org.spigotmc.ProtocolInjector;

import me.reinaldy.ylobby.Main;
import me.reinaldy.ylobby.api.Items;
import me.reinaldy.ylobby.score.Score;
import me.reinaldy.ylobby.menus.Perfil;
import me.reinaldy.ylobby.menus.Servidores;
import net.minecraft.server.v1_7_R4.ChatSerializer;
import net.minecraft.server.v1_7_R4.IChatBaseComponent;
import net.minecraft.server.v1_7_R4.PlayerConnection;

public class Listeners implements Listener{
	
	private static int VERSION = 47;
	
	@EventHandler
	public void ParaNaoLevarDano(final EntityDamageEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void ParaFicaSemFome(final FoodLevelChangeEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void ParaNaoReceberConquistas(final PlayerAchievementAwardedEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void ParaNaoChover(final WeatherChangeEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void ParaNaoDropar(final PlayerDropItemEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void ParaNaoQuebrar(final BlockBreakEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void ParaNaoAbrirPlaca(final BlockPlaceEvent e) {
		e.setCancelled(true);
	}
   
    @EventHandler
    public void Antivoid(final PlayerMoveEvent e) {
        final Player p = e.getPlayer();
        final Location loc = p.getLocation();
        if (loc.getBlockY() <= 0) {
            p.teleport(e.getPlayer().getWorld().getSpawnLocation());
        }
    }
    
	@EventHandler
	public void onPlayerToggleFly(final PlayerToggleFlightEvent e) {
		final Player p = e.getPlayer();
		if (p.getGameMode() == GameMode.CREATIVE) {
			return;
		}
		e.setCancelled(true);
		p.setFlying(true);
		p.setAllowFlight(true);
		if (!p.isSneaking()) {
			p.setFallDistance(-2.0f);
			final Vector vector = p.getEyeLocation().getDirection();
			vector.multiply(3.3f);
			vector.setY(1.7f);
			p.setVelocity(vector);
		} else {
			p.setFallDistance(-5.0f);
			final Vector vector = p.getEyeLocation().getDirection();
			vector.multiply(3.35f);
			vector.setY(1.9);
			p.setVelocity(vector);
		}
	}
    
	@EventHandler
	public void EntrarNoServerComyReinaldy_(PlayerJoinEvent e) {
		final Player p = e.getPlayer();
		if (p.hasPermission("tag.membro")) {
			if (p.getName().contains("yReinaldy_")) {
				e.getPlayer().sendMessage("§2§lVERIFICADO §7Você recebeu o grupo: §3[DEV] §7por ter programado o servidor!");
				Main.tag.setDev(p);
				Bukkit.getConsoleSender().sendMessage("");
				Bukkit.getConsoleSender().sendMessage("§2O Programador do plugin entrou no servidor!");
				Bukkit.getConsoleSender().sendMessage("");
			}
		}
	}
    
	@EventHandler
	public void EntrarNoServer(PlayerJoinEvent e) {
		e.setJoinMessage(null);
		Player p = e.getPlayer();
		p.setGameMode(GameMode.ADVENTURE);
		p.setExp(0.0f);
		p.playSound(p.getLocation(), Sound.LEVEL_UP,  2.0F, 1.0F);
		Main.tag.EntrarComTag(p);
		Score.hasScore.add(p);
		Score.createScoreLobby(p);
		Items.EntrarComItemsLobby(p);
		p.teleport(Bukkit.getWorld("world").getSpawnLocation());
		p.setGameMode(GameMode.ADVENTURE);
		for (int clearchat = 1; clearchat < 100; clearchat++) {
			e.getPlayer().sendMessage("");
		}
		p.sendMessage("           " + Main.getPlugin().getConfig().getString("1.nome_do_server").replace("&", "§"));
		p.sendMessage("    §7Servidores: " + Main.getPlugin().getConfig().getString("1.tipo_do_server").replace("&", "§"));
		p.sendMessage("");
	}
	
	@EventHandler
	public void SairDoServer(PlayerQuitEvent e) {
		e.setQuitMessage(null);
		for (int clearchat = 1; clearchat < 100; clearchat++) {
			e.getPlayer().sendMessage("");
		}
	}
	
	@EventHandler
	public void CommandsPlugins(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		if (e.getMessage().toLowerCase().startsWith("/me") || (e.getMessage().toLowerCase().startsWith("/deop") || (e.getMessage().toLowerCase().startsWith("/op") || (e.getMessage().toLowerCase().startsWith("/say") || (e.getMessage().toLowerCase().startsWith("/ver") || (e.getMessage().toLowerCase().startsWith("/ban") || (e.getMessage().toLowerCase().startsWith("/kick") || (e.getMessage().toLowerCase().startsWith("/minecraft:me")|| (e.getMessage().toLowerCase().startsWith("/pl")|| (e.getMessage().toLowerCase().startsWith("/plugins")|| (e.getMessage().toLowerCase().startsWith("/about")))))))))))) {
			e.setCancelled(true);
			p.sendMessage(Main.getPlugin().getConfig().getString("1.nome_do_server").replace("&", "§") + " §cComando não encontrado.");
		}
	}
	
	@EventHandler
	public void ComandoDesconhecido(PlayerCommandPreprocessEvent e) {
		if (e.isCancelled()) {
			return;
		}
		Player p = e.getPlayer();
		String msg = e.getMessage().split(" ")[0];
		HelpTopic topic = Bukkit.getServer().getHelpMap().getHelpTopic(msg);
		if (topic == null) {
			e.setCancelled(true);
			p.sendMessage(Main.getPlugin().getConfig().getString("1.nome_do_server").replace("&", "§") + " §cComando não encontrado.");
		}
	}
	
	@EventHandler
	public void ChatFormat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		if (p.hasPermission("beneficio.chatcolor")) {
			e.setFormat(p.getDisplayName() + "§6 : §7" + e.getMessage().replace("%", "%%").replace("&", "§"));
		} else {
			e.setFormat(p.getDisplayName() + "§6 : §7" + e.getMessage().replace("%", "%%"));
		}
	}
	
	@EventHandler
	public void TabDoServidor(PlayerJoinEvent evento) {
		final Player p = evento.getPlayer();
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.plugin, new Runnable() {
			public void run() {
				PlayerConnection connect = ((CraftPlayer) p).getHandle().playerConnection;
				IChatBaseComponent top = ChatSerializer.a(
						"{'extra': [{text: '', color: 'aqua'}],'color': gold, 'text': '\n            " + Main.getPlugin().getConfig().getString("1.nome_do_server").replace("&", "§") + "            \n            " + Main.getPlugin().getConfig().getString("2.tab_do_server_cima").replace("&", "§") + "            \n                        '}");

				IChatBaseComponent bottom = ChatSerializer
						.a("{'extra': [{'color': 'aqua', 'text': '\n              " + Main.getPlugin().getConfig().getString("2.tab_do_server_baixo").replace("&", "§") + "            \n"
								+ "', 'underline': 'true'}], 'color': 'gold', 'text': ''}");
				if (((CraftPlayer) p).getHandle().playerConnection.networkManager.getVersion() < VERSION) {
					return;
				}
				connect.sendPacket(new ProtocolInjector.PacketTabHeader(top, bottom));
			}
		}, 0L, 20L);
	}
	
	@EventHandler
	public final void AbrirMenusServidores(final PlayerInteractEvent e) throws Exception {
		final Player p = e.getPlayer();
		if (p.getItemInHand().getType() == Material.COMPASS) {
			e.setCancelled(true);
			Servidores.ServidoresMenu(p);
			p.playSound(p.getLocation(), Sound.CLICK,  2.0F, 1.0F);
		}
	}
	
	@EventHandler
	public final void AbrirMenusPerfil(final PlayerInteractEvent e) throws Exception {
		final Player p = e.getPlayer();
		if (p.getItemInHand().getType() == Material.SKULL_ITEM) {
			e.setCancelled(true);
			Perfil.ContaMenu(p);
			p.playSound(p.getLocation(), Sound.CLICK,  2.0F, 1.0F);
		}
	}
}
