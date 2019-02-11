public class QueenBoard {
  private int[][]board;


  public QueenBoard(int size){
    board = new int[size][size];
  }

  public void removeNull() { //change these back to private later
    for (int a = 0; a < board.length; a++) {
      for (int b = 0; b < board.length; b++) {
	board[a][b] = 0;
      }
    }
  }

  public boolean addQueen(int r, int c) {
    if (board[r][c] == 0) {
      board[r][c] = -1;	
      for (int a = (c + 1); a < board.length; a++) {
        board[r][a] = board[r][a] + 1;
      }
      for (int b = (c + 1); b < board.length; b++) {
	if ((r + (b - c)) < board.length) {
          board[r + (b - c)][b] = board[r + (b - c)][b] + 1;
	}
	if ((r - (b - c)) > -1) {
          board[r - (b - c)][b] = board[r - (b - c)][b] + 1;
	}
      }
      return true;
    }
    else {
      return false;
    }
  }

  private boolean removeQueen(int r, int c) {
    return true;
  }




  /**
  *@return The output string formatted as follows:
  *All numbers that represent queens are replaced with 'Q'
  *all others are displayed as underscores '_'
  *There are spaces between each symbol:
  *"""_ _ Q _
  *Q _ _ _

  *_ _ _ Q

  *_ Q _ _"""
  *(pythonic string notation for clarity,
  *excludes the character up to the *)
  */
  public String toString(){
    String s = "";
    for (int n = 0; n < board.length; n++) {
      for (int a = 0; a < board[0].length; a++) {
	if (board[n][a] == -1) {
	  s += "Q ";
	}
	else if (board[n][a] == 1) {
	  s += "A ";
	}
	else if (board[n][a] == 2) {
	  s += "B ";
	}
	else {
          s += "_ ";
	}
      }
      s += "\n";
    }
    return s;
  }



  /**
  *@return false when the board is not solveable and leaves the board filled with zeros;

  *        true when the board is solveable, and leaves the board in a solved state

  *@throws IllegalStateException when the board starts with any non-zero value

  */
  //public boolean solve(){}

  /**
  *@return the number of solutions found, and leaves the board filled with only 0's
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  //public int countSolutions(){}

}
