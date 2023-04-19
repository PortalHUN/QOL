package com.portalhun.qol.Recipes;

import com.portalhun.qol.Items.TeleportCompassItem;
import com.portalhun.qol.QOL;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

public class TeleportCompassRecipe {
    private  final QOL pl;
    public TeleportCompassRecipe(QOL pl){
        this.pl = pl;
        NamespacedKey key = new NamespacedKey(pl, "teleport_compass");
        ShapedRecipe s = new ShapedRecipe(key, TeleportCompassItem.getTeleportCompass());
        s.shape(
                "I I I",
                "I E I",
                "I I I"
        );
        s.setIngredient('I', Material.IRON_INGOT);
        s.setIngredient('E', Material.ENDER_PEARL);
        Bukkit.addRecipe(s);
    }

}
