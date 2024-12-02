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
            updateItemQuality(item);
            handleItemExpiration(item);
        }
     }

        private void updateSellIn(Item item) {
        item.sellIn -= 1;
    }

         private void updateItemQuality(Item item) {
        if (isAgedBrie(item)) {
            updateAgedBrie(item);
        } else if (isBackstagePass(item)) {
            updateBackstagePass(item);
        } else {
            updateNormalItem(item);
        }
    }

       private void handleItemExpiration(Item item) {
        if (item.sellIn < 0) {
            if (isAgedBrie(item)) {
                item.quality += 1;
            } else if (isBackstagePass(item)) {
                item.quality = 0;
            } else {
                handleExpiredNormalItem(item);
            }
        }
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
    private void handleExpiredNormalItem(Item item) {
    if (item.quality > 0) {
            item.quality -= 1;
        }
    }
    
    private boolean isAgedBrie(Item item) {
        return item.name.equals(AGED_BRIE);
    }

    private boolean isBackstagePass(Item item) {
        return item.name.equals(BACKSTAGE_PASSES);
    }
    
    private boolean isSulfuras(Item item) {
        return item.name.equals(SULFURAS);
    }
