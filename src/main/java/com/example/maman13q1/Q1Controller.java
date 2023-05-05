package com.example.maman13q1;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class Q1Controller {

    private final int BOARD_SIZE = 9;
    private SudokuBoard board;
    @FXML
    private TextFieldWLocationInGrid[][] array;

    @FXML
    private GridPane grid;

    @FXML
    private Label label;

    @FXML
    private Button setBtn;

    @FXML
    private Button clearBtn;
    private Event prevEvent;

    @FXML
    void clearBtnClicked(ActionEvent event) {
        label.setText("Wanna play again?");
        setBtn.setDisable(false);
        board = new SudokuBoard();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                array[i][j].clear();
                array[i][j].setStyle("-fx-text-fill: black; -fx-font-weight: bold");
                array[i][j].setEditable(true);
            }
        }
    }

    @FXML
    void setBtnClicked(ActionEvent event) {
        label.setText("Setting up board...");
        setBtn.setDisable(true);
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (!array[i][j].getText().isBlank()) {
                    array[i][j].setEditable(false);
                    array[i][j].setStyle("-fx-text-fill: purple; -fx-font-weight: bold");
                }
            }
        }
        label.setText("Ready to play!");
    }

    @FXML
    void textFieldKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            TextFieldWLocationInGrid obj = (TextFieldWLocationInGrid) event.getSource();
            try {
                board.setCell(obj.getRow(), obj.getColumn(), Integer.parseInt(obj.getText()));
                label.setText("Input at (" + (obj.getRow() + 1) + "," + (obj.getColumn() + 1) + ") successfully entered.");
                if (board.isVictory())
                    label.setText("Victory!!!");
            } catch (IllegalArgumentException e) { //includes NumberFormatException from Integer.parseInt()
                label.setText("Bad input at (" + (obj.getRow() + 1) + "," + (obj.getColumn() + 1) + ").");
                obj.clear();
            }
            prevEvent = null;
        } else {
            clearUnconfirmedInput(event);
            prevEvent = event;
        }
    }

    @FXML
    void textFieldMouseClicked(MouseEvent event) {
        clearUnconfirmedInput(prevEvent);
    }

    public void initialize() {
        final int FONT_SIZE = 13;
        label.setText("Welcome! Remember to press ↵ to confirm input!");
        board = new SudokuBoard();
        array = new TextFieldWLocationInGrid[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                array[i][j] = new TextFieldWLocationInGrid(i, j);
                array[i][j].setAlignment(Pos.CENTER);
                array[i][j].setFont(Font.font(FONT_SIZE));
                array[i][j].setStyle("-fx-font-weight: bold");
                array[i][j].setBackground(Background.EMPTY);
                array[i][j].setOnKeyPressed(this::textFieldKeyPress);
                array[i][j].setOnMouseClicked(this::textFieldMouseClicked);
                grid.add(array[i][j], j, i);
            }
        }
    }

    public void clearUnconfirmedInput(Event event) {
        if (event != null) {
            TextFieldWLocationInGrid obj = (TextFieldWLocationInGrid) event.getSource();
            if (!board.isSet(obj.getRow(), obj.getColumn()))
                obj.clear();
        }
        label.setText("Remember to press ↵ to confirm input!");
    }
}