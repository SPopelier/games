import javax.swing.*;
import java.util.InputMismatchException;


public class TicTacToe {

    //On accède a la classe Menu
    private Menu menu;

    //on accède à la class Board
    public Board board;

//    //on initialise Cell
//    String Cell = null;

    //on accède à la class Player
    public Player player;

    //on construit le board
    public TicTacToe() {
        this.menu = new Menu();
        this.board = new Board();
    }

    //on délègue la fonction display à la class Board
    public void display() {
        this.board.display();
    }

    //méthode pour retourner une paire de valeurs pour désigner une case, et s'assurer que les inputs sont valides
    public int[] getMoveFromPlayer() {

        // On creer une boîte de dialogue
        JOptionPane pane = new JOptionPane();

        int choice = -1;

        //on apel askNumber pour boucler et vérifier la validité du choice
        int col,row;

        // Tant que l'input est invalide
        do {
            col = askNumber(pane, "column");
            row = askNumber(pane, "row");

            // On demande a l'utilisateur
//            String userInput = menu.requestText("Please enter a whole number in " + col + "and " + row + ": ");
//            choice = Integer.parseInt(userInput);
//            menu.displayText("You have chosen " + choice + " .");

            // On valide le choix
//            if (checkNumber(col, row)) {
//                menu.displayText("This box is empty. You have played !");
//            } else {
//                menu.displayText("This box is not empty ! Replay !");
//            }
        } while (!checkNumber(col, row));
        // Fin tant que
        int[] result = new int[2];
        result[0] = col;
        result[1] = row;
        return result;
    }


    //méthode qui boucle sur choice pour vérifier que l'input est valide
    private int askNumber(JOptionPane pane, String label) {
        //String userInput = menu.requestText("Please enter a whole number in "+label+" : ");
        int choice = -1;

        boolean isInvalid = true;
        // While user hasn't entered a valid number
        while (isInvalid) {
            try {
                String userInput = menu.requestText("Please enter a whole number in " + label + " : ");
                choice = Integer.parseInt(userInput);
                menu.displayText("You have chosen " + choice + " .");
                if (choice >= 0 && choice <= 2) {
                    isInvalid = false;
                }

            } catch (InputMismatchException e) {
                menu.requestText("Please enter a number ! ");
            }
        }
        return choice;
    }

    // Verifier si l'emplacement est libre
    private boolean checkNumber(int col, int row) {
        return true;
//        int searchElement = 0;
//        boolean cellIsNotEmpty = true;
//        while (cellIsNotEmpty) { // if dans la boucle c'est ok ?????
//            if (choice == searchElement) {
//                menu.displayText("This box is not empty. You have played !");
//            }
//            if (choice != searchElement) {
//                menu.displayText("This box is empty ! Replay !");
//            }
//        }
//        return choice;
    }
}
