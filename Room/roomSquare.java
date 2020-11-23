package Room;

public class roomSquare {

  int[][][] R;

  public roomSquare(int n) {
    R = new int[n][n][2];
    for(int i = 1; i < n; i++) {
      for(int j = 1; j < n; j++) {
        R[i][j][0] = -1;
        R[i][j][1] = -1;
      }
    }
  }

  public void print() {
    for(int i = 1; i < R.length; i++) {
      for(int j = 1; j < R[i].length; j++) {
        System.out.print("|");
        System.out.print(((R[i][j][0] < 0)?" -":String.format("%2d", R[i][j][0])));
        System.out.print(",");
        System.out.print(((R[i][j][1] < 0)?" -":String.format("%2d", R[i][j][1])));
      }
      System.out.println("|");
    }
    System.out.println("");
  }

  public int getLeft(int i, int j) { return(R[i][j][0]); }
  public int getRight(int i, int j) { return(R[i][j][1]); }

  public void setLeft(int i, int j, int k) {
    R[i][j][0] = k;
  }

  public void setRight(int i, int j, int k) {
    R[i][j][1] = k;
  }

}
