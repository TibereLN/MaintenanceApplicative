package com.gildedrose;

class GildedRose {
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT = "Backstage passes to a DamYUNG concert";
    public static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    public static final String CONJURED = "Conjured";
    ItemSimple[] items;

    public GildedRose(ItemSimple[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (ItemSimple item : items) {
            item.updateQuality();
        }
    }
}
