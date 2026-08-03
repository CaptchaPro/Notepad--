package com.captchapro.texteditor.model;

public class TextContext extends GapBuffer {
    private int goalColumn;
    private int currentColumn;

    public void setCurrentColumn(int column) {
        this.currentColumn = column;
    }

    public void setCurrentAndGoalColumn(int column) {
        this.currentColumn = this.goalColumn = column;
    }

    public LineData getPreviousLineLength(int position) {
        int nCount = 0;
        LineData line =  new LineData();

        if (charAt(position) == '\n') {
            position--;
        }

        while (position > 0 && nCount < 2) {
            if (charAt(position) == '\n') {
                if (nCount == 0) {
                    line.end = position;
                } else {
                    line.start = position + 1;
                }

                nCount++;
            }

            position--;
        }

        line.length = line.end - line.start;

        return line;
    }

    public LineData getNextLineLength(int position) {
        int nCount = 0;
        LineData line = new LineData();

        while (position < getTextLength() && nCount < 2) {
            if (charAt(position) == '\n') {
                if (nCount == 0) {
                    line.start = position + 1;
                } else {
                    line.end = position;
                }

                nCount++;
            }

            position++;
        }

        if (nCount == 1) {
            line.end = getTextLength();
        }

        line.length = line.end - line.start;

        return line;
    }

    public char charAt(int logicalIndex) {
        return getBuffer()[getPhysicalIndex(logicalIndex)];
    }

    private int getPhysicalIndex(int logicalIndex) {
        if (logicalIndex >= this.getTextLength()) {
            return this.getTextLength();
        }

        if (logicalIndex >= this.getGapStart()) {
            logicalIndex += this.getGapLength();
        }

        return logicalIndex;
    }

    public int getGoalColumn() {
        return goalColumn;
    }

    public int getCurrentColumn() {
        return currentColumn;
    }
}