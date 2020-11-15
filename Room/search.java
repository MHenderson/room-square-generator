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
      graph1.oneFactorisation(g);
      graph1.initGraph(h);
      graph1.initRoomSquare(R);
      for (int j = 0; j < count; j++) {
        if (graph1.random(0, 1) == 0) {
          graph1.oh1(g, h, R);
        } else {
          graph1.oh2(g, h, R);
        }
        if (graph1.graphFull(h)) {
          System.err.println();
          System.err.println("Got one. " + j +" iterations required.");
          graph1.printRoomSquare(R);
          break;
        }
      }
    }
  }
}


