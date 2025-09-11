package com.gildedrose.refactoredV2;

import com.gildedrose.refactoredV2.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;    //Estos son para la parametrizacion
import org.junit.jupiter.params.provider.CsvSource;   //de los 5 test repetidos

import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * Para solucionar los problemas con los test lo unico
 * que me ha hecho falta cambiar ha sido
 * int newQuality = item.quality; por
 *  int newQuality = gildedRose.items[0].quality;
 *  Esto es debido a TypedItem que he cambiado en GiltedRose
 *  Aparte de eso no he cambiado nada
 */

/**
 * Unit tests for the Gilded Rose project
 *
 * @author Antonio J. Nebro
 */
@DisplayName(("The Gilded Rose"))
class GildedRoseTest {

  @Nested
  @DisplayName("When update Aged Brie")
  class TestCasesForBrie {
    public static final String AGED_BRIE = "Aged Brie";

    @DisplayName("Increases its quality by one if sellIn is greater than zero")
    @Test
    void givenAQualityValueAndPositiveSellInWhenUpdateThenQualityIncreasesByOne() {
      // Arrange
      int quality = 0;
      int sellIn = 2;
      com.gildedrose.refactoredV2.Item item = new com.gildedrose.refactoredV2.Item(AGED_BRIE, sellIn, quality);
      com.gildedrose.refactoredV2.GildedRose gildedRose = new com.gildedrose.refactoredV2.GildedRose(new com.gildedrose.refactoredV2.Item[] {item});

      // Act
      gildedRose.updateQuality();

      // Assert
      int newQuality = gildedRose.items[0].quality;
      assertEquals(quality + 1, newQuality);
    }

    @DisplayName("Increases its quality by two if sellIn is zero")
    @Test
    void givenAQualityValueAndSellInIsZeroWhenUpdateThenQualityIncreasesByOne() {
      // Arrange
      int quality = 10;
      int sellIn = 0;
      com.gildedrose.refactoredV2.Item item = new com.gildedrose.refactoredV2.Item(AGED_BRIE, sellIn, quality);
      com.gildedrose.refactoredV2.GildedRose gildedRose = new com.gildedrose.refactoredV2.GildedRose(new com.gildedrose.refactoredV2.Item[] {item});

      // Act
      gildedRose.updateQuality();

      // Assert
      int newQuality = gildedRose.items[0].quality;
      assertEquals(quality + 2, newQuality);
    }

    @DisplayName("Does not increase its quality with this is 50 and sell in is 0")
    @Test
    void givenAQualityValueOf50AndSellInIsZeroWhenUpdateThenQualityRemains() {
      // Arrange
      int quality = 50;
      int sellIn = 0;
      com.gildedrose.refactoredV2.Item item = new com.gildedrose.refactoredV2.Item(AGED_BRIE, sellIn, quality);
      com.gildedrose.refactoredV2.GildedRose gildedRose = new com.gildedrose.refactoredV2.GildedRose(new com.gildedrose.refactoredV2.Item[] {item});

      // Act
      gildedRose.updateQuality();

      // Assert
      int newQuality = item.quality;
      assertEquals(quality, newQuality);
    }

    @DisplayName("Does not increase its quality with this is 50 and sell in is positive")
    @Test
    void givenAQualityValueOf50AndSellInIsPositiveWhenUpdateThenQualityRemains() {
      // Arrange
      int quality = 50;
      int sellIn = 2;
      com.gildedrose.refactoredV2.Item item = new com.gildedrose.refactoredV2.Item(AGED_BRIE, sellIn, quality);
      com.gildedrose.refactoredV2.GildedRose gildedRose = new com.gildedrose.refactoredV2.GildedRose(new com.gildedrose.refactoredV2.Item[] {item});

      // Act
      gildedRose.updateQuality();

      // Assert
      int newQuality = item.quality;
      assertEquals(quality, newQuality);
    }

    @DisplayName("Decreases sell in with this is positive")
    @Test
    void givenAPositiveSellInValueWhenUpdateThenItsValueDecreasesByOne() {
      // Arrange
      int quality = 50;
      int sellIn = 2;
      com.gildedrose.refactoredV2.Item item = new com.gildedrose.refactoredV2.Item("Aged Brie", sellIn, quality);
      com.gildedrose.refactoredV2.GildedRose gildedRose = new com.gildedrose.refactoredV2.GildedRose(new com.gildedrose.refactoredV2.Item[] {item});

      // Act
      gildedRose.updateQuality();

      // Assert
      int newSellIn = gildedRose.items[0].sellIn;
      assertEquals(sellIn - 1, newSellIn);
    }

    @DisplayName("Decreases sell in with this is zero")
    @Test
    void givenAZeroSellInValueWhenUpdateThenItsValueDecreasesByOne() {
      // Arrange
      int quality = 50;
      int sellIn = 0;
      com.gildedrose.refactoredV2.Item item = new com.gildedrose.refactoredV2.Item("Aged Brie", sellIn, quality);
      com.gildedrose.refactoredV2.GildedRose gildedRose = new com.gildedrose.refactoredV2.GildedRose(new com.gildedrose.refactoredV2.Item[] {item});

      // Act
      gildedRose.updateQuality();

      // Assert
      int newSellIn = gildedRose.items[0].sellIn;
      assertEquals(sellIn - 1, newSellIn);
    }
  }

  @Nested
  @DisplayName("When update a backstage pass")
  class TestCasesForBackstagePass {
    public static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";

    @ParameterizedTest(name = "SellIn: {0}, Expected Quality Increase: {1}")
    @CsvSource({
            "11, 1",
            "10, 2",
            "8, 2",
            "5, 3",
            "1, 3"
    })
    void givenSellInValueWhenUpdateThenQualityIncreasesAsExpected(int sellIn, int expectedIncrease) {
      int quality = 20;
      com.gildedrose.refactoredV2.Item item = new com.gildedrose.refactoredV2.Item(BACKSTAGE_PASS, sellIn, quality);
      com.gildedrose.refactoredV2.GildedRose gildedRose = new com.gildedrose.refactoredV2.GildedRose(new com.gildedrose.refactoredV2.Item[]{item});

      gildedRose.updateQuality();
      int newQuality = gildedRose.items[0].quality;

      assertEquals(quality + expectedIncrease, newQuality);
    }

    @DisplayName("Its quality is zero when sell in is zero")
    @Test
    void givenSellInIsZeroWhenUpdateThenItsQualityIsZero() {
      // Arrange
      int quality = 20;
      int sellIn = 0;
      com.gildedrose.refactoredV2.Item item = new com.gildedrose.refactoredV2.Item(BACKSTAGE_PASS, sellIn, quality);
      com.gildedrose.refactoredV2.GildedRose gildedRose = new com.gildedrose.refactoredV2.GildedRose(new com.gildedrose.refactoredV2.Item[] {item});

      // Act
      gildedRose.updateQuality();

      // Assert
      int newQuality = gildedRose.items[0].quality;
      assertEquals(0, newQuality);
    }
  }

  @Nested
  @DisplayName("When update Sulfuras")
  class TestCasesForSulfuras {
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    @DisplayName("Its quality does not decrease")
    @Test
    void whenUpdateThenItsQualityRemainsTheSame() {
      // Arrange
      int quality = 80;
      int sellIn = 4;
      com.gildedrose.refactoredV2.Item item = new com.gildedrose.refactoredV2.Item(SULFURAS, sellIn, quality);
      com.gildedrose.refactoredV2.GildedRose gildedRose = new com.gildedrose.refactoredV2.GildedRose(new com.gildedrose.refactoredV2.Item[] {item});

      // Act
      gildedRose.updateQuality();

      // Assert
      int newQuality = gildedRose.items[0].quality;
      assertEquals(quality, newQuality);
    }

    @DisplayName("Its sell in date does not decrease")
    @Test
    void whenUpdateThenItsSellInValueRemainsTheSame() {
      // Arrange
      int quality = 80;
      int sellIn = 4;
      com.gildedrose.refactoredV2.Item item = new com.gildedrose.refactoredV2.Item(SULFURAS, sellIn, quality);
      com.gildedrose.refactoredV2.GildedRose gildedRose = new com.gildedrose.refactoredV2.GildedRose(new com.gildedrose.refactoredV2.Item[] {item});

      // Act
      gildedRose.updateQuality();

      // Assert
      int newSellIn = gildedRose.items[0].sellIn;
      assertEquals(sellIn, newSellIn);
    }
  }

  @Nested
  @DisplayName("When update a regular item")
  class TestCasesForRegularItems {
    public static final String REGULAR_ITEM = "An Item";

    @DisplayName("Decreases its quality by one if sellIn is greater than zero")
    @Test
    void givenAQualityValueAndPositiveSellInWhenUpdateThenQualityDecreasesByOne() {
      // Arrange
      int quality = 10;
      int sellIn = 2;
      com.gildedrose.refactoredV2.Item item = new com.gildedrose.refactoredV2.Item(REGULAR_ITEM, sellIn, quality);
      com.gildedrose.refactoredV2.GildedRose gildedRose = new com.gildedrose.refactoredV2.GildedRose(new com.gildedrose.refactoredV2.Item[] {item});

      // Act
      gildedRose.updateQuality();

      // Assert
      int newQuality = gildedRose.items[0].quality;
      assertEquals(quality - 1, newQuality);
    }

    @DisplayName("Decreases its quality by two if sellIn is zero")
    @Test
    void givenAQualityValueAndSellInIsZeroWhenUpdateThenQualityDecreasesByTwo() {
      // Arrange
      int quality = 10;
      int sellIn = 0;
      com.gildedrose.refactoredV2.Item item = new com.gildedrose.refactoredV2.Item(REGULAR_ITEM, sellIn, quality);
      com.gildedrose.refactoredV2.GildedRose gildedRose = new com.gildedrose.refactoredV2.GildedRose(new Item[] {item});

      // Act
      gildedRose.updateQuality();

      // Assert
      int newQuality = gildedRose.items[0].quality;
      assertEquals(quality - 2, newQuality);
    }
  }
}
