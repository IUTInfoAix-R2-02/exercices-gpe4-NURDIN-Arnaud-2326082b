package fr.amu.iut.exercice5;

import javafx.scene.shape.Rectangle;

import java.util.Collection;

public class Obstacle extends Rectangle {

        public Obstacle(double x, double y, double width, double height) {
            super(x, y, width, height);
    }

    boolean collisionMur(Personnage autrePersonnage) {
        return getBoundsInParent().contains(autrePersonnage.getBoundsInParent())
                || autrePersonnage.getBoundsInParent().contains(getBoundsInParent());
    }

}
