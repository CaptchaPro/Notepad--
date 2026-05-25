package com.captchapro.texteditor.controller;

import com.captchapro.texteditor.controller.handlers.*;
import com.captchapro.texteditor.model.TextContext;
import com.captchapro.texteditor.view.Renderer;
import javafx.fxml.FXML;
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
            typedChain.handleKeyEvent(keyEvent, context);

            render.redrawDocument(context);
            render.updateCursorPosition(context);
        });

        textPane.setOnKeyPressed(keyEvent -> {
            controlChain.handleKeyEvent(keyEvent, context);

            render.redrawDocument(context);
            render.updateCursorPosition(context);
        });
    }

    private void createHandlerChains() {
        KeyHandler character = new CharacterHandler();

        KeyHandler backspace = new BackspaceHandler();
        KeyHandler leftArrow = new LeftArrowHandler();
        KeyHandler rightArrow = new RightArrowHandler();
        KeyHandler enter = new EnterHandler();

        backspace.setNextHandler(leftArrow);
        leftArrow.setNextHandler(rightArrow);
        rightArrow.setNextHandler(enter);

        typedChain = character;
        controlChain = backspace;
    }
}