package com.captchapro.texteditor.controller.handlers;

import com.captchapro.texteditor.model.TextContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class LeftArrowHandler extends KeyHandler {
    public void handleKeyEvent(KeyEvent event, TextContext context) {
        int cursorPosition = context.getCursorPosition();

        if (event.getCode() == KeyCode.LEFT && cursorPosition > 0) {
            int offset = context.getCurrentColumn() == 0 ? context.getPreviousLineLength(cursorPosition).getLength() : -1;
            context.moveGapLeft(cursorPosition - 1);
            context.setCurrentAndGoalColumn(context.getCurrentColumn() + offset);
            System.out.println("Current: " + context.getCurrentColumn() + " Goal: " + context.getGoalColumn());
        } else {
            super.handleKeyEvent(event, context);
        }
    }
}