public class Driver {

  public static void main(String[] args) {

    QueenBoard q = new QueenBoard(8);
    q.removeNull();
    System.out.println(q);
    q.addQueen(2, 3);
    System.out.println(q);
    q.addQueen(4, 3);
    System.out.println(q);
    q.removeQueen(2, 3);
    System.out.println(q);
    q.removeQueen(4, 4);
    System.out.println(q);
    q.addQueen(4, 4);
    System.out.println(q);
  }

}