package fr.amu.iut.exercice15;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LoginControl extends GridPane {
    @FXML
    private TextField userId;

    @FXML
    private PasswordField pwd;

    @FXML
    private Button okBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private void initialize() {
        createBindings();
    }

    private void createBindings() {
        // 1. Le champ du mot de passe ne doit pas être éditable si le nom de l’utilisateur fait moins de 6 caractères
        pwd.editableProperty().bind(Bindings.createBooleanBinding(
                () -> userId.getText().length() >= 6,
                userId.textProperty()
        ));

        // 2. Le bouton cancel ne doit pas être cliquable si les deux champs sont vides
        BooleanBinding fieldsEmpty = userId.textProperty().isEmpty().and(pwd.textProperty().isEmpty());
        cancelBtn.disableProperty().bind(fieldsEmpty);

        // 3. Le bouton ok ne doit pas être cliquable tant que le mot de passe n’a pas au moins 8 caractères,
        // et ne contient pas au moins une majuscule et un chiffre.
        BooleanBinding validPassword = Bindings.createBooleanBinding(() -> {
            String password = pwd.getText();
            return password.length() >= 8 && password.chars().anyMatch(Character::isUpperCase) &&
                    password.chars().anyMatch(Character::isDigit);
        }, pwd.textProperty());

        okBtn.disableProperty().bind(validPassword.not());
    }

    @FXML
    private void okClicked() {
        System.out.print(userId.getText() + " ");
        for (char c : pwd.getText().toCharArray()) {
            System.out.print("*");
        }
        System.out.println();
    }

    @FXML
    private void cancelClicked() {
        userId.setText("");
        pwd.setText("");
    }
}
