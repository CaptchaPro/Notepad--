package com.captchapro.texteditor.controller;

import com.captchapro.texteditor.controller.handlers.*;
import com.captchapro.texteditor.model.InputContext;
import com.captchapro.texteditor.model.TextContext;
import com.captchapro.texteditor.view.Renderer;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public class TextEditorController {
    @FXML
    public Pane textPane;

    private final TextContext context = new TextContext();
    private Renderer render;

    private KeyHandler typedChain;
    private KeyHandler controlChain;

    @FXML
    public void initialize() {
        render = new Renderer(textPane);

        render.updateCursorPosition(context);
        createHandlerChains();

        textPane.setOnKeyTyped(keyEvent -> {
            InputContext input = new InputContext(keyEvent.getCharacter());

            typedChain.handleKeyEvent(input, context);

            render.redrawDocument(context);
            render.updateCursorPosition(context);
        });

        textPane.setOnKeyPressed(keyEvent -> {
            boolean isControlDown = keyEvent.isControlDown();
            boolean isShiftDown = keyEvent.isShiftDown();
            boolean isAltDown = keyEvent.isAltDown();
            KeyCode code = keyEvent.getCode();

            if (code.isModifierKey()) {
                return;
            }

            InputContext input = new InputContext(code, isControlDown, isShiftDown, isAltDown);

            controlChain.handleKeyEvent(input, context);

            render.redrawDocument(context);
            render.updateCursorPosition(context);
        });
    }

    private void createHandlerChains() {
        KeyHandler character = new CharacterHandler();

        KeyHandler redo = new RedoHandler();
        KeyHandler undo = new UndoHandler();

        KeyHandler backspace = new BackspaceHandler();
        KeyHandler leftArrow = new LeftArrowHandler();
        KeyHandler rightArrow = new RightArrowHandler();
        KeyHandler upArrow = new UpArrowHandler();
        KeyHandler downArrow = new DownArrowHandler();
        KeyHandler enter = new EnterHandler();

        redo.setNextHandler(undo);
        undo.setNextHandler(backspace);
        backspace.setNextHandler(leftArrow);
        leftArrow.setNextHandler(rightArrow);
        rightArrow.setNextHandler(upArrow);
        upArrow.setNextHandler(downArrow);
        downArrow.setNextHandler(enter);

        typedChain = character;
        controlChain = redo;
    }
}