package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class FillCharactersApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Fill Characters");

        // Tạo GridPane với 4 ô vuông
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Tạo mảng lưu trữ các ô
        TextField[][] textFields = new TextField[2][2];

        // Điền từng ô vào GridPane
        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 2; col++) {
                TextField textField = new TextField();
                textField.setPrefColumnCount(1); // Chỉ cho phép một ký tự trong ô
                textFields[row][col] = textField;
                gridPane.add(textField, col, row);
            }
        }

        // Xử lý sự kiện khi nhập chữ vào ô
        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 2; col++) {
                int finalRow = row;
                int finalCol = col;
                textFields[row][col].textProperty().addListener((observable, oldValue, newValue) -> {
                    // Giới hạn chỉ cho phép một ký tự trong ô
                    if (newValue.length() > 1) {
                        textFields[finalRow][finalCol].setText(newValue.substring(0, 1));
                    }
                });
            }
        }

        Scene scene = new Scene(gridPane, 200, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}