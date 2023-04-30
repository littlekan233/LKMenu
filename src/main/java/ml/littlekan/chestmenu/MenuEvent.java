package ml.littlekan.chestmenu;

import java.io.FileNotFoundException;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

public class MenuEvent implements Listener {
    @EventHandler
    public void onItemClick(InventoryClickEvent inv){
        JavaPlugin instance = JavaPlugin.getPlugin(ChestMenu.class);
        String[] menus = (String[]) instance.getConfig().get("enabled-menu");
        MenuJSONTemplate curinv = null;
        for (String menu : menus) {
            try {
                curinv = new Loader().load(menu);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            try {
                if(inv.getWhoClicked().getOpenInventory().getTitle() == curinv.getTitle()){
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        inv.setCancelled(true);
    }
}
