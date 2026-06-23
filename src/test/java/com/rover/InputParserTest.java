package com.rover;

import com.rover.model.ExplorationMission;
import com.rover.parser.TextInputParserImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class InputParserTest {

    private final TextInputParserImpl parser = new TextInputParserImpl();

    @TempDir
    Path tempDir;

    private Path createInputFile(String content) throws IOException {
        Path file = tempDir.resolve("input.txt");
        Files.writeString(file, content);
        return file;
    }

    @Test
    void shouldReturnCorrectGridDimensions_whenInputIsValid_callParse() throws IOException {
        Path file = createInputFile("6 6\n2 1 E\nMMLMMRMM\n");
        ExplorationMission mission = parser.parse(file.toString());
        assertEquals(7, mission.grid().width());
        assertEquals(7, mission.grid().height());
    }

    @Test
    void shouldReturnCorrectRoverMissionCount_whenInputHasMultipleRovers_callParse() throws IOException {
        Path file = createInputFile("6 6\n2 1 E\nMMLMMRMM\n0 3 N\nMMRMMMLMM\n");
        ExplorationMission mission = parser.parse(file.toString());
        assertEquals(2, mission.roverMissions().size());
    }

    @Test
    void shouldReturnCorrectInitialPosition_whenInputIsValid_callParse() throws IOException {
        Path file = createInputFile("6 6\n2 1 E\nMMLMMRMM\n");
        ExplorationMission mission = parser.parse(file.toString());
        assertEquals("2 1 E", mission.roverMissions().getFirst().rover().toString());
    }

    @Test
    void shouldReturnCorrectInstructions_whenInputIsValid_callParse() throws IOException {
        Path file = createInputFile("6 6\n2 1 E\nMMLMMRMM\n");
        ExplorationMission mission = parser.parse(file.toString());
        assertEquals("MMLMMRMM", mission.roverMissions().getFirst().instructions());
    }

    @Test
    void shouldIgnoreBlankLines_whenInputContainsEmptyLines_callParse() throws IOException {
        Path file = createInputFile("6 6\n\n2 1 E\n\nMMLMMRMM\n");
        ExplorationMission mission = parser.parse(file.toString());
        assertEquals(1, mission.roverMissions().size());
    }

    @Test
    void shouldThrowIOException_whenFileDoesNotExist_callParse() {
        assertThrows(IOException.class, () -> parser.parse("nonexistent.txt"));
    }
}