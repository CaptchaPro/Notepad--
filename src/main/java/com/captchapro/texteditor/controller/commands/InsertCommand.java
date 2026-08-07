package com.captchapro.texteditor.controller.commands;

import com.captchapro.texteditor.model.TextContext;

public class InsertCommand implements Command {
    char character;
    TextContext context;

    public InsertCommand(char glyph, TextContext context) {
        this.character = glyph;
        this.context = context;
    }

    @Override
    public void execute() {
        context.insertGlyph(character);
        context.setCurrentAndGoalColumn(context.getCurrentColumn() + 1);
    }

    @Override
    public void undo() {
        int cursorPosition = context.getCursorPosition();
        int offset = context.getCurrentColumn() == 0 ? context.getPreviousLineLength(cursorPosition).getLength() : -1;

        context.deleteGlyphBehind();
        context.setCurrentAndGoalColumn(context.getCurrentColumn() + offset);
    }
}