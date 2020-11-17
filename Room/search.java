package Room;

import java.util.Random;

public class search {
  public static void main (String[] args) {

    int n = Integer.parseInt(args[0]);
    int r = n;

    int count = Integer.parseInt(args[1]);
    int tries = Integer.parseInt(args[2]);

    int[][] g = new int[n][n];
    int[][] h = new int[n][n];
    int[][][] R = new int[n][n][2];

    Random randnum = new Random(42);

    for (int i = 0; i < tries; i++) {
      graph.init(g);
      algorithm.oneFactorisation(g, randnum);
      graph.init(h);
      square.init(R);
      algorithm.hillClimbing(g, h, R, count, randnum);
    }

  }
}


