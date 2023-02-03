package Cpsc2150.extendedConnectX;

public interface IGameBoard {

    int getNumRows();
    int getNumColumns();
    int getNumToWin();
    void empty();

    /**
     *finds if you can place in a column
     *
     * @param x number of column last placed
     *
     * @return true if there are open spots in a column
     *
     * @pre none
     * @post tells user if the column has an open position
     */
    boolean checkIfFree(int x);

    /**
     *checks if you find numToWin in a row on the last placed tile
     *
     * @param x number of column of last placed
     * @param c current user token
     *
     * @return true if someone won
     *
     * @pre none
     * @post tells user if the last placed token won the game
     */
    boolean checkForWin(int x, char c);

    /**
     *checks if the board is full
     *
     * @return true if board is full
     *
     * @pre none
     * @post tells the user if the board is full without a winning pattern
     */
    boolean checkTie();

    /**
     *fills in a spot on the board
     *
     * @param c the player's symbol
     * @param x the number column placed
     *
     * @pre none
     * @post inserts a player's token into the table
     */
    void placeToken(char c,int x);

    /**
     *checks if you find numToWin in a row looking left and right
     *
     * @param a gives a coordinate to find a win from
     * @param c compares the player's token symbol with a passed char token
     *
     * @return true if U->D finds a win
     *
     * @pre none
     * @post tells the user(s) whether a player won or not
     */
    boolean checkHorizWin(BoardPosition a, char c);

    /**
     *checks if you find numToWin in a row looking up and down
     *
     * @param a gives a coordinate to find a win from
     * @param c compares the player's token symbol with a passed char token
     *
     * @return true if L->R finds a win
     *
     * @pre none
     * @post tells the user(s) whether a player won or not
     */
    boolean checkVertWin(BoardPosition a, char c);

    /**
     *checks if you find numToWin in a row up left to down right, and down left to up right
     *
     * @param a gives a coordinate to find a win from
     * @param c compares the player's token symbol with a passed char token
     *
     * @return true if UL->DR or UR->DL finds a win
     *
     * @pre none
     * @post tells the user(s) whether a player won or not
     */
    boolean checkDiagWin(BoardPosition a, char c);

    /**
     *finds what character is at a coordinate
     *
     * @param a looks at the coordinates on the map
     *
     * @return the sign on a coordinate
     *
     * @pre none
     * @post tells the user the character at coordinates a
     */
    char whatsAtPos(BoardPosition a);

    /**
     *tells if someone is already at a space
     *
     * @param a looks at the coordinates on the map
     * @param c compares the char at the given coordinates to the passed in char c
     *
     * @return true if someone is already at a spot
     *
     * @pre none
     * @post tells the user if a specific player with symbol c is at boardPosition a
     */
    boolean isPlayerAtPos(BoardPosition a, char c);

    /**
     *turns the board into a string
     *
     * @return the indexes of every position on the board as a string
     *
     * @pre none
     * @post  Gives the user a string with a table
     */
    String toString();
}