package ml.littlekan.chestmenu;

import java.io.*;
import java.util.List;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Loader {
    public MenuJSONTemplate load(String name) throws FileNotFoundException {
        File jsondata = null;
        File datafolder = org.bukkit.plugin.java.JavaPlugin.getPlugin(ChestMenu.class).getDataFolder();
        for(File f : datafolder.listFiles()) if(f.getName() == "menus" && f.isDirectory()) break; else return null;
        File menus = new File(datafolder, "menus");
        for (File f : menus.listFiles()) if (f.getName() == name && f.isFile()) {jsondata = f; break;} else return null;
        Gson gson = new Gson();
        MenuJSONTemplate gui = gson.fromJson(new FileReader(jsondata), MenuJSONTemplate.class);
        return gui;
    }

    public Inventory toInstance(MenuJSONTemplate template){
        JavaPlugin instance = JavaPlugin.getPlugin(ChestMenu.class);

        int width = template.getWidth();
        int height = template.getHeight();
        String title = template.getTitle()
                .replace("&&","&")
                .replace("&","§");
        ItemJson[] items = template.getItems();


        if (width == 0) {
            instance.getConfig().get("default-width");
        }
        if (width == 0) {
            instance.getConfig().get("default-height");
        }

        Inventory gui = Bukkit.createInventory(null, height*width, title);

        for (ItemJson item : items) {
            ItemStack stack = new ItemStack(Material.getMaterial(item.getId()), item.getAmount());
            stack.getData();
            gui.setItem(item.getIndex(), stack);
        }

        return gui;
    }
}
