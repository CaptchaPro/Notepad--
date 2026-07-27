package com.captchapro.texteditor.controller.handlers;

import com.captchapro.texteditor.model.GapBuffer;
import javafx.scene.input.KeyEvent;

public class CharacterHandler extends KeyHandler {
    public void handleKeyEvent(KeyEvent event, GapBuffer buffer) {
        String character = event.getCharacter();

        if (!character.isEmpty() && character.charAt(0) > 31) {
            buffer.insertGlyph(character.charAt(0));
        } else {
            super.handleKeyEvent(event, buffer);
        }
    }
}