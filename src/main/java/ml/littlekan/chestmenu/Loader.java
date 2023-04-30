package ml.littlekan.chestmenu;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Loader {
    public JavaPlugin instance = JavaPlugin.getPlugin(ChestMenu.class);

    public MenuJSONTemplate load(String name) throws FileNotFoundException {
        if (!instance.getConfig().get("enabled-menu").equals(name)){
            instance.getLogger().severe("This menu is enabled, but file \""+name+".json\" does not exist!");
            return null;
        }
        File jsondata = null;
        File datafolder = org.bukkit.plugin.java.JavaPlugin.getPlugin(ChestMenu.class).getDataFolder();
        for(File f : datafolder.listFiles()) if(f.getName() == "menus" && f.isDirectory()) break; else return null;
        File menus = new File(datafolder, "menus");
        for (File f : menus.listFiles()) if (f.getName() == name && f.isFile()) {
            jsondata = f;
            break;
        } else {
            instance.getLogger().severe("File \""+name+".json\" exist, but not enable in config.yml!");
            return null;
        }
        Gson gson = new Gson();
        MenuJSONTemplate gui = gson.fromJson(new FileReader(jsondata), MenuJSONTemplate.class);
        return gui;
    }

    public Inventory toInstance(MenuJSONTemplate template){
        int width = template.getWidth();
        int height = template.getHeight();
        String title = template.getTitle()
                .replace("&","§")
                .replace("§$","&");
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
            if(item.getMeta() != new MetaJsonTemplate()){
                MetaJsonTemplate json = item.getMeta();
                ItemMeta meta = stack.getItemMeta();
                if(json.getDisplayName() != "") meta.setDisplayName(json.getDisplayName());
                if(json.getLore() != new String[]{}){
                    List<String> lore = new ArrayList<String>();
                    for (String str : json.getLore()){
                        lore.add(str.replace("&", "§").replace("§$", "&"));
                    }
                    meta.setLore(lore);
                }
                if(json.getEnchantments() != new EnchantmentJson[]{}){
                    for (EnchantmentJson enchant : json.getEnchantments()){
                        meta.addEnchant(Enchantment.getByKey(NamespacedKey.fromString(enchant.getId())), enchant.getLevel(), false);
                    }
                }
            }
            gui.setItem(item.getIndex(), stack);
        }

        return gui;
    }
}
