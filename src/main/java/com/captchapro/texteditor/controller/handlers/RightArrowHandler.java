package com.captchapro.texteditor.controller.handlers;

import com.captchapro.texteditor.model.TextContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class RightArrowHandler extends KeyHandler {
    public void handleKeyEvent(KeyEvent event, TextContext context) {
        int cursorPosition = context.getGapBuffer().getCursorPosition();

        if (event.getCode() == KeyCode.RIGHT && cursorPosition < context.textLength()) {
            context.getGapBuffer().moveGapRight(cursorPosition + 1);
        } else {
            super.handleKeyEvent(event, context);
        }
    }
}