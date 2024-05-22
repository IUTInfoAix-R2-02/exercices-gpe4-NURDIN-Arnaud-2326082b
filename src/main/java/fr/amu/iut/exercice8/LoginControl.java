package fr.amu.iut.exercice8;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LoginControl extends GridPane {

    @FXML
    private TextField userId;

    @FXML
    private PasswordField pwd;

    @FXML
    private Label messageLabel;

    @FXML
    private void okClicked() {
        String user = userId.getText();
        String password = pwd.getText();

        if (user.isEmpty() || password.isEmpty()) {
            messageLabel.setText("User ID and password cannot be empty.");
        } else {
            // Simulate authentication logic
            if (user.equals("admin") && password.equals("admin")) {
                messageLabel.setText("Login successful!");
            } else {
                messageLabel.setText("Invalid User ID or password.");
            }
        }
    }

    @FXML
    private void cancelClicked() {
        userId.clear();
        pwd.clear();
        messageLabel.setText("");
    }
}