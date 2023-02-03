package Cpsc2150.extendedConnectX;

public class BoardPosition {

    /**
     * @invariant 0 <= x <= WIDTH AND 0 <= y <= HEIGHT
     * @Correspondence first = x AND second = y
     *
     */
    int x;
    int y;

    /**
     * BoardPosition represents a single coordinate on the char array
     *
     * @param first the width coordinate on the BoardPosition
     * @param second the height coordinate on the BoardPosition
     *
     * @pre none
     * @post Creates a coordinate within the boundaries of the board
     */
    BoardPosition(int first, int second) {
        x = first;
        y = second;
    }

    /**
     * @pre none
     * @invariants boardPosition coordinates must be in the table
     * @post returns the row of a board position
     */
    int getRow() { return x; }

    /**
     * @pre none
     * @invariants boardPosition coordinates must be in the table
     * @post returns the column of a board position
     */
    int getCol() {
        return y;
    }

    /**
     * @pre none
     * @invariants boardPosition coordinates must be in the table
     * @post returns whether two positions are in the same place
     */
    boolean equals(BoardPosition a, BoardPosition b) {
        return a.x == b.x && a.y == b.y;
    }

}