package ml.littlekan.lkmenu;

import java.io.FileNotFoundException;
import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class MenuEvent implements Listener {
    @EventHandler
    public void onItemClick(InventoryClickEvent inv){
        try {
            JavaPlugin instance = JavaPlugin.getPlugin(LKMenu.class);
            List<String> menus = instance.getConfig().getStringList("enabled-menu");
            Template curinv = null;

            for (String menu : menus) {
                try {
                    curinv = new Loader().load(menu);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                try {
                    if (inv.getWhoClicked().getOpenInventory().getTitle().equals(curinv.getTitle())) {
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

            List<Template.ItemsBean> items = curinv.getItems();
            for (Template.ItemsBean item : items) {
                if (inv.getRawSlot() == item.getIndex()) {
                    Player player = (Player) inv.getWhoClicked();
                    for (String command : item.getClick()) {
                        player.chat(command);
                    }
                }
            }
        } catch (Exception e) {
            // Do nothing
        }
    }
}
