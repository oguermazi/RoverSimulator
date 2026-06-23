package com.rover;

import com.rover.model.Direction;
import com.rover.model.Rover;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoverTest {

    @Test
    void shouldFaceWest_whenFacingNorth_callTurnLeft() {
        Rover rover = new Rover(0, 0, Direction.N);
        rover.turnLeft();
        assertEquals(Direction.W, rover.getDirection());
    }

    @Test
    void shouldFaceEast_whenFacingNorth_callTurnRight() {
        Rover rover = new Rover(0, 0, Direction.N);
        rover.turnRight();
        assertEquals(Direction.E, rover.getDirection());
    }

    @Test
    void shouldUpdateCoordinates_whenMoving_callTranslate() {
        Rover rover = new Rover(2, 2, Direction.N);
        rover.translate(2, 3);
        assertEquals(2, rover.getX());
        assertEquals(3, rover.getY());
    }

    @Test
    void shouldKeepDirectionUnchanged_whenMoving_callTranslate() {
        Rover rover = new Rover(2, 2, Direction.E);
        rover.translate(5, 5);
        assertEquals(Direction.E, rover.getDirection());
    }
}