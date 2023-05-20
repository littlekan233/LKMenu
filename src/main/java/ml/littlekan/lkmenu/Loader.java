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
import org.apache.commons.jexl3.JexlContext;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;
import org.apache.commons.jexl3.MapContext;
import org.apache.commons.jexl3.internal.Engine;

public class Loader {
    public JavaPlugin instance = JavaPlugin.getPlugin(LKMenu.class);

    public Template menu;

    public Loader(String name) throws FileNotFoundException, LKMenuLoadException {
        File datafolder = instance.getDataFolder();
        File menus = new File(datafolder, "menus");
        File file = new File(menus, name + ".json");
        if(!file.exists()){
            throw new LKMenuLoadException("This menu is (not) enabled, but file \""+name+".json\" does not exists!");
        }else if (file.exists() && !instance.getConfig().getStringList("enabled-menus").contains(name)){
            throw new LKMenuLoadException("File \""+name+".json\" exists, but not enable in config.yml!");
        }
        Gson gson = new Gson();
        menu = gson.fromJson(new JsonReader(new FileReader(file)), Template.class);
    }

    private Material getMaterial(String id){
        JexlEngine engine = new Engine();
        JexlContext context = new MapContext();
        context.set("Material", Material.class);
        String name = id.replace("minecraft:", "").toUpperCase();
        JexlExpression exp = engine.createExpression("Material."+name);
        return (Material) exp.evaluate(context);
    }

    public Inventory toInstance(){
        Template template = menu;
        if(template.getTitle() == null || template.getTitle() == "") template.setTitle("&aUntitled menu &r- &6LKMenu");

        int height = template.getHeight();
        String title = template.getTitle()
                .replace("&","§")
                .replace("§$","&");
        List<Template.ItemsBean> items = template.getItems();

        Inventory gui = Bukkit.createInventory(null, 9 * height, title);

        for (Template.ItemsBean item : items) {
            if (item.getAmount() == 0) item.setAmount(1);
            ItemStack stack = new ItemStack(getMaterial(item.getId()), item.getAmount());
            if(item.getMeta() != null){
                Template.ItemsBean.MetaBean json = item.getMeta();
                ItemMeta meta = stack.getItemMeta();
                if(json.getDisplayname() != "") meta.setDisplayName(json.getDisplayname()
                        .replace("&", "§")
                        .replace("§$", "&"));
                if(json.getLore() != null){
                    List<String> lore = new ArrayList<>();
                    for (String str : json.getLore()){
                        lore.add(str
                            .replace("&", "§")
                            .replace("§$", "&")
                        );
                    }
                    meta.setLore(lore);
                }
                if(json.getEnchantment() != null){
                    for (Template.ItemsBean.MetaBean.EnchantmentBean enchant : json.getEnchantment()){
                        meta.addEnchant(Enchantment.getByKey(NamespacedKey.fromString(enchant.getId())), enchant.getLevel(), false);
                    }
                }
                stack.setItemMeta(meta);
            }
            gui.setItem(item.getIndex(), stack);
        }

        return gui;
    }
}
