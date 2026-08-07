package com.captchapro.texteditor.controller.commands;

import com.captchapro.texteditor.model.TextContext;

public class EnterCommand implements Command {
    TextContext context;

    public EnterCommand(TextContext context) {
        this.context = context;
    }

    @Override
    public void execute() {
        context.insertGlyph('\n');
        context.setCurrentAndGoalColumn(0);
    }

    @Override
    public void undo() {
        int cursorPosition = context.getCursorPosition();
        int offset = context.getCurrentColumn() == 0 ? context.getPreviousLineLength(cursorPosition).getLength() : -1;

        context.deleteGlyphBehind();
        context.setCurrentAndGoalColumn(context.getCurrentColumn() + offset);
    }
}