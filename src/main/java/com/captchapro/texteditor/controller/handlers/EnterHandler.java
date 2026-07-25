package com.captchapro.texteditor.controller.handlers;

import com.captchapro.texteditor.model.TextContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class EnterHandler extends KeyHandler {
    public void handleKeyEvent(KeyEvent event, TextContext context) {
        if (event.getCode() == KeyCode.ENTER) {
            context.getGapBuffer().insertGlyph('\n');
        } else {
            super.handleKeyEvent(event, context);
        }
    }
}