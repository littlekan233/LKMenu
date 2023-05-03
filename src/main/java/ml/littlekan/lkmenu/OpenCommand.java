package ml.littlekan.lkmenu;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import java.io.FileNotFoundException;

public class OpenCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0){
            sender.sendMessage("§r[§cLKMenu§r] §cYou are not specify the menu name! See \"/lkmenu help\" for help.");
            return true;
        }
        Template menu;
        try {
            menu = new Loader().load(args[0]);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        if(menu == null){
            sender.sendMessage("§r[§cLKMenu§r] §cThis menu does not exists or not enabled! ");
            return true;
        }

        Inventory gui = new Loader().toInstance(menu);

        Player player = (Player) sender;
        if(!(player instanceof Player)){
            sender.sendMessage("§r[§cLKMenu§r] §cConsole cannot execute this command! ");
            return true;
        }
        player.openInventory(gui);
        return true;
    }
}
