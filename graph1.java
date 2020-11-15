public class graph1 {

  static void printGraph(int[][] graph) {
    for(int i = 0;i < graph.length;i++) {
      System.out.print("<");
        for(int j=0; j < graph[i].length; j++) {
          System.out.print(((graph[i][j] < 0)?"-":String.valueOf(graph[i][j])) + ((j == graph[i].length-1)?"":","));
        }
      System.out.println(">");
    }
  }

  static void initGraph(int[][] graph) {
    for(int i = 1;i < graph.length;i++) {
      for(int j=0; j < graph[i].length; j++) {
        graph[i][j] = -1;
      }
    }
  }

  static void initRoomSquare(int[][][] R) {
    for(int i = 1;i < R.length;i++) {
      for(int j=1; j < R[i].length; j++) {
        R[i][j][0] = -1;
        R[i][j][1] = -1;
      }
    }
  }

  static void printRoomSquare(int[][][] R) {
    for(int i = 1; i < R.length; i++) {
      System.out.print("<");
        for(int j = 1; j < R[i].length; j++) {
            System.out.print("<");
            System.out.print(((R[i][j][0] < 0)?"-":String.valueOf(R[i][j][0])));
            System.out.print(",");
            System.out.print(((R[i][j][1] < 0)?"-":String.valueOf(R[i][j][1])));
            System.out.print(">");
        }
        System.out.println(">");
    }
  }

  // true if vertex v has incident uncoloured edge
  static boolean liveVertex(int[][] g, int v, int n, int r) {
    int c; boolean result; int count;
    count = 0; result = false;
    for(c = 1;c < n;c++) {
      if (g[c][v] == -1) {
        count++;
        if (count > n-r) {
          result = true;
          break;
        }
      }
    }
	return result;
 }

  // true if colour c available for some edge
  static boolean liveColour(int[][] g, int c, int r) {
    int v; boolean result;
    result = false;
    for(v = 0;v < r;v++) {
      if (g[c][v] == -1) {
          result = true;
          break;
      }
    }
    return result;
  }

  // true if vertex v has an edge of colour c
  static boolean colouredWith(int[][] g, int c, int v, int r) {
    int u; boolean result;
    result = false;
    for(u = 0;u < r;u++) {
      if (g[c][u] == v) {
          result = true;
          break;
      }
    }
    return result;
  }

  // true if edge uv has been coloured
  static boolean edgeColoured(int[][] g, int u, int v, int n) {
    int c; boolean result;
    result = false;
    for(c = 1;c < n;c++) {
      if (g[c][u] == v) {
          result = true;
          break;
      }
    }
    return result;
  }

  // random integer between a and b incl.
  static int random(int a,int b) {
    return a + new Double(Math.random()*(b-a+1)).intValue();
  }

  //
  static void h1(int[][] graph, int r, int n) {
    int u, v, c, w;
    do {
      u = random(0, r - 1);
    } while (!liveVertex(graph, u, n, r));
    do {
      c = random(1, n - 1);
    } while (!(liveColour(graph, c, r) & !colouredWith(graph, c, u, r)));
    do {
      v = random(0, r - 1);
    } while (! (u != v & !edgeColoured(graph, u, v, n)));
    if (! colouredWith(graph, c, v, r)) {
      graph[c][u] = v;
      graph[c][v] = u;
    }
    else {
      w = graph[c][v];
      graph[c][v] = -1;
      graph[c][w] = -1;
      graph[c][u] = v;
      graph[c][v] = u;
    }
  }

  static int colourOf(int[][] g, int u, int v, int n) {
    for(int c = 1; c < n; c++) {
      if (g[c][u] == v) {
        return c;
      }
    }
    return -1;
  }

  static void h2(int[][] graph, int r, int n) {
    int u, v, c, d;
    do {
      c = random(1, n - 1);
    } while (!liveColour(graph, c, r));
    do {
      u = random(0, r - 1);
      v = random(0, r - 1);
    }
    while (!(u != v & !colouredWith(graph, c, u, r) & !colouredWith(graph, c, v, r)));
    if (!edgeColoured(graph, u, v, n)) {
      graph[c][u] = v;
      graph[c][v] = u;
    }
    else {
      d = colourOf(graph, u, v, n);
      graph[d][u] = -1;
      graph[d][v] = -1;
      graph[c][u] = v;
      graph[c][v] = u;
    }
  }

  //
  static boolean graphFull(int[][] g, int n, int r) {
    int c;
    boolean result;
    int v;
    result = true;
    for(v = 0; v < r; v++) {
      if (liveVertex(g, v, n, r)) {
        result = false;
        break;
      }
    }
    return result;
  }

  static int oneFactorisation(int[][] graph, int n, int r) {
    int count = 0;
    do {
      if (random(0, 1) == 0)
        h1(graph, r, n);
      else
        h2(graph, r, n);
      count++;
    }
    while (!graphFull(graph, n, r));
    return count;
  }

  static void oh1(int[][] g1, int[][] g2, int[][][] R, int n, int r) {
    int u, v, w, c1j, c1k, c2;
    do {
      u = random(0, r - 1);
    }
    while (!liveVertex(g2, u, n, r));
    do {
      c2 = random(1, n - 1);
    } while (!(liveColour(g2, c2, r) & !colouredWith(g2, c2, u, r)));
    do {
      v = random(0, r - 1);
    } while (! (u != v & !edgeColoured(g2, u, v, n)));

    c1j = colourOf(g1, u, v, n);

    if (R[c1j][c2][0] != -1) return;

    if (! colouredWith(g2, c2, v, r)) {
        g2[c2][u] = v;
        g2[c2][v] = u;
        R[c1j][c2][0] = u;
        R[c1j][c2][1] = v;
    }
    else {
        w = g2[c2][v];
        g2[c2][v] = -1;
        g2[c2][w] = -1;
        g2[c2][u] = v;
        g2[c2][v] = u;
        R[c1j][c2][0] = u;
        R[c1j][c2][1] = v;
        c1k = colourOf(g1, w, v, n);
        R[c1k][c2][0] = -1;
        R[c1k][c2][1] = -1;
    }
  }

  static void oh2(int[][] g1, int[][] g2, int[][][] R, int n, int r) {
    int u,v,c1j,c2i,c2k;
    do {
      c2i = random(1, n - 1);
    } while (!liveColour(g2, c2i, r));
    do {
      u = random(0, r - 1);
      v = random(0, r - 1);
    }
    while (!(u != v & !colouredWith(g2, c2i, u, r) & !colouredWith(g2, c2i, v, r)));

    c1j = colourOf(g1, u, v, n);

    if (R[c1j][c2i][0] != -1) return;

    if (! edgeColoured(g2, u, v, n)) {
      g2[c2i][u] = v;
      g2[c2i][v] = u;
      R[c1j][c2i][0] = u;
      R[c1j][c2i][1] = v;
    }
    else {
      c2k = colourOf(g2, u, v, n);
      g2[c2k][u] = -1;
      g2[c2k][v] = -1;
      g2[c2i][u] = v;
      g2[c2i][v] = u;
      R[c1j][c2i][0] = u;
      R[c1j][c2i][1] = v;
      R[c1j][c2k][0] = -1;
      R[c1j][c2k][1] = -1;
    }
  }

  //
  static void test_initGraph() {
    int[][] graph1 = new int[3][2];
    printGraph(graph1);
    initGraph(graph1);
    printGraph(graph1);
  }

  static void test_h1() {
    int[][] graph1 = new int[4][4];
    initGraph(graph1);
    printGraph(graph1);
    h1(graph1, 4, 4);
    printGraph(graph1);
    h2(graph1, 4, 4);
    printGraph(graph1);
  }

  static void test_oneFactorisation() {
    int[][] graph1 = new int[4][4];
    initGraph(graph1);
    oneFactorisation(graph1, 4, 4);
    printGraph(graph1);
  }

  static void test_oh1() {
    int n = 4;
    int r = 4;
    int[][] graph1 = new int[n][n];
    int[][] graph2 = new int[n][n];
    int[][][] R = new int[n][n][2];
    initGraph(graph1);
    oneFactorisation(graph1, n, r);
    initGraph(graph2);
    initRoomSquare(R);
    oh1(graph1, graph2, R, n, r);
    printRoomSquare(R);
  }

  static void test_oh2() {
    int n = 4;
    int r = 4;
    int[][] graph1 = new int[n][n];
    int[][] graph2 = new int[n][n];
    int[][][] R = new int[n][n][2];
    initGraph(graph1);
    oneFactorisation(graph1, n, r);
    initGraph(graph2);
    initRoomSquare(R);
    oh2(graph1, graph2, R, n, r);
    printRoomSquare(R);
  }

  static void test_findRoomSquare() {
    int n = 18;
    int r = 18;
    // this is how many times we try the heuristics before stopping
    int count = 2000;
    // this is how many times we restart
    int tries = 100;
    int[][] graph1 = new int[n][n];
    int[][] graph2 = new int[n][n];
    int[][][] R = new int[n][n][2];

    for (int i = 0; i < tries; i++) {
      initGraph(graph1);
      oneFactorisation(graph1, n, r);
      initGraph(graph2);
      initRoomSquare(R);
      for (int j = 0; j < count; j++) {
        if (random(0, 1) == 0) {
          oh1(graph1, graph2, R, n, r);
        } else {
          oh2(graph1, graph2, R, n, r);
        }
        if (graphFull(graph2, n, r)) {
          System.err.println();
          System.err.println("Got one. " + j +" iterations required.");
          printRoomSquare(R);
          break;
        }
      }
    }
  }

  public static void main (String[] args) {
    //test_initGraph();
    //test_h1();
    //test_oneFactorisation();
    //test_oh1();
    //test_oh2();
    test_findRoomSquare();
  }
}
