package com.gildedrose;

public class Conjured extends ItemSimple {
    public Conjured(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    public void updateQuality() {
        if (quality > 1)  quality -= 2;
        else if (quality == 1) quality = 0;
        sellIn -= 1;
        if (sellIn < 0) {
            if (quality > 1) quality -= 2;
            else if (quality == 1) quality = 0;
        }
    }
}
