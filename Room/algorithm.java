package Room;

public class algorithm {

  static int random(int a, int b) {
    int value = a + new Double(Math.random()*(b - a + 1)).intValue();
    return(value);
  }

  static void h1(int[][] g) {
    int u, v, c, w;
    int n = g.length;
    int r = g[0].length;
    do {
      u = random(0, r - 1);
    } while (!graph.liveVertex(g, u));
    do {
      c = random(1, n - 1);
    } while (!(graph.liveColour(g, c) & !graph.colouredWith(g, c, u)));
    do {
      v = random(0, r - 1);
    } while (!(u != v & !graph.edgeColoured(g, u, v)));
    if (!graph.colouredWith(g, c, v)) {
      g[c][u] = v;
      g[c][v] = u;
    }
    else {
      w = g[c][v];
      g[c][v] = -1;
      g[c][w] = -1;
      g[c][u] = v;
      g[c][v] = u;
    }
  }

  static void h2(int[][] g) {
    int u, v, c, d;
    int n = g.length;
    int r = g[0].length;
    do {
      c = random(1, n - 1);
    } while (!graph.liveColour(g, c));
    do {
      u = random(0, r - 1);
      v = random(0, r - 1);
    } while (!(u != v & !graph.colouredWith(g, c, u) & !graph.colouredWith(g, c, v)));
    if (!graph.edgeColoured(g, u, v)) {
      g[c][u] = v;
      g[c][v] = u;
    }
    else {
      d = graph.colourOf(g, u, v);
      g[d][u] = -1;
      g[d][v] = -1;
      g[c][u] = v;
      g[c][v] = u;
    }
  }

  static void oh1(int[][] g1, int[][] g2, int[][][] R) {
    int u, v, w, c1j, c1k, c2;
    int n = g1.length;
    int r = g1[0].length;
    do {
      u = random(0, r - 1);
    } while (!graph.liveVertex(g2, u));
    do {
      c2 = random(1, n - 1);
    } while (!(graph.liveColour(g2, c2) & !graph.colouredWith(g2, c2, u)));
    do {
      v = random(0, r - 1);
    } while (! (u != v & !graph.edgeColoured(g2, u, v)));

    c1j = graph.colourOf(g1, u, v);

    if (R[c1j][c2][0] != -1) return;

    if (!graph.colouredWith(g2, c2, v)) {
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
        c1k = graph.colourOf(g1, w, v);
        R[c1k][c2][0] = -1;
        R[c1k][c2][1] = -1;
    }
  }

  static void oh2(int[][] g1, int[][] g2, int[][][] R) {
    int u, v, c1j, c2i, c2k;
    int n = g1.length;
    int r = g1[0].length;
    do {
      c2i = random(1, n - 1);
    } while (!graph.liveColour(g2, c2i));
    do {
      u = random(0, r - 1);
      v = random(0, r - 1);
    }
    while (!(u != v & !graph.colouredWith(g2, c2i, u) & !graph.colouredWith(g2, c2i, v)));

    c1j = graph.colourOf(g1, u, v);

    if (R[c1j][c2i][0] != -1) return;

    if (!graph.edgeColoured(g2, u, v)) {
      g2[c2i][u] = v;
      g2[c2i][v] = u;
      R[c1j][c2i][0] = u;
      R[c1j][c2i][1] = v;
    }
    else {
      c2k = graph.colourOf(g2, u, v);
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

  public static int oneFactorisation(int[][] g, java.util.Random rng) {
    int count = 0;
    int n = g.length;
    int r = g[0].length;
    do {
      int rr = rng.nextInt(2);
      if (rr == 0)
        h1(g);
      else
        h2(g);
      count++;
    } while (!graph.isFull(g));
    return count;
  }

  public static void hillClimbing(int[][] g, int[][] h, int[][][] R, int iterations, java.util.Random rng) {
    for (int j = 0; j < iterations; j++) {
      int rr = rng.nextInt(2);
      if (rr == 0) {
        oh1(g, h, R);
      } else {
        oh2(g, h, R);
      }
      if (graph.isFull(h)) {
        System.err.println();
        System.err.println("Got one. " + j +" iterations required.");
        square.print(R);
        break;
      }
    }
  }

}
