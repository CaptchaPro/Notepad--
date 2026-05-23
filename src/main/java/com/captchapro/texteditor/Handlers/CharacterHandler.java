package com.captchapro.texteditor.Handlers;

import com.captchapro.texteditor.model.TextContext;

import javafx.scene.input.KeyEvent;

public class CharacterHandler extends KeyHandler {
    public void handleKeyEvent(KeyEvent event, TextContext context) {
        String character = event.getCharacter();

        if (character.isEmpty() || character.charAt(0) < 32) {
            super.handleKeyEvent(event, context);
        } else {
            context.getDocument().insert(context.getCursorIndex(), character);
            context.setCursorIndex(context.getCursorIndex() + 1);
        }
    }
}