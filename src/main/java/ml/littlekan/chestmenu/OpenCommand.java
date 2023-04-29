package ml.littlekan.chestmenu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.FileNotFoundException;

public class OpenCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        MenuJSONTemplate menu;
        try {
            menu = new Loader().load(args[0]);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        if(menu == null){
            sender.sendMessage("§r[§cChestMenu§r] §cThis menu is not exists! ");
            return false;
        }

        Inventory gui = new Loader().toInstance(menu);

        Player player = (Player) sender;
        if(!(player instanceof Player)){
            sender.sendMessage("§r[§cChestMenu§r] §cConsole cannot execute this command! ");
            return false;
        }
        player.openInventory(gui);
        return false;
    }
}
