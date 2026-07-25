package com.captchapro.texteditor.model;

public class GapBuffer {
    private char[] buffer;
    private int gapStart;
    private int gapEnd;

    public GapBuffer() {
        buffer = new char[32];
        gapStart = 0;
        gapEnd = buffer.length;
    }

    public int getCursorPosition() {
        return gapStart;
    }

    public int gapLength() {
        return gapEnd - gapStart;
    }

    public void growBuffer() {
        char[] newBuffer = new char[buffer.length * 2];

        // copy text before cursor
        System.arraycopy(buffer, 0, newBuffer, 0, gapStart);

        // copy text after cursor
        int rightLength = buffer.length - gapEnd;
        System.arraycopy(buffer, gapEnd, newBuffer, newBuffer.length - rightLength, rightLength);

        buffer = newBuffer;
        gapEnd = newBuffer.length - rightLength;
    }

    public void moveGapLeft(int position) {
        while (position < gapStart) {
            gapStart--;
            gapEnd--;
            buffer[gapEnd] = buffer[gapStart];
            buffer[gapStart] = '_';
        }
    }

    public void moveGapRight(int position) {
        while (position > gapStart) {
            buffer[gapStart] = buffer[gapEnd];
            buffer[gapEnd] = '_';
            gapStart++;
            gapEnd++;
        }
    }

    public void moveCursor(int position) {
        if (position < gapStart) {
            moveGapLeft(position);
        } else {
            moveGapRight(position);
        }
    }

    public void insertGlyph(char input) {
        if (gapStart == gapEnd) {
            growBuffer();
        }

        buffer[gapStart] = input;
        gapStart++;
    }

    public void deleteGlyphBehind(int positon) {
        if (gapStart == 0) {
            return;
        }

        moveCursor(positon);
        gapStart--;
        buffer[gapStart] = '_';
    }

    public char[] getBuffer() {
        return buffer;
    }

    public int getGapStart() {
        return gapStart;
    }

    public int getGapEnd() {
        return gapEnd;
    }

    //test methods
    public void setBuffer(String input) {
        for (int i = 0; i < input.length(); i++) {
            buffer[i] = input.charAt(i);
        }
    }

    public void setGapStart(int num) {
        gapStart = num;
    }

    public void setGapEnd(int num) {
        gapEnd = num;
    }
}