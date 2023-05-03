package ml.littlekan.lkmenu;

import org.bukkit.plugin.java.JavaPlugin;
import com.google.gson.annotations.SerializedName;
import java.util.List;

@Deprecated
public class MenuJSONTemplate {
    @SerializedName("TITLE")
    private String title;
    @SerializedName("ITEMS")
    private List<ml.littlekan.lkmenu.ItemJson> items;
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

    public List<ml.littlekan.lkmenu.ItemJson> getItems() {
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
