package com.captchapro.texteditor;

import com.captchapro.texteditor.Handlers.CharacterHandler;
import com.captchapro.texteditor.Handlers.KeyHandler;
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

import static com.captchapro.texteditor.model.GapBuffer.buffer;

public class Controller {
    @FXML
    public Pane textPane;

    // to be placed in TextContext
    // temp settings
    private String fontName = "Arial";
    private int size = 12;
    private Paint color = Color.BLACK;
    private double xPos = 0.0;
    private double yPos = 20.0;

    private final GlyphFactory glyphFactory = GlyphFactory.getInstance();
    private final TextContext context = new TextContext();
    private KeyHandler keyChain;

    // to be replaced
    //private final StringBuilder document = new StringBuilder();
    //private int cursorIndex  = 0;

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

        textPane.setOnKeyTyped(keyEvent -> {
            keyChain = new CharacterHandler();
            keyChain.handleKeyEvent(keyEvent, context);

            // want to try moving this out of controller class
            redrawDocument();
            updateCursorPosition();
        });

        // textPane.setOnKeyPressed(keyEvent -> {
        //     //System.out.println("Pressed: " + keyEvent.getCode());
        //     switch (keyEvent.getCode()) {
        //         case LEFT -> {
        //             if (cursorIndex > 0) {
        //                 cursorIndex--;
        //                 updateCursorPosition();
        //             }
        //         }
        //
        //         case RIGHT -> {
        //             if (cursorIndex < document.length()) {
        //                 cursorIndex++;
        //                 updateCursorPosition();
        //             }
        //         }
        //
        //         case BACK_SPACE -> {
        //             if (cursorIndex > 0) {
        //                 document.deleteCharAt(cursorIndex - 1);
        //                 cursorIndex--;
        //
        //                 redrawDocument();
        //             }
        //         }
        //     }
        // });
    }

    private void redrawDocument() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        double currentX = 0;
        double currentY = yPos;

        TextGlyph glyph = glyphFactory.getTextGlyph(fontName, size, color);

        for (int i = 0; i < context.getDocument().length(); i++) {
            String character = String.valueOf(context.getDocument().charAt(i));
            glyph.draw(gc, character, currentX, currentY);

            currentX += getFontWidth(fontName, size, character);
        }

        updateCursorPosition();
    }

    private void updateCursorPosition() {
        double cursorX = 0;

        for (int i = 0; i < context.getCursorIndex(); i++) {
            cursorX += getFontWidth(fontName, size, String.valueOf(context.getDocument().charAt(i)));
        }

        cursor.setTranslateX(cursorX);
        cursor.setTranslateY(yPos);
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
}