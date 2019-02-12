import java.util.ArrayList;
public class QueenBoard {
  private int[][]board;
  private ArrayList<String> queens;


  public QueenBoard(int size){
    board = new int[size][size];
    removeNull();
    queens = new ArrayList<String>(); //not needed anymore
  }

  public void removeNull() { 
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

  public boolean removeQueen(int r, int c) {
    if (board[r][c] == -1) {
      board[r][c] = 0;	
      for (int a = (c + 1); a < board.length; a++) {
        board[r][a] = board[r][a] - 1;
      }
      for (int b = (c + 1); b < board.length; b++) {
	if ((r + (b - c)) < board.length) {
          board[r + (b - c)][b] = board[r + (b - c)][b] - 1;
	}
	if ((r - (b - c)) > -1) {
          board[r - (b - c)][b] = board[r - (b - c)][b] - 1;
	}
      }
      return true;
    }
    else {
      return false;
    }
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
	else {
          s += "_ ";
	}
      }
      s += "\n";
    }
    return s;
  }
//base - if num of queens equal size
//if add successfully move on to next column and in space with no x
//if whole row cant add queen go back a row and add queen to next space

  /**
  *@return false when the board is not solveable and leaves the board filled with zeros;

  *        true when the board is solveable, and leaves the board in a solved state

  *@throws IllegalStateException when the board starts with any non-zero value

  */
  private boolean checkZero() {
    for (int a = 0; a < board.length; a++) {
      for (int b = 0; b < board.length; b++) {
        if (board[a][b] != 0) {
          return false;
        }
      }
    }
    return true;
  }

  public boolean solve(){
    if (!checkZero()) {
      throw new IllegalStateException();
    }
    return solveR(0);
  }

//is there a queen in this column already - if yes remove that queen find next avaliable queen space if no then just find next avaliable queen space - not necessary now
  private boolean solveH(int r, int c) { //my attempt
    int hold;
    boolean add;
    if (queens.size() == board.length) {
      return true;
    }
    else {
      hold = findQueen(c);
      add = addQueen(r, c);
      if ((hold != -1) && (hold >= (board.length - 1)) && (c != 0)) {
	return false || solveH(r, c - 1);
      }
      if ((hold != -1) && (hold >= (board.length - 1)) && (c == 0)) {
	return false;
      }
      if ((add == false) && (r >= (board.length - 1)) && (c != 0)) {
	return false || solveH(r, c - 1);
      }
      else if ((add == false) && (r >= (board.length - 1)) && (c == 0)) {
	return false;
      }
      else if (add == false) {
	return false || solveH(r + 1, c);
      }
      if (hold != -1) {
	removeQueen(hold, c);
	queens.remove(queens.size() - 1);
	return false || solveH(hold + 1, c);
      }
      else if (addQueen(r, c)) {
	queens.add(r + "," + c);
	return false || solveH(0, c + 1);
      }
    }
    return false;
  }

  public boolean solveR(int c) { //trying again using k's approach
    if (c >= board.length) {
      return true;
    }
    for (int a = 0; a < board.length; a++) {
      if (addQueen(a, c)) {
	if (solveR(c + 1)) {
	  return true;
	}
	removeQueen(a, c);
      }
    }
    return false;
  }

  private int findQueen(int c) { //not necessary now
    for (int a = 0; a < board.length; a++) {
      if (board[a][c] == -1) {
	return a;
      }
    }
    return -1;
  }

  /**
  *@return the number of solutions found, and leaves the board filled with only 0's
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public int countSolutions(){
    if (!checkZero()) {
      throw new IllegalStateException();
    }
    return countH(0);
  }

  public int countH(int c) {
    if (c >= board.length) {
      return 1;
    }
    else {
    for (int a = 0; a < board.length; a++) {
      if (addQueen(a, c)) {
        if (solveR(c + 1)) {
	  return 1 + countH(c);
	}
        removeQueen(a, c);
      }
    }
    }
    return 0;
  }
}
