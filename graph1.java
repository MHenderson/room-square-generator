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

  public static void main (String[] args) {
    //test_initGraph();
    //test_h1();
    test_oneFactorisation();
  }
}
