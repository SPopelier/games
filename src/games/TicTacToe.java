package games;

import model.Cell;
import model.Game;
import model.GameModel;

public class TicTacToe implements GameModel {

    /////////////////////////ATTRIBUTS/////////////////////////
    private final Game game;

    /////////////////////////CONSTRUCTEUR/////////////////////////
    public TicTacToe() {
        game = new Game(3, 3);
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
