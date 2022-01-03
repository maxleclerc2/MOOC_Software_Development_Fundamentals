package week4.battleship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class OceanTest {
    Ocean ocean;
    BattleShip bt;

    @BeforeEach
    void setUp() {
        ocean = new Ocean();
        bt = new BattleShip();
    }

    @Test
    void placeAllShipsRandomly() {
        ocean.placeAllShipsRandomly();
        //ocean.print();
    }

    @Test
    void isOccupied() {
        bt.placeShipAt(3, 8, true, ocean);

        assertFalse(ocean.isOccupied(1, 3));
        assertTrue(ocean.isOccupied(3, 10));
    }

    @Test
    void shootAt() {
        bt.placeShipAt(3, 8, true, ocean);

        ocean.print();

        assertFalse(ocean.shootAt(1, 3));
        System.out.println("Shots fired: " + ocean.getShotsFired() +
                " ; Hits: " + ocean.getHitCount() + " ; Ships sunk: " + ocean.getShipsSunk());

        ocean.print();

        assertTrue(ocean.shootAt(3, 10));
        System.out.println("Shots fired: " + ocean.getShotsFired() +
                " ; Hits: " + ocean.getHitCount() + " ; Ships sunk: " + ocean.getShipsSunk());

        ocean.print();

        for (int j = 8; j < 16; j++) {
            assertTrue(ocean.shootAt(3, j));

            System.out.println("Shots fired: " + ocean.getShotsFired() +
                    " ; Hits: " + ocean.getHitCount() + " ; Ships sunk: " + ocean.getShipsSunk());

            System.out.println(Arrays.toString(ocean.ships[3][j].getHit()));
        }

        ocean.print();

        assertFalse(ocean.shootAt(3, 15));
        System.out.println("Shots fired: " + ocean.getShotsFired() +
                " ; Hits: " + ocean.getHitCount() + " ; Ships sunk: " + ocean.getShipsSunk());
        System.out.println(Arrays.toString(ocean.ships[3][8].getHit()));

        ocean.print();
    }

    @Test
    void getShotsFired() {
        for (int i = 1; i < 11; i++) {
            ocean.shootAt(i, i);
        }

        assertEquals(10, ocean.getShotsFired());
    }

    @Test
    void getHitCount() {
        bt.placeShipAt(5, 5, true, ocean);

        for (int i = 1; i < 11; i++) {
            ocean.shootAt(i, i);
        }

        assertEquals(1, ocean.getHitCount());
    }

    @Test
    void getShipsSunk() {
        bt.placeShipAt(5, 2, false, ocean);

        for (int i = 5; i < 13; i++) {
            assertEquals(0, ocean.getShipsSunk());
            ocean.shootAt(i, 2);
        }

        assertEquals(1, ocean.getShipsSunk());
    }

    @Test
    void isGameOver() {
        assertFalse(ocean.isGameOver());

        ocean.shipsSunk = 13;

        assertTrue(ocean.isGameOver());
    }

    @Test
    void print() {
        ocean.print();
    }
}