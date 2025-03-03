package com.gildedrose;

public class BackstagePasses extends ItemUpdate {
    public BackstagePasses(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    public void updateQuality() {
        if (quality < 50) {
            quality = quality + 1;
            if (sellIn < 11 && quality < 50) {
                quality += 1;
            }
            if (sellIn < 6 && quality < 50) {
                quality += 1;
            }

        }
        sellIn = sellIn - 1;
        if (sellIn < 0) {
            quality = 0;
        }
    }
}
