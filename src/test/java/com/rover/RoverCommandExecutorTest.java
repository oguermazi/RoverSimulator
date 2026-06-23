package com.rover;

import com.rover.executor.RoverCommandExecutorImpl;
import com.rover.model.Direction;
import com.rover.model.Rover;
import com.rover.model.RoverMission;
import com.rover.model.Grid;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoverCommandExecutorTest {

    private final List<Rover> otherRovers = new ArrayList<>();
    private final RoverCommandExecutorImpl executor = new RoverCommandExecutorImpl(new Grid(7, 7), otherRovers);

    @Test
    void shouldNavigateCorrectly_whenGivenValidInstructions_callExecute() {
        Rover rover = new Rover(2, 1, Direction.E);
        RoverMission roverMission = new RoverMission(rover, "MMLMMRMM");
        String result = executor.execute(roverMission);
        assertEquals("6 3 E", result);
    }

    @Test
    void shouldNotExceedGridBoundary_whenInstructionsGoOutOfBounds_callExecute() {
        Rover rover = new Rover(0, 3, Direction.N);
        RoverMission roverMission = new RoverMission(rover, "MMRMMLMMMMM");
        String result = executor.execute(roverMission);
        assertEquals("2 6 N", result);
    }

    @Test
    void shouldNotMove_whenInstructionsContainOnlyLeftRotations_callExecute() {
        Rover rover = new Rover(3, 3, Direction.N);
        RoverMission roverMission = new RoverMission(rover, "LLLL");
        String result = executor.execute(roverMission);
        assertEquals("3 3 N", result);
    }

    @Test
    void shouldNotMove_whenInstructionsContainOnlyRightRotations_callExecute() {
        Rover rover = new Rover(3, 3, Direction.N);
        RoverMission roverMission = new RoverMission(rover, "RRRR");
        String result = executor.execute(roverMission);
        assertEquals("3 3 N", result);
    }

    @Test
    void shouldThrowException_whenInstructionsContainInvalidCharacter_callExecute() {
        Rover rover = new Rover(1, 1, Direction.N);
        RoverMission roverMission = new RoverMission(rover, "MXM");
        assertThrows(IllegalArgumentException.class, () -> executor.execute(roverMission));
    }

    @Test
    void shouldNotMove_whenAnotherRoverOccupiesTargetPosition_callExecute() {
        Rover rover = new Rover(2, 2, Direction.N);
        Rover blocker = new Rover(2, 3, Direction.S);
        otherRovers.clear();
        otherRovers.addAll(List.of(rover, blocker));

        RoverMission roverMission = new RoverMission(rover, "M");
        String result = executor.execute(roverMission);
        assertEquals("2 2 N", result);
    }

    @Test
    void shouldMove_whenOtherRoverOccupiesADifferentPosition_callExecute() {
        Rover rover = new Rover(2, 2, Direction.N);
        Rover otherRover = new Rover(5, 5, Direction.S);
        otherRovers.clear();
        otherRovers.addAll(List.of(rover, otherRover));

        RoverMission roverMission = new RoverMission(rover, "M");
        String result = executor.execute(roverMission);
        assertEquals("2 3 N", result);
    }
}