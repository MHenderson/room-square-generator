package Room;

/*! \brief Generate Room squares
 *         
 */
public class graph1 {

  /*! \brief print a graph
   *         
   */
  static void printGraph(int[][] graph) {
    for(int i = 0; i < graph.length; i++) {
      System.out.print("<");
      for(int j = 0; j < graph[i].length; j++) {
        System.out.print(((graph[i][j] < 0)?"-":String.valueOf(graph[i][j])) + ((j == graph[i].length - 1)?"":","));
      }
      System.out.println(">");
    }
  }

  static void initGraph(int[][] graph) {
    for(int i = 1;i < graph.length; i++) {
      for(int j = 0; j < graph[i].length; j++) {
        graph[i][j] = -1;
      }
    }
  }

  static void initRoomSquare(int[][][] R) {
    for(int i = 1;i < R.length; i++) {
      for(int j = 1; j < R[i].length; j++) {
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

  // random integer between a and b incl.
  static int random(int a, int b) {
    return a + new Double(Math.random()*(b - a + 1)).intValue();
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

