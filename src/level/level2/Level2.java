package level.level2;

import player.PlayerNode;

public class Level2 {
    Monster front = null;
    Monster rear = null;
    PlayerNode player;

    public Level2(PlayerNode player) {
        this.player = player;
    }

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