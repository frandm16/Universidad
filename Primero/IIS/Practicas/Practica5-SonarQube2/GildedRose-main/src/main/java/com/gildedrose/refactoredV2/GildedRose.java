package com.gildedrose.refactoredV2;

import java.util.Arrays;

abstract class TypedItem extends Item {
    protected TypedItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    public abstract void updateQuality();
}

class AgedBrieItem extends TypedItem {
    public AgedBrieItem(int sellIn, int quality) {
        super(GildedRose.BRIE, sellIn, quality);
    }

    @Override
    public void updateQuality() {
        if (quality < 50) quality++;
        sellIn--;
        if (sellIn < 0 && quality < 50) quality++;
    }
}

class BackstagePassesItem extends TypedItem {
    public BackstagePassesItem(int sellIn, int quality) {
        super(GildedRose.PASSES, sellIn, quality);
    }

    @Override
    public void updateQuality() {
        if (sellIn > 10) {
            if (quality < 50) quality++;
        } else if (sellIn > 5) {
            if (quality < 50) quality += 2;
        } else if (sellIn > 0) {
            if (quality < 50) quality += 3;
        } else {
            quality = 0;
        }
        sellIn--;
    }
}

class SulfurasItem extends TypedItem {
    public SulfurasItem(int sellIn, int quality) {
        super(GildedRose.SULFURAS, sellIn, quality);
    }

    @Override
    public void updateQuality() {
        // Sulfuras no cambia
    }
}

class NormalItem extends TypedItem {    //lo que sería el default del SWITCH
    public NormalItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void updateQuality() {
        if (quality > 0) quality--;
        sellIn--;
        if (sellIn < 0 && quality > 0) quality--;
    }
}

class GildedRose {
    public static final String PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String BRIE = "Aged Brie";

    TypedItem[] items;

    public GildedRose(Item[] items) {
        this.items = Arrays.stream(items)
                .map(GildedRose::createTypedItem)
                .toArray(TypedItem[]::new);
    }

    private static TypedItem createTypedItem(Item item) {
        switch (item.name) {
            case BRIE:
                return new AgedBrieItem(item.sellIn, item.quality);
            case PASSES:
                return new BackstagePassesItem(item.sellIn, item.quality);
            case SULFURAS:
                return new SulfurasItem(item.sellIn, item.quality);
            default:
                return new NormalItem(item.name, item.sellIn, item.quality);
        }
    }

    public void updateQuality() {
        for (TypedItem item : items) {
            item.updateQuality();
        }
    }
}
