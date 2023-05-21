package ml.littlekan.lkmenu;

import java.io.FileNotFoundException;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.plugin.java.JavaPlugin;

public class MenuEvent implements Listener {
    @EventHandler
    public void onItemClick(InventoryClickEvent inv){
        JavaPlugin instance = JavaPlugin.getPlugin(LKMenu.class);
        try {
            InventoryType invtype = inv.getWhoClicked().getOpenInventory().getType();
            if(invtype != InventoryType.CHEST){
                return;
            }
            List<String> menus = instance.getConfig().getStringList("enabled-menus");
            Template curinv = null;
            boolean isMenu = false;

            for (String menu : menus) {
                try {
                    curinv = new Loader(menu).menu;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                try {
                    if (inv.getWhoClicked().getOpenInventory().getTitle().equals(curinv.getTitle())) {
                        isMenu = true;
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if(!isMenu) return;
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
            java.util.logging.Logger l = instance.getLogger();
            Player p = (Player) inv.getWhoClicked();
            l.warning("Event process exception!");
            l.warning("Player: "+p.getName());
            l.warning("Stack: \n"+e);
        }
    }
}
