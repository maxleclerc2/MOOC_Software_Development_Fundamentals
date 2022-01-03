package week4.battleship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipTest {
    Ocean ocean;
    BattleShip bt;
    BattleCruiser bc;

    @BeforeEach
    void setUp() {
        ocean = new Ocean();
        bt = new BattleShip();
        bc = new BattleCruiser();
    }

    @Test
    void okToPlaceShipAt() {
        assertTrue(bt.okToPlaceShipAt(5, 5, true, ocean));

        assertTrue(bt.okToPlaceShipAt(0, 5, true, ocean));
        assertTrue(bt.okToPlaceShipAt(5, 0, true, ocean));

        assertFalse(bt.okToPlaceShipAt(-1, 5, true, ocean));
        assertFalse(bt.okToPlaceShipAt(5, -1, true, ocean));

        assertFalse(bt.okToPlaceShipAt(20, 5, true, ocean));
        assertFalse(bt.okToPlaceShipAt(5, 20, true, ocean));

        bt.placeShipAt(5, 5, true, ocean);

        assertFalse(bc.okToPlaceShipAt(5, 5, false, ocean));

        assertFalse(bc.okToPlaceShipAt(5, 4, false, ocean));
        assertFalse(bc.okToPlaceShipAt(6, 5, false, ocean));

        assertFalse(bc.okToPlaceShipAt(-1, 4, false, ocean));
        assertFalse(bc.okToPlaceShipAt(6, -1, false, ocean));

        assertFalse(bc.okToPlaceShipAt(20, 4, false, ocean));
        assertFalse(bc.okToPlaceShipAt(6, 20, false, ocean));

    }
}