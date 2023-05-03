package ml.littlekan.lkmenu;

import com.google.gson.annotations.SerializedName;
import java.util.List;

@Deprecated
public class ItemJson {
    @SerializedName("INDEX")
    private Integer index;
    @SerializedName("AMOUNT")
    private Integer amount;
    @SerializedName("ID")
    private String id;
    @SerializedName("META")
    private MetaJsonTemplate meta;
    @SerializedName("CLICK")
    private List<String> onclick;

    public int getAmount() {
        if (amount == null) {
            return 1;
        }
        return amount;
    }

    public MetaJsonTemplate getMeta() {
        if (meta == null) {
            return new MetaJsonTemplate();
        }
        return meta;
    }

    public String getId() {
        return id;
    }

    public int getIndex() {
        return index;
    }

    public List<String> getClickEvent(){
        return onclick;
    }
}
