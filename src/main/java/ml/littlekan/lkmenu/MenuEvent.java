package ml.littlekan.lkmenu;

import java.io.FileNotFoundException;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

public class MenuEvent implements Listener {
    @EventHandler
    public void onItemClick(InventoryClickEvent inv){
        try {
            JavaPlugin instance = JavaPlugin.getPlugin(LKMenu.class);
            String[] menus = (String[]) instance.getConfig().get("enabled-menu");
            MenuJSONTemplate curinv = null;

            for (String menu : menus) {
                try {
                    curinv = new Loader().load(menu);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                try {
                    if (inv.getWhoClicked().getOpenInventory().getTitle() == curinv.getTitle()) {
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return;
            }
            inv.setCancelled(true);
            if (inv.getRawSlot() > inv.getInventory().getSize() || inv.getRawSlot() < 0) {
                return;
            }

            ItemJson[] items = curinv.getItems();
            for (ItemJson item : items) {
                if (inv.getRawSlot() == item.getIndex()) {
                    Player player = (Player) inv.getWhoClicked();
                    for (String command : item.getClickEvent()) {
                        player.chat(command);
                    }
                }
            }
        }catch (Exception e){
            // Do nothing
        }
    }
}
