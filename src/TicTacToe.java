import javax.swing.*;
import java.util.InputMismatchException;

public class

TicTacToe {

    //instance de la Class Menu pour gérer les interactions
    private Menu menu;

    //instance de la Class Board pour gérer le GameBoard
    public Board board;

    //instance de la Class Player currentPlayer
    public Player player;

    //stockage de la variable optionSybmol
    String optionSymbol;

    //on construit le board(Menu, GameBoard)
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

        //variable pour stocker les coordonnées
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
    public void setOwner(int col, int row, String currentPlayer) {
        Cell cell = this.board.getCell(col, row);
        if (cell.isEmpty()) {
            cell.setRepresentation(currentPlayer);
        } else {
            menu.displayText("This cell is already occupied!");
        }
    }

    public void play() {

        boolean hasEmpty = true;

        // demander le symbole une seule fois
        optionSymbol = ChooseSymbol();
        if (optionSymbol == null) {
            menu.displayText("Please choose your symbol, X or O ?");
            return;
        }

        while(hasEmpty) {
            //demander le coup du joueur
            int[] moveFromPlayer = getMoveFromPlayer();

            if (moveFromPlayer == null) {
                menu.displayText("Please choose your symbol, X or O ?");
            }
            int col = moveFromPlayer[0];
            int row = moveFromPlayer[1];

            //placer le symbol
            setOwner(col, row, optionSymbol);

            //affiche le []
            display();

            //vérifier si la grille contient encore une cellule vide
            hasEmpty = false;
            for (int i = 0; i < board.size; i++) {
                for (int j = 0; i < board.size; j++) {
                    if (board.getCell(i, j).isEmpty()) {
                        hasEmpty = true;
                        break;
                    }
                    if (hasEmpty) break;
                }
                // 5️⃣ si la grille n’est pas pleine, changer le symbole pour le tour suivant
                //if (hasEmpty) {
                //   optionSymbol = optionSymbol.equals("X") ? "O" : "X";
                //}
            }
            menu.displayText("All cells are now filled!");
        }
    }
}
