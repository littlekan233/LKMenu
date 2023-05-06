package ml.littlekan.lkmenu;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public class MainCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        JavaPlugin instance = JavaPlugin.getPlugin(LKMenu.class);

        if(args.length == 0 || args[0].equals("version")){
            sender.sendMessage("§aServer is running §6LKMenu §bv1.0.0§a! <3");
            sender.sendMessage("§aType §6§l\"/lkmenu help\" §afor more §linformation.");
        } else if(args[0].equals("open")){
            if(sender.hasPermission("lkmenu.open")){
                new OpenCommand().onCommand(sender, command, "openmenu", new String[]{args[1]});
            }else{
                sender.sendMessage("§r[§cLKMenu§r] §cYou don't have permission to do that!");
            }
        }else if(args[0].equals("reload")){
            if(sender.hasPermission("lkmenu.reload")){
                instance.reloadConfig();
                sender.sendMessage("§a[§6LKMenu§a] Reload success!");
            }else{
                sender.sendMessage("§r[§cLKMenu§r] §cYou don't have permission to do that!");
            }
        }else if(args[0].equals("help")){
            if(sender.hasPermission("lkmenu.help")){
                sender.sendMessage("§l§7--- §6LKMenu Help §7---");
                sender.sendMessage("§6/openmenu <name>  §7-- §aOpen a menu.");
                sender.sendMessage("§6/lkmenu [version]  §7-- §aShow version.");
                sender.sendMessage("§6/lkmenu help  §7-- §aShow this message.");
                sender.sendMessage("§6/lkmenu open <name>  §7-- §aSee §6\"/openmenu <name>\"§a.");
                sender.sendMessage("§6/lkmenu reload  §7-- §aReload config. ");
            }else{
                sender.sendMessage("§r[§cLKMenu§r] §cYou don't have permission to do that!");
            }
        }else{
            sender.sendMessage("§r[§cLKMenu§r] §cUnknown command! Type §6\"/lkmenu help\"§c for help.");
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 1){
            List<String> list = new ArrayList<>();
            list.add("help");
            list.add("open");
            list.add("reload");
            list.add("version");
        }
        return null;
    }
}
