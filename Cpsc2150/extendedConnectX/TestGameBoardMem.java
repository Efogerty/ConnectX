package Cpsc2150.extendedConnectX;
import org.junit.Test;
import static org.junit.Assert.*;
//Evan Fogerty
public class TestGameBoardMem {
    private IGameBoard IGBFactory(int width, int height, int numToWin){return new GameBoardMem(width,height,numToWin);}

    private String ConvertArray(Character[][] array){
        StringBuilder board = new StringBuilder();

        //display |0|1|2|3|4|5|6...numColumns
        for(int i=0;i<array.length;i++){
            //adds a space for single digit numbers so they're the same size as double digit numbers
            if(i<10) {
                board.append("| ").append(i);
            }
            else{
                //displays double digit numbers in the same space as single digit numbers for correct formatting
                board.append("|").append(i);
            }
        }
        //add in a new line at the end of a row, and add token at each position
        for(int i=array[0].length-1;i>=0;i--){
            board.append("|\n");
            for(Character[] characters : array){
                board.append("|").append(characters[i]).append(" ");

            }
        }
        board.append("|");
        return board.toString();
    }

    @Test
    public void testConstructor_height_and_width_equal(){
        int width = 5;
        int height = 5;
        int numberToWin = 3;
        Character[][] array = new Character[width][height];
        for(int i=0;i<array[0].length;i++){
            for(int j=0;j<array.length;j++){
                array[j][i] = ' ';
            }
        }
        IGameBoard board = IGBFactory(width,height,numberToWin);
        assertEquals(board.toString(),ConvertArray(array));
    }

    @Test
    public void testConstructor_height_greater_than_width(){
        int width = 3;
        int height = 5;
        int numberToWin = 3;
        Character[][] array = new Character[width][height];
        for(int i=0;i<array[0].length;i++){
            for(int j=0;j<array.length;j++){
                array[j][i] = ' ';
            }
        }
        IGameBoard board = IGBFactory(width,height,numberToWin);
        assertEquals(board.toString(),ConvertArray(array));
    }

    @Test
    public void testConstructor_height_less_than_width(){
        int width = 5;
        int height = 3;
        int numberToWin = 3;
        Character[][] array = new Character[width][height];
        for(int i=0;i<array[0].length;i++){
            for(int j=0;j<array.length;j++){
                array[j][i] = ' ';
            }
        }
        IGameBoard board = IGBFactory(width,height,numberToWin);
        assertEquals(board.toString(),ConvertArray(array));
    }

    @Test
    public void testCheckIfFree_empty_column(){
        int width = 8;
        int height = 6;
        int numberToWin = 4;
        Character[][] array = new Character[width][height];
        for(int i=0;i<array[0].length;i++){
            for(int j=0;j<array.length;j++){
                array[j][i] = ' ';
            }
        }
        IGameBoard board = IGBFactory(width,height,numberToWin);
        assertTrue(board.checkIfFree(0));
    }

    @Test
    public void testCheckIfFree_full_column(){
        int width = 8;
        int height = 6;
        int numberToWin = 4;
        Character[][] array = new Character[width][height];
        for(int i=0;i<array[0].length;i++){
            for(int j=0;j<array.length;j++){
                array[j][i] = ' ';
            }
        }
        IGameBoard board = IGBFactory(width,height,numberToWin);
        for(int i=0;i<array[0].length;i++){
            board.placeToken('x',0);
        }
        assertFalse(board.checkIfFree(0));
    }

    @Test
    public void testCheckIfFree_top_right_boundary(){
        int width = 8;
        int height = 6;
        int numberToWin = 4;
        Character[][] array = new Character[width][height];
        for(int i=0;i<array[0].length;i++){
            for(int j=0;j<array.length;j++){
                array[j][i] = ' ';
            }
        }
        IGameBoard board = IGBFactory(width,height,numberToWin);
        for(int i=0;i<array[0].length-1;i++){
            board.placeToken('x',width-1);
        }
        assertTrue(board.checkIfFree(height-1));
    }

    @Test
    public void testHorizWin_leftmost_boundary(){
        int width = 8;
        int height = 6;
        int numberToWin = 4;
        Character[][] array = new Character[width][height];
        BoardPosition temp = new BoardPosition(0,0);
        for(int i=0;i<array[0].length;i++){
            for(int j=0;j<array.length;j++){
                array[j][i] = ' ';
            }
        }
        IGameBoard board = IGBFactory(width,height,numberToWin);
        for(int i=numberToWin;i>=0;i--){
            board.placeToken('x',i);
        }
        assertTrue(board.checkHorizWin(temp,'x'));
    }

    @Test
    public void testHorizWin_rightmost_boundary(){
        int width = 8;
        int height = 6;
        int numberToWin = 4;
        BoardPosition temp = new BoardPosition(7,0);
        IGameBoard board = IGBFactory(width,height,numberToWin);
        for(int i=width-1;i>width-(numberToWin+1);i--){
            board.placeToken('x',i);
        }
        assertTrue(board.checkHorizWin(temp,'x'));
    }

    @Test
    public void testHorizWin_break_on_other_inputs(){
        int width = 8;
        int height = 6;
        int numberToWin = 4;
        Character[][] array = new Character[width][height];
        BoardPosition temp = new BoardPosition(3,0);
        for(int i=0;i<array[0].length;i++){
            for(int j=0;j<array.length;j++){
                array[j][i] = ' ';
            }
        }
        IGameBoard board = IGBFactory(width,height,numberToWin);
        for(int i=1;i<numberToWin+2;i++){
            if(i!= 4){
                board.placeToken('x',i);
            }else{
                board.placeToken('o',i);
            }
        }
        assertFalse(board.checkHorizWin(temp,'x'));
    }

    @Test
    public void testHorizWin_middle_win_placement(){
        int width = 8;
        int height = 6;
        int numberToWin = 4;
        Character[][] array = new Character[width][height];
        BoardPosition temp = new BoardPosition(3,0);
        for(int i=0;i<array[0].length;i++){
            for(int j=0;j<array.length;j++){
                array[j][i] = ' ';
            }
        }
        IGameBoard board = IGBFactory(width,height,numberToWin);
        for(int i=1;i<numberToWin+1;i++){
            if(i!= 3) {
                board.placeToken('x', i);
            }
        }
        board.placeToken('x', 3);
        assertTrue(board.checkHorizWin(temp,'x'));
    }

    @Test
    public void testVertWin_leftmost_boundary() {
        int width = 8;
        int height = 6;
        int numberToWin = 4;
        Character[][] array = new Character[width][height];
        BoardPosition temp = new BoardPosition(0, height-1);
        for (int i = 0; i < array[0].length; i++) {
            for (int j = 0; j < array.length; j++) {
                array[j][i] = ' ';
            }
        }
        IGameBoard board = IGBFactory(width,height,numberToWin);
        for(int i=0;i<height-numberToWin;i++){
            board.placeToken('x',0);
        }
        for(int i=height-numberToWin;i<height;i++){
            board.placeToken('o',0);
        }
        assertTrue(board.checkVertWin(temp,'o'));
    }

    @Test
    public void testVertWin_rightmost_boundary() {
        int width = 8;
        int height = 6;
        int numberToWin = 4;
        Character[][] array = new Character[width][height];
        BoardPosition temp = new BoardPosition(width-1,height-1);
        for (int i = 0; i < array[0].length; i++) {
            for (int j = 0; j < array.length; j++) {
                array[j][i] = ' ';
            }
        }
        IGameBoard board = IGBFactory(width,height,numberToWin);
        for(int i=0;i<height-numberToWin;i++){
            board.placeToken('x',width-1);
        }
        for(int i=height-numberToWin;i<height;i++){
            board.placeToken('o',width-1);
        }
        assertTrue(board.checkVertWin(temp,'o'));
    }

    @Test
    public void testVertWin_break_on_other_inputs() {
        int width = 8;
        int height = 6;
        int numberToWin = 4;
        Character[][] array = new Character[width][height];
        BoardPosition temp = new BoardPosition(2, 4);
        for (int i = 0; i < array[0].length; i++) {
            for (int j = 0; j < array.length; j++) {
                array[j][i] = ' ';
            }
        }
        IGameBoard board = IGBFactory(width,height,numberToWin);
        board.placeToken('o',2);
        board.placeToken('o',2);
        board.placeToken('x',2);
        board.placeToken('o',2);
        board.placeToken('o',2);
        assertFalse(board.checkVertWin(temp,'o'));
    }

    @Test
    public void testVertWin_win_in_center_of_board() {
        int width = 8;
        int height = 6;
        int numberToWin = 4;
        Character[][] array = new Character[width][height];
        BoardPosition temp = new BoardPosition(2, 4);
        for (int i = 0; i < array[0].length; i++) {
            for (int j = 0; j < array.length; j++) {
                array[j][i] = ' ';
            }
        }
        IGameBoard board = IGBFactory(width,height,numberToWin);
        board.placeToken('x',2);
        board.placeToken('o',2);
        board.placeToken('o',2);
        board.placeToken('o',2);
        board.placeToken('o',2);
        assertTrue(board.checkVertWin(temp,'o'));
    }

    @Test
    public void testDiagWin_leftmost_UL_to_DR(){
        int width = 8;
        int height = 6;
        int numberToWin = 4;
        Character[][] array = new Character[width][height];
        BoardPosition temp = new BoardPosition(0, 3);
        for (int i = 0; i < array[0].length; i++) {
            for (int j = 0; j < array.length; j++) {
                array[j][i] = ' ';
            }
        }
        IGameBoard board = IGBFactory(width,height,numberToWin);
        board.placeToken('x',0);
        board.placeToken('x',0);
        board.placeToken('x',0);
        board.placeToken('x',1);
        board.placeToken('x',1);
        board.placeToken('x',2);
        board.placeToken('o',3);
        board.placeToken('o',2);
        board.placeToken('o',1);
        assertTrue(board.checkDiagWin(temp,'o'));
    }

    @Test
    public void testDiagWin_rightmost_UL_to_DR(){
        int width = 8;
        int height = 6;
        int numberToWin = 4;
        Character[][] array = new Character[width][height];
        BoardPosition temp = new BoardPosition(3,0);
        for (int i = 0; i < array[0].length; i++) {
            for (int j = 0; j < array.length; j++) {
                array[j][i] = ' ';
            }
        }
        IGameBoard board = IGBFactory(width,height,numberToWin);
        board.placeToken('x',0);
        board.placeToken('x',0);
        board.placeToken('x',0);
        board.placeToken('x',1);
        board.placeToken('x',1);
        board.placeToken('x',2);
        board.placeToken('o',0);
        board.placeToken('o',1);
        board.placeToken('o',2);
        assertTrue(board.checkDiagWin(temp,'o'));
    }

    @Test
    public void testDiagWin_leftmost_UR_to_DL(){
        int width = 8;
        int height = 6;
        int numberToWin = 4;
        Character[][] array = new Character[width][height];
        BoardPosition temp = new BoardPosition(width-4,0);
        for (int i = 0; i < array[0].length; i++) {
            for (int j = 0; j < array.length; j++) {
                array[j][i] = ' ';
            }
        }
        IGameBoard board = IGBFactory(width,height,numberToWin);
        board.placeToken('x',width-1);
        board.placeToken('x',width-1);
        board.placeToken('x',width-1);
        board.placeToken('x',width-2);
        board.placeToken('x',width-2);
        board.placeToken('x',width-3);
        board.placeToken('o',width-1);
        board.placeToken('o',width-3);
        board.placeToken('o',width-2);
        assertTrue(board.checkDiagWin(temp,'o'));
    }

    @Test
    public void testDiagWin_rightmost_UR_to_DL(){
        int width = 8;
        int height = 6;
        int numberToWin = 4;
        Character[][] array = new Character[width][height];
        BoardPosition temp = new BoardPosition(width-1,3);
        for (int i = 0; i < array[0].length; i++) {
            for (int j = 0; j < array.length; j++) {
                array[j][i] = ' ';
            }
        }
        IGameBoard board = IGBFactory(width,height,numberToWin);
        board.placeToken('x',width-1);
        board.placeToken('x',width-1);
        board.placeToken('x',width-1);
        board.placeToken('x',width-2);
        board.placeToken('x',width-2);
        board.placeToken('x',width-3);
        board.placeToken('o',width-4);
        board.placeToken('o',width-3);
        board.placeToken('o',width-2);
        assertTrue(board.checkDiagWin(temp,'o'));
    }

    @Test
    public void testDiagWin_middle_win_UL_to_DR(){
        int width = 8;
        int height = 6;
        int numberToWin = 4;
        Character[][] array = new Character[width][height];
        BoardPosition temp = new BoardPosition(2,1);
        for (int i = 0; i < array[0].length; i++) {
            for (int j = 0; j < array.length; j++) {
                array[j][i] = ' ';
            }
        }
        IGameBoard board = IGBFactory(width,height,numberToWin);
        board.placeToken('x',0);
        board.placeToken('x',0);
        board.placeToken('x',0);
        board.placeToken('x',1);
        board.placeToken('x',1);
        board.placeToken('x',2);
        board.placeToken('o',0);
        board.placeToken('o',1);
        board.placeToken('o',3);

        assertTrue(board.checkDiagWin(temp,'o'));
    }

    @Test
    public void testDiagWin_middle_win_UR_to_DL(){
        int width = 8;
        int height = 6;
        int numberToWin = 4;
        Character[][] array = new Character[width][height];
        BoardPosition temp = new BoardPosition(width-2,2);
        for (int i = 0; i < array[0].length; i++) {
            for (int j = 0; j < array.length; j++) {
                array[j][i] = ' ';
            }
        }
        IGameBoard board = IGBFactory(width,height,numberToWin);
        board.placeToken('x',width-1);
        board.placeToken('x',width-1);
        board.placeToken('x',width-1);
        board.placeToken('x',width-2);
        board.placeToken('x',width-2);
        board.placeToken('x',width-3);
        board.placeToken('o',width-4);
        board.placeToken('o',width-3);
        board.placeToken('o',width-1);
        assertTrue(board.checkDiagWin(temp,'o'));
    }

    @Test
    public void testDiagWin_break_on_other_inputs(){
        int width = 8;
        int height = 6;
        int numberToWin = 4;
        Character[][] array = new Character[width][height];
        BoardPosition temp = new BoardPosition(1,3);
        for (int i = 0; i < array[0].length; i++) {
            for (int j = 0; j < array.length; j++) {
                array[j][i] = ' ';
            }
        }
        IGameBoard board = IGBFactory(width,height,numberToWin);
        board.placeToken('x',0);
        board.placeToken('x',0);
        board.placeToken('x',0);
        board.placeToken('x',0);
        board.placeToken('x',1);
        board.placeToken('x',1);
        board.placeToken('x',1);
        board.placeToken('x',2);
        board.placeToken('x',2);
        board.placeToken('x',3);
        board.placeToken('o',0);
        board.placeToken('o',1);
        board.placeToken('x',2);
        board.placeToken('o',3);
        board.placeToken('o',4);
        assertFalse(board.checkDiagWin(temp,'o'));
    }

    @Test
    public void testCheckTie_final_place_top_right() {
        int width = 8;
        int height = 6;
        int numberToWin = 4;
        IGameBoard board = IGBFactory(width, height, numberToWin);
        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                board.placeToken('x',i);
            }
        }
        assertTrue(board.checkTie());
    }

    @Test
    public void testCheckTie_final_place_top_left() {
        int width = 8;
        int height = 6;
        int numberToWin = 4;
        IGameBoard board = IGBFactory(width, height, numberToWin);
        for(int i=width-1;i>=0;i--){
            for(int j=height-1;j>=0;j--){
                board.placeToken('x',i);
            }
        }
        assertTrue(board.checkTie());
    }

    @Test
    public void testCheckTie_final_place_in_between() {
        int width = 8;
        int height = 6;
        int numberToWin = 4;
        IGameBoard board = IGBFactory(width, height, numberToWin);
        for(int i=width-1;i>=0;i--){
            for(int j=height-1;j>=0;j--){
                if(i!=3) {
                    board.placeToken('x', i);
                }
            }
        }
        board.placeToken('x', 3);
        board.placeToken('x', 3);
        board.placeToken('x', 3);
        board.placeToken('x', 3);
        board.placeToken('x', 3);
        board.placeToken('x', 3);
        assertTrue(board.checkTie());
    }

    @Test
    public void testCheckTie_empty_board() {
        int width = 8;
        int height = 6;
        int numberToWin = 4;
        Character[][] array = new Character[width][height];
        for (int i = 0; i < array[0].length; i++) {
            for (int j = 0; j < array.length; j++) {
                array[j][i] = ' ';
            }
        }
        IGameBoard board = IGBFactory(width, height, numberToWin);
        assertFalse(board.checkTie());
    }

    @Test
    public void testWhatsAtPos_empty_space_empty_board(){
        int width = 8;
        int height = 6;
        int numberToWin = 4;
        BoardPosition temp = new BoardPosition(0,0);
        IGameBoard board = IGBFactory(width,height,numberToWin);
        assertEquals(' ', board.whatsAtPos(temp));
    }

    @Test
    public void testWhatsAtPos_empty_space_partly_filled_board(){
        int width = 8;
        int height = 6;
        int numberToWin = 4;
        BoardPosition temp = new BoardPosition(0,4);
        IGameBoard board = IGBFactory(width,height,numberToWin);
        for(int i=0;i<height/2;i++){
            for(int j=0;j<width;j++){
                board.placeToken('x',j);
            }
        }

        assertEquals(' ', board.whatsAtPos(temp));
    }

    @Test
    public void testWhatsAtPos_filled_space_partly_filled_board(){
        int width = 8;
        int height = 6;
        int numberToWin = 4;
        BoardPosition temp = new BoardPosition(0,2);
        IGameBoard board = IGBFactory(width,height,numberToWin);
        for(int i=0;i<height/2;i++){
            for(int j=0;j<width;j++){
                board.placeToken('x',j);
            }
        }
        assertEquals('x', board.whatsAtPos(temp));
    }

    @Test
    public void testWhatsAtPos_filled_space_top_right_boundary(){
        int width = 8;
        int height = 6;
        int numberToWin = 4;
        BoardPosition temp = new BoardPosition(width-1,height-1);
        IGameBoard board = IGBFactory(width,height,numberToWin);
        for(int i=0;i<height;i++) {
            board.placeToken('x', width - 1);
        }
        assertEquals('x', board.whatsAtPos(temp));
    }

    @Test
    public void testWhatsAtPos_filled_space_top_left_boundary(){
        int width = 8;
        int height = 6;
        int numberToWin = 4;
        BoardPosition temp = new BoardPosition(0,height-1);
        IGameBoard board = IGBFactory(width,height,numberToWin);
        for(int i=0;i<height;i++){
            board.placeToken('x',0);
        }
        assertEquals('x', board.whatsAtPos(temp));
    }

    @Test
    public void testIsPlayerAtPos_return_false_for_nonmatching(){
        int width = 8;
        int height = 6;
        int numberToWin = 4;
        BoardPosition temp = new BoardPosition(0,0);
        IGameBoard board = IGBFactory(width,height,numberToWin);
        board.placeToken('x',0);
        assertFalse(board.isPlayerAtPos(temp,'o'));
    }

    @Test
    public void testIsPlayerAtPos_return_true_for_matching(){
        int width = 8;
        int height = 6;
        int numberToWin = 4;
        BoardPosition temp = new BoardPosition(0,0);
        IGameBoard board = IGBFactory(width,height,numberToWin);
        board.placeToken('x',0);
        assertTrue(board.isPlayerAtPos(temp,'x'));
    }

    @Test
    public void testIsPlayerAtPos_check_capitalization(){
        int width = 8;
        int height = 6;
        int numberToWin = 4;
        BoardPosition temp = new BoardPosition(0,0);
        IGameBoard board = IGBFactory(width,height,numberToWin);
        board.placeToken('X',0);
        assertFalse(board.isPlayerAtPos(temp,'x'));
    }

    @Test
    public void testIsPlayerAtPos_match_with_nonletter_character(){
        int width = 8;
        int height = 6;
        int numberToWin = 4;
        BoardPosition temp = new BoardPosition(0,0);
        IGameBoard board = IGBFactory(width,height,numberToWin);
        board.placeToken('1',0);
        assertTrue(board.isPlayerAtPos(temp,'1'));
    }

    @Test
    public void testIsPlayerAtPos_check_empty_space(){
        int width = 8;
        int height = 6;
        int numberToWin = 4;
        BoardPosition temp = new BoardPosition(0,0);
        IGameBoard board = IGBFactory(width,height,numberToWin);
        assertTrue(board.isPlayerAtPos(temp,' '));
    }

    @Test
    public void testPlaceToken_empty_column(){
        int width = 8;
        int height = 6;
        int numberToWin = 4;
        Character[][] array = new Character[width][height];
        for(int i=0;i<array[0].length;i++){
            for(int j=0;j<array.length;j++){
                array[j][i] = ' ';
            }
        }
        IGameBoard board = IGBFactory(width,height,numberToWin);
        board.placeToken('x',0);
        array[0][0] = 'x';
        assertEquals(ConvertArray(array),board.toString());
    }

    @Test
    public void testPlaceToken_full_column(){
        int width = 8;
        int height = 6;
        int numberToWin = 4;
        Character[][] array = new Character[width][height];
        for(int i=0;i<array[0].length;i++){
            for(int j=0;j<array.length;j++){
                array[j][i] = ' ';
            }
        }
        IGameBoard board = IGBFactory(width,height,numberToWin);
        for(int i=0;i<height;i++){
            board.placeToken('x',3);
            array[3][i]='x';
        }
        board.placeToken('o',3);
        assertEquals(ConvertArray(array),board.toString());
    }

    @Test
    public void testPlaceToken_top_left_boundary(){
        int width = 8;
        int height = 6;
        int numberToWin = 4;
        Character[][] array = new Character[width][height];
        for(int i=0;i<array[0].length;i++){
            for(int j=0;j<array.length;j++){
                array[j][i] = ' ';
            }
        }
        IGameBoard board = IGBFactory(width,height,numberToWin);
        for(int i=0;i<height;i++){
            board.placeToken('x',0);
            array[0][i]='x';
        }
        assertEquals(ConvertArray(array),board.toString());
    }

    @Test
    public void testPlaceToken_top_right_boundary(){
        int width = 8;
        int height = 6;
        int numberToWin = 4;
        Character[][] array = new Character[width][height];
        for(int i=0;i<array[0].length;i++){
            for(int j=0;j<array.length;j++){
                array[j][i] = ' ';
            }
        }
        IGameBoard board = IGBFactory(width,height,numberToWin);
        for(int i=0;i<height;i++){
            board.placeToken('x',width-1);
            array[width-1][i]='x';
        }
        assertEquals(ConvertArray(array),board.toString());
    }

    @Test
    public void testPlaceToken_partially_filled_board(){
        int width = 8;
        int height = 6;
        int numberToWin = 4;
        Character[][] array = new Character[width][height];
        for(int i=0;i<array[0].length;i++){
            for(int j=0;j<array.length;j++){
                array[j][i] = ' ';
            }
        }
        IGameBoard board = IGBFactory(width,height,numberToWin);
        for(int i=0;i<(height/2)+1;i++){
            for(int j=0;j<width;j++){
                if(i%2==0) {
                    board.placeToken('x', j);
                    array[j][i] = 'x';
                }else{
                    board.placeToken('0', j);
                    array[j][i] = '0';
                }
            }
        }
        assertEquals(ConvertArray(array),board.toString());
    }
}