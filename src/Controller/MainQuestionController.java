package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import model.MainQuestion;

public class MainQuestionController {

    @FXML
    private Button buttonSummit;

    @FXML
    private GridPane grid;

    @FXML
    private Text mainQuestion;

    private MainQuestion question;

    public void setData() {
        this.mainQuestion.setText("Chướng ngại vật có 3 chữ cái");
    }

    public void setBoxAnswer () {
        question = new MainQuestion();
        question.setAnswer("abcd");
        question.setLengthAnswer(question.getAnswer().length());

        this.grid.setHgap(10);
        this.grid.setVgap(10);
        TextField[] textFields = new TextField[question.getLengthAnswer()];

        for (int i = 0; i < question.getLengthAnswer(); i++) {
            TextField textField = new TextField();
            textField.setPrefColumnCount(1);
            textFields[i] = textField;
            grid.add(textFields[i], i, 0);

            //set grid width
            grid.setMinWidth(Region.USE_COMPUTED_SIZE);
            grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
            grid.setMaxWidth(Region.USE_PREF_SIZE);

            //set grid height
            grid.setMinHeight(Region.USE_COMPUTED_SIZE);
            grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
            grid.setMaxHeight(Region.USE_PREF_SIZE);
        }

        for (int i = 0; i < question.getLengthAnswer(); i++) {
            int finalCol = i;
            textFields[i].textProperty().addListener((observable, oldValue, newValue) -> {
                if(newValue.length() > 1) {
                    textFields[finalCol].setText(newValue.substring(0, 1));
                }
            });
        }
    }
}
