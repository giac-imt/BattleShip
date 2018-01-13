package battleship.test;

import org.junit.Before;
import org.junit.Test;
import battleship.Ship;

import static org.junit.Assert.*;

public class TestShip {

    private Ship ship;

    @Before
    public void setUp() {
        ship = new Ship("Chalutier", 5, Ship.Orientation.HORIZONTAL);
    }

    @Test
    public void test_initialisation() {
        assertEquals("should be named as required", "Chalutier", ship.name());
        assertEquals("should have required size", 5, ship.size());
        assertFalse("should still be floating", ship.isSunk());
    }

    @Test
    public void test_bombing() {
        ship.bomb();
        ship.bomb();
        ship.bomb();
        ship.bomb();
        assertFalse("should still be alive", ship.isSunk());
        ship.bomb();
        assertTrue("should be dead by now", ship.isSunk());
    }
}
