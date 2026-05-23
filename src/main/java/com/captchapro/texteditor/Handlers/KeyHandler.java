package com.captchapro.texteditor.Handlers;

import com.captchapro.texteditor.model.TextContext;

import javafx.scene.input.KeyEvent;

public abstract class KeyHandler {
    protected KeyHandler nextHandler;

    public void setNextHandler(KeyHandler handler) {
        this.nextHandler = handler;
    }

    public void handleKeyEvent(KeyEvent event, TextContext context) {
        if (nextHandler != null) {
            nextHandler.handleKeyEvent(event, context);
        } else {
            System.out.println("Unknown Key");
        }
    }

    public KeyHandler getNextHandler() {
        return nextHandler;
    }
}