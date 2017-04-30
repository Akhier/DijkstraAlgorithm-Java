package com.akhier.djikstra;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;


/**
 * @author Dragonheart
 */
public class testVector2D {
    private Vector2D testVector;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        testVector = new Vector2D(0, 0, true);
    }

    /**
     * Test method for {@link Vector2D#visited()}.
     */
    @Test
    public final void testVisited() {
        assertFalse(testVector.visited());
    }

    /**
     * Test method for {@link Vector2D#setVisited()}.
     */
    @Test
    public final void testSetVisited() {
        testVector.setVisited();
        assertTrue(testVector.visited());
    }

    /**
     * Test method for {@link Vector2D#resetVisited()}.
     */
    @Test
    public final void testResetVisited() {
        testVector.setVisited();
        testVector.resetVisited();
        assertFalse(testVector.visited());
    }

    /**
     * Test method for {@link Vector2D#vectorId()}.
     */
    @Test
    public final void testVectorId() {
        int startid = testVector.vectorId();
        testVector = new Vector2D(0, 0, true);
        assertNotEquals(startid, testVector.vectorId());
        assertEquals(startid + 1, testVector.vectorId());
    }
}
