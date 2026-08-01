package com.captchapro.texteditor.controller.handlers;

import com.captchapro.texteditor.model.LineData;
import com.captchapro.texteditor.model.TextContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class DownArrowHandler extends KeyHandler {
    public void handleKeyEvent(KeyEvent event, TextContext context) {
        if (event.getCode() == KeyCode.DOWN) {
            LineData line = context.getNextLineLength(context.getCursorPosition());
            int currentColumn = context.getCurrentColumn();

            if (line.getStart() == 0) {
                return;
            }

            int newCursorPosition = line.getStart();

            if (line.getLength() > context.getGoalColumn()) {
                newCursorPosition += context.getGoalColumn();
                currentColumn = context.getGoalColumn();
            } else {
                newCursorPosition = line.getEnd();
                currentColumn = line.getLength();
            }

            context.moveCursor(newCursorPosition);
            context.setCurrentColumn(currentColumn);
            System.out.println("Current: " + context.getCurrentColumn() + " Goal: " + context.getGoalColumn());
        } else {
            super.handleKeyEvent(event, context);
        }
    }
}