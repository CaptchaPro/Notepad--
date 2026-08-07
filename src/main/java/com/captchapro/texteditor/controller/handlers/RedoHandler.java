package com.captchapro.texteditor.controller.handlers;

import com.captchapro.texteditor.controller.commands.Command;
import com.captchapro.texteditor.model.InputContext;
import com.captchapro.texteditor.model.TextContext;
import javafx.scene.input.KeyCode;

public class RedoHandler extends KeyHandler {
    public void handleKeyEvent(InputContext input, TextContext context) {
        if (input.isControlDown() && input.isShiftDown() && input.getMainKey() == KeyCode.Z) {
            if (!context.getRedoStack().empty()) {
                Command lastUndo = context.getRedoStack().pop();
                context.getUndoStack().push(lastUndo);
                lastUndo.execute();
            }
        } else {
            super.handleKeyEvent(input, context);
        }
    }
}