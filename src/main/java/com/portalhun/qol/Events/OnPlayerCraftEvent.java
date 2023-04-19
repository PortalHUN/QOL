package com.portalhun.qol.Events;

import com.portalhun.qol.Items.TeleportCompassItem;
import com.portalhun.qol.QOL;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

public class OnPlayerCraftEvent implements Listener {
    private final QOL pl;
    public OnPlayerCraftEvent(QOL pl){
        this.pl = pl;
    }

    @EventHandler
    public void onPlayerCraftEvent(CraftItemEvent e){
        Player p = (Player) e.getView().getPlayer();
        if(e.getRecipe().getResult().getItemMeta().equals(TeleportCompassItem.getTeleportCompass().getItemMeta())){
            Location loc = pl.getConfig().getLocation("spawn");
            if(loc !=null) p.setCompassTarget(loc);
        }
    }
}
