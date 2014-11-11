import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

public class Graph {  
	
	/*
	 * symbolTable
	 */
	public class SymbolTable {
		
		@SuppressWarnings("rawtypes")
		private HashMap st = new HashMap();

		@SuppressWarnings("unchecked")
		public void put(String key, Object value) { st.put(key, value);   }
	    public Object get(String key)             { return st.get(key);   }

	}

    /*
     * adjList
     */
	public class AdjList {
	    private adjNode first;        // beginning of list
	    private int N;             // size of list
	    
	    // helper linked list data type
	    private class adjNode {
	        String name;
	        adjNode next;
	        adjNode(String name, adjNode next) {
	            this.name = name;
	            this.next = next;
	        }
	    }
	    
	    // add s to the adjacency list
	    public void insert(String s) {
	        first = new adjNode(s, first);
	        N++;
	    }

	    // return array of strings comprising this adjacency list
	    public String[] toArray() {
	        String[] names = new String[N];
	        int i = N;
	        for (adjNode x = first; x != null; x = x.next)
	            names[--i] = x.name;
	        return names;
	    }
	}
    
    /*
     * queue
     */
	public class Queue {
	    private queueNode first;        // beginning of queue
	    private queueNode last;         // end of queue

	    private class queueNode {
	        Object item;
	        queueNode next;
	    }

	    public boolean isEmpty() { return (first == null); }

	    //add anItem to the queue
	    public void enqueue(Object anItem) {
	        queueNode x = new queueNode();
	        x.item = anItem;
	        x.next = null;
	        if (isEmpty()) first = x;
	        else last.next = x;
	        last = x;
	    }

	    // remove and return the least recently added item
	    public Object dequeue() {
	        Object val = first.item;
	        first = first.next;
	        return val;
	    }
	}
	
	/*
	 * construction
	 */
	public class Construction {
		
	    private SymbolTable st;     // symbol table of linked lists

	    // a graph with no vertices or edges
	    public Construction() {
	        st = new SymbolTable();
	    }
	    
	    // add v to w's list of neighbors and w to v's list of neighbors, parallel edges allowed
	    public void addEdge(String v, String w) {
	        if (st.get(v) == null) addVertex(v);
	        if (st.get(w) == null)  addVertex(w);
	        AdjList vlist = (AdjList) st.get(v);
	        AdjList wlist = (AdjList) st.get(w);
	        vlist.insert(w);
	        wlist.insert(v);
	    }
	    
	    // add a new vertex v with no neighbors
	    public void addVertex(String v) {
	        st.put(v, new AdjList());
	    }
	    
	    // return the array of vertices incident to v
	    public String[] neighbors(String v) {
	        AdjList adjlist = (AdjList) st.get(v);
	        if (adjlist == null) return new String[0];
	        else                 return adjlist.toArray();
	    }
	}
    
    /*
     * readfile
     */
	class readfile {
		
	    private BufferedReader br;

	    // for stdin
	    public readfile() {
	        InputStreamReader isr = new InputStreamReader(System.in);
	        br = new BufferedReader(isr);
	    }

	    // for stdin
	    public readfile(Socket socket) {
	        try {
	            InputStream is        = socket.getInputStream();
	            InputStreamReader isr = new InputStreamReader(is);
	            br                    = new BufferedReader(isr);
	        } catch (IOException ioe) { ioe.printStackTrace(); }
	    }

	    // for URLs
	    public readfile(URL url) {
	        try {
	            URLConnection site    = url.openConnection();
	            InputStream is        = site.getInputStream();
	            InputStreamReader isr = new InputStreamReader(is);
	            br                    = new BufferedReader(isr);
	        } catch (IOException ioe) { ioe.printStackTrace(); }
	    }

	    // for files and web pages
	    public readfile(String s) {

	        try {

	            // first try to read file from local file system
	            File file = new File(s);
	            if (file.exists()) {
	                FileReader fr = new FileReader(s);
	                br = new BufferedReader(fr);
	                return;
	            }

	            // next try for files included in jar
	            URL url = getClass().getResource(s);

	            // or URL from web
	            if (url == null) url = new URL(s);

	            URLConnection site    = url.openConnection();
	            InputStream is        = site.getInputStream();
	            InputStreamReader isr = new InputStreamReader(is);
	            br = new BufferedReader(isr);
	        } catch(IOException ioe) { throw new RuntimeException("Could not open " + s); }
	    }

	    // return rest of line as string and return it, not including newline 
	    public String readLine() {
	        if (br == null) return null;
	        String s = null;
	        try { s = br.readLine(); }
	        catch(IOException ioe) { ioe.printStackTrace(); }
	        return s;
	    }
	}

	/*
	 * BFS
	 */
	public class BFS {
		
	    private SymbolTable visited = new SymbolTable();

	    // run BFS in graph G from given source vertex s
	    public BFS(Construction G, String s) {

	        // put source on the queue
	        Queue q = new Queue();
	        q.enqueue(s);
	        visited.put(s, "");
	        
	        while (!q.isEmpty()) {
	            String v = (String) q.dequeue();
	            String[] neighbors = G.neighbors(v);
	            for (int i = 0; i < neighbors.length; i++) {
	                String w = neighbors[i];
	                if (visited.get(w) == null) {
	                    q.enqueue(w);
	                    visited.put(w, v);
	                }
	            }
	        }
	    }

	    // compute the length of the shortest path from v to s
	    public int pathLength(String v) {
	        int len = -1;
	        while (visited.get(v) != null) {
	            v = (String) visited.get(v);
	            len++;
	        }
	        return len/2;
	    }

	    // print the shortest path from v to s to standard output
	    public void showPath(String v) {
	        while (visited.get(v) != null) {
	            System.out.println(v);
	            v = (String) visited.get(v);
	        }
	    }
	}
	
	private Construction G = new Construction();
	
	/*
	 * construct an graph by the input txt file name.
	 */
	public Graph(String fileName) {
        readfile data = new readfile(fileName);
        String line;
        while ((line = data.readLine()) != null) {
            String[] names = line.split("/");
            String movie = names[0];
            for (int i = 1; i < names.length; i++) 
              G.addEdge(movie, names[i]);
        }
	}

	/*
	 * return the Bacon number between the two actors or actrees.
	 */
	public int pathLength(String name1, String name2) {
        BFS bfs = new BFS(G, name1);
		return bfs.pathLength(name2);
	}       

	/*
	 * unit testing.
	 */
	public static void main(String[] args) {
    	Graph record = new Graph("1.txt");
    	System.out.println(record.pathLength("Bell, Doron", "Ramis, Harold"));
    	System.out.println(record.pathLength("Burger, Karlheinz", "Butler, Josephine"));
    	System.out.println(record.pathLength("Bacon, Kevin", "Barbaro, Rocco"));
    }	
}
