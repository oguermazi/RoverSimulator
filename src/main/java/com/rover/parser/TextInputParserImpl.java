package com.rover.parser;

import com.rover.model.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class TextInputParserImpl implements InputParser {

    @Override
    public ExplorationMission parse(String filePath) throws IOException {
        List<String> lines = readLines(filePath);
        Grid grid = parseGrid(lines.getFirst());
        List<RoverMission> roverMissions = parseRovers(lines);
        return new ExplorationMission(grid, roverMissions);
    }

    /**
     * Reads all lines from the file, trimming whitespace and discarding blank lines.
     *
     * @param filePath path to the input file
     * @return list of lines
     */
    private List<String> readLines(String filePath) throws IOException {
        return Files.readAllLines(Path.of(filePath))
                .stream()
                .map(String::trim)
                .filter(line -> !line.isEmpty())
                .toList();
    }

    /**
     * Parses the first line of the input file into a {@link Grid}.
     * The input coordinates represent the upper-right corner of the grid,
     * so 1 is added to each dimension to convert to width and height.
     *
     * @param line a string
     * @return the corresponding {@link Grid}
     */
    private Grid parseGrid(String line) {
        String[] parts = line.split(" ");
        return new Grid(
                Integer.parseInt(parts[0]) + 1,
                Integer.parseInt(parts[1]) + 1
        );
    }

    /**
     * Parses all lines after the first into a list of {@link RoverMission}.
     * Lines are consumed in pairs starting at index 1, where the first line of each pair
     * represents the {@link Rover}'s initial position and the second line represents the list of instructions.
     *
     * @param lines the list of input lines
     * @return a list of {@link RoverMission}
     */
    private List<RoverMission> parseRovers(List<String> lines) {
        List<RoverMission> result = new ArrayList<>();
        for (int i = 1; i < lines.size() - 1; i += 2) {

            String[] parts = lines.get(i).split(" ");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            Direction dir = Direction.valueOf(parts[2]);
            Rover rover = new Rover(x, y, dir);

            String instructions = lines.get(i + 1);

            result.add(new RoverMission(rover, instructions));
        }
        return result;
    }
}
