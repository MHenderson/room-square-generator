package Room;

/*! \brief Generate Room squares
 *         
 */
public class Graph {

  /*! \brief print a g
   *         
   */
  static void printGraph(int[][] g) {
    for(int i = 0; i < g.length; i++) {
      System.out.print("<");
      for(int j = 0; j < g[i].length; j++) {
        System.out.print(((g[i][j] < 0)?"-":String.valueOf(g[i][j])) + ((j == g[i].length - 1)?"":","));
      }
      System.out.println(">");
    }
  }

  static void initGraph(int[][] g) {
    for(int i = 1;i < g.length; i++) {
      for(int j = 0; j < g[i].length; j++) {
        g[i][j] = -1;
      }
    }
  }

  // true if vertex v has incident uncoloured edge
  static boolean liveVertex(int[][] g, int v) {
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
  static boolean liveColour(int[][] g, int c) {
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
  static boolean colouredWith(int[][] g, int c, int v) {
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
  static boolean edgeColoured(int[][] g, int u, int v) {
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

  static int colourOf(int[][] g, int u, int v) {
    int n = g.length;
    for(int c = 1; c < n; c++) {
      if (g[c][u] == v) {
        return c;
      }
    }
    return -1;
  }

  //
  static boolean graphFull(int[][] g) {
    int c;
    boolean result;
    int v;
    result = true;
    int n = g.length;
    int r = g[0].length;
    for(v = 0; v < r; v++) {
      if (liveVertex(g, v)) {
        result = false;
        break;
      }
    }
    return result;
  }

}

