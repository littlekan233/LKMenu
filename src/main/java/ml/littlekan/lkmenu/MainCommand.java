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
            sender.sendMessage("§aServer is running §6LKMenu §bv" + SharedVariable.version + "§a! <3");
            sender.sendMessage("§aType §6§l\"/lkmenu help\" §afor more §linformation.");
        } else if(args[0].equals("open")){
            if(sender.hasPermission("lkmenu.open")){
                return new OpenCommand().onCommand(sender, command, "openmenu", new String[]{args[1]});
            }else{
                sender.sendMessage("§r[§cLKMenu§r] §cYou don't have permission to do that!");
            }
        }else if(args[0].equals("logo")){
            if(sender.hasPermission("lkmenu.showlogo")){
                sender.sendMessage("§a[§6LKMenu§a]§r Disabling LKMenu v"+SharedVariable.version);
                sender.sendMessage("§a[§6LKMenu§a]§r Unregistering events");
                sender.sendMessage("§a[§6LKMenu§a]§r See you next time");
                sender.sendMessage("§a[§6LKMenu§a]§r Enabling LKMenu v"+SharedVariable.version);
                for(String str : SharedVariable.logo){
                    sender.sendMessage("§a[§6LKMenu§a]§r "+str);
                }
                sender.sendMessage("§a[§6LKMenu§a]§r LKMenu v" +SharedVariable.version + ", by @littlekan233");
                sender.sendMessage("§a[§6LKMenu§a]§r Hello, server owner! >w<");
                sender.sendMessage("§a[§6LKMenu§a]§r Registering commands & event listeners...");
                sender.sendMessage("§a[§6LKMenu§a]§r Registered ml.littlekan.lkmenu.OpenCommand to command /openmenu");
                sender.sendMessage("§a[§6LKMenu§a]§r Registered ml.littlekan.lkmenu.MainCommand to command /lkmenu");
                sender.sendMessage("§a[§6LKMenu§a]§r Registered event ml.littlekan.lkmenu.MenuEvent");
                sender.sendMessage("§a[§6LKMenu§a]§r Done! ");
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
                sender.sendMessage("§6/lkmenu logo  §7-- §aRestart plugin...?");
                sender.sendMessage("§6/lkmenu open <name>  §7-- §aSee §6\"/openmenu <name>\"§a.");
                sender.sendMessage("§6/lkmenu reload  §7-- §aReload config. (if add hard will be restart plugin (for update))");
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
        List<String> list = new ArrayList<>();
        if(args.length == 1){
            list.add("help");
            list.add("logo");
            list.add("open");
            list.add("reload");
            list.add("version");
            return list;
        }
        return null;
    }
}
