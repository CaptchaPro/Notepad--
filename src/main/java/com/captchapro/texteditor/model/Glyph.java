package com.captchapro.texteditor.model;

import javafx.scene.canvas.GraphicsContext;

public interface Glyph {
    public void draw(GraphicsContext graphicsContext, String glyph, double x, double y);
}