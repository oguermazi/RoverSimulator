package com.rover.model;

/**
 * Represents the four cardinal directions.
 */
public enum Direction {
    N, E, S, W;
    public Direction rotateLeft() {
        return switch (this) {
            case N -> W;
            case E -> N;
            case S -> E;
            case W -> S;
        };
    }

    public Direction rotateRight() {
        return switch (this) {
            case N -> E;
            case E -> S;
            case S -> W;
            case W -> N;
        };
    }
}