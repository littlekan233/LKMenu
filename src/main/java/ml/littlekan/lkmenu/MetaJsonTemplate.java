package ml.littlekan.lkmenu;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class MetaJsonTemplate {
    @SerializedName("DISPLAYNAME")
    private String displayname;
    @SerializedName("LORE")
    private List<String> lore;
    @SerializedName("ENCHANTMENT")
    private List<EnchantmentJson> enchantments;

    public String getDisplayName(){
        if (displayname == null) {
            return "";
        }
        return displayname;
    }

    public List<String> getLore(){
        if (lore == null) {
            return new ArrayList<>();
        }
        return lore;
    }

    public List<EnchantmentJson> getEnchantments(){
        if (enchantments == null) {
            return new ArrayList<>();
        }
        return enchantments;
    }
}
