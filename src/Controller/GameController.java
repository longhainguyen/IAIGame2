package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
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

    private List<Question> questions = new ArrayList<>();
    private Image image;
    private MyListener myListener;

    private List<Question> getData() {
        List<String> listAnswer = new ArrayList<>();
        listAnswer.add("A. 1945 – 1949");
        listAnswer.add("B. 1946- 1950.");
        listAnswer.add("C. 1947-1951.");
        listAnswer.add("D. 1945- 1951.");
        List<Question> questions = new ArrayList<>();
        Question question;

        question = new Question();
        question.setQuestionText("Liên Xô chế tạo thành công bom nguyên tử vào thời gian nào?");
        question.setListAnswer(listAnswer);
        question.setCorrectAnswer(1);
        question.setImgSrc("/img/coconut.png");
        question.setColor("A7745B");
        questions.add(question);

        question = new Question();
        question.setQuestionText("Kế hoạch 5 năm khôi phục kinh tế sau chiến tranh ở Liên Xô diễn ra trong khoảng thời gian nào ?");
        question.setListAnswer(listAnswer);
        question.setCorrectAnswer(1);
        question.setImgSrc("/img/peach.png");
        question.setColor("F16C31");
        questions.add(question);

        question = new Question();
        question.setQuestionText("Kế hoạch 5 năm khôi phục kinh tế sau chiến tranh ở Liên Xô diễn ra trong khoảng thời gian nào ?");
        question.setListAnswer(listAnswer);
        question.setCorrectAnswer(1);
        question.setImgSrc("/img/grapes.png");
        question.setColor("291D36");
        questions.add(question);

        question = new Question();
        question.setQuestionText("Kế hoạch 5 năm khôi phục kinh tế sau chiến tranh ở Liên Xô diễn ra trong khoảng thời gian nào ?");
        question.setListAnswer(listAnswer);
        question.setCorrectAnswer(1);
        question.setImgSrc("/img/watermelon.png");
        question.setColor("22371D");
        questions.add(question);

        question = new Question();
        question.setQuestionText("Kế hoạch 5 năm khôi phục kinh tế sau chiến tranh ở Liên Xô diễn ra trong khoảng thời gian nào ?");
        question.setListAnswer(listAnswer);
        question.setCorrectAnswer(1);
        question.setImgSrc("/img/orange.png");
        question.setColor("FB5D03");
        questions.add(question);

        question = new Question();
        question.setQuestionText("Kế hoạch 5 năm khôi phục kinh tế sau chiến tranh ở Liên Xô diễn ra trong khoảng thời gian nào ?");
        question.setListAnswer(listAnswer);
        question.setCorrectAnswer(1);
        question.setImgSrc("/img/strawberry.png");
        question.setColor("80080C");
        questions.add(question);

        question = new Question();
        question.setQuestionText("Kế hoạch 5 năm khôi phục kinh tế sau chiến tranh ở Liên Xô diễn ra trong khoảng thời gian nào ?");
        question.setListAnswer(listAnswer);
        question.setCorrectAnswer(1);
        question.setImgSrc("/img/mango.png");
        question.setColor("FFB605");
        questions.add(question);

        question = new Question();
        question.setQuestionText("Kế hoạch 5 năm khôi phục kinh tế sau chiến tranh ở Liên Xô diễn ra trong khoảng thời gian nào ?");
        question.setListAnswer(listAnswer);
        question.setCorrectAnswer(1);
        question.setImgSrc("/img/cherry.png");
        question.setColor("5F060E");
        questions.add(question);

        question = new Question();
        question.setQuestionText("Kế hoạch 5 năm khôi phục kinh tế sau chiến tranh ở Liên Xô diễn ra trong khoảng thời gian nào ?");
        question.setListAnswer(listAnswer);
        question.setCorrectAnswer(1);
        question.setImgSrc("/img/banana.png");
        question.setColor("E7C00F");
        questions.add(question);

        return questions;
    }

    private void setChosenFruit(Question question) {
        List<String> listAnswer = question.getListAnswer();
        image = new Image(getClass().getResourceAsStream(question.getImgSrc()));
        questionText.setText(question.getQuestionText());
        answer0.setText(listAnswer.get(0));
        answer1.setText(listAnswer.get(1));
        answer2.setText(listAnswer.get(2));
        answer3.setText(listAnswer.get(3));
        questionImg.setImage(image);
        chosenQuestionCard.setStyle("-fx-background-color: #" + question.getColor() + ";\n" +
                "    -fx-background-radius: 30;");
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
            for (int i = 0; i < questions.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(questions.get(i),myListener);

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
    }

}
