package level.Map;

public class Graph {
    Vertex head;
    private int playerLocationId = -1; // -1 = unknown / not set

    public Graph() {
        head = null;
    }

    public void addVertex(int id, String name) {
        Vertex newVertex = new Vertex(id, name);
        if (head == null) {
            head = newVertex;
        } else {
            Vertex temp = head;
            while (temp.next != null) temp = temp.next;
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
            if (temp.id == id) return temp;
            temp = temp.next;
        }
        return null;
    }

    public Vertex findVertexByName(String name) {
        Vertex temp = head;
        while (temp != null) {
            if (temp.name.equalsIgnoreCase(name)) return temp;
            temp = temp.next;
        }
        return null;
    }

    public int returnID(String name) {
        Vertex temp = head;
        while (temp != null) {
            if (temp.name.equalsIgnoreCase(name)) return temp.id;
            temp = temp.next;
        }
        return 9999;
    }

    public String findById(int id) {
        Vertex temp = head;
        while (temp != null) {
            if (temp.id == id) return temp.name;
            temp = temp.next;
        }
        return " ";
    }

    // Player location helpers
    public void setPlayerLocation(int id) { this.playerLocationId = id; }
    public void setPlayerLocationByName(String name) {
        Vertex v = findVertexByName(name);
        if (v != null) this.playerLocationId = v.id;
    }
    public int getPlayerLocationId() { return playerLocationId; }
    public String getPlayerLocationName() { return findById(playerLocationId); }

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
                System.out.println("- Point " + neighbor.id + " (" + neighbor.name + ") (Distance: " + edge.weight + ")");
            }
            edge = edge.next;
        }
    }

    public int getDistanceBetween(String currentLocation, String destinationName) {
        Vertex currentVertex = findVertexByName(currentLocation);
        if (currentVertex == null) return -1;
        Edge edge = currentVertex.adjacencyList;
        while (edge != null) {
            Vertex neighbor = findVertex(edge.destination);
            if (neighbor != null && neighbor.name.equalsIgnoreCase(destinationName)) return edge.weight;
            edge = edge.next;
        }
        return -1;
    }

    public void displayCurrentLocation(String currentLocation) {
        System.out.println("\nCurrent Location: " + currentLocation);
    }

    public boolean isValidDestination(String currentLocation, String destination) {
        Vertex currentVertex = findVertexByName(currentLocation);
        if (currentVertex == null) return false;
        Edge edge = currentVertex.adjacencyList;
        while (edge != null) {
            Vertex neighbor = findVertex(edge.destination);
            if (neighbor != null && neighbor.name.equalsIgnoreCase(destination)) return true;
            edge = edge.next;
        }
        return false;
    }

    public void resetGraph() {
        Vertex current = head;
        while (current != null) {
            current.distance = Integer.MAX_VALUE;
            current.visited = false;
            current.prev = null;
            current = current.next;
        }
    }

    // Mark a vertex as visited (player has entered/completed that level)
    public void markVisited(int id) {
        Vertex v = findVertex(id);
        if (v != null) v.visited = true;
    }

    // Check if a vertex has been visited
    public boolean isVisited(int id) {
        Vertex v = findVertex(id);
        return v != null && v.visited;
    }

    // Display a compact 8-node map (0..7)
    public void displayMap() {
        String mapTemplate =
                "   %s  -------- %s ----------- %s\n" +
                "               |                 |\n" +
                "               |                 |\n" +
                "              %s ------------ %s\n" +
                "               |                 |\n" +
                "               |                 |\n" +
                "              %s -------- %s";

        String loc0 = "(0)", loc1 = "(1)", loc2 = "(2)", loc3 = "(3)", loc4 = "(4)", loc5 = "(5)", loc6 = "(6)", loc7 = "(7)";

        for (int i = 0; i <= 7; i++) {
            Vertex v = findVertex(i);
            String mark;
            if (v == null) {
                mark = "( )";
            } else if (v.id == playerLocationId) {
                mark = "(P)";
            } else if (v.visited) {
                mark = "(#)";
            } else {
                mark = "(" + v.id + ")";
            }

            switch (i) {
                case 0: loc0 = mark; break;
                case 1: loc1 = mark; break;
                case 2: loc2 = mark; break;
                case 3: loc3 = mark; break;
                case 4: loc4 = mark; break;
                case 5: loc5 = mark; break;
                case 6: loc6 = mark; break;
                case 7: loc7 = mark; break;
            }
        }

        int consoleWidth = 80;
        int mapWidth = 63;
        int padding = (consoleWidth - mapWidth) / 2;
        String pad = " ".repeat(Math.max(0, padding));

        System.out.println(pad + "##" + "=".repeat(mapWidth) + "##");
        String playerName = getPlayerLocationName().trim();
        String locationText = (playerName.equals("") || playerName.equals(" ")) ? "Location Unknown" : playerName;
        int textLength = locationText.length();
        int centerPadding = Math.max(0, (mapWidth - textLength) / 2);
        System.out.println(pad + "||"
                + " ".repeat(centerPadding)
                + locationText
                + " ".repeat(Math.max(0, mapWidth - textLength - centerPadding))
                + "||");
        System.out.println(pad + "##" + "=".repeat(mapWidth) + "##");

        String filled = String.format(mapTemplate,
                loc0, loc1, loc3, loc2, loc5, loc4, loc6, loc7);

        for (String line : filled.split("\\n")) {
            System.out.println(pad + line);
        }
        System.out.println("\n");
    }
}