package ml.littlekan.lkmenu;

import java.io.*;
import java.util.*;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Loader {
    public JavaPlugin instance = JavaPlugin.getPlugin(LKMenu.class);

    private boolean isItemExists(List<String> strlist, String str){
        for (String s : strlist) if(s.equals(str)) return true;
        return false;
    }
    public MenuJSONTemplate load(String name) throws FileNotFoundException {
        File datafolder = org.bukkit.plugin.java.JavaPlugin.getPlugin(LKMenu.class).getDataFolder();
        File menus = new File(datafolder, "menus");
        File file = new File(menus, name + ".json");
        if(!file.exists()){
            instance.getLogger().severe("This menu is enabled, but file \""+name+".json\" does not exists!");
            return null;
        }else if (file.exists() && !isItemExists(instance.getConfig().getStringList("enabled-menus"), name)){
            instance.getLogger().severe("File \""+name+".json\" exists, but not enable in config.yml!");
            return null;
        }
        Gson gson = new Gson();
        MenuJSONTemplate gui = gson.fromJson(new JsonReader(new FileReader(file)), MenuJSONTemplate.class);
        return gui;
    }

    public Inventory toInstance(MenuJSONTemplate template){
        int width = template.getWidth();
        int height = template.getHeight();
        String title = template.getTitle()
                .replace("&","§")
                .replace("§$","&");
        List<ItemJson> items = template.getItems();

        Inventory gui = Bukkit.createInventory(null, height*width, title);

        for (ItemJson item : items) {
            ItemStack stack = new ItemStack(Material.getMaterial(item.getId()), item.getAmount());
            if(item.getMeta() != new MetaJsonTemplate()){
                MetaJsonTemplate json = item.getMeta();
                ItemMeta meta = stack.getItemMeta();
                if(json.getDisplayName() != "") meta.setDisplayName(json.getDisplayName());
                if(json.getLore() != new ArrayList<String>()){
                    List<String> lore = new ArrayList<>();
                    for (String str : json.getLore()){
                        lore.add(str.replace("&", "§").replace("§$", "&"));
                    }
                    meta.setLore(lore);
                }
                if(json.getEnchantments() != new ArrayList<EnchantmentJson>()){
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
