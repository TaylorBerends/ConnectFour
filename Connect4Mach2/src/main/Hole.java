package main;

public class Hole {
    boolean filled;
    int filledBy;
    int row;
    int col;
    Board board;

    //constructor
    public Hole(Board board, int row, int col) {
        filled = false;
        filledBy = 0;
        this.board = board;
        this.row = row;
        this.col = col;
    }

    //function to fill hole
    public void FillHole(int playerNum) {
        filled = true;
        filledBy = playerNum;
    }

    //function that checks for four in a row
    public boolean CheckForFour() {

        int counter;
        
        //check down
        counter = 0;
        for(int r = row; r <= row + 3; r++) {
            if(isValidPos(r, col)) {
                if(board.holeCollection[r][col].filledBy == this.filledBy) {
                    counter++;
                }
            }
        }

        if(counter >= 4) {
            return true;
        }

        //check right
        counter = 0;
        for(int c = col; c <= col + 3; c++) {
            if(isValidPos(row, c)) {
                if(board.holeCollection[row][c].filledBy == this.filledBy) {
                    counter++;
                }
            }
        }

        if(counter >= 4) {
            return true;
        }
        
      //check left
        counter = 0;
        for(int c = col; c >= col - 3; c--) {
            if(isValidPos(row, c)) {
                if(board.holeCollection[row][c].filledBy == this.filledBy) {
                    counter++;
                }
            }
        }

        if(counter >= 4) {
            return true;
        }


        //check down-right diagonal 
        counter = 0;
        for(int r = row, c = col; r <= row + 3 && c <= col + 3; r++, c++) {
            if(isValidPos(r, c)) {
                if(board.holeCollection[r][c].filledBy == this.filledBy) {
                    counter++;
                }
            }
        }

        if(counter >= 4) {
            return true;
        }
        
      //check down-left diagonal 
        counter = 0;
        for(int r = row, c = col; r <= row + 3 && c >= col - 3; r++, c--) {
            if(isValidPos(r, c)) {
                if(board.holeCollection[r][c].filledBy == this.filledBy) {
                    counter++;
                }
            }
        }

        if(counter >= 4) {
            return true;
        }
        
      //check up-right diagonal 
        counter = 0;
        for(int r = row, c = col; r >= row - 3 && c <= col + 3; r--, c++) {
            if(isValidPos(r, c)) {
                if(board.holeCollection[r][c].filledBy == this.filledBy) {
                    counter++;
                }
            }
        }

        if(counter >= 4) {
            return true;
        }
        
      //check up-left diagonal 
        counter = 0;
        for(int r = row, c = col; r >= row - 3 && c >= col - 3; r--, c--) {
            if(isValidPos(r, c)) {
                if(board.holeCollection[r][c].filledBy == this.filledBy) {
                    counter++;
                }
            }
        }

        if(counter >= 4) {
            return true;
        } else {
            return false;
        }
    }

    //valid position checker for the CheckForFour function. Cecks if position is in bounds of array before checking
    //to reduce errors
    boolean isValidPos(int r, int c)
    {
        int n = board.holeCollection.length;
        int m = board.holeCollection[0].length;
        if (r < 0 || c < 0 || r > n - 1 || c > m - 1) {
            return false;
        }
        return true;
    }


}
