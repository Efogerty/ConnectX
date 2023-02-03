package Cpsc2150.extendedConnectX;

public abstract class AbsGameBoard implements IGameBoard{

    /**
     * This method overrides {@link Object#toString()} to provide a string
     * representation for GameBoard objects.
     *
     */
 @Override
    public String toString(){
     StringBuilder board = new StringBuilder();
     BoardPosition temp = new BoardPosition(0,0);
     //display |0|1|2|3|4|5|6...numColumns
     for(int i=0;i<getNumColumns();i++){
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
     for(int i=getNumRows()-1;i>=0;i--){
         board.append("|\n");
         for(int j=0;j<getNumColumns();j++){
             temp.x = j;
             temp.y = i;
             board.append("|").append(whatsAtPos(temp)).append(" ");
         }
     }
     board.append("|");
        return board.toString();
    }
}