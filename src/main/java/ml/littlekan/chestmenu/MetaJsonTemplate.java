package ml.littlekan.chestmenu;

import com.google.gson.annotations.SerializedName;

public class MetaJsonTemplate {
    @SerializedName("DISPLAYNAME")
    private String displayname = "";
    @SerializedName("LORE")
    private String[] lore = new String[]{};
    @SerializedName("ENCHANTMENT")
    private EnchantmentJson[] enchantments = new EnchantmentJson[]{};

    public String getDisplayName(){
        return displayname;
    }

    public String[] getLore(){
        return lore;
    }

    public EnchantmentJson[] getEnchantments(){
        return enchantments;
    }
}
