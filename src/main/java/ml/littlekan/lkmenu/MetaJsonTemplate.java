package ml.littlekan.lkmenu;

import com.google.gson.annotations.SerializedName;

public class MetaJsonTemplate {
    @SerializedName("DISPLAYNAME")
    private String displayname;
    @SerializedName("LORE")
    private String[] lore;
    @SerializedName("ENCHANTMENT")
    private EnchantmentJson[] enchantments;

    public String getDisplayName(){
        if (displayname == null) {
            return "";
        }
        return displayname;
    }

    public String[] getLore(){
        if (lore == null) {
            return new String[]{};
        }
        return lore;
    }

    public EnchantmentJson[] getEnchantments(){
        if (enchantments == null) {
            return new EnchantmentJson[]{};
        }
        return enchantments;
    }
}
