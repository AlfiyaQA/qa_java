package com.example;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@RunWith(Parameterized.class)

public class FelineTest {

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    Feline feline = spy(new Feline());
    private final int expectedKittens;
    private final int actualKittens;

    public FelineTest(int expectedKittens, int actualKittens) {
        this.expectedKittens = expectedKittens;
        this.actualKittens = actualKittens;
    }

    @Parameterized.Parameters(name = "Тестовые данные: {0} {1}")
    public static Object[][] getData() {
        return new Object[][]{
                {10, 10},
                {5, 5}
        };
    }

    @Test
    public void getKittensReturnsCorrectValue() {
        Mockito.when(feline.getKittens()).thenReturn(expectedKittens);
        int actualKittens = feline.getKittens();
        Assert.assertEquals("Method getKittens() returns incorrect value", expectedKittens, actualKittens);
    }

    @Test
    public void checkEatingMeat() throws Exception {
        Mockito.when(feline.getFood("Хищник")).thenReturn(List.of("Животные", "Птицы", "Рыба"));
        feline.eatMeat();
        verify(feline).getFood("Хищник");
    }

    @Test
    public void getCorrectFamilyName() {
        String expectedFamily = "Кошачьи";
        String actualFamily = feline.getFamily();
        Assert.assertEquals("Method getFamily() returns incorrect value", expectedFamily, actualFamily);
    }

    @Test
    public void getKittensReturnsOne() {
        int expected = 1;
        int actual = feline.getKittens();
        Assert.assertEquals("Method getKittens() returns incorrect value", expected, actual);
    }
}
