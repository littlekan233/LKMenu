package ml.littlekan.lkmenu;

import org.bukkit.plugin.java.JavaPlugin;

import com.google.gson.annotations.SerializedName;

public class MenuJSONTemplate {
    @SerializedName("TITLE")
    private String title;
    @SerializedName("ITEMS")
    private ItemJson[] items;
    @SerializedName("WIDTH")
    private Integer width;
    @SerializedName("HEIGHT")
    private Integer height;

    public String getTitle() {
        if (title == null) {
            return "Title";
        }
        return title;
    }

    public ItemJson[] getItems() {
        return items;
    }

    public int getWidth() {
        if (width == null) {
            return JavaPlugin.getPlugin(LKMenu.class).getConfig().getInt("default-width");
        }
        return width;
    }

    public int getHeight() {
        if (height == null) {
            return JavaPlugin.getPlugin(LKMenu.class).getConfig().getInt("default-height");
        }
        return height;
    }
}
