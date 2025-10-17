package model;

public class Cell {

    //Chaque cell commence vide puis X ou O quand player play
    private String representation = " ";

    //méthode principale retourne true si cell est vide
    public boolean isEmpty() {
        return this.representation.equals(" "); }

    public Cell() {};

    //pour mettre X ou O du joueur dans la cell
    public void setRepresentation(String representation) {
        this.representation = representation;
    }

    //retourne la valeur de la cell
    public String getRepresentation() {
        return this.representation;
    }

    //pour afficher la cell
    public String toString() {
        return this.representation;
    }
}
