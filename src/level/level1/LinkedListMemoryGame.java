package level.level1;

public class LinkedListMemoryGame {

    NodeMemoryGame head;

    private int createRandomInt() {
        return (int) (Math.random() * 10);  
    }

    public void add(int value) {
        NodeMemoryGame newNode = new NodeMemoryGame(value);

        if (head == null) {
            head = newNode;
            return;
        }

        NodeMemoryGame temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    public void addRandom(int amount) {
        for (int i = 0; i < amount; i++) {
            add(createRandomInt());
        }
    }

    public void show() {
        NodeMemoryGame temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public boolean equalsList(int[] arr) {
        NodeMemoryGame temp = head;
        int i = 0;

        while (temp != null && i < arr.length) {
            if (temp.data != arr[i]) {
                return false;
            }
            temp = temp.next;
            i++;
        }

        return temp == null && i == arr.length;
    }
}
