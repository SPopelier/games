package model;

import java.security.SecureRandom;
import java.util.Objects;

import games.Gomoku;
import games.Puissance4;
import games.TicTacToe;
import view.View;
import model.Cell;
import player.Player;
import controller.InteractionUtilisateur;

public class Game implements GameModel {

    /////////////////////////ATTRIBUTS/////////////////////////
    protected  int col;
    protected int row;
    protected Cell[][] board;

    //instance de la Class Menu pour gérer les interactions
    private final InteractionUtilisateur interactionUtilisateur;

    //instance de la Class Player pour gérer currentPlayer
    public Player player;

    //instance de la class View
    public View view;

    //stockage de la variable optionSybmol
    String optionSymbol;

    //stockage de la variabe type
    String type;

    //stockage de la variable game
    String game;

    /////////////////////////INITIALISATION/////////////////////////

    //stockage du boolean started
    public boolean started = false;

    /////////////////////////CONSTRUCTEUR/////////////////////////
    //new board(Menu, GameBoard)
    public Game(int col, int row) {
        this.col = col;
        this.row = row;
        this.interactionUtilisateur = new InteractionUtilisateur();
        this.view = new View();
    }

    // new Player
    Player player1 = new Player(optionSymbol, type);
    Player player2 = new Player(optionSymbol, type);

    ////////////////////////AUTO RÉFÉRENCE/////////////////////////

    //on délègue la fonction display à la class View
    @Override
    public void display() {
        this.view.display(board);
    }

    ////////////////////////MÉTHODES/////////////////////////
    @Override
    public void initialiseBoard() {
        board =  new Cell[col][row];
        for(int i = 0; i < col; i++) {
            for(int j = 0; j < row; j++) {
                board[i][j] = new Cell();
            }
        }
    };

    //méthode pour retourner une paire de valeurs pour désigner une case, et s'assurer que les inputs sont valides
    public int[] getMoveFromPlayer() {

        //variable pour stocker les coordonnées
        int col, row;


        // Tant que l'input est invalide
        do {
            col = this.interactionUtilisateur.askNumber("column");
            row = this.interactionUtilisateur.askNumber("row");

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
        randomResult[0] = secureRandom.nextInt(col);
        randomResult[1] = secureRandom.nextInt(row);
        interactionUtilisateur.displayText("Your opponent has played " + randomResult[0] + " in column and " + " in row " + randomResult[1]);
        return  randomResult;
    }

    public boolean hasEmptyCell() {
        boolean hasEmpty = false;
        //vérifier si la grille contient encore une cellule vide
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (getCell(i, j).isEmpty()) {
                    hasEmpty = true;
                    break;
                }
                if (hasEmpty) break;
            }
        }
        return hasEmpty;
    }

    // Verifier si l'emplacement est libre
    private boolean checkNumber(int col, int row) {
        if (col < 0 || col >= this.col || row < 0 || row >= this.row) {
            return false;
        }
        Cell cell = board[col][row];
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
        Cell cell = board[movePlayer[0]][movePlayer[1]];
        if (cell.isEmpty()) {
            cell.setRepresentation(currentPlayer.getRepresentation());
        } else {
            interactionUtilisateur.displayText("This cell is already occupied!");
        }
    }

    public void play() {

        boolean hasEmpty = true;

        //demander quel jeu
        this.game = this.interactionUtilisateur.chooseGame();
        if (this.game == null) {
            interactionUtilisateur.displayText("Please choose your game !");
        }

        //instancier le bon jeu en fonction du game
        GameModel gameModel; // variable locale pour gameModel
        if ("TicTacToe".equals(this.game)) {
            gameModel = new TicTacToe();
        } else if ("Gomoku".equals(this.game)) {
            gameModel = new Gomoku();
        } else if ("Puissance4".equals(this.game)) {
            gameModel = new Puissance4();
        } else {
            interactionUtilisateur.displayText("Choosen your game !");
            return;
        }

        this.initialiseBoard();

        //demander le type du player
        type = this.interactionUtilisateur.choosePlayerType();
        if (type == null) {
            interactionUtilisateur.displayText("Please choose, Humanplayer or ArtificialPlayer ?");
            return;
        } player1.setPlayerType(type);

        //demander le type de l'adversaire
        type = this.interactionUtilisateur.choosePlayerTypeOpponent();
        if (type == null) {
            interactionUtilisateur.displayText("Please choose your opponent, Humanplayer or ArtificialPlayer ?");
            return;
        } player2.setPlayerType(type);


        // demander le symbole une seule fois
        optionSymbol = this.interactionUtilisateur.chooseSymbol();
        if (optionSymbol == null) {
            interactionUtilisateur.displayText("Please choose your symbol, X or O ?");
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
            if((!started) || (currentPlayer == player1)){
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
                interactionUtilisateur.displayText(currentPlayer.getRepresentation()+" has Won !!");
            } else {
                hasEmpty = hasEmptyCell();
            }

        }
        interactionUtilisateur.displayText("All cells are now filled!");
    }

    public boolean isOver() {
        return checkCol() || checkRow() || checkDiag1() || checkDiag2();
    }

    //méthode pour vérifier les col
    public boolean checkCol() {
//        boolean isFullCol = false;

        //je parcours les col
        for (int col = 0; col < board.length; col++) {
            //je récupère la valeur de la cell (de type cell)
            Cell cell = board[col][0];
            //si la cell est vide
            if (!cell.isEmpty()) {
                //je parcours la ligne
                int row = 1;
                //tant que mes deux cases sont identiques
                while (Objects.equals(this.board[col][row].getRepresentation(), cell.getRepresentation())) {
                    //
                    if (row == board.length - 1) {
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
        for (int row = 0; row < board.length; row++) {
            //je récupère la valeur de la cell (de type cell)
            Cell cell = this.board[0][row];
            //si la cell est vide
            if (!cell.isEmpty()) {
                //je parcours la ligne
                int col = 1;
                //tant que mes deux cases sont identiques
                while (Objects.equals(this.board[col][row].getRepresentation(), cell.getRepresentation())) {
                    //
                    if (col == board.length - 1) {
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
        Cell cell = this.board[0][0];
        //si la cell est vide
        if (!cell.isEmpty()) {
            int col = 0;
            int row = 0;
            //tant que mes deux cases sont identiques
            while (Objects.equals(this.board[col][row].getRepresentation(), cell.getRepresentation())) {
                //
                if (col == board.length - 1) {
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
        Cell cell = this.board[0][0];
        //si la cell est vide
        if (!cell.isEmpty()) {
            int col = 0;
            int row = 2;
            //tant que mes deux cases sont identiques
            while (Objects.equals(this.board[col][row].getRepresentation(), cell.getRepresentation())) {
                //
                if (col == board.length - 1) {
                    return true;
                }
                col++;
                row--;
            }
        }
        return false;
    }


    ////////////////////////GETTER/SETTER/////////////////////////
    public Cell getCell(int col, int row) {
        return board[col][row];
    }

    @Override
    public Cell[][] getBoard() {
        return board;
    }
}
