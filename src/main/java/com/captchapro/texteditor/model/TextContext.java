package com.captchapro.texteditor.model;

public class TextContext {
    private final StringBuilder document = new StringBuilder();
    private final GapBuffer buffer = new GapBuffer();
    private int cursorIndex = 0;

    public GapBuffer getGapBuffer() {
        return buffer;
    }

    public void setCursorIndex(int cursorIndex) {
        this.cursorIndex = cursorIndex;
    }

    // return char array instead for gap buffer
    public StringBuilder getDocument() {
        return document;
    }

    // return index of character in array that the cursor is behind
    public int getCursorIndex() {
        return cursorIndex;
    }
}