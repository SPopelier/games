//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.display();
        //mettre dans une méthode play dans tictactoe pour que les méthodes bouclent jusqu'a ce que le [] soit plein
        //utiliser ce que renvoie chooseSymbol (le stocker pour l'utiliser)
        ticTacToe.ChooseSymbol();
        int[] choice = ticTacToe.getMoveFromPlayer(); // int[] choice pour stocker le resultat de la méthode getMoveFromPlayer
        ticTacToe.setOwner(choice[0],choice[1],"X");
        ticTacToe.display();
    }
}