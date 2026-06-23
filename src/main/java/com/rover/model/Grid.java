package com.rover.model;

public record Grid(int width, int height) {

    /**
     * Checks whether the given coordinates are within the navigable area of the grid.
     *
     * @param x the x coordinate to validate
     * @param y the y coordinate to validate
     * @return {@code true} if the position is within bounds, {@code false} otherwise
     */
    public boolean isWithinBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }
}