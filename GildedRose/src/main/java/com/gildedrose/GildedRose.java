package com.gildedrose;

class GildedRose {
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    public static final String CONJURED = "Conjured";
    ItemUpdate[] items;

    public GildedRose(ItemUpdate[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (ItemUpdate item : items) {
            item.updateQuality();
        }
    }
}
