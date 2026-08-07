package com.captchapro.texteditor.controller.handlers;

import com.captchapro.texteditor.model.InputContext;
import com.captchapro.texteditor.model.TextContext;
import javafx.scene.input.KeyCode;

public class RightArrowHandler extends KeyHandler {
    public void handleKeyEvent(InputContext input, TextContext context) {
        int cursorPosition = context.getCursorPosition();

        if (input.getMainKey() == KeyCode.RIGHT && cursorPosition < context.getTextLength()) {
            int offset = context.getBuffer()[cursorPosition] == '\n' ? -context.getCurrentColumn() : 1;

            context.moveGapRight(cursorPosition + 1);
            context.setCurrentAndGoalColumn(context.getCurrentColumn() + offset);
            //System.out.println("Current: " + context.getCurrentColumn() + " Goal: " + context.getGoalColumn());
        } else {
            super.handleKeyEvent(input, context);
        }
    }
}