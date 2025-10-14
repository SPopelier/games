package games;

import model.Cell;
import model.Game;
import model.GameModel;

public class Gomoku implements GameModel {

    /////////////////////////ATTRIBUTS/////////////////////////
    private final Game game;

    /////////////////////////CONSTRUCTEUR/////////////////////////
    public Gomoku() {
        game = new Game(15, 15);
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
