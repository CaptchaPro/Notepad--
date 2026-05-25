package com.captchapro.texteditor.controller.handlers;

import com.captchapro.texteditor.model.TextContext;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class BackspaceHandler extends KeyHandler {
    public void handleKeyEvent(KeyEvent event, TextContext context) {
        int cursorIndex = context.getCursorIndex();

        if (event.getCode() == KeyCode.BACK_SPACE && cursorIndex > 0) {
            context.getDocument().deleteCharAt(cursorIndex - 1);
            context.setCursorIndex(cursorIndex - 1);
        } else {
            super.handleKeyEvent(event, context);
        }
    }
}