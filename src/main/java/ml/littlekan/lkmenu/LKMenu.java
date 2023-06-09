package ml.littlekan.lkmenu;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import org.bstats.bukkit.Metrics;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class LKMenu extends JavaPlugin {
    private java.util.logging.Logger logger = getLogger();

    private void shareversion(){
        String pluginYmlPath = "/plugin.yml";
        Reader reader = new InputStreamReader(getClass().getResourceAsStream(pluginYmlPath));
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(reader);
        SharedVariable.version = yaml.getString("version");
    }

    public void update(){
        if(SharedVariable.version.contains("SNAPSHOT")){
            logger.warning("Snapshot version can't use updater! ");
            return;
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                org.bukkit.plugin.java.JavaPlugin i = org.bukkit.plugin.java.JavaPlugin.getPlugin(
                    ml.littlekan.lkmenu.LKMenu.class
                );
                java.util.logging.Logger l = i.getLogger();
                ml.littlekan.lkmenu.UpdateChecker u = new UpdateChecker(l);
                long _lt = 0;
                long _ct = 0;
                ml.littlekan.lkmenu.UpdateCheckerTemplate v = null;
                try {
                    for (ml.littlekan.lkmenu.UpdateCheckerTemplate t : u.check()) {
                        long _ts = u.pttts(t.getPublished_at());
                        if (t.getTag_name() == ml.littlekan.lkmenu.SharedVariable.version){
                            _ct = _ts;
                        }
                        if (_ts > _lt) {
                            _lt = _ts;
                            v = t;
                        }
                    }
                    if(_lt > _ct){
                        l.info("§aNew version is §lavailable§r§a! ");
                        l.info("§6Current version: §l"+SharedVariable.version);
                        l.info("§aLatest version: §l"+v.getTag_name());
                    }else{
                        l.info("§6No new version available");
                    }
                } catch (IOException e) {
                    l.warning("Could not check updates! ");
                    l.warning(e.getMessage());
                }
            }
        }.runTaskAsynchronously(this);
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        new Metrics(this, 18702);
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
        logger.info("Done! Checking updates...");
        update();
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
