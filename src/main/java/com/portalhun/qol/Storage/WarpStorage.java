package com.portalhun.qol.Storage;

import com.google.gson.Gson;
import com.portalhun.qol.Models.Warp;
import com.portalhun.qol.QOL;
import org.bukkit.ChatColor;
import org.bukkit.Location;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WarpStorage {

  public static ArrayList<Warp> warps = new ArrayList<>();
  public static Warp createWarp(String Name, Location loc) {
    Warp w = new Warp(Name, loc);
    warps.add(w);
    return w;
  }

  public static Warp createWarp(Warp w) throws IOException {
    if(findWarpByName(w.Name) == null){
      warps.add(w);
      saveWarps();
      return w;
    }else return null;
  }

  public static Warp findWarpByName(String name){
    for (Warp warp : warps){
      if(warp.Name.equalsIgnoreCase(name)) return warp;
    }

    return null;
  }

  public static List<Warp> getAllWarps(){
    return warps;
  }

  public static Warp deleteWarp(String name) throws IOException {
    int cv=0;
    while(warps.get(cv).Name.equalsIgnoreCase(name))
      cv++;

    if(warps.size() < cv-1){
      Warp w = warps.get(cv);
      warps.remove(cv);
      saveWarps();
      return w;
    }else{
      return null;
    }
  }

  public static Warp updateWarp(Warp w) throws IOException {
    int cv=0;
    while(warps.get(cv).Name.equalsIgnoreCase(w.Name))
      cv++;

    if(warps.size() < cv-1){
      warps.get(cv).Item = w.Item;
      warps.get(cv).Loc = w.Loc;
      saveWarps();
      return w;
    }else{
      return null;
    }

  }

  public static void saveWarps() throws IOException {
    Gson gson = new Gson();
    File file = new File(QOL.getPlugin().getDataFolder().getAbsolutePath()+"/warps.json");
    file.getParentFile().mkdir();
    file.createNewFile();
    Writer writer = new FileWriter(file);
    gson.toJson(warps, writer);
    writer.flush();
    writer.close();
    System.out.println("[QOL] Warps Saved.");
  }

  public static void loadWarps() throws IOException{
    Gson gson = new Gson();
    File file = new File(QOL.getPlugin().getDataFolder().getAbsolutePath() + "/warps.json");
    if (file.exists()){
      Reader reader = new FileReader(file);
      Warp[] n = gson.fromJson(reader, Warp[].class);
      warps = new ArrayList<>(Arrays.asList(n));
      System.out.println("[QOL] Warps loaded.");
    }
  }
}
