package com.captchapro.texteditor.controller.handlers;

import com.captchapro.texteditor.controller.commands.Command;
import com.captchapro.texteditor.model.InputContext;
import com.captchapro.texteditor.model.TextContext;
import javafx.scene.input.KeyCode;

public class UndoHandler extends KeyHandler {
    public void handleKeyEvent(InputContext input, TextContext context) {
        if (input.isControlDown() && input.getMainKey() == KeyCode.Z) {
            if (!context.getUndoStack().empty()) {
                Command lastCommand = context.getUndoStack().pop();
                context.getRedoStack().push(lastCommand);
                lastCommand.undo();
            }
        } else {
            super.handleKeyEvent(input, context);
        }
    }
}