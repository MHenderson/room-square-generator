package Room;

import java.util.Random;

public class test {
  //
  static void test_initGraph() {
    int[][] g = new int[3][2];
    graph.print(g);
    graph.init(g);
    graph.print(g);
  }

  static void test_h1(Random rng) {
    int[][] g = new int[4][4];
    graph.init(g);
    graph.print(g);
    algorithm.h1(g, rng);
    graph.print(g);
//    algorithm.h2(g);
//    graph.print(g);
  }

  static void test_h2(Random rng) {
    int[][] g = new int[4][4];
    graph.init(g);
    graph.print(g);
    algorithm.h1(g, rng);
    graph.print(g);
    algorithm.h2(g, rng);
    graph.print(g);
  }

   static void test_oneFactorisation(Random rng) {
    int[][] g = new int[4][4];
    graph.init(g);
    algorithm.oneFactorisation(g, rng);
    graph.print(g);
  }

  static void test_oh1(Random rng) {
    int n = 4;
    int r = 4;
    int[][] g = new int[n][n];
    int[][] h = new int[n][n];
    int[][][] R = new int[n][n][2];
    graph.init(g);
    algorithm.oneFactorisation(g, rng);
    graph.init(h);
    square.init(R);
    algorithm.oh1(g, h, R, rng);
    square.print(R);
  }

  static void test_oh2(Random rng) {
    int n = 4;
    int r = 4;
    int[][] g = new int[n][n];
    int[][] h = new int[n][n];
    int[][][] R = new int[n][n][2];
    graph.init(g);
    algorithm.oneFactorisation(g, rng);
    graph.init(h);
    square.init(R);
    algorithm.oh2(g, h, R, rng);
    square.print(R);
  }

  public static void main (String[] args) {
    Random randnum = new Random(42);
    test_initGraph();
    test_h1(randnum);
    test_h2(randnum);
    test_oneFactorisation(randnum);
    test_oh1(randnum);
    test_oh2(randnum);
  }

}
