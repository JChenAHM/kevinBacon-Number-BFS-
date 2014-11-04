public class Graph {  

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
    	System.out.println(record.pathLength("Jeong, Jae-yeong", "Dolan, Mike"));
    }	
}
