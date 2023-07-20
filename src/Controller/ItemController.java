package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import main.Main;
import main.MyListener;
import model.Question;

import java.util.List;

public class ItemController {
    @FXML
    private ImageView img;

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(question);
    }

    private Question question;
    private MyListener myListener;


    public void setData(Question question, MyListener myListener) {
        this.question = question;
        this.myListener = myListener;

        Image image = new Image(getClass().getResourceAsStream(question.getImgSrc()));
        img.setImage(image);
    }

    public void changeImageToCorrectImage () {
        Image image = new Image("/img/kiwi.png");
        img.setImage(image);
    }

    public void setSecret(MyListener myListener, Question question) {
        this.question = question;
        this.myListener = myListener;
        Image image = new Image("/img/question.jpg");
        img.setImage(image);
    }
}
