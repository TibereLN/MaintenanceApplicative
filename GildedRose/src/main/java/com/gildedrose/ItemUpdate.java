package com.gildedrose;

public class ItemUpdate extends Item {
    public ItemUpdate(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    public void updateQuality() {
        if (quality > 0) {
            quality = quality - 1;
        }
        sellIn -= 1;
        if (sellIn < 0 && quality > 0) {
            quality -= 1;
        }
    }
}
