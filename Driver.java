public class Driver {

  public static void main(String[] args) {

    QueenBoard q = new QueenBoard(5);
    q.removeNull();
    System.out.println(q);
    q.addQueen(2, 3);
    System.out.println(q);
  }

}