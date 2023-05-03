package ml.littlekan.lkmenu;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Template implements Serializable {
    private int width;
    private int height;
    private String title;
    private List<ItemsBean> items;

    @Data
    public static class ItemsBean implements Serializable {
        private int index;
        private int amount;
        private String id;
        private MetaBean meta;
        private List<String> click;

        @Data
        public static class MetaBean implements Serializable {
            private String displayname;
            private List<String> lore;
            private List<EnchantmentBean> enchantment;

            @Data
            public static class EnchantmentBean implements Serializable {
                private String id;
                private int level;
            }
        }
    }
}
