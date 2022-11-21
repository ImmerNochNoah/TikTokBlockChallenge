package de.immernochnoah.listener;

import de.immernochnoah.Main;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class OnFoodLevelChangeEvent implements Listener {

    public void onFoodLevelChangeEvent(FoodLevelChangeEvent event) {
        if (!Main.isStarted) {
            event.setCancelled(true);
        }
    }
}
