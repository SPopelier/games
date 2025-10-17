package games;

import model.Cell;
import model.Game;
import model.GameModel;

public class Gomoku implements GameModel {

    /////////////////////////ATTRIBUTS/////////////////////////
    private final Game game;
    public final int victory = 5;

    /////////////////////////CONSTRUCTEUR/////////////////////////
    public Gomoku() throws Exception {
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
    public int[] getBoardSize() {
        return new int[]{15, 15};
    }

    public int getVictory() {
        return 0;
    }
}
