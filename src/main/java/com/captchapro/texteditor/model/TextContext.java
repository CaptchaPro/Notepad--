package com.captchapro.texteditor.model;

public class TextContext extends GapBuffer {
    private int goalColumn;
    private int currentColumn;

    public void setGoalColumn(int column) {
        this.goalColumn = column;
    }

    public void setCurrentColumn(int column) {
        this.currentColumn = column;
    }

    public void setCurrentAndGoalColumn(int column) {
        this.currentColumn = this.goalColumn = column;
    }

    public int getGoalColumn() {
        return goalColumn;
    }

    public int getCurrentColumn() {
        return currentColumn;
    }
}