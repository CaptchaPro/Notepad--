package com.captchapro.texteditor.controller.commands;

public interface Command {
    void execute();
    void undo();
}