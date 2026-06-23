package com.rover.executor;

import com.rover.model.RoverMission;

public interface RoverCommandExecutor {

    /**
     * Executes a rover's movement instructions on the given rover from the {@code RoverMission} object,
     * then returns its final position and orientation.
     *
     * @param roverMission the rover to navigate
     * @return the final state of the rover
     */
    String execute(RoverMission roverMission);
}
