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
  public static Warp createWarp(String Name, Location loc) throws IOException {
    Warp w = new Warp(Name, loc);
    warps.add(w);
    saveWarps();
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

  public static int findIndexByName(String name){
    for (int i=0; i<warps.size();i++){
      if(warps.get(i).Name.equalsIgnoreCase(name)){
        return i;
      }
    }
    return -1;
  }

  public static List<Warp> getAllWarps(){
    return warps;
  }

  public static Warp deleteWarp(String name) throws IOException {
    Warp w = findWarpByName(name);
    if(w != null){
      warps.remove(w);
      saveWarps();
      return w;
    }else return null;
  }

  public static Warp updateWarp(Warp w) throws IOException {
    int i = findIndexByName(w.Name);
    if(i!=-1){
      warps.set(i, w);
      saveWarps();
      return w;
    }else return null;
  }

  public static Warp updateWarpName(String oName, String nName) throws IOException {
    int i = findIndexByName(oName);
    if(i!=-1){
      Warp w = warps.get(i);
      w.Name = nName;
      warps.set(i, w);
      saveWarps();
      return w;
    }else return null;
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
