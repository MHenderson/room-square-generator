package org.Room;

public class algorithm {

  static void h1(graph g) {
    int u, v, c, w;
    int n = g.n();
    int r = g.m();
    do {
      u = g.randomVertex();
    } while (!g.liveVertex(u));
    do {
      c = g.randomColour();
    } while (!(g.liveColour(c) & !g.colouredWith(c, u)));
    do {
      v = g.randomVertex();
    } while (!(u != v & !g.edgeColoured(u, v)));
    if (!g.colouredWith(c, v)) {
      g.colourEdge(u, v, c);
    }
    else {
      w = g.get(c, v);
      g.removeColour(v, w, c);
      g.colourEdge(u, v, c);
    }
  }

  static void h2(graph g) {
    int u, v, c, d;
    int n = g.n();
    int r = g.m();
    do {
      c = g.randomColour();
    } while (!g.liveColour(c));
    do {
      u = g.randomVertex();
      v = g.randomVertex();
    } while (!(u != v & !g.colouredWith(c, u) & !g.colouredWith(c, v)));
    if (!g.edgeColoured(u, v)) {
      g.colourEdge(u, v, c);
    }
    else {
      d = g.colourOf(u, v);
      g.removeColour(u, v, d);
      g.colourEdge(u, v, c);
    }
  }

  static void oh1(graph g1, graph g2, roomSquare R) {
    int u, v, w, c1j, c1k, c2;
    int n = g1.n();
    int r = g1.m();
    do {
      u = g2.randomVertex();
    } while (!g2.liveVertex(u));
    do {
      c2 = g2.randomColour();
    } while (!(g2.liveColour(c2) & !g2.colouredWith(c2, u)));
    do {
      v = g2.randomVertex();
    } while (! (u != v & !g2.edgeColoured(u, v)));

    c1j = g1.colourOf(u, v);

    if (R.getLeft(c1j, c2) != -1) return;

    if (!g2.colouredWith(c2, v)) {
        g2.colourEdge(u, v, c2);
        R.set(c1j, c2, u, v);
    }
    else {
        w = g2.get(c2, v);
        g2.removeColour(v, w, c2);
        g2.colourEdge(u, v, c2);
        R.set(c1j, c2, u, v);
        c1k = g1.colourOf(w, v);
        R.remove(c1k, c2);
    }
  }

  static void oh2(graph g1, graph g2, roomSquare R) {
    int u, v, c1j, c2i, c2k;
    int n = g1.n();
    int r = g1.m();
    do {
      c2i = g2.randomColour();
    } while (!g2.liveColour(c2i));
    do {
      u = g2.randomVertex();
      v = g2.randomVertex();
    }
    while (!(u != v & !g2.colouredWith(c2i, u) & !g2.colouredWith(c2i, v)));

    c1j = g1.colourOf(u, v);

    if (R.getLeft(c1j, c2i) != -1) return;

    if (!g2.edgeColoured(u, v)) {
      g2.colourEdge(u, v, c2i);
      R.set(c1j, c2i, u, v);
    }
    else {
      c2k = g2.colourOf(u, v);
      g2.removeColour(u, v, c2k);
      g2.colourEdge(u, v, c2i);
      R.set(c1j, c2i, u, v);
      R.remove(c1j, c2k);
    }
  }

  public static int oneFactorisation(graph g, java.util.Random rng) {
    int count = 0;
    int n = g.n();
    int r = g.m();
    do {
      int rr = rng.nextInt(2);
      if (rr == 0)
        h1(g);
      else
        h2(g);
      count++;
    } while (!g.isFull());
    return count;
  }

  public static void hillClimbing(graph g, graph h, roomSquare R, int iterations, java.util.Random rng) {
    for (int j = 0; j < iterations; j++) {
      int rr = rng.nextInt(2);
      if (rr == 0) {
        oh1(g, h, R);
      } else {
        oh2(g, h, R);
      }
      if (h.isFull()) {
        System.err.println();
        System.err.println("Got one. " + j +" iterations required.");
        R.print();
        break;
      }
    }
  }

}