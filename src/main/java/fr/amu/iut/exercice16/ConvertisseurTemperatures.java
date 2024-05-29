package fr.amu.iut.exercice16;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class ConvertisseurTemperatures extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Création des sliders
        Slider celsiusSlider = new Slider(0, 100, 0);
        celsiusSlider.setOrientation(Orientation.VERTICAL);
        celsiusSlider.setMinHeight(900); // Hauteur minimale pour étirer le slider
        Slider fahrenheitSlider = new Slider(0, 212, 32);
        fahrenheitSlider.setOrientation(Orientation.VERTICAL);
        fahrenheitSlider.setMinHeight(900); // Hauteur minimale pour étirer le slider

        // Création des TextField
        TextField celsiusField = new TextField();
        TextField fahrenheitField = new TextField();

        // Configuration des sliders pour afficher les valeurs actuelles
        celsiusSlider.setShowTickLabels(true);
        celsiusSlider.setShowTickMarks(true);
        fahrenheitSlider.setShowTickLabels(true);
        fahrenheitSlider.setShowTickMarks(true);

        // Labels
        Label celsiustxt = new Label("°C");
        celsiustxt.setStyle("-fx-font-weight: bold");
        Label fartxt = new Label("°F");
        fartxt.setStyle("-fx-font-weight: bold");

        // Layouts pour les panneaux Celsius et Fahrenheit
        VBox panneauCelsius = new VBox(10, celsiustxt, celsiusSlider, celsiusField);
        panneauCelsius.setFillWidth(true);
        VBox panneauFahrenheit = new VBox(10, fartxt, fahrenheitSlider, fahrenheitField);
        panneauFahrenheit.setFillWidth(true);

        // Root layout
        HBox root = new HBox(30, panneauCelsius, panneauFahrenheit);
        root.setPadding(new Insets(20));
        root.setFillHeight(true); // Pour que les enfants remplissent la hauteur du parent

        // Création des propriétés pour les valeurs
        DoubleProperty celsius = new SimpleDoubleProperty();
        DoubleProperty fahrenheit = new SimpleDoubleProperty();

        // Binding bidirectionnel entre les sliders et les propriétés
        celsius.bindBidirectional(celsiusSlider.valueProperty());
        fahrenheit.bindBidirectional(fahrenheitSlider.valueProperty());

        // Binding bidirectionnel entre les propriétés et les champs de texte
        Bindings.bindBidirectional(celsiusField.textProperty(), celsius, new NumberStringConverter());
        Bindings.bindBidirectional(fahrenheitField.textProperty(), fahrenheit, new NumberStringConverter());

        // Listeners pour synchroniser les sliders avec conversion
        celsiusSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            double fahrenheitValue = newValue.doubleValue() * 9 / 5 + 32;
            if (fahrenheitSlider.getValue() != fahrenheitValue) {
                fahrenheitSlider.setValue(fahrenheitValue);
            }
        });

        fahrenheitSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            double celsiusValue = (newValue.doubleValue() - 32) * 5 / 9;
            if (celsiusSlider.getValue() != celsiusValue) {
                celsiusSlider.setValue(celsiusValue);
            }
        });

        // Configuration de la scène et affichage
        primaryStage.setTitle("Convertisseur de Températures");
        primaryStage.setScene(new Scene(root, 400, 1000)); // Taille initiale de la fenêtre augmentée
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
