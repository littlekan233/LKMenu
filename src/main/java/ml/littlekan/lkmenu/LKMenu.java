package ml.littlekan.lkmenu;

import org.bukkit.plugin.java.JavaPlugin;

public final class LKMenu extends JavaPlugin {
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
            getConfig().set("enabled-menu",new String[]{});
            saveConfig();
        }
        logger.info("Registering commands & event listeners...");
        getCommand("openmenu").setExecutor(new OpenCommand());
        getServer().getPluginManager().registerEvents(new MenuEvent(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
