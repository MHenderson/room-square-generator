package Room;

public class test {
  //
  static void test_initGraph() {
    int[][] g = new int[3][2];
    Graph.print(g);
    Graph.init(g);
    Graph.print(g);
  }

  static void test_h1() {
    int[][] g = new int[4][4];
    Graph.init(g);
    Graph.print(g);
    algorithm.h1(g);
    Graph.print(g);
    algorithm.h2(g);
    Graph.print(g);
  }

  static void test_oneFactorisation() {
    int[][] g = new int[4][4];
    Graph.init(g);
    algorithm.oneFactorisation(g);
    Graph.print(g);
  }

  static void test_oh1() {
    int n = 4;
    int r = 4;
    int[][] g = new int[n][n];
    int[][] h = new int[n][n];
    int[][][] R = new int[n][n][2];
    Graph.init(g);
    algorithm.oneFactorisation(g);
    Graph.init(h);
    square.init(R);
    algorithm.oh1(g, h, R);
    square.print(R);
  }

  static void test_oh2() {
    int n = 4;
    int r = 4;
    int[][] g = new int[n][n];
    int[][] h = new int[n][n];
    int[][][] R = new int[n][n][2];
    Graph.init(g);
    algorithm.oneFactorisation(g);
    Graph.init(h);
    square.init(R);
    algorithm.oh2(g, h, R);
    square.print(R);
  }

  public static void main (String[] args) {
    test_initGraph();
    test_h1();
    test_oneFactorisation();
    test_oh1();
    test_oh2();
  }

}
