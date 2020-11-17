package Room;

public class square {
  static void init(int[][][] R) {
    for(int i = 1;i < R.length; i++) {
      for(int j = 1; j < R[i].length; j++) {
        R[i][j][0] = -1;
        R[i][j][1] = -1;
      }
    }
  }

  static void print(int[][][] R) {
    for(int i = 1; i < R.length; i++) {
      for(int j = 1; j < R[i].length; j++) {
        System.out.print("|");
        System.out.print(((R[i][j][0] < 0)?"-":String.valueOf(R[i][j][0])));
        System.out.print(",");
        System.out.print(((R[i][j][1] < 0)?"-":String.valueOf(R[i][j][1])));
      }
      System.out.println("|");
    }
  }
}
