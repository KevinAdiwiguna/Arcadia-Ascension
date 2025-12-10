package level.level2;

public class QueueLevel2 {
    private Monster front = null;
    private Monster rear = null;

    boolean isEmpty() {
        return front == null;
    }

    public void enqueue(String namaMonster, int damageMonster, int healthMonster) {
        Monster newMonster = new Monster(namaMonster, damageMonster, healthMonster);

        if (isEmpty()) {
            front = newMonster;
            rear = newMonster;
            return;
        } 
        rear.next = newMonster;
        rear = newMonster;
    }

    public void dequeue() {
        if (isEmpty()) {
            return;
        }
        front = front.next;
        if (front == null) {
            rear = null;
        }
    }

    public Monster peek() {
        return front;
    }

}