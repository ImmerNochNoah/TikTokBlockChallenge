package de.immernochnoah;

import de.immernochnoah.listener.OnEntityDamageEvent;
import de.immernochnoah.listener.OnPlayerDeathEvent;
import de.immernochnoah.listener.OnPlayerMoveEvent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class Main extends JavaPlugin {

    public static boolean isStarted = false;
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new OnPlayerMoveEvent(), this);
        Bukkit.getPluginManager().registerEvents(new OnPlayerDeathEvent(), this);
        Bukkit.getPluginManager().registerEvents(new OnEntityDamageEvent(), this);
        getCommand("start").setExecutor(new StartCommand());
    }

}

class StartCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player p = (Player) commandSender;
        if (!Main.isStarted) {
            Bukkit.getOnlinePlayers().forEach(onlinePlayer -> {
                onlinePlayer.sendMessage("§6ES GEHT IN 5 SEKUNDEN LOS!");
                onlinePlayer.playSound(onlinePlayer.getLocation(), Sound.ENTITY_WITHER_AMBIENT, 1.0f, 1.0f);
                onlinePlayer.setHealth(20);
                onlinePlayer.setGameMode(GameMode.SURVIVAL);
                OnPlayerMoveEvent.cooldown.put(onlinePlayer.getName(), System.currentTimeMillis() + (5000));
            });
            Main.isStarted = true;
        } else {
            Bukkit.getOnlinePlayers().forEach(onlinePlayer -> {
                onlinePlayer.sendMessage("§aES IST VORBEI!");
                onlinePlayer.playSound(onlinePlayer.getLocation(), Sound.ITEM_TOTEM_USE, 1.0f, 1.0f);
            });
            Main.isStarted = false;
        }
        return false;
    }
}
