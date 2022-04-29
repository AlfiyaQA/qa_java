package com.example;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class CatTest {

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    Feline feline = new Feline();
    Cat cat = new Cat(feline);

    @Test
    public void getSoundReturnsCorrectValue() {
        String expectedSound = "Мяу";
        String actualSound = cat.getSound();
        Assert.assertEquals("Method getSound() returns incorrect value", expectedSound, actualSound);
    }

    @Mock
    Predator predator;

    @Test
    public void getFoodReturnsCorrectValueForCat() throws Exception {
        Mockito.when(predator.eatMeat()).thenReturn(List.of("Животные", "Птицы", "Рыба"));
        List<String> expected = List.of("Животные", "Птицы", "Рыба");
        List<String> actual = cat.getFood();
        Assert.assertEquals("Method getFood() returns incorrect value for Cat", expected, actual);
    }
}
