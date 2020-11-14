public class graph1 {

	static void printGraph(int[][] graph) {
		for(int i = 1;i < graph.length;i++) {
			System.out.print("<");
				for(int j=0; j < graph[i].length; j++) {
					System.out.print(((graph[i][j] < 0)?"-":String.valueOf(graph[i][j])) +
						((j == graph[i].length-1)?"":","));
				}
			System.out.println(">");
		}
	}

//

	static void printRoomSquare(int[][][] R) {
		for(int i = 1;i < R.length;i++) {
			System.out.print("<");
				for(int j=1; j < R[i].length; j++) {	
					System.out.print("<");
					System.out.print(((R[i][j][0] < 0)?"-":String.valueOf(R[i][j][0])));
					System.out.print(",");
					System.out.print(((R[i][j][1] < 0)?"-":String.valueOf(R[i][j][1])));
					System.out.print(">");
				}
			System.out.println(">");
		}
	}

//

static void htmlRoomSquare(int[][][] R) {
		System.out.println("<html>");
		System.out.println("<body>");
		System.out.print("<H3> Room Square of order  " + n + "</H3>");
		System.out.println("<table border = 1 align = \"center\">");
		for(int i = 1;i < R.length;i++) {
			System.out.print("<tr>");
				for(int j=1; j < R[i].length; j++) {	
					System.out.print("<td align = \"center\">");
					System.out.print("<font size =2>");
					System.out.print(((R[i][j][0] < 0)?" ":String.valueOf(R[i][j][0])));
					System.out.print(((R[i][j][0] < 0)?" - ": ","));
					System.out.print(((R[i][j][1] < 0)?" ":String.valueOf(R[i][j][1])));
					System.out.print("</font>");
					System.out.print("</td>");
				}
			System.out.println("</tr>");
		}
		System.out.println("</table>");
		System.out.println("</body>");
		System.out.println("</html>");
	}
	
//

	static void initGraph(int[][] graph) {
		for(int i = 1;i < graph.length;i++) {
				for(int j=0; j < graph[i].length; j++) {
					graph[i][j] = -1;
				}
		}
	}

//

	static void initRoomSquare(int[][][] R) {
		for(int i = 1;i < R.length;i++) {
				for(int j=1; j < R[i].length; j++) {
					R[i][j][0] = -1;
					R[i][j][1] = -1;
				}
		}
	}



// liveVertex true if vertex v has incident uncoloured edge	
	static boolean liveVertex(int[][] g, int v) {
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

// liveColour true if colour c available for some edge
	static boolean liveColour(int[][] g, int c) {
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

// colouredWith true if vertex v has an edge of colour c	
	static boolean colouredWith(int[][] g, int c, int v) {
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

// edgeColoured true if edge uv has been coloured	
	static boolean edgeColoured(int[][] g, int u, int v) {
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

// random returns a random integer between a and b incl.
	static int random(int a,int b) {
		return a + new Double(Math.random()*(b-a+1)).intValue();
	}

// graphFull true if vertex v has incident uncoloured edge	
	static boolean graphFull(int[][] g) {
		int c; boolean result; int v;
		result = true;
			for(v = 0;v < r;v++) {
				if (liveVertex(g,v)) {
					result = false;
					break;
				}
			}
	  return result;
	}

//

static int colourOf(int[][] g, int u, int v) {
			for(int c = 1;c < n;c++) {
				if (g[c][u] == v) {
					return c;
				}
			}
	  return -1;
	} 

//
	static void h1(int[][] graph) {
		int u,v,c,w;
			do {u = random(0,r-1);} while (! liveVertex(graph,u));
			do {c = random(1,n-1);} while (! (liveColour(graph,c) & !colouredWith(graph,c,u)));
			do {v = random(0,r-1);} while (! (u != v & !edgeColoured(graph,u,v)));

			if (! colouredWith(graph,c,v)) {
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

//

	static void h2(int[][] graph) {
		int u,v,c,d;
			do {c = random(1,n-1);} while (! liveColour(graph,c));
			do {u = random(0,r-1);v = random(0,r-1);} 
			while (!(u != v & !colouredWith(graph,c,u) & !colouredWith(graph,c,v)));

			if (! edgeColoured(graph,u,v)) {
				graph[c][u] = v;
				graph[c][v] = u;
			}
			else {
				d = colourOf(graph,u,v);
				graph[d][u] = -1;
				graph[d][v] = -1;
				graph[c][u] = v;
				graph[c][v] = u;
			}

   }

//

	static int oneFactorisation(int[][] graph) {
			int count =0;
			do {
				if (random(0,1) == 0) h1(graph); else h2(graph);
			count++;
			}		 
			while (! graphFull(graph));
			return count;
   }

//

	

//

	static void oh1(int[][] g1, int[][] g2, int[][][] R) {
		int u,v,w,c1j,c1k,c2;
			do {u = random(0,r-1);} while (! liveVertex(g2,u));
			do {c2 = random(1,n-1);} while (! (liveColour(g2,c2) & !colouredWith(g2,c2,u)));
			do {v = random(0,r-1);} while (! (u != v & !edgeColoured(g2,u,v)));

			c1j = colourOf(g1,u,v);
			
			if (R[c1j][c2][0] != -1) return;

			if (! colouredWith(g2,c2,v)) {
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
				c1k = colourOf(g1,w,v);
				R[c1k][c2][0] = -1;
				R[c1k][c2][1] = -1;
			}

   }

//

	static void oh2(int[][] g1, int[][] g2, int[][][] R) {
		int u,v,c1j,c2i,c2k;
			do {c2i = random(1,n-1);} while (! liveColour(g2,c2i));
			do {u = random(0,r-1);v = random(0,r-1);} 
			while (!(u != v & !colouredWith(g2,c2i,u) & !colouredWith(g2,c2i,v)));

			c1j = colourOf(g1,u,v);
			if (R[c1j][c2i][0] != -1) return;

			if (! edgeColoured(g2,u,v)) {
				g2[c2i][u] = v;
				g2[c2i][v] = u;
				R[c1j][c2i][0] = u;
				R[c1j][c2i][1] = v;
			}
			else {
				c2k = colourOf(g2,u,v);
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



  	
	static int n; static int r;
	
// r is no. of vertices, vertices numbered 0..r-1
// n is no. of colours, numbered 1..n-1

	public static void main (String[] args) {
		r = Integer.parseInt(args[0]);
//		int count = Integer.parseInt(args[1]);
		int tries = Integer.parseInt(args[1]);
		int low = Integer.parseInt(args[2]);
		int upp = Integer.parseInt(args[3]);
		n = r;
		int[][] graph1 = new int[n][n];
		int[][] graph2 = new int[n][n];
		int[][][] R = new int[n][n][2];
	out:	for (int count = low; count < upp; count += 500) {
			System.err.println();System.err.println("Performing " +count +" iterations.");
				for (int i=0; i<tries; i++) {
					System.err.print(i);
					initGraph(graph1);
					oneFactorisation(graph1);
					initGraph(graph2);
					initRoomSquare(R);
			
						for (int j = 0; j<count; j++) {
							if (random(0,1) == 0) oh1(graph1,graph2,R); else oh2(graph1,graph2,R);
							if (graphFull(graph2)) {System.err.println();System.err.println("Got one. " + j +" iterations required.");break;}
						}
					if (graphFull(graph2)) break out;
			}
		}
		htmlRoomSquare(R);
//		int[][] graph = {{-1,-1,-1,-1},{3,1,2,0},{1,0,-1,-1},{2,-1,0,-1}};
//		printGraph(graph);
//		System.out.println(colourOf(graph,u,v));
		
//		for (int i = 1;i < count;i++) {
//			h2(graph); if (graphFull(graph)) break;
//		}
//		int its = oneFactorisation(graph);printGraph(graph);
//		System.out.println("Iterations = " + its);
	}
}
