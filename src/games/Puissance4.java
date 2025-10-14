package games;

import model.Cell;
import model.Game;
import model.GameModel;

public class Puissance4 implements GameModel {

    /////////////////////////ATTRIBUTS/////////////////////////
    private final Game game;

    /////////////////////////CONSTRUCTEUR/////////////////////////
    public Puissance4() {
        game = new Game(7, 6);
    }

    ////////////////////////MÉTHODES/////////////////////////
    @Override
    public void initialiseBoard() {
        game.initialiseBoard();
    }

    @Override
    public void display() {
        game.display();
    }

    ////////////////////////GETTER/SETTER/////////////////////////
    public Cell[][] getBoard() {
        return game.getBoard();
    }
}
