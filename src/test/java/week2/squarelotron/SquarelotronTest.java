package week2.squarelotron;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SquarelotronTest {
    Squarelotron sq;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        sq = new Squarelotron(3);
    }

    @Test
    void upsideDownFlip() {
        int[][] matrixTest = new int[3][3];
        int[] l1 = {7, 8, 9};
        int[] l2 = {4, 5, 6};
        int[] l3 = {1, 2, 3};
        matrixTest[0] = l1;
        matrixTest[1] = l2;
        matrixTest[2] = l3;

        assertArrayEquals(matrixTest, sq.upsideDownFlip(1).squarelotron);
    }

    @Test
    void mainDiagonalFlip() {
        int[][] matrixTest = new int[3][3];
        int[] l1 = {1, 4, 7};
        int[] l2 = {2, 5, 8};
        int[] l3 = {3, 6, 9};
        matrixTest[0] = l1;
        matrixTest[1] = l2;
        matrixTest[2] = l3;

        assertArrayEquals(matrixTest, sq.mainDiagonalFlip(1).squarelotron);
    }

    @Test
    void rotateRight() {
        int[][] matrixTest = new int[3][3];
        int[] l1 = {7, 4, 1};
        int[] l2 = {8, 5, 2};
        int[] l3 = {9, 6, 3};
        matrixTest[0] = l1;
        matrixTest[1] = l2;
        matrixTest[2] = l3;

        sq.rotateRight(3);
        assertArrayEquals(matrixTest, sq.squarelotron);
    }
}