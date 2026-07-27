package com.captchapro.texteditor.controller.handlers;

import com.captchapro.texteditor.model.GapBuffer;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class LeftArrowHandler extends KeyHandler {
    public void handleKeyEvent(KeyEvent event, GapBuffer buffer) {
        int cursorPosition = buffer.getCursorPosition();

        if (event.getCode() == KeyCode.LEFT && cursorPosition > 0) {
            buffer.moveGapLeft(cursorPosition - 1);
        } else {
            super.handleKeyEvent(event, buffer);
        }
    }
}