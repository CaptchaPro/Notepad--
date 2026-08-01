package com.captchapro.texteditor.controller.handlers;

import com.captchapro.texteditor.model.TextContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class BackspaceHandler extends KeyHandler {
    public void handleKeyEvent(KeyEvent event, TextContext context) {
        int cursorPosition = context.getCursorPosition();

        if (event.getCode() == KeyCode.BACK_SPACE && cursorPosition > 0) {
            int offset = context.getCurrentColumn() == 0 ? context.getPreviousLineLength(cursorPosition).getLength() : -1;
            context.deleteGlyphBehind();
            context.setCurrentAndGoalColumn(context.getCurrentColumn() + offset);
        } else {
            super.handleKeyEvent(event, context);
        }
    }
}