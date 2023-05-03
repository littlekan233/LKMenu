package ml.littlekan.lkmenu;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Template implements Serializable {
    /**
     * width : 9
     * height : 6
     * title : &bawa
     * items : [{"index":3,"amount":4,"id":"minecraft:iron_ingot","meta":{"displayname":"&6love&$TEST","lore":["&aThis&$is A TEST","&lawa&kAAA"],"enchantment":[{"id":"protection","level":"1"},{"id":"respiration","level":"0"}]}}]
     */

    private int width;
    private int height;
    private String title;
    private List<ItemsBean> items;

    @Data
    public static class ItemsBean implements Serializable {
        /**
         * index : 3
         * amount : 4
         * id : minecraft:iron_ingot
         * meta : {"displayname":"&6love&$TEST","lore":["&aThis&$is A TEST","&lawa&kAAA"],"enchantment":[{"id":"protection","level":"1"},{"id":"respiration","level":"0"}]}
         */

        private int index;
        private int amount;
        private String id;
        private MetaBean meta;
        private List<String> click;

        @Data
        public static class MetaBean implements Serializable {
            /**
             * displayname : &6love&$TEST
             * lore : ["&aThis&$is A TEST","&lawa&kAAA"]
             * enchantment : [{"id":"protection","level":"1"},{"id":"respiration","level":"0"}]
             */

            private String displayname;
            private List<String> lore;
            private List<EnchantmentBean> enchantment;

            @Data
            public static class EnchantmentBean implements Serializable {
                /**
                 * id : protection
                 * level : 1
                 */

                private String id;
                private int level;
            }
        }
    }
}
