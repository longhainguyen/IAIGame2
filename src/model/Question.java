package model;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Question {

    private String questionText;
    private List<String> listAnswer;

    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;

    private int correctAnswer;
    private int questionId;
    private String imgSrc;
    private String color;

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<String> getListAnswer() {
        return listAnswer;
    }

    public void setListAnswer(List<String> listAnswer) {
        this.listAnswer = listAnswer;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public static List<Question> setDataForQuestionList() {
        List<Question> questions = new ArrayList<>();
        Question question = null;
        try {
            /**
             *
             */
            // Đường dẫn tương đối của tệp tin txt
            String filePath = "src/data/question.txt";
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(line.equals("#####")) {
                    question = new Question();
                    continue;
                }
                String[] parts = line.split(" ");
                switch (parts[0]) {
                    case "id":
                        assert question != null;
                        question.setQuestionId(Integer.parseInt(parts[1]));
                        break;
                    case "question":
                        String data = line.substring( 1 + parts[0].length());
                        assert question != null;
                        question.setQuestionText(data);
                        break;
                    case "answer1":
                        assert question != null;
                        question.setAnswer1(line.substring(1 + parts[0].length()));
                        break;
                    case "answer2":
                        assert question != null;
                        question.setAnswer2(line.substring(1 + parts[0].length()));
                        break;
                    case "answer3":
                        assert question != null;
                        question.setAnswer3(line.substring(1 + parts[0].length()));
                        break;
                    case "answer4":
                        assert question != null;
                        question.setAnswer4(line.substring(1 + parts[0].length()));
                        break;
                    case "correct":
                        assert question != null;
                        question.setCorrectAnswer(Integer.parseInt(line.substring(1 + parts[0].length())));
                        break;
                    case "src":
                        assert question != null;
                        question.setImgSrc(line.substring(1 + parts[0].length()));
                        questions.add(question);
                        break;
                }

            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return questions;
    }

    public static void main(String[] args) {
       List<Question> questions = setDataForQuestionList();
       for(Question question : questions) {
           System.out.println(question.questionId);
       }

    }
}
