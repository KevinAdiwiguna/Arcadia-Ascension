package level.Map;

public class Edge {
    int destination;
    Edge next;
    int weight;

    public Edge(int destination, int weight) {
        this.destination = destination;
        this.weight = weight;
        this.next = null;
    }
}
