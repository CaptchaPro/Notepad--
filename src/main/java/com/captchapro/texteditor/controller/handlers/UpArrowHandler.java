package com.captchapro.texteditor.controller.handlers;

import com.captchapro.texteditor.model.GapBuffer;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class UpArrowHandler extends KeyHandler {
    public void handleKeyEvent(KeyEvent event, GapBuffer buffer) {
        if (event.getCode() == KeyCode.UP) {

        } else {
            super.handleKeyEvent(event, buffer);
        }
    }
}