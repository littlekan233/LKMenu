package ml.littlekan.lkmenu;

import com.google.gson.annotations.SerializedName;

public class MenuJSONTemplate {
    @SerializedName("TITLE")
    private String title = "Title";
    @SerializedName("ITEMS")
    private ItemJson[] items;
    @SerializedName("WIDTH")
    private int width = 0;
    @SerializedName("HEIGHT")
    private int height = 0;

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
