package ml.littlekan.lkmenu;

import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public final class LKMenu extends JavaPlugin {
    private java.util.logging.Logger logger = getLogger();

    private void shareversion(){
        String pluginYmlPath = "/plugin.yml";
        Reader reader = new InputStreamReader(getClass().getResourceAsStream(pluginYmlPath));
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(reader);
        SharedVariable.version = yaml.getString("version");
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        if(SharedVariable.version.equals("")) shareversion();
        for (String str : SharedVariable.logo) {
            logger.info(str);
        }
        logger.info("LKMenu v" +SharedVariable.version + ", by @littlekan233");
        logger.info("Hello, server owner! >w<");
        if(!getDataFolder().isDirectory()){
            logger.info("It seems like you're first of use this plugin, initializing...");
            /** Lazy to delete
            getConfig().set("default-width", 7);
            getConfig().set("default-height", 5);
            getConfig().set("enabled-menu",new String[]{});
            saveConfig();
             **/
            //
            getDataFolder().mkdir();
            saveDefaultConfig();
            new File(getDataFolder(), "menus").mkdir();
        }
        logger.info("Registering commands & event listeners...");
        getCommand("openmenu").setExecutor(new OpenCommand());
        logger.info("Registered ml.littlekan.lkmenu.OpenCommand to command /openmenu");
        getCommand("lkmenu").setExecutor(new MainCommand());
        getCommand("lkmenu").setTabCompleter(new MainCommand());
        logger.info("Registered ml.littlekan.lkmenu.MainCommand to command /lkmenu");
        getServer().getPluginManager().registerEvents(new MenuEvent(), this);
        logger.info("Registered event ml.littlekan.lkmenu.MenuEvent");
        logger.info("Done! ");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        logger.info("Unregistering event");
        HandlerList handler = new HandlerList();
        handler.unregister(new MenuEvent());
        logger.info("See you next time");  
    }
}
