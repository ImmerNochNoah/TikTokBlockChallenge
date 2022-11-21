package de.immernochnoah.listener;

import de.immernochnoah.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class OnPlayerDeathEvent implements Listener {

    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent event) {
        Player player = event.getPlayer();
        if (Main.isStarted) {
            Bukkit.getOnlinePlayers().forEach(onlinePlayer -> {
                onlinePlayer.sendMessage(player.getName() + "§c ist gestorben!");
                onlinePlayer.playSound(onlinePlayer.getLocation(), Sound.ITEM_TOTEM_USE, 1.0f, 1.0f);
                onlinePlayer.sendTitle("§cChallenge ist nun vorbei.", "");
            });
            player.sendMessage("§cChallenge ist nun vorbei.");
            player.setHealth(20);
            player.setGameMode(GameMode.SPECTATOR);
            Main.isStarted = false;
        }
        event.setDeathMessage(null);
    }
}
