package player.inventory;

public class InventoryNode {
    private static int counter = 1;

    String name;
    int position;
    int damage;     
    int healAmount; 
    InventoryNode next; 

    public InventoryNode(String name, int healAmount) {
        this.position = counter++;
        this.name = name;
        this.healAmount = healAmount;
        this.damage = 0;
        this.next = null;
    }

    public InventoryNode(String name, int damage, boolean isSword) {
        this.position = counter++;
        this.name = name;
        this.damage = damage;
        this.healAmount = 0;
        this.next = null;
    }

    public void use() {
        if (healAmount > 0) {
            System.out.println("Menggunakan potion " + name + ", heal " + healAmount + " HP!");
        } else if (damage > 0) {
            System.out.println("Menggunakan pedang " + name + ", attack " + damage + " damage!");
        } else {
            System.out.println("Item " + name + " tidak memiliki efek.");
        }
    }
}
