package Room;

public class algorithm {

  static void h1(int[][] graph) {
    int u, v, c, w;
    int n = graph.length;
    int r = graph[0].length;
    do {
      u = graph1.random(0, r - 1);
    } while (!graph1.liveVertex(graph, u));
    do {
      c = graph1.random(1, n - 1);
    } while (!(graph1.liveColour(graph, c) & !graph1.colouredWith(graph, c, u)));
    do {
      v = graph1.random(0, r - 1);
    } while (! (u != v & !graph1.edgeColoured(graph, u, v)));
    if (! graph1.colouredWith(graph, c, v)) {
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

  static void h2(int[][] graph) {
    int u, v, c, d;
    int n = graph.length;
    int r = graph[0].length;
    do {
      c = graph1.random(1, n - 1);
    } while (!graph1.liveColour(graph, c));
    do {
      u = graph1.random(0, r - 1);
      v = graph1.random(0, r - 1);
    } while (!(u != v & !graph1.colouredWith(graph, c, u) & !graph1.colouredWith(graph, c, v)));
    if (!graph1.edgeColoured(graph, u, v)) {
      graph[c][u] = v;
      graph[c][v] = u;
    }
    else {
      d = graph1.colourOf(graph, u, v);
      graph[d][u] = -1;
      graph[d][v] = -1;
      graph[c][u] = v;
      graph[c][v] = u;
    }
  }

  static void oh1(int[][] g1, int[][] g2, int[][][] R) {
    int u, v, w, c1j, c1k, c2;
    int n = g1.length;
    int r = g1[0].length;
    do {
      u = graph1.random(0, r - 1);
    } while (!graph1.liveVertex(g2, u));
    do {
      c2 = graph1.random(1, n - 1);
    } while (!(graph1.liveColour(g2, c2) & !graph1.colouredWith(g2, c2, u)));
    do {
      v = graph1.random(0, r - 1);
    } while (! (u != v & !graph1.edgeColoured(g2, u, v)));

    c1j = graph1.colourOf(g1, u, v);

    if (R[c1j][c2][0] != -1) return;

    if (! graph1.colouredWith(g2, c2, v)) {
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
        c1k = graph1.colourOf(g1, w, v);
        R[c1k][c2][0] = -1;
        R[c1k][c2][1] = -1;
    }
  }

  static void oh2(int[][] g1, int[][] g2, int[][][] R) {
    int u, v, c1j, c2i, c2k;
    int n = g1.length;
    int r = g1[0].length;
    do {
      c2i = graph1.random(1, n - 1);
    } while (!graph1.liveColour(g2, c2i));
    do {
      u = graph1.random(0, r - 1);
      v = graph1.random(0, r - 1);
    }
    while (!(u != v & !graph1.colouredWith(g2, c2i, u) & !graph1.colouredWith(g2, c2i, v)));

    c1j = graph1.colourOf(g1, u, v);

    if (R[c1j][c2i][0] != -1) return;

    if (! graph1.edgeColoured(g2, u, v)) {
      g2[c2i][u] = v;
      g2[c2i][v] = u;
      R[c1j][c2i][0] = u;
      R[c1j][c2i][1] = v;
    }
    else {
      c2k = graph1.colourOf(g2, u, v);
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

  public static int oneFactorisation(int[][] graph) {
    int count = 0;
    int n = graph.length;
    int r = graph[0].length;
    do {
      if (graph1.random(0, 1) == 0)
        h1(graph);
      else
        h2(graph);
      count++;
    } while (!graph1.graphFull(graph));
    return count;
  }

  public static void hillClimbing(int[][] g, int[][] h, int[][][] R, int iterations) {
    for (int j = 0; j < iterations; j++) {
      if (graph1.random(0, 1) == 0) {
        oh1(g, h, R);
      } else {
        oh2(g, h, R);
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
