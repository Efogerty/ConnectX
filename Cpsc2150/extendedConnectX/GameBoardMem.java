package Cpsc2150.extendedConnectX;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class GameBoardMem extends AbsGameBoard implements IGameBoard{
    /**
     * @invariant 3 <= HEIGHT <= 100 AND 3 <= WIDTH <= 100 AND 3 <= numToWin <= HEIGHT AND 3 <= numToWin <= WIDTH
     * @Correspondence c = WIDTH AND r = HEIGHT AND win = numToWin AND MAXTURNS = c*r
     *
     */
    private final int HEIGHT;
    private final int WIDTH;
    private int turnNumber;
    public final int MAXTURNS;
    private final int numToWin;
    private final Map <Character,List<BoardPosition>> array;

    /**
     * Builds an character to BoardPosition map to use for connectX
     *
     * @param r The height of the array (number of rows)
     * @param c The width of the array (number of columns)
     * @param win The number in a row the user needs to win
     *
     * @pre none
     * @post Creates a GameBoardMem object and allows up to 10 players to have BoardPosition lists to store values they have placed on
     */
    GameBoardMem(int c,int r,int win) {
        WIDTH = c;
        HEIGHT = r;
        numToWin = win;
        turnNumber = 0;
        MAXTURNS = HEIGHT*WIDTH;
        array = new HashMap<>();
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
        //gets rid of all array objects
        array.clear();
    }

    @Override
    //look at the top of the column for an empty space
    public boolean checkIfFree(int x) {
        return whatsAtPos(new BoardPosition(x, HEIGHT - 1)) == ' ';
    }

    @Override
    public boolean checkForWin(int x, char c) {
        BoardPosition temp = new BoardPosition(x, HEIGHT-1);
        //find the first spot with a token in the column
        for (int i = HEIGHT-1; i >= 0; i--) {
            if (whatsAtPos(temp) == ' ') {
                temp.y = i;
            }
        }
        //pass in token with a boardPosition to vert horiz and diag win
        return checkHorizWin(temp, c) || checkVertWin(temp, c) || checkDiagWin(temp, c);

    }

    @Override
    public boolean checkTie(){
        //if the running turn number is the same as width * height, that means no one won
        return turnNumber == MAXTURNS;
    }

    @Override
    public void placeToken(char c,int x){
        //find the first space in the column from the bottom and replace it with token
        List<BoardPosition> temp = new ArrayList<>();
        array.putIfAbsent(c,temp);
        if(checkIfFree(x)){
            turnNumber++;
            BoardPosition insert = new BoardPosition(x,0);
            while(whatsAtPos(insert)!=' '){
                insert.y++;
            }
            array.get(c).add(insert);
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
                BoardPosition temp = new BoardPosition(a.x-i,a.y);
                //check equivalence
                if(isPlayerAtPos(temp,c) && L){
                    RunningCount++;
                }else {
                    //if the spot isn't the same as c, stop looking left
                    L = false;
                }
            }
            //check in bounds
            if(a.x+i < getNumColumns()) {
                BoardPosition temp2 = new BoardPosition(a.x+i,a.y);
                //check equivalence
                if (isPlayerAtPos(temp2,c) && R){
                    RunningCount++;
                }else {
                    //if the spot isn't the same as c, stop looking right
                    R = false;
                }
            }

            //if you ever find numToWin in a row, win
            if(RunningCount>=numToWin)
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
                BoardPosition temp = new BoardPosition(a.x,a.y-i);
                if(isPlayerAtPos(temp,c) && counting){
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
                BoardPosition temp = new BoardPosition(a.x-i,a.y-i);
                //check equivalence
                if(isPlayerAtPos(temp,c) && DL) {
                    UR_to_DL_RunningCount++;

                    //if not the same, stop looking down left
                }else{
                    DL = false;
                }
            }
            //check in bounds
            if(a.x+i < getNumColumns() && a.y-i >= 0){
                BoardPosition temp = new BoardPosition(a.x+i,a.y-i);
                //check equivalence
                if(isPlayerAtPos(temp,c) && DR) {
                    UL_to_DR_RunningCount++;
                    //if not the same, stop looking down right
                }else {
                    DR = false;
                }

            }
            //check bounds
            if(a.x-i >= 0 && a.y+i < getNumRows()){
                BoardPosition temp = new BoardPosition(a.x-i,a.y+i);
                //check equivalence
                if(isPlayerAtPos(temp,c) && UL) {
                    UL_to_DR_RunningCount++;
                    //if not the same, stop looking up left
                } else {
                    UL = false;
                }
            }
            //check bounds
            if(a.x+i < getNumColumns() && a.y+i < getNumRows()) {
                BoardPosition temp = new BoardPosition(a.x+i,a.y+i);
                //check equivalence
                if(isPlayerAtPos(temp,c) && UR) {
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
    public char whatsAtPos(BoardPosition a){
        char characterSign = ' ';
        for(Character key: array.keySet()){
            for(int i=0;i<array.get(key).size();i++){
                if(array.get(key).get(i).x == a.x && array.get(key).get(i).y == a.y){
                    characterSign = key;
                }
            }
        }
        return characterSign;
    }

    @Override
    //return if a specific player is at position a
    public boolean isPlayerAtPos(BoardPosition a, char c){
        return whatsAtPos(a) == c;
    }
}