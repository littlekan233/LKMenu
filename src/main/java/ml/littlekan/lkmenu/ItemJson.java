package ml.littlekan.lkmenu;

import com.google.gson.annotations.SerializedName;

public class ItemJson {
    @SerializedName("INDEX")
    private int index;
    @SerializedName("AMOUNT")
    private int amount = 1;
    @SerializedName("ID")
    private String id;
    @SerializedName("META")
    private MetaJsonTemplate meta = new MetaJsonTemplate();
    @SerializedName("CLICK")
    private String[] onclick;

    public int getAmount() {
        return amount;
    }

    public MetaJsonTemplate getMeta() {
        return meta;
    }

    public String getId() {
        return id;
    }

    public int getIndex() {
        return index;
    }

    public String[] getClickEvent(){
        return onclick;
    }
}
