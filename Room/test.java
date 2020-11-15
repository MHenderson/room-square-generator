package Room;

public class test {
  //
  static void test_initGraph() {
    int[][] g = new int[3][2];
    graph1.printGraph(g);
    graph1.initGraph(g);
    graph1.printGraph(g);
  }

  static void test_h1() {
    int[][] g = new int[4][4];
    graph1.initGraph(g);
    graph1.printGraph(g);
    graph1.h1(g);
    graph1.printGraph(g);
    graph1.h2(g);
    graph1.printGraph(g);
  }

  static void test_oneFactorisation() {
    int[][] g = new int[4][4];
    graph1.initGraph(g);
    graph1.oneFactorisation(g);
    graph1.printGraph(g);
  }

  static void test_oh1() {
    int n = 4;
    int r = 4;
    int[][] g = new int[n][n];
    int[][] h = new int[n][n];
    int[][][] R = new int[n][n][2];
    graph1.initGraph(g);
    graph1.oneFactorisation(g);
    graph1.initGraph(h);
    graph1.initRoomSquare(R);
    graph1.oh1(g, h, R);
    graph1.printRoomSquare(R);
  }

  static void test_oh2() {
    int n = 4;
    int r = 4;
    int[][] g = new int[n][n];
    int[][] h = new int[n][n];
    int[][][] R = new int[n][n][2];
    graph1.initGraph(g);
    graph1.oneFactorisation(g);
    graph1.initGraph(h);
    graph1.initRoomSquare(R);
    graph1.oh2(g, h, R);
    graph1.printRoomSquare(R);
  }

  static void test_findRoomSquare() {
    int n = 20;
    int r = 20;
    // this is how many times we try the heuristics before stopping
    int count = 2000;
    // this is how many times we restart
    int tries = 100;
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

  public static void main (String[] args) {
    test_initGraph();
    //test_h1();
    //test_oneFactorisation();
    //test_oh1();
    //test_oh2();
    //test_findRoomSquare();
  }

}
