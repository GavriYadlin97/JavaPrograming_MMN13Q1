package com.example.maman13q1;

import javafx.scene.control.TextField;

public class TextFieldWLocationInGrid extends TextField {

    private final int _row;
    private final int _column;

    public TextFieldWLocationInGrid(int row, int column) {
        this._row = row;
        this._column = column;
    }

    public int getColumn() {
        return this._column;
    }

    public int getRow() {
        return this._row;
    }
}
