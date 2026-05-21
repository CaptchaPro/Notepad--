package com.captchapro.texteditor;

import com.captchapro.texteditor.model.GlyphFactory;
import com.captchapro.texteditor.model.TextGlyph;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class HelloController {
    @FXML
    public Pane textPane;

    // temp settings
    private String font = "Arial";
    private int size = 12;
    private Paint color = Color.BLACK;
    private double xPos = 0.0;
    private double yPos = 20.0;

    private final GlyphFactory glyphFactory = GlyphFactory.getInstance();
    Canvas canvas = new Canvas(640, 455);
    Line cursor = new Line(0, 0, 0, -20);
    GraphicsContext gc = canvas.getGraphicsContext2D();

    @FXML
    public void initialize() {
        textPane.getChildren().add(canvas);
        textPane.getChildren().add(cursor);
        textPane.setFocusTraversable(true);

        textPane.setOnKeyTyped(keyEvent -> {
            String key = keyEvent.getCharacter();
            renderGlyph(key);
            renderCursor();
        });
    }

    private void renderGlyph(String key) {
        TextGlyph glyph = glyphFactory.getTextGlyph(font, size, color);
        glyph.draw(gc, key, xPos, yPos);

        Text text = new Text(key);
        double keyWidth = text.getLayoutBounds().getWidth();

        xPos += keyWidth;
    }

    private void renderCursor() {
        cursor.setTranslateX(xPos);
        cursor.setTranslateY(yPos);
    }
}