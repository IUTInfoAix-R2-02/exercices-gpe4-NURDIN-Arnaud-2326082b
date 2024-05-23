package fr.amu.iut.exercice9;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CustomButton extends Parent {

    private ImageView image;
    private Label label;

    public CustomButton() {
        StackPane st = new StackPane();
        image = new ImageView(new Image(getClass().getResourceAsStream("/exercice9/Rond.png")));
        label = new Label("Clic");
        label.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
        label.setTextFill(Color.ORANGERED);

        st.getChildren().addAll(image, label);
        this.getChildren().add(st);
    }
}