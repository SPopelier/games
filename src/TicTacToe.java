import javax.swing.*;
import java.util.InputMismatchException;


public class TicTacToe {

    //On accède a la classe Menu
    private Menu menu;

    //on accède à la class Board
    public Board board;

    //on accède à la class Player
    public Player player;

    //on construit le board
    public TicTacToe() {
        this.menu = new Menu();
        this.board = new Board();
        player= new Player();
    }

    //on délègue la fonction display à la class Board
    public void display() {
        this.board.display();
    }

    public String ChooseSymbol() {
        String[] symbols = {"X", "O"};

        String optionSymbol = (String)JOptionPane.showInputDialog(
                null,
                "Choose your symbol, X or O ?",
                "Choose Symbol",
                JOptionPane.QUESTION_MESSAGE,
                null,
                symbols,
                symbols[0]);
        if (optionSymbol != null) {
            JOptionPane.showMessageDialog(
                    null,
                    "Your symbol is " + optionSymbol,
                    "Choose Symbol",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        return optionSymbol;
    }
    //méthode pour retourner une paire de valeurs pour désigner une case, et s'assurer que les inputs sont valides
    public int[] getMoveFromPlayer() {

        // On creer une boîte de dialogue
        JOptionPane pane = new JOptionPane();

        //on apel askNumber pour boucler et vérifier la validité du choice
        int col,row;

        // Tant que l'input est invalide
        do {
            col = askNumber(pane, "column");
            row = askNumber(pane, "row");

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
                } else {
                    menu.showError("Please enter a number between 0 and 2");
                }

            } catch (NumberFormatException e) {
                menu.showError("Please enter a number ! ");
            }
        }
        return choice;
    }

    // Verifier si l'emplacement est libre
    private boolean checkNumber(int col, int row) {
        Cell cell = this.board.getCell(col, row);
        return cell.isEmpty();
    }

    //récupère si l'emplacement est libre et remplace par optionSymbol
    private void setOwner(int col, int row, String currentPlayer) {

    }
}
