package com.captchapro.texteditor.controller.handlers;

import com.captchapro.texteditor.model.InputContext;
import com.captchapro.texteditor.model.TextContext;
import javafx.scene.input.KeyCode;

public class LeftArrowHandler extends KeyHandler {
    public void handleKeyEvent(InputContext input, TextContext context) {
        int cursorPosition = context.getCursorPosition();

        if (input.getMainKey() == KeyCode.LEFT && cursorPosition > 0) {
            int offset = context.getCurrentColumn() == 0 ? context.getPreviousLineLength(cursorPosition).getLength() : -1;

            context.moveGapLeft(cursorPosition - 1);
            context.setCurrentAndGoalColumn(context.getCurrentColumn() + offset);
            //System.out.println("Current: " + context.getCurrentColumn() + " Goal: " + context.getGoalColumn());
        } else {
            super.handleKeyEvent(input, context);
        }
    }
}