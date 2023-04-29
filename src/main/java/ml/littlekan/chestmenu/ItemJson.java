package ml.littlekan.chestmenu;

import com.google.gson.annotations.SerializedName;

public class ItemJson {
    @SerializedName("INDEX")
    private int index;
    @SerializedName("AMOUNT")
    private int amount = 1;
    @SerializedName("ID")
    private String id;
    @SerializedName("TAG")
    private Object tag;

    public int getAmount() {
        return amount;
    }

    public Object getTag() {
        return tag;
    }

    public String getId() {
        return id;
    }

    public int getIndex() {
        return index;
    }
}
