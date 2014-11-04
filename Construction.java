public class Construction {
	
    private SymbolTable st;     // symbol table of linked lists

    /*
     * a graph with no vertices or edges
     */
    public Construction() {
        st = new SymbolTable();
    }

    /*
     * add v to w's list of neighbors and w to v's list of neighbors
     * parallel edges allowed
     */
    public void addEdge(String v, String w) {
        if (st.get(v) == null) addVertex(v);
        if (st.get(w) == null)  addVertex(w);
        AdjList vlist = (AdjList) st.get(v);
        AdjList wlist = (AdjList) st.get(w);
        vlist.insert(w);
        wlist.insert(v);
    }

    /*
     * add a new vertex v with no neighbors
     */
    public void addVertex(String v) {
        st.put(v, new AdjList());
    }

    /*
     * return the array of vertices incident to v
     */
    public String[] neighbors(String v) {
        AdjList adjlist = (AdjList) st.get(v);
        if (adjlist == null) return new String[0];
        else                 return adjlist.toArray();
    }
}
