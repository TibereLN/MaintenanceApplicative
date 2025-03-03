package com.gildedrose;

public class AgedBrie extends ItemUpdate {
    public AgedBrie(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    public void updateQuality() {
        if (quality < 50) {
            quality += 1;
        }
        sellIn = sellIn - 1;
        if (sellIn < 0 && quality < 50) {
            quality += 1;
        }
    }
}
