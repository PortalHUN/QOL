package com.portalhun.qol.Events;

import com.portalhun.qol.Items.TeleportCompassItem;
import com.portalhun.qol.Menus.TeleportMenu;
import com.portalhun.qol.QOL;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class OnPlayerClickActionEvent implements Listener {
    private final QOL pl;
    public OnPlayerClickActionEvent(QOL pl){
        this.pl = pl;
    }
    @EventHandler
    public void onPlayerClickActionEvent(PlayerInteractEvent e){
        if(e.getItem() != null){
            if(e.getItem().equals(TeleportCompassItem.get(pl))&& (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK))){
                TeleportMenu.Open(e.getPlayer());
            }
        }
    }
}
