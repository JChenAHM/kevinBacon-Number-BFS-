public class Graph {  
	
	public Graph(String fileName) {
		// read in data and initialize graph
        In data = new In(fileName);
        Construction G = new Construction();
        String line;
        while ((line = data.readLine()) != null) {
            String[] names = line.split("/");
            String movie = names[0];
            for (int i = 1; i < names.length; i++) 
              G.addEdge(movie, names[i]);
        }

        // run breadth first search for an actor A
        System.out.println("Enter the name of an actor A");
        In queries1 = new In();
        String actor1;
        actor1 = queries1.readLine();
        BFS bfs = new BFS(G, actor1);

        // compute the shortest path from actor B to actor A
        System.out.println("Enter the name of another actor B");
        In queries2 = new In();
        String actor2;
        actor2 = queries2.readLine();
        System.out.println("The shortest path from actor B to actor A is diaplayed below");
        bfs.showPath(actor2);
        System.out.println(bfs.pathLength(actor2));
	}

	//public int pathLength(String name1) {return bfs.pathLength(name1);}       // return the Bacon number between the two actors or actrees.

    public static void main(String[] args) {
    	Graph record = new Graph("1.txt");
    	System.out.println(record);
    }
}

