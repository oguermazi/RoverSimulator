package com.rover.executor;

import com.rover.model.Rover;
import com.rover.model.RoverMission;
import com.rover.model.Grid;

import java.util.List;

public class RoverCommandExecutorImpl implements RoverCommandExecutor {

    private final Grid grid;
    private final List<Rover> rovers;

    public RoverCommandExecutorImpl(Grid grid, List<Rover> rovers) {
        this.grid = grid;
        this.rovers = rovers;
    }

    @Override
    public String execute(RoverMission roverMission) {
        Rover rover = roverMission.rover();
        for (char command : roverMission.instructions().toCharArray()) {
            switch (command) {
                case 'L' -> rover.turnLeft();
                case 'R' -> rover.turnRight();
                case 'M' -> moveIfPossible(rover);
                default  -> throw new IllegalArgumentException("Unknown command : " + command);
            }
        }
        return rover.toString();
    }

    /**
     *
     * Moves the rover one step forward in its current direction, if the resulting
     * position is within the bounds of the given {@link Grid} and is not occupied by another {@code rovers}.
     *
     * @param rover The rover to move.
     */
    private void moveIfPossible(Rover rover) {
        int newX = rover.getX();
        int newY = rover.getY();

        switch (rover.getDirection()) {
            case N -> newY++;
            case E -> newX++;
            case S -> newY--;
            case W -> newX--;
        }

        if (!grid.isWithinBounds(newX, newY)) {
            return;
        }

        for (Rover otherRover : rovers) {
            if (otherRover == rover) {
                continue;
            }
            if (otherRover.getX() == newX && otherRover.getY() == newY) {
                return;
            }
        }

        rover.translate(newX, newY);
    }

}
