package Room;

public class test {
  //
  static void test_initGraph() {
    int[][] g = new int[3][2];
    Graph.printGraph(g);
    Graph.initGraph(g);
    Graph.printGraph(g);
  }

  static void test_h1() {
    int[][] g = new int[4][4];
    Graph.initGraph(g);
    Graph.printGraph(g);
    algorithm.h1(g);
    Graph.printGraph(g);
    algorithm.h2(g);
    Graph.printGraph(g);
  }

  static void test_oneFactorisation() {
    int[][] g = new int[4][4];
    Graph.initGraph(g);
    algorithm.oneFactorisation(g);
    Graph.printGraph(g);
  }

  static void test_oh1() {
    int n = 4;
    int r = 4;
    int[][] g = new int[n][n];
    int[][] h = new int[n][n];
    int[][][] R = new int[n][n][2];
    Graph.initGraph(g);
    algorithm.oneFactorisation(g);
    Graph.initGraph(h);
    algorithm.initRoomSquare(R);
    algorithm.oh1(g, h, R);
    algorithm.printRoomSquare(R);
  }

  static void test_oh2() {
    int n = 4;
    int r = 4;
    int[][] g = new int[n][n];
    int[][] h = new int[n][n];
    int[][][] R = new int[n][n][2];
    Graph.initGraph(g);
    algorithm.oneFactorisation(g);
    Graph.initGraph(h);
    algorithm.initRoomSquare(R);
    algorithm.oh2(g, h, R);
    algorithm.printRoomSquare(R);
  }

  public static void main (String[] args) {
//    test_initGraph();
    test_h1();
//   test_oneFactorisation();
//    test_oh1();
//    test_oh2();
  }

}
