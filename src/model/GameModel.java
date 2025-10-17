package model;

public interface GameModel {

    ////////////////////////MÉTHODES/////////////////////////
    void initialiseBoard();
    void display();


    ////////////////////////GETTER/SETTER/////////////////////////
    Cell[][] getBoard();
    int[] getBoardSize();
    int getVictory();

}
