package com.captchapro.texteditor.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.awt.*;

public class TextGlyph implements Glyph {
    private final String font;
    private final int fontSize;
    private final Paint color;

    public TextGlyph(String font, int fontSize, Paint color) {
        this.font = font;
        this.fontSize = fontSize;
        this.color = color;
    }

    @Override
    public void draw(GraphicsContext graphicsContext, String glyph, double x, double y) {
        graphicsContext.setFont(Font.font(font, fontSize));
        graphicsContext.setFill(color);
        graphicsContext.fillText(glyph, x, y);
    }
}