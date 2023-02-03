package Cpsc2150.extendedConnectX;

public class GameBoard extends AbsGameBoard implements IGameBoard{

    /**
     * @invariant 3 <= HEIGHT <= 100 AND 3 <= WIDTH <= 100 AND 3 <= numToWin <= HEIGHT AND 3 <= numToWin <= WIDTH
     * @Correspondence r = HEIGHT AND c = WIDTH AND c*r = MAXTURNS AND array is made of size [r][c] AND win = numToWin
     *
     */
    private final int HEIGHT;
    private final int WIDTH;
    private int turnNumber;
    public final int MAXTURNS;
    private final int numToWin;
    private final char[][] array;

    /**
     * Builds a 2D array to use for connectX
     *
     * @param r The height of the array (number of rows)
     * @param c The width of the array (number of columns)
     * @param win The number in a row the user needs to win
     *
     * @pre none
     * @post Creates a GameBoard array of size [w][h] that lets it store BoardPositions
     */
    GameBoard(int c,int r,int win) {
        WIDTH = c;
        HEIGHT = r;
        numToWin = win;
        MAXTURNS = HEIGHT*WIDTH;
        array = new char[c][r];
        for(int i=0;i<HEIGHT;i++){
            for(int j=0;j<WIDTH;j++){
                array[j][i] = ' ';
            }
        }
    }

    @Override
    //returns the size of a column
    public int getNumRows(){
        return HEIGHT;
    }
    @Override
    //returns the size of a row
    public int getNumColumns(){
        return WIDTH;
    }
    @Override
    //returns how many you want in a row
    public int getNumToWin(){
        return numToWin;
    }

    @Override
    public void empty(){
        //go through every position of the board and replace with space
        for(int i=0;i<getNumColumns();i++){
            for(int j=0;j<getNumRows();j++){
                array[i][j] = ' ';
            }
        }
    }

    @Override
    //look at the top of the column for an empty space
    public boolean checkIfFree(int x) {
        return array[x][HEIGHT - 1] == ' ';
    }

    @Override
    public boolean checkForWin(int x, char c) {
        char sign;
        BoardPosition temp = new BoardPosition(x, 0);
        //find the first spot with a token in the column
        for (int i = HEIGHT-1; i >= 0; i--) {
            if (array[x][i] != ' ') {
                temp.y = i;
                break;
            }
        }
        //pass in token with a boardPosition to vert horiz and diag win
        sign = whatsAtPos(temp);
        return checkHorizWin(temp, sign) || checkVertWin(temp, sign) || checkDiagWin(temp, sign);

    }

    @Override
    public boolean checkTie(){
        //if the running turn number is the same as width * height, that means no one won
        return turnNumber == MAXTURNS;
    }

    @Override
    public void placeToken(char c,int x){
        //find the first space in the column from the bottom and replace it with token
        for(int i=0;i<HEIGHT;i++)
        {
            if(array[x][i] == ' ')
            {
                array[x][i] = c;
                turnNumber++;
                break;
            }
        }
    }

    @Override
    public boolean checkHorizWin(BoardPosition a, char c){
        int RunningCount = 1;
        boolean L = true;
        boolean R = true;
        for(int i=1;i<numToWin;i++)
        {
            //check in bounds
            if (a.x - i >= 0) {
                //check equivalence
                if(array[a.x-i][a.y]==c & L){
                    RunningCount++;
                }else {
                    //if the spot isn't the same as c, stop looking left
                    L = false;
                }
            }
            //check in bounds
            if(a.x+i < getNumColumns()) {
                //check equivalence
                if (array[a.x+i][a.y]==c && R){
                        RunningCount++;
                    }else {
                    //if the spot isn't the same as c, stop looking right
                    R = false;
                }
            }
            //if you ever find numToWin in a row, win
            if(RunningCount>=getNumToWin())
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkVertWin(BoardPosition a, char c){
        int runningCount = 1;
        boolean counting = true;
        for(int i=1;i<numToWin;i++)
        {
            //check in bounds
            if(a.y-i >= 0){
                //check equivalence
                if(array[a.x][a.y-i]==c && counting){
                    runningCount++;

                }else{
                    //if the spot isn't c stop looking
                    counting = false;
                }
            }
            //if the count finds enough in a row, win
            if(runningCount == getNumToWin()){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkDiagWin(BoardPosition a, char c){
        int UL_to_DR_RunningCount = 1;
        int UR_to_DL_RunningCount = 1;
        boolean UL = true;
        boolean UR = true;
        boolean DL = true;
        boolean DR = true;
        for(int i=1;i<numToWin;i++){
            //check in bounds
            if(a.x-i >= 0 && a.y - i >= 0){
                //check equivalence
                if(array[a.x-i][a.y-i] == c && DL) {
                    UR_to_DL_RunningCount++;
                    //if not the same, stop looking down left
                }else{
                        DL = false;
                    }
                }
            //check in bounds
            if(a.x+i < getNumColumns() && a.y-i >= 0){
                //check equivalence
                if(array[a.x+i][a.y-i] == c && DR) {
                    UL_to_DR_RunningCount++;
                    //if not the same, stop looking down right
                }else {
                    DR = false;
                }

            }
            //check bounds
            if(a.x-i >= 0 && a.y+i < getNumRows()){
                //check equivalence
                if(array[a.x-i][a.y+i] == c && UL) {
                    UL_to_DR_RunningCount++;
                    //if not the same, stop looking up left
                } else {
                    UL = false;
                }
            }
            //check bounds
            if(a.x+i < getNumColumns() && a.y+i < getNumRows()) {
                //check equivalence
                if(array[a.x+i][a.y+i] == c && UR) {
                    UR_to_DL_RunningCount++;
                    //if not the same, stop looking up right
                } else {
                    UR = false;
                }
            }
            //if either count finds enough in a row, win
            if(UR_to_DL_RunningCount >= numToWin || UL_to_DR_RunningCount >= numToWin){
                return true;
            }
        }
        return false;
    }

    @Override
    //return value of position a
    public char whatsAtPos(BoardPosition a)
    {return array[a.x][a.y];}

    @Override
    //return if a specific player is at position a
    public boolean isPlayerAtPos(BoardPosition a, char c){
        return array[a.x][a.y] == c;
    }
}