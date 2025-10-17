package games;

import model.Cell;
import model.Game;
import model.GameModel;

public class Puissance4 implements GameModel {

    /// //////////////////////ATTRIBUTS/////////////////////////
    private final Game game;
    public final int victory;

    /// //////////////////////CONSTRUCTEUR/////////////////////////
    public Puissance4() throws Exception {
        game = new Game(7, 7);
        this.victory = 4;
    }

    /// /////////////////////MÉTHODES/////////////////////////
    ///
    @Override
    public void initialiseBoard() {
        game.initialiseBoard();
    }

    @Override
    public void display() {
        game.display();
    }


    /// /////////////////////GETTER/SETTER/////////////////////////
    public Cell[][] getBoard() {
        return game.getBoard();
    }

    public int[] getBoardSize() {
        return new int[]{7, 7};
    }

    public int getVictory() {
        return victory;
    }
}
