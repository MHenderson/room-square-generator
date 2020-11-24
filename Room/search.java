package Room;

import java.util.Random;

public class search {
  public static void main (String[] args) {

    int n = Integer.parseInt(args[0]);

    int count = Integer.parseInt(args[1]);
    int tries = Integer.parseInt(args[2]);
    int seed = Integer.parseInt(args[3]);

    Random randnum = new Random(seed);

    for (int i = 0; i < tries; i++) {
      graph g = new graph(n, n);
      algorithm.oneFactorisation(g, randnum);
      graph h = new graph(n, n);
      roomSquare R = new roomSquare(n);
      algorithm.hillClimbing(g, h, R, count, randnum);
    }

  }
}


