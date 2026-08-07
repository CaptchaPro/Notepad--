package com.captchapro.texteditor.controller.handlers;

import com.captchapro.texteditor.model.InputContext;
import com.captchapro.texteditor.model.TextContext;

public abstract class KeyHandler {
    protected KeyHandler nextHandler;
    protected final int commandHistoryLimit = 32;

    public void setNextHandler(KeyHandler handler) {
        this.nextHandler = handler;
    }

    public void handleKeyEvent(InputContext input, TextContext context) {
        if (nextHandler != null) {
            nextHandler.handleKeyEvent(input, context);
        }
    }

    public KeyHandler getNextHandler() {
        return nextHandler;
    }
}