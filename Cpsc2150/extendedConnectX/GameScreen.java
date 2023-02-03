package Cpsc2150.extendedConnectX;
import java.util.*;
//Evan Fogerty
public class GameScreen {

    public static void main(String[] args){
        //bool to keep the code looping, input for user columns, arraySigns for toString displaying the board, and setters for later constructors
        boolean Play;
        String input;
        String arraySigns;
        int numColumns,numRows,numWin,numPlayers;

        //first loop taking care of game initialization
        while(true) {
            int player = 1;
            Scanner scanner = new Scanner(System.in);
            Play = true;
            //make number of columns on the board until valid input
            System.out.println("How many columns would you like to play with?");
            numColumns = Integer.parseInt(scanner.nextLine());
            while (numColumns < 3 || numColumns > 100) {
                System.out.println("Please enter a number between 3 and 100");
                System.out.println("How many columns would you like to play with?");
                numColumns = Integer.parseInt(scanner.nextLine());
            }
            //make number of rows on the board until valid input
            System.out.println("How many rows would you like to play with?");
            numRows = Integer.parseInt(scanner.nextLine());
            while (numRows < 3 || numRows > 100) {
                System.out.println("Please enter a number between 3 and 100");
                System.out.println("How many rows would you like to play with?");
                numRows = Integer.parseInt(scanner.nextLine());
            }
            //make winning number in a row until valid input
            System.out.println("How many in a row would you like to win?");
            numWin = Integer.parseInt(scanner.nextLine());
            while (numWin < 3 || numWin > 100) {
                System.out.println("Please enter a number between 3 and the number of rows and columns");
                System.out.println("How many in a row would you like to win?");
                numWin = Integer.parseInt(scanner.nextLine());
            }
            //make number of players until valid input
            System.out.println("How many players would you like to play with?");
            numPlayers = Integer.parseInt(scanner.nextLine());
            while (numPlayers < 2 || numPlayers > 10) {
                System.out.println("Please enter a number between 2 and 10");
                System.out.println("How many players would you like to play with?");
                numPlayers = Integer.parseInt(scanner.nextLine());
            }
            //ask each player for token until valid input
            List<Character> users = new ArrayList<>();
            for (int i = 1; i <= numPlayers; i++) {
                System.out.println("Player " + i + ", what token would you like to use to represent you?");
                char addToList = scanner.nextLine().charAt(0);
                while (users.contains(Character.toUpperCase(addToList))) {
                    System.out.println("Please enter a character that has not yet been used");
                    System.out.println("Player " + i + ", what token would you like to use to represent you?");
                    addToList = scanner.nextLine().charAt(0);
                }
                //adds user characters to the list
                users.add(Character.toUpperCase(addToList));
            }
            //ask fast or memory efficient until valid input
            System.out.println("Would you like a Fast Game(F/f) or a Memory Efficient Game(M/m)?");
            char gameCondition = scanner.nextLine().charAt(0);
            while (Character.toUpperCase(gameCondition) != 'F' && Character.toUpperCase(gameCondition) != 'M') {
                System.out.println("Please enter a valid game state");
                System.out.println("Would you like a Fast Game(F/f) or a Memory Efficient Game(M/m)?");
                gameCondition = scanner.nextLine().charAt(0);
            }
            //play fast if they select fast with a gameBoard
            if (Character.toUpperCase(gameCondition) == 'F') {
                GameBoard gb = new GameBoard(numColumns, numRows, numWin);
                while (Play) {
                    char letter = users.get(player - 1);
                    int column;
                    //display initial board
                    arraySigns = gb.toString();
                    System.out.println(arraySigns);
                    //ask for column placement
                    System.out.println("Player " + letter + ", what column do you want to place your marker in?");
                    input = scanner.nextLine();
                    column = Integer.parseInt(input);
                    //place a token at the specified column and swap player (ignore if column was invalid)
                    if (column >= 0 && column < gb.getNumColumns() && gb.checkIfFree(column)) {
                        gb.placeToken(letter, column);
                        //see if someone won off of that move or if they tied
                        if (gb.checkForWin(column,letter)) {
                            arraySigns = gb.toString();
                            System.out.println(arraySigns);
                            System.out.println("Player " + letter + " Won!\nWould you like to play again? Y/N");
                            char reset = scanner.nextLine().charAt(0);
                            //ask user to play again until valid input
                            while(Character.toUpperCase(reset)!='Y' && Character.toUpperCase(reset)!='N'){
                                System.out.println("Please enter a Y or an N");
                                reset = scanner.nextLine().charAt(0);
                            }
                            if (Character.toUpperCase(reset) == 'Y') {
                                //reset the board
                                gb.empty();
                                Play = false;
                            } else {
                                //end the game (no play again)
                                return;
                            }
                        } else if (gb.checkTie()) {
                            arraySigns = gb.toString();
                            System.out.println(arraySigns);
                            System.out.println("No winner.\nWould you like to play again? Y/N");
                            char reset = scanner.nextLine().charAt(0);
                            //ask user to play again until valid input
                            while(Character.toUpperCase(reset)!='Y' && Character.toUpperCase(reset)!='N'){
                                System.out.println("Please enter a Y or an N");
                                reset = scanner.nextLine().charAt(0);
                            }
                            if (Character.toUpperCase(reset) == 'Y') {
                                //reset the board
                                gb.empty();
                                Play = false;
                            } else {
                                //end the game (no play again)
                                return;
                            }
                        } else {
                            if (player == numPlayers) {
                                player = 1;
                            } else {
                                player++;
                            }
                        }
                    }
                }
            }
            //memory efficient with a GameBoardMem
            else if(Character.toUpperCase(gameCondition) == 'M'){
                GameBoardMem gb = new GameBoardMem(numColumns, numRows, numWin);
                while (Play) {
                    char letter = users.get(player - 1);
                    int column;
                    //display initial board
                    arraySigns = gb.toString();
                    System.out.println(arraySigns);
                    //ask user for column placement
                    System.out.println("Player " + letter + ", what column do you want to place your marker in?");
                    input = scanner.nextLine();
                    column = Integer.parseInt(input);
                    //place a token if the column was valid and swap player (ignore otherwise)
                    if (column >= 0 && column < gb.getNumColumns() && gb.checkIfFree(column)) {
                        gb.placeToken(letter, column);
                        //check for win or tie
                        if (gb.checkForWin(column,letter)) {
                            arraySigns = gb.toString();
                            System.out.println(arraySigns);
                            System.out.println("Player " + letter + " Won!\nWould you like to play again? Y/N");
                            char reset = scanner.nextLine().charAt(0);
                            //ask user if they play again until a valid input
                            while(Character.toUpperCase(reset)!='Y' && Character.toUpperCase(reset)!='N'){
                                System.out.println("Please enter a Y or an N");
                                reset = scanner.nextLine().charAt(0);
                            }
                            if (Character.toUpperCase(reset) == 'Y') {
                                //reset the board
                                gb.empty();
                                Play = false;
                            } else {
                                //end the game (no play again)
                                return;
                            }
                        } else if (gb.checkTie()) {
                            arraySigns = gb.toString();
                            System.out.println(arraySigns);
                            System.out.println("No winner.\nWould you like to play again? Y/N");
                            char reset = scanner.nextLine().charAt(0);
                            //ask user if they play again until a valid input
                            while(Character.toUpperCase(reset)!='Y' && Character.toUpperCase(reset)!='N'){
                                System.out.println("Please enter a Y or an N");
                                reset = scanner.nextLine().charAt(0);
                            }
                            if (Character.toUpperCase(reset) == 'Y') {
                                //reset the board
                                gb.empty();
                                Play = false;
                            } else {
                                //end the game (no play again)
                                return;
                            }
                        } else {
                            if (player == numPlayers) {
                                player = 1;
                            } else {
                                player++;
                            }
                        }
                    }
                }
            }
        }
    }
}