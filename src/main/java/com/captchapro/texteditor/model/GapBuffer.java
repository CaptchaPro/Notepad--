package com.captchapro.texteditor.model;

public class GapBuffer {
    public static char[] buffer = new char[50];
    private static int gapSize = 10;
    private static int gapLeft = 0;
    private static int gapRight = gapSize - gapLeft - 1;
    private static int size = 10;

    public static void growBuffer(int gap, int position) {
        char[] chars = new char[size];

        if (size - position >= 0) {
            System.arraycopy(buffer, position, chars, 0, size - position);
        }

        for (int i = 0; i < gap; i++) {
            buffer[i + position] = ' ';
        }

        for (int i = 0; i < gap; i++) {
            buffer[position + gap + i] = chars[i];
        }

        size += gap;
        gapRight += gap;
    }

    public static void moveGapLeft(int position) {
        while(position < gapLeft) {
            gapLeft--;
            gapRight--;
            buffer[gapRight + 1] = buffer[gapLeft];
            buffer[gapLeft] = ' ';
        }
    }

    public static void moveGapRight(int position) {
        while(position > gapLeft) {
            gapLeft++;
            gapRight++;
            buffer[gapLeft - 1] = buffer[gapRight];
            buffer[gapRight] = ' ';
        }
    }

    public static void moveCursor(int position) {
        if (position < gapLeft) {
            moveGapLeft(position);
        } else {
            moveGapRight(position);
        }
    }

    private static void insertString(String input, int position) {
        int length = input.length();
        int i = 0;

        if (position != gapLeft) {
            moveCursor(position);
        }

        while (i < length) {
            if (gapRight == gapLeft) {
                int gap = 10;
                growBuffer(gap, position);
            }

            buffer[gapLeft] = input.charAt(i);
            gapLeft++;
            i++;
            position++;
        }
    }
}