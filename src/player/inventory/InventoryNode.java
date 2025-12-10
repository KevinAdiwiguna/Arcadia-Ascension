package player.inventory;

public class InventoryNode {
    private static int counter = 1;

    public String name;
    public int position;
    public int damage;
    public int healAmount;
    public InventoryNode next;

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
}
