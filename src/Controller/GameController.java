package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import main.MyListener;
import model.Question;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    private List<ItemController> itemControllerList = new ArrayList<>();

    @FXML
    private VBox vboxMainQuestion;

    @FXML
    private ToggleGroup answer;

    @FXML
    private RadioButton answer0;

    @FXML
    private RadioButton answer1;

    @FXML
    private RadioButton answer2;

    @FXML
    private RadioButton answer3;

    @FXML
    private Text questionText;

    @FXML
    private VBox chosenQuestionCard;

    @FXML
    private ImageView questionImg;

    @FXML
    private GridPane grid;

    @FXML
    private Button summitAnswer;

    private List<Question> questions = new ArrayList<>();
    private Image image;
    private MyListener myListener;

    private int idQuestionIsSelected;

    private int answerIsSelected;

    private List<Question> getData() {
        return Question.setDataForQuestionList();
    }
    @FXML
    public void handleAnswerButtonAction(ActionEvent actionEvent) {
        if(this.answer0.isSelected()) {
            this.answerIsSelected = 1;
        } else if (this.answer1.isSelected()) {
            this.answerIsSelected = 2;
        } else if (this.answer2.isSelected()) {
            this.answerIsSelected = 3;
        } else if (this.answer3.isSelected()) {
            this.answerIsSelected = 4;
        }
        if(checkAnswer()) {
            itemControllerList.get(this.idQuestionIsSelected - 1).changeImageToCorrectImage();
        }
    }

    private boolean checkAnswer() {
        boolean check = false;
        for(Question question : this.questions) {
            if(question.getQuestionId() == this.idQuestionIsSelected) {
                if (question.getCorrectAnswer() == this.answerIsSelected) {
                    check = true;
                }
            }
        }
        return check;
    }
    private void setChosenFruit(Question question) {
        image = new Image(getClass().getResourceAsStream(question.getImgSrc()));
        questionText.setText(question.getQuestionText());
        answer0.setSelected(false);
        answer1.setSelected(false);
        answer2.setSelected(false);
        answer3.setSelected(false);
        answer0.setText(question.getAnswer1());
        answer1.setText(question.getAnswer2());
        answer2.setText(question.getAnswer3());
        answer3.setText(question.getAnswer4());
        questionImg.setImage(image);
        chosenQuestionCard.setStyle("-fx-background-radius: 30;");
        this.idQuestionIsSelected = question.getQuestionId();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        questions.addAll(getData());
        if (questions.size() > 0) {
            setChosenFruit(questions.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Question question) {
                    setChosenFruit(question);
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < 9; i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemControllerList.add(itemController);
//                itemController.setData(questions.get(i),myListener);
                itemController.setSecret(myListener, questions.get(i));

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/views/mainQuestion.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();

            MainQuestionController mainQuestionController = fxmlLoader.getController();
            mainQuestionController.setData();
            mainQuestionController.setBoxAnswer();
            vboxMainQuestion.getChildren().add(anchorPane);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
