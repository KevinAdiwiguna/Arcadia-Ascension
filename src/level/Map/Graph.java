package level.Map;

public class Graph {
    Vertex head;

    public void addVertex(int id, String name) { 
        Vertex newVertex = new Vertex(id, name); 
        if (head == null) {head = newVertex; 
        }else {
            Vertex temp = head; 
            while (temp.next != null) {temp = temp.next;} 
            temp.next = newVertex;
        }
    } 
    public int returnID(String name){ 
        Vertex temp = head; 
        while(temp != null){ 
            if(temp.name.equalsIgnoreCase(name)){ 
                return temp.id;
            }
            temp = temp.next;
        }
        return 9999;
    }

    public String findById(int id){ 
        Vertex temp = head; 
        while(temp != null){
            if(temp.id == id){
                return temp.name;
            } 
            temp = temp.next;
        }
        return " ";
    }

    public Vertex findVertex(int id) { 
        Vertex temp = head; 
        while (temp != null) {
            if (temp.id == id) {
                return temp;
            } 
            temp = temp.next;
        }
        return null;
    } 

    public Vertex findVertexByName(String name) { 
        Vertex temp = head; 
        while (temp != null) { 
            if (temp.name.equalsIgnoreCase(name)) { 
                return temp; 
            } 
            temp = temp.next; 
        } 
        return null; 
    }
    
    public void addEdge(int source, int destination, int weight) { 
        Vertex sourceVertex = findVertex(source); 
        Vertex destinationVertex = findVertex(destination); 
        if (sourceVertex != null && destinationVertex != null) { 
            sourceVertex.addEdge(destination, weight); 
            destinationVertex.addEdge(source, weight); 
        } 
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
                System.out.println("- " + " Point " + 
                neighbor.id + " (Distance: " + edge.weight + ")"); 
            } 
            edge = edge.next;  
        } 
    } 

    public int getDistanceBetween(String currentLocation, String destinationName) { 
        Vertex currentVertex = findVertexByName(currentLocation); 
        if (currentVertex == null) { 
            return -1; 
        } 
        Edge edge = currentVertex.adjacencyList; 
        while (edge != null) { 
            Vertex neighbor = findVertex(edge.destination); 
            if 
            (neighbor != null && neighbor.name.equalsIgnoreCase(destinationName)) { 
                return edge.weight; 
            } 
            edge = edge.next; 
        } 
        return -1; 
    }

    public void displayCurrentLocation(String currentLocation) { 
        System.out.println("\nCurrent Location: " + currentLocation);
    } 

    public boolean isValidDestination(String currentLocation, String destination) { 
        Vertex currentVertex = findVertexByName(currentLocation); 
        if (currentVertex == null) {return false;} 
        Edge edge = currentVertex.adjacencyList; 
        while (edge != null) { 
            Vertex neighbor = findVertex(edge.destination); 
            if (neighbor != null && neighbor.name.equalsIgnoreCase(destination)) { return true;} 
            edge = edge.next;
        } 
        return false;
    } 

    public void resetGraph() { 
        Vertex current = head; 
        while (current != null) { 
            current.distance = Integer.MAX_VALUE; 
            current.visited = false;current.prev = null; 
            current = current.next;
        }
    } 
}
