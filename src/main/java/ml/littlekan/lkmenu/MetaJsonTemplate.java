package ml.littlekan.lkmenu;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public class MetaJsonTemplate {
    @SerializedName("DISPLAYNAME")
    private String displayname;
    @SerializedName("LORE")
    private List<java.lang.String> lore;
    @SerializedName("ENCHANTMENT")
    private List<ml.littlekan.lkmenu.EnchantmentJson> enchantments;

    public String getDisplayName(){
        if (displayname == null) {
            return "";
        }
        return displayname;
    }

    public List<java.lang.String> getLore(){
        if (lore == null) {
            return new ArrayList<>();
        }
        return lore;
    }

    public List<ml.littlekan.lkmenu.EnchantmentJson> getEnchantments(){
        if (enchantments == null) {
            return new ArrayList<>();
        }
        return enchantments;
    }
}
