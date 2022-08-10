package com.javaplusjs;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class GameOfLife {

    private int[][] state;
    private final int size;
    private final ScriptEngine engine;

    public GameOfLife(int[][] state, ScriptEngine engine) {
        this.state = state;
        this.size = state.length;
        this.engine = engine;
    }

    public void runStep() throws ScriptException, NoSuchMethodException {
        int[][] newState = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int[] neighbours = getNeighbours(i, j);
                newState[i][j] = (Integer) ((Invocable) engine).invokeFunction("step", state[i][j], neighbours);
            }
        }
        state = newState;
    }

    private int[] getNeighbours(int row, int col) {
        int[] result = new int[8];
        result[0] = state[getCorrectPosition(row - 1)][getCorrectPosition(col + 1)];
        result[1] = state[row][getCorrectPosition(col + 1)];
        result[2] = state[getCorrectPosition(row + 1)][getCorrectPosition(col + 1)];
        result[3] = state[getCorrectPosition(row - 1)][getCorrectPosition(col - 1)];
        result[4] = state[row][getCorrectPosition(col - 1)];
        result[5] = state[getCorrectPosition(row + 1)][getCorrectPosition(col - 1)];
        result[6] = state[getCorrectPosition(row - 1)][col];
        result[7] = state[getCorrectPosition(row + 1)][col];
        return result;
    }

    private int getCorrectPosition(int position) {
        int result = position % size;
        if (result < 0) {
            result += size;
        }
        return result;
    }

    public int getSize() {
        return size;
    }

    public int[][] getState() {
        return state;
    }



}
