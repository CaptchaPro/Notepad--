package com.captchapro.texteditor.controller.handlers;

import com.captchapro.texteditor.model.GapBuffer;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class BackspaceHandler extends KeyHandler {
    public void handleKeyEvent(KeyEvent event, GapBuffer buffer) {
        int cursorPosition = buffer.getCursorPosition();

        if (event.getCode() == KeyCode.BACK_SPACE && cursorPosition > 0) {
            buffer.deleteGlyphBehind(cursorPosition);
        } else {
            super.handleKeyEvent(event, buffer);
        }
    }
}