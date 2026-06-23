package com.rover.parser;

import com.rover.model.ExplorationMission;

import java.io.IOException;

public interface InputParser {

    /**
     * Parses the given input file into an {@link ExplorationMission}.
     *
     * @param filePath path to the input file
     * @return {@link ExplorationMission}
     */
    ExplorationMission parse(String filePath) throws IOException;
}
