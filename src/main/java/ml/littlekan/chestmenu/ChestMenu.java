package ml.littlekan.chestmenu;

import org.bukkit.plugin.PluginLogger;
import org.bukkit.plugin.java.JavaPlugin;
import com.google.gson.*;

import java.io.FileNotFoundException;

public final class ChestMenu extends JavaPlugin {
    private java.util.logging.Logger logger = getLogger();

    @Override
    public void onEnable() {
        // Plugin startup logic
        logger.info("ChestMenu 1.0.0, by @littlekan233");
        logger.info("Hello, server owner! >w<");
        if(!getDataFolder().isDirectory()){
            logger.info("It seems like you're first of use this plugin, initializing...");
            getConfig().set("default-width", 7);
            getConfig().set("default-height", 5);
        }
        logger.info("Registering commands...");
        getCommand("openmenu").setExecutor(new OpenCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
