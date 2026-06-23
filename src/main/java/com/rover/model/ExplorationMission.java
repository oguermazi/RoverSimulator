package com.rover.model;

import java.util.List;

public record ExplorationMission(Grid grid, List<RoverMission> roverMissions) {}