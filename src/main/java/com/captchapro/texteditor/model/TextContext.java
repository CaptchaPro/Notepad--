package com.captchapro.texteditor.model;

public class TextContext {
    private final StringBuilder document = new StringBuilder();
    private int cursorIndex = 0;

    public void setCursorIndex(int cursorIndex) {
        this.cursorIndex = cursorIndex;
    }

    public StringBuilder getDocument() {
        return document;
    }

    public int getCursorIndex() {
        return cursorIndex;
    }
}