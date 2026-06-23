package com.rover;


import com.rover.executor.RoverCommandExecutor;
import com.rover.executor.RoverCommandExecutorImpl;
import com.rover.model.Rover;
import com.rover.model.RoverMission;
import com.rover.model.ExplorationMission;
import com.rover.parser.InputParser;
import com.rover.parser.TextInputParserImpl;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.err.println("Invalid arguments: no input file provided !");
            return;
        }

        InputParser parser = new TextInputParserImpl();
        ExplorationMission mission = parser.parse(args[0]);
        List<Rover> allRovers = mission.roverMissions().stream().map(RoverMission::rover).toList();
        RoverCommandExecutor roverCommandExecutor = new RoverCommandExecutorImpl(mission.grid(), allRovers);

        for (RoverMission roverMission : mission.roverMissions()) {
            System.out.println(roverCommandExecutor.execute(roverMission));
        }

    }
}