package models;

public class Inventory {
    private ItemNode head;

    public Inventory() {
        this.head = null;
    }

    // Method untuk menambah item (Insert at End)
    public void addItem(String name, String type, int value) {
        ItemNode newItem = new ItemNode(name, type, value);
        if (head == null) {
            head = newItem;
        } else {
            ItemNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newItem;
        }
        System.out.println("[INFO] Mendapatkan item: " + name + " (" + type + ")");
    }

    public void addItem(String name, String type) {
        addItem(name, type, 0);
    }

    // Method untuk melihat isi tas (Traversal)
    public void showItems() {
        if (head == null) {
            System.out.println(">> Tas kamu kosong.");
            return;
        }
        System.out.println(">> Isi Tas Kamu:");
        ItemNode current = head;
        int count = 1;
        while (current != null) {
            if (current.value > 0) {
                System.out.println("   " + count + ". " + current.name + " (" + current.type + ") - Value: " + current.value);
            } else {
                System.out.println("   " + count + ". " + current.name + " (" + current.type + ")");
            }
            current = current.next;
            count++;
        }
    }

    // Method untuk mencari item
    public ItemNode searchItem(String name) {
        ItemNode current = head;
        while (current != null) {
            if (current.name.equalsIgnoreCase(name)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    // Method untuk menghapus item
    public boolean removeItem(String name) {
        if (head == null) return false;
        
        if (head.name.equalsIgnoreCase(name)) {
            head = head.next;
            System.out.println("[INFO] Item " + name + " dihapus.");
            return true;
        }
        
        ItemNode current = head;
        while (current.next != null) {
            if (current.next.name.equalsIgnoreCase(name)) {
                current.next = current.next.next;
                System.out.println("[INFO] Item " + name + " dihapus.");
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Method untuk menghitung jumlah item
    public int countItems() {
        int count = 0;
        ItemNode current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public ItemNode getHead() {
        return head;
    }
}
