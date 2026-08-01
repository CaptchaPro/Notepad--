package com.captchapro.texteditor.controller.handlers;

import com.captchapro.texteditor.model.TextContext;
import javafx.scene.input.KeyEvent;

public class CharacterHandler extends KeyHandler {
    public void handleKeyEvent(KeyEvent event, TextContext context) {
        char character = event.getCharacter().charAt(0);

        if (character > 31) {
            context.insertGlyph(character);
            context.setCurrentAndGoalColumn(context.getCurrentColumn() + 1);
        } else {
            super.handleKeyEvent(event, context);
        }
    }
}