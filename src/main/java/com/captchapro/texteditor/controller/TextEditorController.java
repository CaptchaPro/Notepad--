package com.captchapro.texteditor.controller;

import com.captchapro.texteditor.controller.handlers.*;
import com.captchapro.texteditor.model.GapBuffer;
import com.captchapro.texteditor.view.Renderer;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class TextEditorController {
    @FXML
    public Pane textPane;

    private final GapBuffer buffer = new GapBuffer();
    private Renderer render;

    private KeyHandler typedChain;
    private KeyHandler controlChain;

    @FXML
    public void initialize() {
        render = new Renderer(textPane);

        render.updateCursorPosition(buffer);
        createHandlerChains();

        textPane.setOnKeyTyped(keyEvent -> {
            typedChain.handleKeyEvent(keyEvent, buffer);

            render.redrawDocument(buffer);
            render.updateCursorPosition(buffer);
        });

        textPane.setOnKeyPressed(keyEvent -> {
            controlChain.handleKeyEvent(keyEvent, buffer);

            render.redrawDocument(buffer);
            render.updateCursorPosition(buffer);
        });
    }

    private void createHandlerChains() {
        KeyHandler character = new CharacterHandler();

        KeyHandler backspace = new BackspaceHandler();
        KeyHandler leftArrow = new LeftArrowHandler();
        KeyHandler rightArrow = new RightArrowHandler();
        KeyHandler upArrow = new UpArrowHandler();
        KeyHandler downArrow = new DownArrowHandler();
        KeyHandler enter = new EnterHandler();

        backspace.setNextHandler(leftArrow);
        leftArrow.setNextHandler(rightArrow);
        rightArrow.setNextHandler(upArrow);
        upArrow.setNextHandler(downArrow);
        downArrow.setNextHandler(enter);

        typedChain = character;
        controlChain = backspace;
    }
}