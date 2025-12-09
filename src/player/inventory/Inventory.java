
package player.inventory;

import java.util.Stack;

import player.PlayerNode;

public class Inventory {
    public InventoryNode top;
    private int size;
    private PlayerNode owner;

    public Inventory() {
        top = null;
        size = 0;
        owner = null;
    }

    public Inventory(PlayerNode owner) {
        this();
        this.owner = owner;
    }

    public void setOwner(PlayerNode owner) { this.owner = owner; }

    public void push(InventoryNode node) {
        node.next = top;
        top = node;
        size++;
        System.out.println(node.name + " ditambahkan ke inventory.");
    }

    public InventoryNode pop() {
        if (isEmpty()) {
            System.out.println("Inventory kosong!");
            return null;
        }
        InventoryNode removed = top;
        top = top.next;
        size--;
        System.out.println(removed.name + " diambil dari inventory.");
        return removed;
    }

    public InventoryNode peek() {
        if (isEmpty()) {
            System.out.println("Inventory kosong!");
            return null;
        }
        return top;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void printInventory() {
        InventoryNode current = top;
        System.out.println("Isi Inventory:");
        while (current != null) {
            System.out.print("- " + current.name);
            if (current.healAmount > 0)
                System.out.println(", heal: " + current.healAmount);
            else if (current.damage > 0)
                System.out.println(", damage: " + current.damage);
            else
                System.out.println();
            current = current.next;
        }
    }

    public int hitungTotalInventory(){
        InventoryNode current = top;
        int count = 0;
        while (current != null) {
            count++;
            current = current.next;
        }

        return count;
    }

    public boolean SearchPotion(String namaPotion){
        InventoryNode current = top;
        
        while (current != null) {
            if(current.name != null && current.name.equalsIgnoreCase(namaPotion)){
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void UsePotion(String namaPotion){
        

        if (isEmpty()){
            System.out.println("Inventory kosong!");
            return;
        }

        Stack<InventoryNode> temp = new Stack<>();

        // Pindahkan item teratas ke stack sementara sampai menemukan potion yang dicari
        while (top != null && (top.name == null || !top.name.equalsIgnoreCase(namaPotion))) {
            InventoryNode moved = pop();
            if (moved != null) temp.push(moved);
        }

        // Jika sudah habis dan tidak ditemukan
        if (top == null) {
            System.out.println("Potion '" + namaPotion + "' tidak ditemukan dalam inventory.");
            // Kembalikan semua item dari stack sementara ke inventory
            while (!temp.isEmpty()) {
                push(temp.pop());
            }
            return;
        }

        // Top sekarang adalah potion yang dicari
        InventoryNode found = pop();
        if (found != null) {
            if (found.healAmount > 0) {
                if (owner != null) {
                    owner.Heal(found.healAmount);
                    System.out.println(owner.getPlayerName() + " healed " + found.healAmount + " HP oleh " + found.name + ".");
                } else {
                    System.out.println("Menggunakan potion " + found.name + ", heal " + found.healAmount + " HP! (tidak ada owner untuk di-heal)");
                }
            } else {
                System.out.println("Potion '" + namaPotion + "' tidak memiliki efek heal.");
            }
            System.out.println("Potion '" + namaPotion + "' digunakan dan dihapus dari inventory.");
        }

        while (!temp.isEmpty()) {
            push(temp.pop());
        }
    }


    // Ukuran stack
    public int getSize() {
        return size;
    }
}