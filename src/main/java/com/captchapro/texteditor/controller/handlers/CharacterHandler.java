package com.captchapro.texteditor.controller.handlers;

import com.captchapro.texteditor.controller.commands.Command;
import com.captchapro.texteditor.controller.commands.InsertCommand;
import com.captchapro.texteditor.model.InputContext;
import com.captchapro.texteditor.model.TextContext;

public class CharacterHandler extends KeyHandler {
    public void handleKeyEvent(InputContext input, TextContext context) {
        char character = input.getTypedKey().charAt(0);

        if (character > 31) {
            Command insertCommand = new InsertCommand(character, context);
            context.getUndoStack().push(insertCommand);
            insertCommand.execute();

            //context.insertGlyph(character);
            //context.setCurrentAndGoalColumn(context.getCurrentColumn() + 1);
        } else {
            super.handleKeyEvent(input, context);
        }
    }
}