package Room;

public class search {
  public static void main (String[] args) {

    int n = Integer.parseInt(args[0]);
    int r = n;

    int count = Integer.parseInt(args[1]);
    int tries = Integer.parseInt(args[2]);

    int[][] g = new int[n][n];
    int[][] h = new int[n][n];
    int[][][] R = new int[n][n][2];

    for (int i = 0; i < tries; i++) {
      graph1.initGraph(g);
      algorithm.oneFactorisation(g);
      graph1.initGraph(h);
      graph1.initRoomSquare(R);
      algorithm.hillClimbing(g, h, R, count);
    }

  }
}


