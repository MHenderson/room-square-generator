package Room;

public class graph {

  int[][] g;

  public graph(int n, int m) {
    g = new int[n][m];
    for(int i = 1; i < n; i++) {
      for(int j = 0; j < m; j++) {
        g[i][j] = -1;
      }
    }
  }

  public void print() {
    for(int i = 0; i < g.length; i++) {
      System.out.print("<");
      for(int j = 0; j < g[i].length; j++) {
        System.out.print(((g[i][j] < 0)?"-":String.valueOf(g[i][j])) + ((j == g[i].length - 1)?"":","));
      }
      System.out.println(">");
    }
  }

  public int get(int i, int j) {
    return g[i][j];
  }

  public void set(int i, int j, int k) {
    g[i][j] = k;
  }

  public int n() { return g.length; }
  public int m() { return g[0].length; }

  // true if vertex v has incident uncoloured edge
  public boolean liveVertex(int v) {
    int c;
    boolean result;
    int count;
    int n = g.length;
    count = 0;
    result = false;
    for(c = 1; c < n; c++) {
      if (g[c][v] == -1) {
        count++;
        if (count > 0) {
          result = true;
          break;
        }
      }
    }
	return result;
 }

  // true if colour c available for some edge
  public boolean liveColour(int c) {
    int v;
    boolean result;
    int r = g[0].length;
    result = false;
    for(v = 0; v < r; v++) {
      if (g[c][v] == -1) {
        result = true;
        break;
      }
    }
    return result;
  }

  // true if vertex v has an edge of colour c
  public boolean colouredWith(int c, int v) {
    int u;
    boolean result;
    int r = g[0].length;
    result = false;
    for(u = 0; u < r; u++) {
      if (g[c][u] == v) {
        result = true;
        break;
      }
    }
    return result;
  }

  // true if edge uv has been coloured
  public boolean edgeColoured(int u, int v) {
    int c;
    boolean result;
    int n = g.length;
    result = false;
    for(c = 1; c < n; c++) {
      if (g[c][u] == v) {
        result = true;
        break;
      }
    }
    return result;
  }

  public int colourOf(int u, int v) {
    int n = g.length;
    for(int c = 1; c < n; c++) {
      if (g[c][u] == v) {
        return c;
      }
    }
    return -1;
  }

  //
  public boolean isFull() {
    int c;
    boolean result;
    int v;
    result = true;
    int n = g.length;
    int r = g[0].length;
    for(v = 0; v < r; v++) {
      if (liveVertex(v)) {
        result = false;
        break;
      }
    }
    return result;
  }

}

