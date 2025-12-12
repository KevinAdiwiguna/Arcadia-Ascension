package player.inventory;

import player.PlayerNode;
import utils.Utils;

public class Inventory {
    private InventoryNode top;
    int size;

    public Inventory() {
        top = null;
        size = 0;
    }

    // Push node ke stack
    public void push(InventoryNode node) {
        node.next = top;
        top = node;
        size++;

        System.out.println("\n╔════════════════════════════════════════════╗");
        System.out.println("║ Potion " + node.name + " ditambahkan! ");
        System.out.println("║ Heal Amount : " + node.healAmount);
        System.out.println("╚════════════════════════════════════════════╝");
    }

    // Pop node dari stack
    public InventoryNode pop() {
        if (isEmpty()) {
            System.out.println("\n╔════════════════════════════════════════╗");
            System.out.println("║  Inventory kosong! Tidak ada item yang bisa diambil. ║");
            System.out.println("╚════════════════════════════════════════╝");
            return null;
        }

        InventoryNode removed = top;
        top = top.next;
        size--;

        System.out.println("\n╔════════════════════════════════════════════════════╗");
        System.out.println("║ Item: " + removed.name + " diambil dari inventory");
        System.out.println("╚════════════════════════════════════════════════════╝");

        return removed;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void printInventory() {
        InventoryNode current = top;
        System.out.println("Isi Inventory:");
        while (current != null) {
            System.out.print("- " + current.name + " (posisi: " + current.position + ")");
            if (current.healAmount > 0)
                System.out.println(", heal: " + current.healAmount);
            else if (current.damage > 0)
                System.out.println(", damage: " + current.damage);
            else
                System.out.println();
            current = current.next;
        }
    }

    public boolean SearchPotion(String namaPotion) {
        InventoryNode current = top;
        while (current != null) {
            if (current.name != null && current.name.equalsIgnoreCase(namaPotion)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void UsePotion(String namaPotion, PlayerNode player) {
        if (isEmpty()) {
            System.out.println("\n╔════════════════════════════════════╗");
            System.out.println("║ Inventory kosong! Tidak ada potion yang bisa digunakan. ║");
            System.out.println("╚════════════════════════════════════╝");
            return;
        }

        InventoryNode tempStack = null;

        while (top != null && (top.name == null || !top.name.equalsIgnoreCase(namaPotion))) {
            InventoryNode moved = pop();
            moved.next = tempStack;
            tempStack = moved;
        }

        if (top == null) {
            System.out.println("\n╔════════════════════════════════════╗");
            System.out.println("║ Potion '" + namaPotion + "' tidak ditemukan dalam inventory! ║");
            System.out.println("╚════════════════════════════════════╝");

            while (tempStack != null) {
                InventoryNode node = tempStack;
                tempStack = tempStack.next;
                push(node);
            }
            return;
        }

        InventoryNode found = pop();
        if (found != null && player != null) {
            player.Heal(found.healAmount);

            System.out.println("\n╔════════════════════════════════════════════╗");
            System.out.println("║ Potion '" + found.name + "' digunakan! ✨");
            System.out.println("║ " + player.getPlayerName() + " sembuh sebanyak " + found.healAmount + " HP!");
            System.out.println("╚════════════════════════════════════════════╝");

            Utils.loadingAnimation(20, 100);
        }

        while (tempStack != null) {
            InventoryNode node = tempStack;
            tempStack = tempStack.next;
            push(node);
        }
    }

    public int hitungTotalInventory() {
        InventoryNode current = top;
        int count = 0;
        while (current != null) {
            count++;
            current = current.next;
        }

        return count;
    }
}
