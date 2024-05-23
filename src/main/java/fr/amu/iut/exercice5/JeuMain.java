package fr.amu.iut.exercice5;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class JeuMain extends Application {

    private Scene scene;
    private BorderPane root;

    // Attributs pour gérer le score
    private int nbj1 = 0;
    private int nbj2 = -1;
    private final Label nb = new Label("   Nombre de victoires : " + nbj1);
    private final Label nb2 = new Label("Nombre de victoires : " + nbj2 + "   ");
    private Timer timer;

    @Override
    public void start(Stage primaryStage) {
        root = new BorderPane();

        // Initialisation de l'interface de démarrage
        HBox startBox = new HBox();
        startBox.setAlignment(Pos.CENTER);
        startBox.setStyle("-fx-background-color: black;");
        Button startButton = new Button("Start Game");
        startButton.setStyle("-fx-font-size: 20px; -fx-padding: 10px;");
        startBox.getChildren().add(startButton);

        root.setCenter(startBox);

        // Gestion du clic sur le bouton de démarrage
        startButton.setOnAction(event -> {
            root.setCenter(createGamePane());
            creerEtDemarrerTimer();
        });

        scene = new Scene(root);

        primaryStage.setTitle("Pacman1v1.io");
        primaryStage.setScene(scene);
        primaryStage.setWidth(1920);  // Largeur totale des trois panneaux (3 x 640)
        primaryStage.setHeight(1080); // Hauteur totale de la scène
        primaryStage.show();
    }

    private Pane createGamePane() {
        // Acteurs du jeu
        Personnage pacman = new Pacman();
        Personnage fantome = new Fantome();
        fantome.setLayoutX(560);
        fantome.setLayoutY(60);
        pacman.setLayoutX(60);
        pacman.setLayoutY(400);

        // Création des obstacles du jeu
        ArrayList<Obstacle> obstacles = new ArrayList<>();
        obstacles.add(new Obstacle(0, 0, 60, 482));
        obstacles.add(new Obstacle(580, 0, 60, 480));
        obstacles.add(new Obstacle(0, 0, 640, 60));
        obstacles.add(new Obstacle(0, 420, 640, 62));
        obstacles.add(new Obstacle(400, 260, 20, 80));
        obstacles.add(new Obstacle(400, 260, 80, 20));
        obstacles.add(new Obstacle(160, 200, 80, 20));
        obstacles.add(new Obstacle(220, 140, 20, 80));

        // Panneau du jeu
        Pane jeu = new Pane();
        jeu.setPrefSize(640, 480);
        jeu.getChildren().addAll(pacman, fantome);
        jeu.getChildren().addAll(obstacles);
        jeu.setStyle("-fx-background-color: red;");

        // Création des interfaces publicitaires à gauche et à droite de l'écran du jeu
        VBox pubGauche = new VBox();
        pubGauche.setPrefSize(640, 1080);
        pubGauche.setStyle("-fx-background-color: BLACK;");
        VBox pubDroite = new VBox();
        pubDroite.setPrefSize(640, 1080);
        pubDroite.setStyle("-fx-background-color: BLACK;");

        // Création de l'écran d'affichage du jeu (VBox du milieu)
        VBox ecran = new VBox();
        ecran.setPrefSize(640, 1080);

        // Création de l'interface sous l'écran de jeu
        HBox miseEnPage = new HBox();
        Separator sep5 = new Separator(Orientation.VERTICAL);
        sep5.setStyle("-fx-background-color: white;");
        Separator sep6 = new Separator(Orientation.VERTICAL);
        sep6.setStyle("-fx-background-color: white;");

        // Création de l'écran de score
        HBox score = new HBox();
        VBox scorej1 = new VBox();
        Label nom = new Label("   Pacman");
        nom.setTextFill(Color.WHITE);
        nom.setFont(Font.font("Courier", FontWeight.NORMAL, 25));
        HBox nomm = new HBox(nom);
        nomm.setAlignment(Pos.CENTER_LEFT);

        nb.setTextFill(Color.WHITE);
        nb.setFont(Font.font("Courier", FontWeight.NORMAL, 20));
        HBox nbb = new HBox(nb);
        nbb.setAlignment(Pos.BOTTOM_LEFT);
        Label espace = new Label("      ");
        scorej1.getChildren().addAll(nomm, espace, nbb);
        scorej1.setPrefSize(320, 500);
        scorej1.setAlignment(Pos.CENTER);

        VBox scorej2 = new VBox();
        Label nom2 = new Label("Fantome  ");
        nom2.setTextFill(Color.WHITE);
        nom2.setFont(Font.font("Courier", FontWeight.NORMAL, 25));
        HBox nomm2 = new HBox(nom2);
        nomm2.setAlignment(Pos.CENTER_RIGHT);

        nb2.setTextFill(Color.WHITE);
        nb2.setFont(Font.font("Courier", FontWeight.NORMAL, 20));
        HBox nbb2 = new HBox(nb2);
        nbb2.setAlignment(Pos.BOTTOM_RIGHT);
        Label espace2 = new Label("      ");
        scorej2.getChildren().addAll(nomm2, espace2, nbb2);
        scorej2.setPrefSize(320, 500);
        scorej2.setAlignment(Pos.CENTER);

        score.getChildren().addAll(scorej1, scorej2);
        score.setPrefSize(640, 500);
        score.setStyle("-fx-background-color: BLACK;");

        // Création du titre au milieu du jeu
        Label titre = new Label("PACMAN 1V1");
        titre.setStyle("-fx-background-color: black;");
        titre.setTextFill(Color.WHITE);
        titre.setFont(Font.font("Courier", FontWeight.NORMAL, 30        ));
        miseEnPage.getChildren().addAll(sep5, score, sep6);
        Separator sep = new Separator(Orientation.HORIZONTAL);
        sep.setStyle("-fx-background-color: white;");
        Separator sep4 = new Separator(Orientation.HORIZONTAL);
        sep4.setStyle("-fx-background-color: white;");

        ecran.getChildren().addAll(jeu, titre, sep, miseEnPage, sep4);
        ecran.setAlignment(Pos.CENTER);
        HBox hbox = new HBox();
        Separator sep2 = new Separator(Orientation.VERTICAL);
        sep2.setStyle("-fx-background-color: white;");
        Separator sep3 = new Separator(Orientation.VERTICAL);
        sep3.setStyle("-fx-background-color: white;");
        hbox.getChildren().addAll(pubGauche, ecran, pubDroite);
        hbox.setStyle("-fx-background-color: black;");
        return hbox;
    }

    private void creerEtDemarrerTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                nbj2++;
                Platform.runLater(() -> nb2.setText("Nombre de victoires : " + nbj2));
            }
        }, 0, 30000);
    }

    /**
     * Permet de gérer les événements de type clavier, pression des touches
     * pour le j1(up,down, right, left), pour le j2( z,q,s,d)
     *
     * @param j1      joueur 1
     * @param j2      joueur 2
     * @param obstacles liste d'obstacles présent sur la map
     */
    private void deplacer(Personnage j1, Personnage j2, ArrayList<Obstacle> obstacles) {
        scene.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()) {
                case LEFT:
                    j1.deplacerAGauche();
                    break;
                case RIGHT:
                    j1.deplacerADroite(scene.getWidth());
                    break;
                case UP:
                    j1.deplacerEnHaut();
                    break;
                case DOWN:
                    j1.deplacerEnBas(scene.getHeight());
                    break;
                case Q:
                    j2.deplacerAGauche();
                    break;
                case D:
                    j2.deplacerADroite(scene.getWidth());
                    break;
                case Z:
                    j2.deplacerEnHaut();
                    break;
                case S:
                    j2.deplacerEnBas(scene.getHeight());
                    break;
            }
            if (j1.estEnCollision(j2)) {
                nbj1++;
                nb.setText("   Nombre de victoires : " + nbj1);
                j2.setLayoutX(560);
                j2.setLayoutY(60);
                j1.setLayoutX(60);
                j1.setLayoutY(400);

                // Arrêt du timer existant et création d'un nouveau
                timer.cancel();
                nbj2--;
                creerEtDemarrerTimer();
            }
            for (Obstacle value : obstacles) {
                if (value.collisionMur(j1)) {
                    j1.setLayoutX(j1.getPosX());
                    j1.setLayoutY(j1.getPosY());
                }
                if (value.collisionMur(j2)) {
                    j2.setLayoutX(j2.getPosX());
                    j2.setLayoutY(j2.getPosY());
                }
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}

