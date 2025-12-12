package level.Map;

public class Vertex {
    int id;
    String name;
    int distance;
    boolean visited;
    Edge adjacencyList;
    Vertex next;
    Vertex prev;

    public Vertex(int id, String name) {
        this.id = id;
        this.name = name;
        visited = false;
        distance = Integer.MAX_VALUE;
        adjacencyList = null;
        next = null;
        prev = null;
    }

    public void addEdge(int destination, int weight) {
        Edge newEdge = new Edge(destination, weight);
        if (adjacencyList == null) {
            adjacencyList = newEdge;
        } else {
            Edge temp = adjacencyList;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newEdge;
        }
    }
}
