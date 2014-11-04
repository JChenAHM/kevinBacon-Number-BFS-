public class AdjList {
    private Node first;        // beginning of list
    private int N;             // size of list

    /*
     * helper linked list data type
     */
    private static class Node {
        String name;
        Node next;
        Node(String name, Node next) {
            this.name = name;
            this.next = next;
        }
    }

    /*
     * add s to the adjacency list
     */
    public void insert(String s) {
        first = new Node(s, first);
        N++;
    }

    /*
     * return array of strings comprising this adjacency list
     */
    public String[] toArray() {
        String[] names = new String[N];
        int i = N;
        for (Node x = first; x != null; x = x.next)
            names[--i] = x.name;
        return names;
    }
}
