package com.captchapro.texteditor.controller.commands;

import com.captchapro.texteditor.model.TextContext;

public class BackspaceCommand implements Command {
    TextContext context;
    char deletedChar;

    public BackspaceCommand(TextContext context) {
        this.context = context;
    }

    @Override
    public void execute() {
        int cursorPosition = context.getCursorPosition();
        int offset = context.getCurrentColumn() == 0 ? context.getPreviousLineLength(cursorPosition).getLength() : -1;

        deletedChar = context.getBuffer()[cursorPosition - 1];
        context.deleteGlyphBehind();
        context.setCurrentAndGoalColumn(context.getCurrentColumn() + offset);
    }

    @Override
    public void undo() {
        context.insertGlyph(deletedChar);
        context.setCurrentAndGoalColumn(context.getCurrentColumn() + 1);
    }
}