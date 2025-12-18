package level.Map;

public class Graph {
    Vertex head;
    private int playerLocationId = -1; // -1 = unknown / not set

    private static class SimpleIntSet {
        private int[] data;
        private int size;

        public SimpleIntSet() {
            this.data = new int[16];
            this.size = 0;
        }

        public boolean contains(int x) {
            for (int i = 0; i < size; i++) {
                if (data[i] == x)
                    return true;
            }
            return false;
        }

        public void add(int x) {
            if (contains(x))
                return;
            if (size == data.length) {
                int[] newData = new int[data.length * 2];
                for (int i = 0; i < data.length; i++)
                    newData[i] = data[i];
                data = newData;
            }
            data[size++] = x;
        }
    }

    public Graph() {
        head = null;
    }

    public void addVertex(int id, String name) {
        Vertex newVertex = new Vertex(id, name);
        if (head == null) {
            head = newVertex;
        } else {
            Vertex temp = head;
            while (temp.next != null)
                temp = temp.next;
            temp.next = newVertex;
        }
    }

    public void addEdge(int source, int destination, int weight) {
        Vertex sourceVertex = findVertex(source);
        Vertex destinationVertex = findVertex(destination);
        if (sourceVertex != null && destinationVertex != null) {
            sourceVertex.addEdge(destination, weight);
            destinationVertex.addEdge(source, weight);
        }
    }

    public Vertex findVertex(int id) {
        Vertex temp = head;
        while (temp != null) {
            if (temp.id == id)
                return temp;
            temp = temp.next;
        }
        return null;
    }

    public Vertex findVertexByName(String name) {
        Vertex temp = head;
        while (temp != null) {
            if (temp.name.equalsIgnoreCase(name))
                return temp;
            temp = temp.next;
        }
        return null;
    }

    public int returnID(String name) {
        Vertex temp = head;
        while (temp != null) {
            if (temp.name.equalsIgnoreCase(name))
                return temp.id;
            temp = temp.next;
        }
        return 9999;
    }

    public String findById(int id) {
        Vertex temp = head;
        while (temp != null) {
            if (temp.id == id)
                return temp.name;
            temp = temp.next;
        }
        return " ";
    }

    public void setPlayerLocation(int id) {
        this.playerLocationId = id;
    }

    public int getPlayerLocationId() {
        return playerLocationId;
    }

    public String getPlayerLocationName() {
        return findById(playerLocationId);
    }

    public void displayAdjacentLocations(String currentLocation) {
        Vertex currentVertex = findVertexByName(currentLocation);
        if (currentVertex == null) {
            System.out.println("Location not found.");
            return;
        }
        Edge edge = currentVertex.adjacencyList;
        while (edge != null) {
            Vertex neighbor = findVertex(edge.destination);
            if (neighbor != null) {
                System.out
                        .println("- Point " + neighbor.id + " (" + neighbor.name + ") (Distance: " + edge.weight + ")");
            }
            edge = edge.next;
        }
    }

    public boolean isValidDestination(String currentLocation, String destination) {
        Vertex currentVertex = findVertexByName(currentLocation);
        if (currentVertex == null)
            return false;
        Edge edge = currentVertex.adjacencyList;
        while (edge != null) {
            Vertex neighbor = findVertex(edge.destination);
            if (neighbor != null && neighbor.name.equalsIgnoreCase(destination))
                return true;
            edge = edge.next;
        }
        return false;
    }

    public void resetGraph() {
        Vertex current = head;
        while (current != null) {
            current.distance = Integer.MAX_VALUE;
            current.prev = null;
            current = current.next;
        }
    }

    public void dijkstra(int sourceId) {
        resetGraph();
        Vertex source = findVertex(sourceId);
        if (source == null)
            return;

        source.distance = 0;

        SimpleIntSet processed = new SimpleIntSet();

        while (true) {
            Vertex minVertex = null;
            int minDist = Integer.MAX_VALUE;
            Vertex temp = head;
            while (temp != null) {
                if (!processed.contains(temp.id) && temp.distance < minDist) {
                    minDist = temp.distance;
                    minVertex = temp;
                }
                temp = temp.next;
            }

            if (minVertex == null)
                break;

            processed.add(minVertex.id);

            Edge e = minVertex.adjacencyList;
            while (e != null) {
                Vertex neighbor = findVertex(e.destination);
                if (neighbor != null && !processed.contains(neighbor.id)) {
                    int newDist = minVertex.distance + e.weight;
                    if (newDist < neighbor.distance) {
                        neighbor.distance = newDist;
                        neighbor.prev = minVertex;
                    }
                }
                e = e.next;
            }
        }
    }

    public int[] shortestPath(int sourceId, int destId) {
        Vertex source = findVertex(sourceId);
        Vertex dest = findVertex(destId);
        if (source == null || dest == null)
            return new int[0];

        dijkstra(sourceId);

        if (dest.distance == Integer.MAX_VALUE)
            return new int[0];

        int count = 0;
        Vertex cur = dest;
        while (cur != null) {
            count++;
            cur = cur.prev;
        }

        int[] path = new int[count];
        cur = dest;
        for (int i = count - 1; i >= 0; i--) {
            path[i] = cur.id;
            cur = cur.prev;
        }

        return path;
    }

    public void markVisited(int id) {
        Vertex v = findVertex(id);
        if (v != null)
            v.visited = true;
    }

    public boolean isVisited(int id) {
        Vertex v = findVertex(id);
        return v != null && v.visited;
    }

    public void displayMap() {
        String mapTemplate = "        %s ------ %s ------------ %s\n" +
                "                    |                 |\n" +
                "                    |                 |\n" +
                "                   %s ------------- %s\n" +
                "                    |                  \\\n" +
                "                    |                   \\\n" +
                "                    |                    \\\n" +
                "                    |                     \\\n" +
                "                   %s ------------------ %s ------- %s";

        String loc0 = "(0)", loc1 = "(1)", loc2 = "(2)", loc3 = "(3)", loc4 = "(4)", loc5 = "(5)", loc6 = "(6)",
                loc7 = "(7)";

        for (int i = 0; i <= 7; i++) {
            Vertex v = findVertex(i);
            String mark;
            if (v == null) {
                mark = "( )";
            } else if (v.id == playerLocationId) {
                mark = "(P)";
            } else {
                mark = "(" + v.id + ")";
            }

            switch (i) {
                case 0:
                    loc0 = mark;
                    break;
                case 1:
                    loc1 = mark;
                    break;
                case 2:
                    loc2 = mark;
                    break;
                case 3:
                    loc3 = mark;
                    break;
                case 4:
                    loc4 = mark;
                    break;
                case 5:
                    loc5 = mark;
                    break;
                case 6:
                    loc6 = mark;
                    break;
                case 7:
                    loc7 = mark;
                    break;
            }
        }

        int mapWidth = 63;

        System.out.println("##" + "=".repeat(mapWidth) + "##");
        String playerName = getPlayerLocationName().trim();
        String locationText = (playerName.equals("") || playerName.equals(" ")) ? "Location Unknown" : playerName;
        int textLength = locationText.length();
        int centerPadding = Math.max(0, (mapWidth - textLength) / 2);
        System.out.println("||"
                + " ".repeat(centerPadding)
                + locationText
                + " ".repeat(Math.max(0, mapWidth - textLength - centerPadding))
                + "||");
        System.out.println("##" + "=".repeat(mapWidth) + "##");

        String filled = String.format(mapTemplate,
                loc0, loc1, loc3, loc2, loc5, loc4, loc6, loc7);

        for (String line : filled.split("\\n")) {
            System.out.println(line);
        }
        System.out.println("\n");
    }
}