package models;

public class ItemNode {
    public String name;
    public String type; // Weapon, Potion, Key, Spell
    public int value;   // damage, healing amount, atau value
    public ItemNode next;

    public ItemNode(String name, String type, int value) {
        this.name = name;
        this.type = type;
        this.value = value;
        this.next = null;
    }

    public ItemNode(String name, String type) {
        this(name, type, 0);
    }
}
