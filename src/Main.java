import model.Game;
import games.TicTacToe;
import games.Gomoku;
import games.Puissance4;
import model.GameModel;

public class Main {
    public static void main(String[] args) {
        GameModel game1 = new TicTacToe();
        game1.initialiseBoard();
        game1.display();

        GameModel game2 = new Gomoku();
        game2.initialiseBoard();
        game2.display();

        GameModel game3 = new Puissance4();
        game3.initialiseBoard();
        game3.display();
    }
}