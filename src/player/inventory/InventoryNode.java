package player.inventory;

import player.PlayerNode;

public class InventoryNode {
    String name;
    int damage;     
    int healAmount; 
    InventoryNode next; 

    public InventoryNode(String name, int healAmount) {
        this.name = name;
        this.healAmount = healAmount;
        this.damage = 0;
        this.next = null;
    }

    public InventoryNode(String name, int damage, boolean isSword) {
        this.name = name;
        this.damage = damage;
        this.healAmount = 0;
        this.next = null;
    }
}
