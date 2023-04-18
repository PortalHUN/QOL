package com.portalhun.qol;

import com.portalhun.qol.Commands.SetSpawnCommand;
import com.portalhun.qol.Commands.SetWarpCommand;
import com.portalhun.qol.Commands.SpawnCommand;
import com.portalhun.qol.Commands.WarpCommand;
import com.portalhun.qol.Events.OnPlayerDeathEvent;
import com.portalhun.qol.Events.OnPlayerRespawnEvent;
import com.portalhun.qol.Storage.WarpStorage;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class QOL extends JavaPlugin {

  //XP cap
  //Teleport scroll for XP cap

  private static QOL plugin;

  @Override
    public void onEnable() {
      plugin = this;
      getConfig().options().copyDefaults(true);
      saveDefaultConfig();

      //Commands
      getCommand("setspawn").setExecutor(new SetSpawnCommand(this));
      getCommand("spawn").setExecutor(new SpawnCommand(this));
      getCommand("setwarp").setExecutor(new SetWarpCommand());
      getCommand("warp").setExecutor(new WarpCommand(this));


      //Events
      getServer().getPluginManager().registerEvents(new OnPlayerRespawnEvent(this),this);
      getServer().getPluginManager().registerEvents(new OnPlayerDeathEvent(),this);


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
