package ml.littlekan.lkmenu;

import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import io.papermc.lib.PaperLib;

public final class LKMenu extends JavaPlugin {
    private java.util.logging.Logger logger = getLogger();

    @Override
    public void onEnable() {
        // Plugin startup logic
        logger.info("LKMenu 1.0.0, by @littlekan233");
        logger.info("Hello, server owner! >w<");
        if(!getDataFolder().isDirectory()){
            logger.info("It seems like you're first of use this plugin, initializing...");
            /** Lazy to delete
            getConfig().set("default-width", 7);
            getConfig().set("default-height", 5);
            getConfig().set("enabled-menu",new String[]{});
            saveConfig();
             **/
            new File(getDataFolder(), "menus").mkdir();
            saveDefaultConfig();
        }
        logger.info("Registering commands & event listeners...");
        getCommand("openmenu").setExecutor(new OpenCommand());
        getCommand("lkmenu").setExecutor(new MainCommand());
        getServer().getPluginManager().registerEvents(new MenuEvent(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        logger.info("See you next time");  
    }
}
