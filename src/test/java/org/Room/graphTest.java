package org.Room;

import junit.framework.TestCase;

import java.util.Random;

public class graphTest extends TestCase {

    public void testN() {
        Random randnum = new Random(42);
        Room.graph g = new Room.graph(3, 3, randnum);
        assertEquals(g.n(), 3);
    }
}