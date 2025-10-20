package model;

import java.security.SecureRandom;

import games.Gomoku;
import games.Puissance4;
import games.TicTacToe;
import player.ArtificialPlayer;
import view.View;
import player.Player;
import controller.InteractionUtilisateur;

public class Game implements GameModel {

    /// //////////////////////ATTRIBUTS/////////////////////////
    protected int col;
    protected int row;
    protected Cell[][] board;
    GameModel gameInstance;

    //instance de la Class Menu pour gérer les interactions
    private final InteractionUtilisateur interactionUtilisateur;

    //instance de la class View
    public View view;

    //stockage de la variable optionSybmol
    String optionSymbol;

    //stockage de la variabe type
    String type;

    //stockage de la variable game
    String game;

    /// //////////////////////INITIALISATION/////////////////////////

    //stockage du boolean started
    public boolean started = false;


    /// //////////////////////CONSTRUCTEUR/////////////////////////
    //new board(Menu, GameBoard)
    public Game(int col, int row) throws Exception {
        this.col = col;
        this.row = row;
        this.interactionUtilisateur = new InteractionUtilisateur();
        this.view = new View();

        if (interactionUtilisateur == null) {
            throw new Exception("Error file interactionUtilisateur is not accessible!");
        }
    }

    /// /////////////////////AUTO RÉFÉRENCE/////////////////////////

    //on délègue la fonction display à la class View
    @Override
    public void display() {
        this.view.display(board);
    }

    /// /////////////////////MÉTHODES/////////////////////////
    @Override
    public void initialiseBoard() {
        board = new Cell[col][row];
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                board[i][j] = new Cell();
            }
        }
    }

    //méthode pour retourner une paire de valeurs pour désigner une case, et s'assurer que les inputs sont valides
    public int[] getMoveFromPlayer() {

        //variable pour stocker les coordonnées
        int col, row;


        // Tant que l'input est invalide
        do {
            col = this.interactionUtilisateur.askNumber("column", getBoardSize()[0]);
            row = this.interactionUtilisateur.askNumber("row", getBoardSize()[1]);

        } while (!checkNumber(col, row));
        // Fin tant que
        int[] result = new int[2];
        result[0] = col;
        result[1] = row;
        return result;
    }

    public int[] getMoveFromArtificialPlayer() {
        final SecureRandom secureRandom = new SecureRandom();
        int[] randomResult = new int[2];
        randomResult[0] = secureRandom.nextInt(col);
        randomResult[1] = secureRandom.nextInt(row);
        interactionUtilisateur.displayText("Your opponent has played " + randomResult[0] + " in column and " + " in row " + randomResult[1]);
        return randomResult;
    }

    /**
     * vérifier si la grille est vide
     *
     * @return
     */
    public boolean hasEmptyCell() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    // Verifier si l'emplacement est libre
    private boolean checkNumber(int col, int row) {
        if (col < 0 || col >= this.col || row < 0 || row >= this.row) {
            return false;
        }
        Cell cell = board[col][row];
        return cell.isEmpty();
    }

    public int[] whoPlayed(Player currentPlayer) {
        if (this.game.equals("Puissance4")) {
            if (currentPlayer.getPlayerType().equals("humanPlayer")) {
                return fallingToken(getMoveFromPlayer()[0]);
            } else {
                return fallingToken(getMoveFromArtificialPlayer()[0]);
            }
        } else {
            if (currentPlayer.getPlayerType().equals("humanPlayer")) {
                return getMoveFromPlayer();
            } else {
                return getMoveFromArtificialPlayer();
            }
        }
    }

    //récupère si l'emplacement est libre et remplace par optionSymbol
    public void setOwner(Player currentPlayer) {
        int[] movePlayer = whoPlayed(currentPlayer);
        Cell cell = board[movePlayer[0]][movePlayer[1]];
        if (cell.isEmpty()) {
            cell.setRepresentation(currentPlayer.getRepresentation());
        } else {
            interactionUtilisateur.displayText("This cell is already occupied!");
        }
    }

    public void play() throws Exception {
        // new Player
        Player player1 = new Player(optionSymbol, type);
        Player player2 = new Player(optionSymbol, type);


        //demander quel jeu
        this.game = this.interactionUtilisateur.chooseGame();
        if (this.game == null) {
            interactionUtilisateur.displayText("Please choose your game !");
        }

        //instancier le bon jeu en fonction du game
        //déclaration de gameinstance
        switch (this.game) {
            case "TicTacToe":
                this.gameInstance = new TicTacToe();
                break;
            case "Gomoku":
                this.gameInstance = new Gomoku();
                break;
            case "Puissance4":
                this.gameInstance = new Puissance4();
                break;
            default:
                interactionUtilisateur.displayText("Choose your game !");
                return;
        }

        int[] size = this.gameInstance.getBoardSize();
        this.col = size[0];
        this.row = size[1];

        initialiseBoard();
        display();
        board = getBoard();


        //demander le type du player
        type = this.interactionUtilisateur.choosePlayerType();
        if (type == null) {
            interactionUtilisateur.displayText("Please choose, Humanplayer or ArtificialPlayer ?");
            return;
        }
        player1.setPlayerType(type);

        //demander le type de l'adversaire
        type = this.interactionUtilisateur.choosePlayerTypeOpponent();
        if (type == null) {
            interactionUtilisateur.displayText("Please choose your opponent, Humanplayer or ArtificialPlayer ?");
            return;
        }
        player2.setPlayerType(type);


        // demander le symbole
        optionSymbol = this.interactionUtilisateur.chooseSymbol();
        if (optionSymbol == null) {
            interactionUtilisateur.displayText("Please choose your symbol, X or O ?");
            return;
        }
        player1.setRepresentation(optionSymbol);


        //si player1 = X alors player2 = O et inversement
        if (player1.getRepresentation().equals("X")) {
            player2.setRepresentation("O");
        } else {
            player2.setRepresentation("X");
        }

        Player currentPlayer = player1;
        Player oldCurrentPlayer = player1;

        while (true) {
            if (checkVictory(oldCurrentPlayer) || hasEmptyCell()) {
                System.exit(0);
            }
            //changement de joueur
            if ((!started) || (currentPlayer == player1)) {
                setOwner(currentPlayer);
                currentPlayer = player2;
                oldCurrentPlayer = player1;
                started = true;
            } else {
                setOwner(currentPlayer);
                currentPlayer = player1;
                oldCurrentPlayer = player2;
            }
            display();
        }
    }

    public boolean checkVictory(Player currentPlayer) {
        if (checkCol(currentPlayer) || checkRow(currentPlayer) || checkDiag(currentPlayer)) {
            interactionUtilisateur.displayText(currentPlayer.getRepresentation() + " has Won !!!!");
            return true;
        }
        return false;
    }

    public boolean checkCol(Player currentPlayer) {
        int countVictory = 0;
        boolean result = false;
        int row = 0;
        while (row != board.length) {
            if (!result) {
                for (int col = 0; col < board.length; col++) {
                    //je récupère la valeur de la cell (de type cell)
                    Cell cell = board[row][col];
                    //si la cell est vide
                    if (!cell.isEmpty()) {
                        //je parcours la ligne
                        if (cell.getRepresentation().equals(currentPlayer.getRepresentation())) {
                            countVictory++;
                            if (countVictory == this.gameInstance.getVictory()) {
                                result = true;
                            }
                        }
                    } else {
                        countVictory = 0;
                    }
                }
            }
            countVictory = 0;
            row++;
        }
        return result;
    }

    public boolean checkRow(Player currentPlayer) {
        int countVictory = 0;
        boolean result = false;
        int col = 0;
        while (col != board.length) {
            if (!result) {
                for (int i = 0; i < board.length; i++) {
                    //je récupère la valeur de la cell (de type cell)
                    Cell cell = board[i][col];
                    //si la cell est vide
                    if (!cell.isEmpty()) {
                        //je parcours la ligne
                        if (cell.getRepresentation().equals(currentPlayer.getRepresentation())) {
                            countVictory++;
                            if (countVictory == this.gameInstance.getVictory()) {
                                result = true;
                            }
                        }
                    } else {
                        countVictory = 0;
                    }
                }
            }
            countVictory = 0;
            col++;
        }
        return result;
    }

    public boolean checkDiag(Player currentPlayer) {
        return checkDiagLeftRight(currentPlayer) || checkDiagRightLeft(currentPlayer);
    }

    public boolean checkDiagLeftRight(Player currentPlayer) {
        int countVictory = 0;
        boolean result = false;
        int verification = 0;
        while (verification != board.length) {
            if (!result) {
                for (int i = 0; i < board.length; i++) {
                    Cell cell = board[i][i];
                    if (cell.getRepresentation().equals(currentPlayer.getRepresentation())) {
                        countVictory++;
                        if (countVictory == this.gameInstance.getVictory()) {
                            result = true;
                        }
                    } else {
                        countVictory = 0;
                    }
                }
            }
            countVictory = 0;
            verification++;
        }
        return result;
    }

    public boolean checkDiagRightLeft(Player currentPlayer) {
        int countVictory = 0;
        boolean result = false;
        int verification = 0;
        int colIndex = board.length - 1;
        while (verification != board.length) {
            if (!result) {
                for (int rowIndex = 0; rowIndex < board.length; rowIndex++) {
                    Cell cell = board[colIndex][rowIndex];
                    if (cell.getRepresentation().equals(currentPlayer.getRepresentation())) {
                        countVictory++;
                        if (countVictory == this.gameInstance.getVictory()) {
                            result = true;
                        }
                    } else {
                        countVictory = 0;
                    }
                    colIndex--;
                }
            }
            countVictory = 0;
            colIndex = board.length - 1;
            verification++;
        }
        return result;
    }

    public int[] fallingToken(int colUser) {
        int[] result = new int[2];
        boolean stopVerification = false;
        for (int rowIndex = (board.length - 1); rowIndex > 0; rowIndex--) {
            if (!stopVerification) {
                Cell cell = board[rowIndex][colUser];
                if (cell.isEmpty()) {
                    stopVerification = true;
                    result = new int[]{colUser, rowIndex};
                }
            }
        }
        return result;
    }

    /// /////////////////////GETTER/SETTER/////////////////////////
    @Override
    public Cell[][] getBoard() {
        return board;
    }

    @Override
    public int[] getBoardSize() {
        return new int[]{this.col, this.row};
    }


    public int getVictory() {
        return 0;
    }

}