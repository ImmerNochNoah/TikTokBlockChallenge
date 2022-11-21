package de.immernochnoah.listener;

import de.immernochnoah.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class OnEntityDamageEvent implements Listener {

    @EventHandler
    public void onEntityDamageEvent(EntityDamageEvent event) {
        if (!Main.isStarted) {
            event.setCancelled(true);
        }
    }
}
