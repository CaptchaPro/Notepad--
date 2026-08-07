package com.captchapro.texteditor.model;

import javafx.scene.input.KeyCode;

public class InputContext {
    private KeyCode mainKey;
    private String typedKey;
    boolean isControlDown;
    boolean isShiftDown;
    boolean isAltDown;

    public InputContext(String typedKey) {
        this.typedKey = typedKey;
        this.isControlDown = false;
        this.isShiftDown = false;
        this.isAltDown = false;
    }

    public InputContext(KeyCode mainKey, boolean isControlDown, boolean isShiftDown, boolean isAltDown) {
        this.mainKey = mainKey;
        this.isControlDown = isControlDown;
        this.isShiftDown = isShiftDown;
        this.isAltDown = isAltDown;
    }

    public KeyCode getMainKey() {
        return mainKey;
    }

    public String getTypedKey() {
        return typedKey;
    }

    public boolean isControlDown() {
        return isControlDown;
    }

    public boolean isShiftDown() {
        return isShiftDown;
    }

    public boolean isAltDown() {
        return isAltDown;
    }
}