public class BFS {
	
    private SymbolTable visited = new SymbolTable();

    /*
     * run BFS in graph G from given source vertex s
     */
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

    /*
     * compute the length of the shortest path from v to s
     */
    public int pathLength(String v) {
        int len = -1;
        while (visited.get(v) != null) {
            v = (String) visited.get(v);
            len++;
        }
        return len/2;
    }

    /*
     * print the shortest path from v to s to standard output
     */
    public void showPath(String v) {
        while (visited.get(v) != null) {
            System.out.println(v);
            v = (String) visited.get(v);
        }
    }
}
