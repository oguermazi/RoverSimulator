package com.rover;

import com.rover.model.Direction;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {

    @Test
    void shouldFaceWest_whenFacingNorth_callRotateLeft() {
        assertEquals(Direction.W, Direction.N.rotateLeft());
    }

    @Test
    void shouldFaceSouth_whenFacingWest_callRotateLeft() {
        assertEquals(Direction.S, Direction.W.rotateLeft());
    }

    @Test
    void shouldFaceEast_whenFacingSouth_callRotateLeft() {
        assertEquals(Direction.E, Direction.S.rotateLeft());
    }

    @Test
    void shouldFaceNorth_whenFacingEast_callRotateLeft() {
        assertEquals(Direction.N, Direction.E.rotateLeft());
    }

    @Test
    void shouldFaceEast_whenFacingNorth_callRotateRight() {
        assertEquals(Direction.E, Direction.N.rotateRight());
    }

    @Test
    void shouldFaceSouth_whenFacingEast_callRotateRight() {
        assertEquals(Direction.S, Direction.E.rotateRight());
    }

    @Test
    void shouldFaceWest_whenFacingSouth_callRotateRight() {
        assertEquals(Direction.W, Direction.S.rotateRight());
    }

    @Test
    void shouldFaceNorth_whenFacingWest_callRotateRight() {
        assertEquals(Direction.N, Direction.W.rotateRight());
    }
}