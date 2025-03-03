package com.gildedrose;

public class Conjured extends ItemUpdate {
    public Conjured(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    public void updateQuality() {
        if (quality > 0) {
            quality -= 2;
        }
        sellIn -= 1;
        if (sellIn < 0) {
            if (quality == 1) quality = 0;
            else quality -= 2;
        }
    }
}
