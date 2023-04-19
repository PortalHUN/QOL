package com.portalhun.qol.Events;

import com.portalhun.qol.QOL;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;

public class OnPlayerInventoryInteraction implements Listener {
    private final QOL pl;
    public OnPlayerInventoryInteraction(QOL pl){
        this.pl = pl;
    }
    @EventHandler
    public void onPlayerInventoryInteraction(InventoryClickEvent e){
        Player p = (Player) e.getView().getPlayer();
        if(e.getView().getTitle().equals(ChatColor.LIGHT_PURPLE+"Teleporter")){
            e.setCancelled(true);
        }
    }
}
