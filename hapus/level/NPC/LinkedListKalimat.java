package level.NPC;

import java.util.Scanner;

public class LinkedListKalimat {
    NodeKalimat first;
    
    public void addKalimat(String kalimat) {
        NodeKalimat newNode = new NodeKalimat(kalimat);
        if (first == null) {
            first = newNode;
        } else {
            NodeKalimat current = first;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
            newNode.back = current;
        }
    }

    public void Display(){
        NodeKalimat current = first;
        Scanner input = new Scanner(System.in);

        while (current != null) {
            System.out.println(current.kalimat);
            current = current.next;
            input.nextLine();
        }
    } 
}
