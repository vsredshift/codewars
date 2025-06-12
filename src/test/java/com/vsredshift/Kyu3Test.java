package test.java.com.vsredshift;

import main.java.com.vsredshift.kyu3.Spiralizor;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class Kyu3Test {

    // SpiralizorTests

    @Test
    public void test5() {
        assertArrayEquals(
                new int[][]{
                        {1, 1, 1, 1, 1},
                        {0, 0, 0, 0, 1},
                        {1, 1, 1, 0, 1},
                        {1, 0, 0, 0, 1},
                        {1, 1, 1, 1, 1}
                },
                Spiralizor.spiralize(5));
    }

    @Test
    public void test8() {
        assertArrayEquals(
                new int[][]{
                        {1, 1, 1, 1, 1, 1, 1, 1},
                        {0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 1, 1, 1, 1, 1, 0, 1},
                        {1, 0, 0, 0, 0, 1, 0, 1},
                        {1, 0, 1, 0, 0, 1, 0, 1},
                        {1, 0, 1, 1, 1, 1, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 1},
                        {1, 1, 1, 1, 1, 1, 1, 1},
                },
                Spiralizor.spiralize(8));
    }
}
