package com.captchapro.texteditor.model;

import javafx.scene.paint.Paint;

import java.util.HashMap;
import java.util.Map;

public class GlyphFactory {
    private static GlyphFactory INSTANCE;
    private final Map<String, TextGlyph> glyphMap = new HashMap<>();

    private GlyphFactory() {
    }

    public static GlyphFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GlyphFactory();
        }

        return INSTANCE;
    }

    public TextGlyph getTextGlyph(String font, int fontSize, Paint color) {
        String key = font + fontSize + color.toString();

        if (!glyphMap.containsKey(key)) {
            glyphMap.put(key, new TextGlyph(font, fontSize, color));
        }

        return glyphMap.get(key);
    }
}