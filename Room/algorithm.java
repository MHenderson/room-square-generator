package Room;

public class algorithm {

  static void h1(graph g, java.util.Random rng) {
    int u, v, c, w;
    int n = g.n();
    int r = g.m();
    do {
      u = rng.nextInt(r);
    } while (!g.liveVertex(u));
    do {
      c = rng.nextInt(n - 1) + 1;
    } while (!(g.liveColour(c) & !g.colouredWith(c, u)));
    do {
      v = rng.nextInt(r);
    } while (!(u != v & !g.edgeColoured(u, v)));
    if (!g.colouredWith(c, v)) {
      g.colourEdge(u, v, c);
    }
    else {
      w = g.get(c, v);
      g.set(c, v, -1);
      g.set(c, w, -1);
      g.colourEdge(u, v, c);
    }
  }

  static void h2(graph g, java.util.Random rng) {
    int u, v, c, d;
    int n = g.n();
    int r = g.m();
    do {
      c = rng.nextInt(n - 1) + 1;
    } while (!g.liveColour(c));
    do {
      u = rng.nextInt(r);
      v = rng.nextInt(r);
    } while (!(u != v & !g.colouredWith(c, u) & !g.colouredWith(c, v)));
    if (!g.edgeColoured(u, v)) {
      g.colourEdge(u, v, c);
    }
    else {
      d = g.colourOf(u, v);
      g.set(d, u, -1);
      g.set(d, v, -1);
      g.colourEdge(u, v, c);
    }
  }

  static void oh1(graph g1, graph g2, roomSquare R, java.util.Random rng) {
    int u, v, w, c1j, c1k, c2;
    int n = g1.n();
    int r = g1.m();
    do {
      u = rng.nextInt(r);;
    } while (!g2.liveVertex(u));
    do {
      c2 = rng.nextInt(n - 1) + 1;
    } while (!(g2.liveColour(c2) & !g2.colouredWith(c2, u)));
    do {
      v = rng.nextInt(r);
    } while (! (u != v & !g2.edgeColoured(u, v)));

    c1j = g1.colourOf(u, v);

    if (R.getLeft(c1j, c2) != -1) return;

    if (!g2.colouredWith(c2, v)) {
        g2.colourEdge(u, v, c2);
        R.setLeft(c1j, c2, u);
        R.setRight(c1j, c2, v);
    }
    else {
        w = g2.get(c2, v);
        g2.set(c2, v, -1);
        g2.set(c2, w, -1);
        g2.colourEdge(u, v, c2);
        R.setLeft(c1j, c2, u);
        R.setRight(c1j, c2, v);
        c1k = g1.colourOf(w, v);
        R.setLeft(c1k, c2, -1);
        R.setRight(c1k, c2, -1);
    }
  }

  static void oh2(graph g1, graph g2, roomSquare R, java.util.Random rng) {
    int u, v, c1j, c2i, c2k;
    int n = g1.n();
    int r = g1.m();
    do {
      c2i = rng.nextInt(n - 1) + 1;
    } while (!g2.liveColour(c2i));
    do {
      u = rng.nextInt(r);
      v = rng.nextInt(r);
    }
    while (!(u != v & !g2.colouredWith(c2i, u) & !g2.colouredWith(c2i, v)));

    c1j = g1.colourOf(u, v);

    if (R.getLeft(c1j, c2i) != -1) return;

    if (!g2.edgeColoured(u, v)) {
      g2.colourEdge(u, v, c2i);
      R.setLeft(c1j, c2i, u);
      R.setRight(c1j, c2i, v);
    }
    else {
      c2k = g2.colourOf(u, v);
      g2.set(c2k, u, -1);
      g2.set(c2k, v, -1);
      g2.colourEdge(u, v, c2i);
      R.setLeft(c1j, c2i, u);
      R.setRight(c1j, c2i, v);
      R.setLeft(c1j, c2k, -1);
      R.setRight(c1j, c2k, -1);
    }
  }

  public static int oneFactorisation(graph g, java.util.Random rng) {
    int count = 0;
    int n = g.n();
    int r = g.m();
    do {
      int rr = rng.nextInt(2);
      if (rr == 0)
        h1(g, rng);
      else
        h2(g, rng);
      count++;
    } while (!g.isFull());
    return count;
  }

  public static void hillClimbing(graph g, graph h, roomSquare R, int iterations, java.util.Random rng) {
    for (int j = 0; j < iterations; j++) {
      int rr = rng.nextInt(2);
      if (rr == 0) {
        oh1(g, h, R, rng);
      } else {
        oh2(g, h, R, rng);
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
