package com.captchapro.texteditor.controller.handlers;

import com.captchapro.texteditor.controller.commands.BackspaceCommand;
import com.captchapro.texteditor.controller.commands.Command;
import com.captchapro.texteditor.model.InputContext;
import com.captchapro.texteditor.model.TextContext;
import javafx.scene.input.KeyCode;

public class BackspaceHandler extends KeyHandler {
    public void handleKeyEvent(InputContext input, TextContext context) {
        int cursorPosition = context.getCursorPosition();

        if (input.getMainKey() == KeyCode.BACK_SPACE && cursorPosition > 0) {
            Command backspaceCommand = new BackspaceCommand(context);
            context.getUndoStack().push(backspaceCommand);
            backspaceCommand.execute();

            // int offset = context.getCurrentColumn() == 0 ? context.getPreviousLineLength(cursorPosition).getLength() : -1;
            // context.deleteGlyphBehind();
            // context.setCurrentAndGoalColumn(context.getCurrentColumn() + offset);
        } else {
            super.handleKeyEvent(input, context);
        }
    }
}