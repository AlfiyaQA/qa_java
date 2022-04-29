package com.example;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class LionTest {

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Mock
    Feline feline = new Feline();
    Lion lion = new Lion(feline);

    private final String sex;

    public LionTest(String sex) {this.sex = sex;}

    @Parameterized.Parameters(name = "Тестовые данные: {0} {1}")
    public static Object[][] getTextData() {
        return new Object[][]{
                {"Самец"},
                {"Самка"}
        };
    }

    @Test
    public void checkHavingMane() throws Exception {
        Lion lion = new Lion(sex);
        lion.doesHaveMane();
        if ("Самец".equals(sex)) {
            assertTrue(lion.hasMane);
        } else {
            assertFalse(lion.hasMane);
        }
    }

    @Test(expected = Exception.class)
    public void checkHasManeThrowsException() throws Exception {
        Lion lion = new Lion("Самочка");
        when(lion.hasMane).thenThrow(Exception.class);
    }

    @Test
    public void getKittensReturnsOneForLion() {
        int expected = 1;
        int actual = lion.getKittens();
        Assert.assertEquals("Method getKittens() returns incorrect value", expected, actual);
    }

    @Test
    public void getFoodCorrectListForLion() throws Exception {
        Mockito.when(feline.getFood("Хищник")).thenReturn(List.of("Животные", "Птицы", "Рыба"));
        List<String> expected = List.of("Животные", "Птицы", "Рыба");
        List<String> actual = lion.getFood();
        Assert.assertEquals("Method getFood() returns incorrect value for Lion", expected, actual);
    }

    @Test
    public void getFoodIncorrectListForLion() throws Exception {
        Mockito.when(feline.getFood("Травоядное")).thenReturn(List.of("Трава", "Различные растения"));
        List<String> expected = List.of("Трава", "Различные растения");
        List<String> actual = lion.getFood();
        Assert.assertNotEquals("Method getFood() returns the value as expected for Lion", expected, actual);
    }

    @Test(expected = Exception.class)
    public void checkGetFoodThrowsException() throws Exception {
        Mockito.when(feline.getFood("Гибрид")).thenReturn(List.of("Трава", "Различные растения"));
        when(lion.getFood()).thenThrow(Exception.class);
    }
}