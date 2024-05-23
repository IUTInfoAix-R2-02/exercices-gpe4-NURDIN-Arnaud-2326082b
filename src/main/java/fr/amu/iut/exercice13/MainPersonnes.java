package fr.amu.iut.exercice13;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class MainPersonnes {

    private static ObservableList<Personne> lesPersonnes;

    private static ListChangeListener<Personne> unChangementListener;

    public static void main(String[] args) {
        lesPersonnes = FXCollections.observableArrayList(personne -> new javafx.beans.Observable[] {personne.ageProperty()});

        unChangementListener = change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    for (Personne p : change.getAddedSubList()) {
                        System.out.println(p.getNom() + " a été ajouté.");
                    }
                }
                if (change.wasRemoved()) {
                    for (Personne p : change.getRemoved()) {
                        System.out.println(p.getNom() + " a été supprimé.");
                    }
                }
                if (change.wasUpdated()) {
                    for (Personne p : lesPersonnes) {
                        System.out.println(p.getNom() + " a maintenant " + p.getAge() + " ans.");
                    }
                }
            }
        };

        lesPersonnes.addListener(unChangementListener);

      //  question1();
        //question2();
        //question3();
        question5();
    }

    public static void question1() {
        Personne pierre = new Personne("Pierre", 20);
        Personne paul = new Personne("Paul", 40);
        Personne jacques = new Personne("Jacques", 60);
        lesPersonnes.add(pierre);
        lesPersonnes.add(paul);
        lesPersonnes.add(jacques);
    }

    public static void question2() {
        Personne pierre = new Personne("Pierre", 20);
        Personne paul = new Personne("Paul", 40);
        Personne jacques = new Personne("Jacques", 60);
        lesPersonnes.add(pierre);
        lesPersonnes.add(paul);
        lesPersonnes.add(jacques);
        lesPersonnes.remove(paul);
    }

    public static void question3() {
        Personne pierre = new Personne("Pierre", 20);
        Personne paul = new Personne("Paul", 40);
        Personne jacques = new Personne("Jacques", 60);
        lesPersonnes.add(pierre);
        lesPersonnes.add(paul);
        lesPersonnes.add(jacques);
        paul.setAge(5);
    }

    public static void question5() {
        Personne pierre = new Personne("Pierre", 20);
        Personne paul = new Personne("Paul", 40);
        Personne jacques = new Personne("Jacques", 60);
        lesPersonnes.addAll(pierre, paul, jacques);
        for (Personne p : lesPersonnes) {
            p.setAge(p.getAge() + 10);
        }
        lesPersonnes.removeAll(paul, pierre);
    }
}
