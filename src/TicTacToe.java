import javax.swing.*;
import java.security.SecureRandom;
import java.util.Objects;


public class

TicTacToe {

    //instance de la Class Menu pour gérer les interactions
    private final Menu menu;

    //instance de la Class Board pour gérer le GameBoard
    public Board board;

    //instance de la Class Player pour gérer currentPlayer
    public Player player;

    //instance de la class View pour gérer l'affichage
    private TicTacToe view;

    //stockage de la variable optionSybmol
    String optionSymbol;

    //stockage de la variabe type
    String type;

    public boolean started = false;


    //on construit le board(Menu, GameBoard)
    public TicTacToe() {
        this.menu = new Menu();
        this.board = new Board();
        player = new Player(optionSymbol, type);
    }

    // new Player
    Player player1 = new Player(optionSymbol, "humanPlayer");
    Player player2 = new Player(optionSymbol, type);

    //on délègue la fonction display à la class View
    public void display() {
        this.board.display();
    }

    //méthode pour le choix de l'adversaire
    public String choosePlayerType() {
        String[] playerType = {"humanPlayer", "artificialPlayer"};

        String type = (String) JOptionPane.showInputDialog(
                null,
                "Choose your way to play, HumanPlayer or ArtificialPlayer ?",
                "Choose way to play",
                JOptionPane.QUESTION_MESSAGE,
                null,
                playerType,
                playerType[0]);
        if (type != null) {
            JOptionPane.showMessageDialog(
                    null,
                    "Your way to play is " + type + " !",
                    "Choose way to play",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        return type;
    }

    //méthode pour le choix de l'adversaire
    public String choosePlayerTypeOpponent() {
        String[] playerType = {"humanPlayer", "artificialPlayer"};

        String type = (String) JOptionPane.showInputDialog(
                null,
                "Choose your opponent, HumanPlayer or ArtificialPlayer ?",
                "Choose Opponent",
                JOptionPane.QUESTION_MESSAGE,
                null,
                playerType,
                playerType[0]);
        if (type != null) {
            JOptionPane.showMessageDialog(
                    null,
                    "Your opponent is " + type + " !",
                    "Choose Opponent",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        return type;
    }

    //méthode pour le choix du symbol
    public String chooseSymbol() {
        String[] symbols = {"X", "O"};

        String optionSymbol = (String) JOptionPane.showInputDialog(
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

        //variable pour stocker les coordonnées
        int col, row;

        // Tant que l'input est invalide
        do {
            col = askNumber("column");
            row = askNumber("row");

        } while (!checkNumber(col, row));
        // Fin tant que
        int[] result = new int[2];
        result[0] = col;
        result[1] = row;
        return result;
    }

    public int[] getMoveFromArtificialPlayer() {
        final SecureRandom secureRandom = new SecureRandom();
        int [] randomResult = new int[2];
        randomResult[0] = secureRandom.nextInt(3);
        randomResult[1] = secureRandom.nextInt(3);
        menu.displayText("Your opponent has played " + randomResult[0] + " in column and " + " in row " + randomResult[1]);
        return  randomResult;
    }


    //méthode qui boucle sur choice pour vérifier que l'input est valide
    private int askNumber(String label) {
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

    public int[] whoPlayed(Player currentPlayer){
     if (currentPlayer.getPlayerType().equals("humanPlayer")){
         return getMoveFromPlayer();
     } else {
         return getMoveFromArtificialPlayer();
     }
    }

    //récupère si l'emplacement est libre et remplace par optionSymbol
    public void setOwner(Player currentPlayer) {
        int [] movePlayer = whoPlayed(currentPlayer);
        Cell cell = this.board.getCell(movePlayer[0],movePlayer[1]);
        if (cell.isEmpty()) {
            cell.setRepresentation(currentPlayer.getRepresentation());
        } else {
            menu.displayText("This cell is already occupied!");
        }
    }

    public void play() {

        boolean hasEmpty = true;

        //demander le type du player
        type = choosePlayerType();
        if (type == null) {
            menu.displayText("Please choose, Humanplayer or ArtificialPlayer ?");
            return;
        } player1.setPlayerType(type);

        //demander le type de l'adversaire
        type = choosePlayerTypeOpponent();
        if (type == null) {
            menu.displayText("Please choose your opponent, Humanplayer or ArtificialPlayer ?");
            return;
        } player2.setPlayerType(type);


        // demander le symbole une seule fois
        optionSymbol = chooseSymbol();
        if (optionSymbol == null) {
            menu.displayText("Please choose your symbol, X or O ?");
            return;
        } player1.setRepresentation(optionSymbol);


        //si player1 = X alors player2 = O et inversement
        if (player1.getRepresentation().equals("X")) {
            player2.setRepresentation("O");
        } else {
            player2.setRepresentation("X");
        }

        Player currentPlayer = player1;

        while (hasEmpty) {


            //changement de joueur
            if(!started || currentPlayer == player1 && started){
                setOwner(currentPlayer);
                currentPlayer = player2;
                started = true;
            } else {
                setOwner(currentPlayer);
                currentPlayer = player1;
            }

            //affiche le []
            display();
            if (isOver()) {
                hasEmpty = false;
                menu.displayText(currentPlayer.getRepresentation()+" has Won !!");
            } else {
                hasEmpty = board.hasEmptyCell();
            }

        }
        menu.displayText("All cells are now filled!");
    }

    public boolean isOver() {
        return checkCol() || checkRow() || checkDiag1() || checkDiag2();
    }

    //méthode pour vérifier les col
    public boolean checkCol() {
//        boolean isFullCol = false;

        //je parcours les col
        for (int col = 0; col < board.size; col++) {
            //je récupère la valeur de la cell (de type cell)
            Cell cell = this.board.getCell(col, 0);
            //si la cell est vide
            if (!cell.isEmpty()) {
                //je parcours la ligne
                int row = 1;
                //tant que mes deux cases sont identiques
                while (Objects.equals(this.board.getCell(col, row).getRepresentation(), cell.getRepresentation())) {
                    //
                    if (row == board.size - 1) {
                        return true;
                    }
                    //j'incrémente row
                    row++;
                }
            }
        }

        return false;
    }

    //méthode pour vérifier les row
    public boolean checkRow() {

        //je parcours les row
        for (int row = 0; row < board.size; row++) {
            //je récupère la valeur de la cell (de type cell)
            Cell cell = this.board.getCell(0, row);
            //si la cell est vide
            if (!cell.isEmpty()) {
                //je parcours la ligne
                int col = 1;
                //tant que mes deux cases sont identiques
                while (Objects.equals(this.board.getCell(col, row).getRepresentation(), cell.getRepresentation())) {
                    //
                    if (col == board.size - 1) {
                        return true;
                    }
                    //j'incrémente row
                    col++;
                }
            }
        }

        return false;
    }

    //méthode pour vérifier les diagonal
    public boolean checkDiag1() {

        //je récupère la valeur de la cell (de type cell)
        Cell cell = this.board.getCell(0, 0);
        //si la cell est vide
        if (!cell.isEmpty()) {
            int col = 0;
            int row = 0;
            //tant que mes deux cases sont identiques
            while (Objects.equals(this.board.getCell(col, row).getRepresentation(), cell.getRepresentation())) {
                //
                if (col == board.size - 1) {
                    return true;
                }
                //j'incrémente col et row
                col++;
                row++;
            }
        }
        return false;
    }
    //méthode pour vérifier les diagonal
    public boolean checkDiag2() {

        //je récupère la valeur de la cell (de type cell)
        Cell cell = this.board.getCell(0, 0);
        //si la cell est vide
        if (!cell.isEmpty()) {
            int col = 0;
            int row = 2;
            //tant que mes deux cases sont identiques
            while (Objects.equals(this.board.getCell(col, row).getRepresentation(), cell.getRepresentation())) {
                //
                if (col == board.size - 1) {
                    return true;
                }
                col++;
                row--;
            }
        }
        return false;
    }
}
