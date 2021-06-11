package edu.uga.cs1302.quiz;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.Collections;

public class GeographyQuiz extends Application {
    int count = 0;
    int countForClose = 0;
    int score = 0;
    QuizResult quizResult = new QuizResult();

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        Pane root = new Pane();
        Text hello = new Text(55, 50, "  Test your knowledge about \ncountries and their continent" +
                " \n\t  in this brief quiz." + "\n\tPress Help for more.");
        hello.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 11));
        hello.setFill(Color.DEEPPINK);
        Rectangle rectangle = new Rectangle(45, 35, 200, 60);
        rectangle.setFill(Color.MISTYROSE);
        rectangle.setEffect(new DropShadow());
        BackgroundImage myBI= new BackgroundImage(new Image("https://i.pinimg.com/originals/f0/e1/86/" +
                "f0e1869820b56bcc79819dee3f35490e.jpg",310,240,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        root.setBackground(new Background(myBI));
        Button play = new Button("Play");
        play.setAlignment(Pos.CENTER);
        play.setLayoutX(130);
        play.setLayoutY(110);
        play.setEffect(new DropShadow());
        play.setOnAction(this::playButtonHandler);
        Button previousTries = new Button("Previous Results");
        previousTries.setAlignment(Pos.CENTER);
        previousTries.setLayoutX(98);
        previousTries.setLayoutY(140);
        previousTries.setEffect(new DropShadow());
        previousTries.setOnAction(this::previousTriesButtonHandler);
        Button help = new Button("Help");
        help.setAlignment(Pos.CENTER);
        help.setLayoutX(128);
        help.setLayoutY(170);
        help.setEffect(new DropShadow());
        help.setOnAction(this::helpButtonHandler);
        Button exit = new Button("Quit");
        exit.setAlignment(Pos.CENTER);
        exit.setLayoutX(128);
        exit.setLayoutY(200);
        exit.setEffect(new DropShadow());
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                quizResult.readFromFile();
                quizResult.writeToFile();
                Platform.exit();
            }
        });
        root.getChildren().addAll(rectangle, hello, play, previousTries, help, exit);
        primaryStage.setTitle("Geography quiz");
        primaryStage.setScene(new Scene(root, 300, 237));
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }

    public void helpButtonHandler(ActionEvent actionEvent) {
        Stage helpStage = new Stage();
        VBox helpWindow = new VBox();
        Text helpText = new Text(115, 100, "Instructions : \n1) Press Play on main Window to start the quiz." +
                "\nSelect the right continent for the country in question and press Submit." +
                "\n6 questions every attempt." +
                "\nOnce started you need to complete the quiz before closing." +
                "\n2) Press Previous Results to see how you did on past tries." +
                "\n3) Press Close to return to main menu." +
                "\n4) Press Quit to close the game.");
        helpStage.initModality(Modality.APPLICATION_MODAL);
        Button close = new Button("Close");
        close.setAlignment(Pos.CENTER);
        close.setLayoutX(130);
        close.setLayoutY(120);
        close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                helpStage.close();
            }
        });
        helpWindow.getChildren().addAll(helpText, close);
        helpStage.setTitle("Help");
        helpStage.setScene(new Scene(helpWindow));
        helpStage.showAndWait();

    }

    public void previousTriesButtonHandler(ActionEvent actionEvent) {
        Stage previousTriesStage = new Stage();
        String resultsString = quizResult.getResults();
        ScrollPane s = new ScrollPane();
        VBox previousTriesWindow = new VBox();
        Text arrayText = new Text(115, 100, resultsString);
        previousTriesStage.initModality(Modality.APPLICATION_MODAL);
        Button close = new Button("Close");
        close.setAlignment(Pos.CENTER);
        close.setLayoutX(130);
        close.setLayoutY(120);
        close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                quizResult.resultingString = "Results:-";
                arrayText.setText("");
                previousTriesStage.close();
            }
        });
        s.setContent(arrayText);
        previousTriesWindow.getChildren().addAll(s, close);
        previousTriesStage.setTitle("Results");
        previousTriesStage.setScene(new Scene(previousTriesWindow,200,300));
        previousTriesStage.initStyle(StageStyle.UNDECORATED);
        previousTriesStage.showAndWait();

    }

    public void playButtonHandler(ActionEvent actionEvent) {
        Question question = new Question();
        Quiz quiz = new Quiz();
        quiz.setCurrentQuestions();
        ArrayList<String> list = new ArrayList<String>();
        Stage playStage = new Stage();
        VBox playWindow = new VBox();
        playStage.initModality(Modality.APPLICATION_MODAL);
        Text answerText = new Text("" + "\n");
        Text playText = new Text(115, 100, quiz.currentQuestions.get(count));
        list.add(question.option1(quiz.countryInQuestion.get(count)));
        list.add(question.option2());
        list.add(question.option3());
        Collections.shuffle(list);
        ToggleGroup toggleAnswer = new ToggleGroup();
        String answerOp1 = list.get(0);
        String answerOp2 = list.get(1);
        String answerOp3 = list.get(2);
        RadioButton playOption1 = new RadioButton(answerOp1);
        playOption1.setToggleGroup(toggleAnswer);
        playOption1.setSelected(true);
        RadioButton playOption2 = new RadioButton(answerOp2);
        playOption2.setToggleGroup(toggleAnswer);
        RadioButton playOption3 = new RadioButton(answerOp3);
        playOption3.setToggleGroup(toggleAnswer);
        Button next = new Button("Submit");
        next.setAlignment(Pos.CENTER);
        next.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(count < 5) {
                    if (question.checkAnswer(((RadioButton)toggleAnswer.getSelectedToggle()).getText())) {
                        answerText.setText("Your answer for previous question was correct.\n");
                        score++;
                    } else {
                        answerText.setText("Your answer for previous question was incorrect. " +
                                "\nCorrect Answer - " + question.option1(quiz.countryInQuestion.get(count)) +
                                "|| Your Answer- " + ((RadioButton)toggleAnswer.getSelectedToggle()).getText());
                    }
                    playText.setText(quiz.currentQuestions.get(count+1));
                    ArrayList<String> list2 = new ArrayList<String>();
                    list2.add(question.option1(quiz.countryInQuestion.get(count+1)));
                    list2.add(question.option2());
                    list2.add(question.option3());
                    Collections.shuffle(list2);
                    String answerOp1 = list2.get(0);
                    String answerOp2 = list2.get(1);
                    String answerOp3 = list2.get(2);
                    playOption1.setText(answerOp1);
                    playOption2.setText(answerOp2);
                    playOption3.setText(answerOp3);
                    count++;
                    countForClose++;
                } else if (count == 5) {
                    if (question.checkAnswer(((RadioButton)toggleAnswer.getSelectedToggle()).getText())) {
                        answerText.setText("Your answer for previous question was correct.\n");
                        score++;
                    } else {
                        answerText.setText("Your answer for previous question was incorrect. " +
                                "\nCorrect Answer - " + question.option1(quiz.countryInQuestion.get(count)) +
                                "|| Your Answer- " + ((RadioButton)toggleAnswer.getSelectedToggle()).getText());
                    }
                    playText.setText("Your Score is: " + score + "/6");
                    playOption1.getStyleClass().remove("radio-button");
                    playOption2.getStyleClass().remove("radio-button");
                    playOption3.getStyleClass().remove("radio-button");
                    playOption1.setText("");
                    playOption2.setText("");
                    playOption3.setText("");
                    next.getStyleClass().remove("button");
                    next.setText("");
                    count =0;
                    quizResult.setResults("Score was: " + score + "/6");
                }
            }
        });
        Button close = new Button("Close");
        close.setAlignment(Pos.CENTER);
        VBox optionBox = new VBox(playOption1, playOption2, playOption3);
        HBox buttons = new HBox(close, next);
        buttons.setSpacing(300);
        close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                score = 0;
                if (countForClose == 5) {
                    answerText.setText("");
                    countForClose = 0;
                    playStage.close();
                } else {
                    Stage closeStage = new Stage();
                    VBox closeWindow = new VBox();
                    Text closeText = new Text(115, 100, "Complete the quiz before exiting.");
                    closeStage.initModality(Modality.APPLICATION_MODAL);
                    Button close = new Button("Close");
                    close.setAlignment(Pos.CENTER);
                    close.setLayoutX(130);
                    close.setLayoutY(120);
                    close.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            closeStage.close();
                        }
                    });
                    closeWindow.getChildren().addAll(closeText, close);
                    closeStage.setTitle("Help");
                    closeStage.setScene(new Scene(closeWindow));
                    closeStage.showAndWait();
                }
            }
        });
        playWindow.getChildren().addAll(answerText, playText, optionBox, buttons);
        playStage.setTitle("Playing");
        playStage.setScene(new Scene(playWindow));
        playStage.initStyle(StageStyle.UNDECORATED);
        playStage.showAndWait();
    }
}

