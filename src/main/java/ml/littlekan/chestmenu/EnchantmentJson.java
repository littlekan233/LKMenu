package ml.littlekan.chestmenu;

import com.google.gson.annotations.SerializedName;

public class EnchantmentJson {
    @SerializedName("ID")
    private String id;
    @SerializedName("LEVEL")
    private int level;

    public String getId(){
        return id;
    }
    public int getLevel(){
        return level;
    }
}
