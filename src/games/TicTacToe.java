package games;

import model.Cell;
import model.Game;
import model.GameModel;

public class TicTacToe implements GameModel {

    /////////////////////////ATTRIBUTS/////////////////////////
    private final Game game;
    public final int victory;

    /////////////////////////CONSTRUCTEUR/////////////////////////
    public TicTacToe() throws Exception {
        this.game = new Game(3, 3);
        this.victory = 3;
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
        return new int[]{3, 3};
    }

    @Override
    public int getVictory() {
        return victory;
    }
}
