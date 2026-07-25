package com.captchapro.texteditor.model;

public class TextContext {
    private final GapBuffer buffer = new GapBuffer();

    public GapBuffer getGapBuffer() {
        return buffer;
    }

    public char[] getBufferArray() {
        return buffer.getBuffer();
    }

    public int textLength() {
        return buffer.getBuffer().length - (buffer.getGapEnd() - buffer.getGapStart());
    }
}