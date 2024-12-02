package com.gildedrose;

class GildedRose {
    List<Item> items;

    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    public GildedRose(List<Item> items) {
        this.items = items;
    }

     public void updateQuality() {
        for (Item item : items) {
            if (isSulfuras(item)) {
                continue;
            }

            updateSellIn(item);

        if (item.name.equals(AGED_BRIE)) {
                updateAgedBrie(item);
                    continue;
            } 
        if (item.name.equals(BACKSTAGE_PASSES)) {
                updateBackstagePass(item);
                    continue;
            } 
                updateNormalItem(item);

            if (item.sellIn < 0) {
                handleExpiredItem(item);
            }
        }
    }
        private void updateSellIn(Item item) {
        item.sellIn -= 1;
    }

      private void updateAgedBrie(Item item) {
        if (item.quality < 50) {
            item.quality += 1;
        }
      }
       private void updateBackstagePass(Item item) {
        if (item.quality < 50) {
            item.quality += 1;
        }
        if (item.sellIn < 10 && item.quality < 50) {
            item.quality += 1;
        }
        if (item.sellIn < 5 && item.quality < 50) {
            item.quality += 1; 
        }
    }
    
    private void updateNormalItem(Item item) {
    if (item.quality > 0) {
        item.quality -= 1; 
    }
}
    private void handleExpiredItem(Item item) {
    if (isSpecialItem(item)) {
        handleExpiredSpecialItem(item);
    } else {
        handleExpiredNormalItem(item);
    }
}
    private boolean isSpecialItem(Item item) {
    return item.name.equals(AGED_BRIE) || item.name.equals(BACKSTAGE_PASSES);
}
    
    private void handleExpiredSpecialItem(Item item) {
    if (item.name.equals(AGED_BRIE)) {
        item.quality += 1;
    } else if (item.name.equals(BACKSTAGE_PASSES)) {
        item.quality = 0;
    }
}
    
    private void handleExpiredNormalItem(Item item) {
        if (item.quality > 0) {
        item.quality -= 1;
    }
}

     private boolean isSulfuras(Item item) {
    return item.name.equals("Sulfuras, Hand of Ragnaros");
     }
}
