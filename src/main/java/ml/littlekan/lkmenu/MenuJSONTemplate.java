package ml.littlekan.lkmenu;

import org.bukkit.plugin.java.JavaPlugin;

import com.google.gson.annotations.SerializedName;

public class MenuJSONTemplate {
    @SerializedName("TITLE")
    private String title = "Title";
    @SerializedName("ITEMS")
    private ItemJson[] items;
    @SerializedName("WIDTH")
    private int width = JavaPlugin.getPlugin(LKMenu.class).getConfig().getInt("default-width");
    @SerializedName("HEIGHT")
    private int height = JavaPlugin.getPlugin(LKMenu.class).getInt("default-height");

    public String getTitle() {
        return title;
    }

    public ItemJson[] getItems() {
        return items;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
