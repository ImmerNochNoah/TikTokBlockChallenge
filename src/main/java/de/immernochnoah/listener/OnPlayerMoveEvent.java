package de.immernochnoah.listener;

import de.immernochnoah.Main;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OnPlayerMoveEvent implements Listener {

    public static Map<String, Long> cooldown = new HashMap<String, Long>();

    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent event) {
        if (Main.isStarted) {
            Player p = event.getPlayer();
            if (!p.getGameMode().equals(GameMode.SPECTATOR)) {
                if (cooldown.containsKey(p.getName())) {
                    if (cooldown.get(p.getName()) > System.currentTimeMillis()) {
                        return;
                    }
                }

                //Diesen Code hab ich von hier geklaut lmao
                //https://bukkit.org/threads/get-block-behind-player.93751/
                //------------------
                World world = p.getWorld();
                Location loc = p.getLocation();
                Block behind = loc.getBlock();
                int direction = (int)loc.getYaw();

                if(direction < 0) {
                    direction += 360;
                    direction = (direction + 45) / 90;
                }else {
                    direction = (direction + 45) / 90;
                }

                switch (direction) {
                    case 1:
                        behind = world.getBlockAt(behind.getX() + 1, behind.getY(), behind.getZ());
                        break;
                    case 2:
                        behind = world.getBlockAt(behind.getX(), behind.getY(), behind.getZ() + 1);
                        break;
                    case 3:
                        behind = world.getBlockAt(behind.getX() - 1, behind.getY(), behind.getZ());
                        break;
                    case 4:
                        behind = world.getBlockAt(behind.getX(), behind.getY(), behind.getZ() - 1);
                        break;
                    case 0:
                        behind = world.getBlockAt(behind.getX(), behind.getY(), behind.getZ() - 1);
                        break;
                    default:
                        break;
                }
                //------------------


                for (int i = behind.getY(); i > -64; i--) {
                    Location loc1 = behind.getLocation().subtract(0,i,0);
                    Block block = loc1.getBlock();
                    if (p.isJumping()|| p.isSneaking() || p.isClimbing() || p.isInWater() || p.isFrozen()) {
                        return;
                    }
                    switch (block.getType()) {
                        case OAK_WOOD, OAK_BOAT, OAK_LOG:
                            return;
                    }
                    block.setType(Material.AIR);
                }
            }
        }
    }
}
