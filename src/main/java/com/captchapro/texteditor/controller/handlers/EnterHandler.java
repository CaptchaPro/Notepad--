package com.captchapro.texteditor.controller.handlers;

import com.captchapro.texteditor.model.GapBuffer;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class EnterHandler extends KeyHandler {
    public void handleKeyEvent(KeyEvent event, GapBuffer buffer) {
        if (event.getCode() == KeyCode.ENTER) {
            buffer.insertGlyph('\n');
        } else {
            super.handleKeyEvent(event, buffer);
        }
    }
}