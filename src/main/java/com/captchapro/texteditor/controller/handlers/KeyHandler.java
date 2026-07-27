package com.captchapro.texteditor.controller.handlers;

import com.captchapro.texteditor.model.GapBuffer;

import javafx.scene.input.KeyEvent;

public abstract class KeyHandler {
    protected KeyHandler nextHandler;

    public void setNextHandler(KeyHandler handler) {
        this.nextHandler = handler;
    }

    public void handleKeyEvent(KeyEvent event, GapBuffer buffer) {
        if (nextHandler != null) {
            nextHandler.handleKeyEvent(event, buffer);
        }
    }

    public KeyHandler getNextHandler() {
        return nextHandler;
    }
}