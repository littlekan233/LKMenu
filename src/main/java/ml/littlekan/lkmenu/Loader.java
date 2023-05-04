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
    public Template load(String name) throws FileNotFoundException {
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
        Template gui = gson.fromJson(new JsonReader(new FileReader(file)), Template.class);
        return gui;
    }

    public Inventory toInstance(Template template){
        if(template.getHeight() == 0) template.setHeight(instance.getConfig().getInt("default-height"));
        if(template.getWidth() == 0) template.setWidth(instance.getConfig().getInt("default-width"));

        int width = template.getWidth();
        int height = template.getHeight();
        String title = template.getTitle()
                .replace("&","§")
                .replace("§$","&");
        List<Template.ItemsBean> items = template.getItems();

        Inventory gui = Bukkit.createInventory(null, height * width, title);

        for (Template.ItemsBean item : items) {
            ItemStack stack = new ItemStack(Material.getMaterial(item.getId()), item.getAmount() | 1);
            if(item.getMeta() != null){
                Template.ItemsBean.MetaBean json = item.getMeta();
                ItemMeta meta = stack.getItemMeta();
                if(json.getDisplayname() != "") meta.setDisplayName(json.getDisplayname()
                        .replace("&", "§")
                        .replace("§$", "&"));
                if(json.getLore() != null){
                    List<String> lore = new ArrayList<>();
                    for (String str : json.getLore()){
                        lore.add(str.replace("&", "§").replace("§$", "&"));
                    }
                    meta.setLore(lore);
                }
                if(json.getEnchantment() != null){
                    for (Template.ItemsBean.MetaBean.EnchantmentBean enchant : json.getEnchantment()){
                        meta.addEnchant(Enchantment.getByKey(NamespacedKey.fromString(enchant.getId())), enchant.getLevel(), false);
                    }
                }
            }
            gui.setItem(item.getIndex(), stack);
        }

        return gui;
    }
}
