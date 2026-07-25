package com.captchapro.texteditor.controller.handlers;

import com.captchapro.texteditor.model.TextContext;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class BackspaceHandler extends KeyHandler {
    public void handleKeyEvent(KeyEvent event, TextContext context) {
        int cursorPosition = context.getGapBuffer().getCursorPosition();

        if (event.getCode() == KeyCode.BACK_SPACE && cursorPosition > 0) {
            context.getGapBuffer().deleteGlyphBehind(cursorPosition);
        } else {
            super.handleKeyEvent(event, context);
        }
    }
}