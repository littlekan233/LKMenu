package ml.littlekan.lkmenu;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

public class OpenCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("§r[§cLKMenu§r] §cConsole cannot execute this command! ");
            return true;
        }

        if(args.length == 0){
            sender.sendMessage("§r[§cLKMenu§r] §cYou are not specify the menu name! See \"/lkmenu help\" for help.");
            return true;
        }
        Template menu;
        try {
            menu = new Loader(args[0]).menu;
        } catch (Exception e) {
            java.util.logging.Logger l = JavaPlugin.getPlugin(LKMenu.class).getLogger();
            l.warning("Command exception!");
            l.warning("Stack: \n"+e);
            menu = null;
        }

        if(menu == null){
            sender.sendMessage("§r[§cLKMenu§r] §cThis menu does not exists or not enabled! ");
            return true;
        }

        Inventory gui = null;
        try {
            gui = new Loader(args[0]).toInstance();
        } catch (Exception e) {
            java.util.logging.Logger l = JavaPlugin.getPlugin(LKMenu.class).getLogger();
            l.warning("Command exception!");
            l.warning("Stack: \n"+e);
            sender.sendMessage("§r[§cLKMenu§r] §cAn internal error to occurred this command! ");
            return true;
        }

        Player player = (Player) sender;
        player.openInventory(gui);
        return true;
    }
}
