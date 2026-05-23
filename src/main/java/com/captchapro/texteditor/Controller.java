package com.captchapro.texteditor;

import com.captchapro.texteditor.Handlers.*;
import com.captchapro.texteditor.model.GlyphFactory;
import com.captchapro.texteditor.model.TextContext;
import com.captchapro.texteditor.model.TextGlyph;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Controller {
    @FXML
    public Pane textPane;

    // temp settings
    private String fontName = "Arial";
    private int size = 12;
    private Paint color = Color.BLACK;

    private final GlyphFactory glyphFactory = GlyphFactory.getInstance();
    private final TextContext context = new TextContext();

    private KeyHandler typedChain;
    private KeyHandler controlChain;

    Canvas canvas = new Canvas(640, 455);
    Line cursor;
    GraphicsContext gc = canvas.getGraphicsContext2D();

    @FXML
    public void initialize() {
        double cursorHeight = getFontHeight(fontName, size);
        cursor = new Line(0, 0, 0, -cursorHeight);

        textPane.getChildren().add(canvas);
        textPane.getChildren().add(cursor);
        textPane.setFocusTraversable(true);

        updateCursorPosition();
        createHandlerChains();

        textPane.setOnKeyTyped(keyEvent -> {
            typedChain.handleKeyEvent(keyEvent, context);

            // want to try moving this out of controller class
            redrawDocument();
            updateCursorPosition();
        });

        textPane.setOnKeyPressed(keyEvent -> {
            controlChain.handleKeyEvent(keyEvent, context);

            redrawDocument();
            updateCursorPosition();
        });
    }

    private void redrawDocument() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        double currentX = 0.0;
        double currentY = 20.0;
        double lineHeight = getFontHeight(fontName, size);

        TextGlyph glyph = glyphFactory.getTextGlyph(fontName, size, color);

        for (int i = 0; i < context.getDocument().length(); i++) {
            char c = context.getDocument().charAt(i);

            if (c == '\n') {
                currentX = 0.0;
                currentY += lineHeight;
                continue;
            }

            String character = String.valueOf(c);
            glyph.draw(gc, character, currentX, currentY);

            currentX += getFontWidth(fontName, size, character);
        }

        updateCursorPosition();
    }

    private void updateCursorPosition() {
        double cursorX = 0.0;
        double cursorY = 20.0;
        double lineHeight = getFontHeight(fontName, size);

        for (int i = 0; i < context.getCursorIndex(); i++) {
            char c = context.getDocument().charAt(i);

            if (c == '\n') {
                cursorX = 0.0;
                cursorY += lineHeight;
            } else {
                cursorX += getFontWidth(fontName, size, String.valueOf(c));
            }
        }

        cursor.setTranslateX(cursorX);
        cursor.setTranslateY(cursorY);
    }

    private double getFontWidth(String fontName, int fontSize, String character) {
        Text text = new Text(character);
        text.setFont(Font.font(fontName, fontSize));

        return text.getLayoutBounds().getWidth();
    }

    private double getFontHeight(String fontName, int fontSize) {
        Text text = new Text();
        text.setFont(Font.font(fontName, fontSize));

        return text.getLayoutBounds().getHeight();
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