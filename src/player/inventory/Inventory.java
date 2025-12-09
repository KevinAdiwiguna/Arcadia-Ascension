package player.inventory;

public class Inventory {
    private InventoryNode top; 
    private int size;

    public Inventory() {
        top = null;
        size = 0;
    }

    // Push node ke stack
    public void push(InventoryNode node) {
        node.next = top;
        top = node;
        size++;
        System.out.println(node.name + " ditambahkan ke inventory di posisi " + node.position);
    }

    // Pop node dari stack
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

    // Lihat node paling atas
    public InventoryNode peek() {
        if (isEmpty()) {
            System.out.println("Inventory kosong!");
            return null;
        }
        return top;
    }

    // Cek apakah stack kosong
    public boolean isEmpty() {
        return top == null;
    }

    // Print semua item
    public void printInventory() {
        InventoryNode current = top;
        System.out.println("Isi Inventory:");
        while (current != null) {
            System.out.print("- " + current.name + " (posisi: " + current.position + ")");
            if (current.healAmount > 0) System.out.println(", heal: " + current.healAmount);
            else if (current.damage > 0) System.out.println(", damage: " + current.damage);
            else System.out.println();
            current = current.next;
        }
    }

    // Ukuran stack
    public int getSize() {
        return size;
    }
}