package com.captchapro.texteditor.controller.handlers;

import com.captchapro.texteditor.model.TextContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class RightArrowHandler extends KeyHandler {
    public void handleKeyEvent(KeyEvent event, TextContext context) {
        if (event.getCode() == KeyCode.RIGHT && context.getCursorIndex() < context.getDocument().length()) {
            context.setCursorIndex(context.getCursorIndex() + 1);
        } else {
            super.handleKeyEvent(event, context);
        }
    }
}