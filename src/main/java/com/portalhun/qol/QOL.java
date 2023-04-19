package com.portalhun.qol;

import com.portalhun.qol.Commands.*;
import com.portalhun.qol.Events.OnPlayerClickActionEvent;
import com.portalhun.qol.Events.OnPlayerCraftEvent;
import com.portalhun.qol.Events.OnPlayerDeathEvent;
import com.portalhun.qol.Events.OnPlayerRespawnEvent;
import com.portalhun.qol.Recipes.TeleportCompassRecipe;
import com.portalhun.qol.Storage.WarpStorage;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class QOL extends JavaPlugin {

  //XP cap
  //Teleport scroll for XP cap

  private static QOL plugin;

  @Override
    public void onEnable() {
      //Config
      plugin = this;
      getConfig().options().copyDefaults(true);
      saveDefaultConfig();

      //Commands
      getCommand("setspawn").setExecutor(new SetSpawnCommand(this));
      getCommand("spawn").setExecutor(new SpawnCommand(this));
      getCommand("setwarp").setExecutor(new SetWarpCommand());
      getCommand("warp").setExecutor(new WarpCommand(this));
      getCommand("getcompass").setExecutor(new GetCompassCommand(this));


      //Events
      getServer().getPluginManager().registerEvents(new OnPlayerRespawnEvent(this),this);
      getServer().getPluginManager().registerEvents(new OnPlayerDeathEvent(),this);
      getServer().getPluginManager().registerEvents(new OnPlayerCraftEvent(this),this);
      getServer().getPluginManager().registerEvents(new OnPlayerClickActionEvent(this),this);



      //Recipes
      new TeleportCompassRecipe(plugin);


      //Load Warps
      try {
        WarpStorage.loadWarps();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

  public static Plugin getPlugin() {
    return plugin;
  }
}
