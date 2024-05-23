package fr.amu.iut.exercice9;

import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Animation extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane root = new StackPane();
        CustomButton customButton = new CustomButton();
        root.getChildren().add(customButton);
        Scene scene = new Scene(root, 400, 400);

        Duration duration = Duration.millis(500);

        TranslateTransition transitionRight = new TranslateTransition(duration, customButton);
        transitionRight.setByX(150);

        TranslateTransition transitionDown = new TranslateTransition(duration, customButton);
        transitionDown.setByY(150);

        TranslateTransition transitionLeft = new TranslateTransition(duration, customButton);
        transitionLeft.setByX(-300);

        TranslateTransition transitionUp = new TranslateTransition(duration, customButton);
        transitionUp.setByY(-300);

        TranslateTransition transitionRightBack = new TranslateTransition(duration, customButton);
        transitionRightBack.setByX(300);

        TranslateTransition transitionDownBack = new TranslateTransition(duration, customButton);
        transitionDownBack.setByY(150);

        TranslateTransition transitionCenter = new TranslateTransition(duration, customButton);
        transitionCenter.setByX(-150);

        //retour en arrière

        TranslateTransition transitionUp2 = new TranslateTransition(duration, customButton);
        transitionUp2.setByY(-150);

        TranslateTransition transitionRight2 = new TranslateTransition(duration, customButton);
        transitionRight2.setByX(150);

        TranslateTransition transitionDown2 = new TranslateTransition(duration, customButton);
        transitionDown2.setByY(300);

        TranslateTransition transitionLeft2 = new TranslateTransition(duration, customButton);
        transitionLeft2.setByX(-300);

        TranslateTransition transitionRightBack2 = new TranslateTransition(duration, customButton);
        transitionRightBack2.setByX(300);

        TranslateTransition transitionUpToCenter2 = new TranslateTransition(duration, customButton);
        transitionUpToCenter2.setByY(-150);

        TranslateTransition transitionRightToCenter2 = new TranslateTransition(duration, customButton);
        transitionRightToCenter2.setByX(-150);

        SequentialTransition sequentialTransition = new SequentialTransition(
                transitionRight, transitionDown, transitionLeft, transitionUp, transitionRightBack, transitionDownBack, transitionCenter,transitionRight2,transitionUp2,transitionLeft2,  transitionDown2,transitionRightBack2,  transitionUpToCenter2, transitionRightToCenter2);
        sequentialTransition.setCycleCount(1);

        customButton.setOnMousePressed(mouseEvent -> sequentialTransition.play());

        primaryStage.setTitle("Animation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}