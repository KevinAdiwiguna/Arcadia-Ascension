class Level2 {
    Monster front = null;
    Monster rear = null;

    boolean isEmpty() {
        return front == null;
    }

    void enqueue(String namaMonster, int damageMonster, int healthMonster, int indexLevelMon) {
        Monster newMonster = new Monster(namaMonster, damageMonster, healthMonster, indexLevelMon);

        if (isEmpty()) {
            front = newMonster;
            rear = newMonster;
            return;
        } 
        rear.next = newMonster;
        rear = newMonster;
    }

    void dequeue() {
        if (isEmpty()) {
            return;
        }
        front = front.next;
        if (front == null) {
            rear = null;
        }
    }
}