package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void testNegativeSellIn() {
        ItemUpdate[] items = new ItemUpdate[] { new ItemUpdate("foo", -1, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(8, app.items[0].quality);
    }

    @Test
    void testNegativeQuality() {
        ItemUpdate[] items = new ItemUpdate[] { new ItemUpdate("foo", -1, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void testNegativeQuality2() {
        ItemUpdate[] items = new ItemUpdate[] { new ItemUpdate("foo", 1, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void testAgedBrieQuality() {
        ItemUpdate[] items = new ItemUpdate[] { new AgedBrie(GildedRose.AGED_BRIE, 1, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(2, app.items[0].quality);
    }

    @Test
    void testAgedBrieQuality2() {
        ItemUpdate[] items = new ItemUpdate[] { new AgedBrie(GildedRose.AGED_BRIE, -1, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(3, app.items[0].quality);
    }

    @Test
    void testAgedBrieQuality3() {
        ItemUpdate[] items = new ItemUpdate[] { new AgedBrie(GildedRose.AGED_BRIE, -1, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void testMaxQuality() {
        ItemUpdate[] items = new ItemUpdate[] { new AgedBrie(GildedRose.AGED_BRIE, 1, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void testSulfurasQuality() {
        ItemUpdate[] items = new ItemUpdate[] { new Sulfuras(GildedRose.SULFURAS_HAND_OF_RAGNAROS, 1, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(80, app.items[0].quality);
    }

    @Test
    void testSulfurasQuality2() {
        ItemUpdate[] items = new ItemUpdate[] { new Sulfuras(GildedRose.SULFURAS_HAND_OF_RAGNAROS, -1, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(80, app.items[0].quality);
    }

    @Test
    void testBackStagePassesQuality() {
        ItemUpdate[] items = new ItemUpdate[] { new BackstagePasses(GildedRose.BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, 5, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, app.items[0].quality);
    }

    @Test
    void testBackStagePassesQuality2() {
        ItemUpdate[] items = new ItemUpdate[] { new BackstagePasses(GildedRose.BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, 10, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(3, app.items[0].quality);
    }

    @Test
    void testBackStagePassesQuality3() {
        ItemUpdate[] items = new ItemUpdate[] { new BackstagePasses(GildedRose.BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, -1, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void testBackStagePassesQuality4() {
        ItemUpdate[] items = new ItemUpdate[] { new BackstagePasses(GildedRose.BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, 11, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(2, app.items[0].quality);
    }

    @Test
    void testBackStagePassesQuality5() {
        ItemUpdate[] items = new ItemUpdate[] { new BackstagePasses(GildedRose.BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, 5, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void testBackStagePassesQuality6() {
        ItemUpdate[] items = new ItemUpdate[] { new BackstagePasses(GildedRose.BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, 10, 49) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void testBackStagePassesQuality7() {
        ItemUpdate[] items = new ItemUpdate[] { new BackstagePasses(GildedRose.BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, 5, 48) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }
}
