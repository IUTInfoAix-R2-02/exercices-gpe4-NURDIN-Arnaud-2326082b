package fr.amu.iut.exercice4;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Palette extends Application {

    private int nbVert = 0;
    private int nbRouge = 0;
    private int nbBleu = 0;

    private Button vert;
    private Button rouge;
    private Button bleu;

    private BorderPane root;
    private Label label;
    private Pane panneau;
    private HBox bas;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.root = new BorderPane();
        this.label = new Label();
        HBox hb = new HBox(label);
        hb.setAlignment(Pos.CENTER);
        this.panneau = new Pane();
        this.vert = new Button("Vert");
        vert.addEventHandler(MouseEvent.MOUSE_CLICKED, actionEvent -> {
            ++this.nbVert;
            label.setText( "Vert choisi "+ nbVert +" fois");
            this.panneau.setStyle("-fx-background-color: green;");

        });
        this.rouge = new Button("Rouge");
        rouge.addEventHandler(MouseEvent.MOUSE_CLICKED, actionEvent -> {
            ++this.nbRouge;
            label.setText( "Rouge choisi "+ nbRouge +" fois");
            this.panneau.setStyle("-fx-background-color: red;");
        });
        this.bleu = new Button("Bleu");
        bleu.addEventHandler(MouseEvent.MOUSE_CLICKED, actionEvent -> {
            ++this.nbBleu;
            label.setText( "Bleu choisi "+ nbBleu +" fois");
            this.panneau.setStyle("-fx-background-color: blue;");
            this.label.setAlignment(Pos.CENTER);
        });
        this.bas = new HBox(vert, rouge, bleu);
        this.bas.setAlignment(Pos.CENTER);
        this.bas.setSpacing(25);
        this.root.setTop(hb);
        this.root.setCenter(panneau);
        this.root.setBottom(bas);
        Scene scene = new Scene(this.root);
        primaryStage.setScene( scene );
        primaryStage.setWidth(400);
        primaryStage.setHeight(200);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

