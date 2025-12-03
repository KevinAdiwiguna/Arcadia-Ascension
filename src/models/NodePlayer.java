package models;

public class NodePlayer {
    public String nama;
    public int hp;
    public int maxHp;
    public int mana;
    public int maxMana;
    public int attack;
    public int defense;
    public int level;
    public int exp;
    public int maxExp;
    public Inventory inventory;

    public NodePlayer(String nama) {
        this.nama = nama;
        this.hp = 100;
        this.maxHp = 100;
        this.mana = 50;
        this.maxMana = 50;
        this.attack = 10;
        this.defense = 5;
        this.level = 1;
        this.exp = 0;
        this.maxExp = 100;
        this.inventory = new Inventory();
    }

    public void levelUp() {
        this.level++;
        this.maxHp += 20;
        this.hp = this.maxHp;
        this.maxMana += 10;
        this.mana = this.maxMana;
        this.attack += 5;
        this.defense += 2;
        this.exp = 0;
        System.out.println("\n=== LEVEL UP! ===");
        System.out.println(nama + " sekarang Level " + level + "!");
        System.out.println("HP: " + hp + "/" + maxHp + ", Mana: " + mana + "/" + maxMana);
        System.out.println("Atk: " + attack + ", Def: " + defense);
    }

    public void takeDamage(int dmg) {
        int damageTaken = Math.max(1, dmg - this.defense);
        this.hp -= damageTaken;
        System.out.println(nama + " menerima " + damageTaken + " damage. Sisa HP: " + hp);
    }

    public void healHP(int amount) {
        this.hp = Math.min(this.hp + amount, this.maxHp);
        System.out.println(nama + " recover " + amount + " HP. Total HP: " + hp + "/" + maxHp);
    }

    public void gainExp(int amount) {
        this.exp += amount;
        System.out.println(nama + " mendapat " + amount + " EXP. Total: " + exp + "/" + maxExp);
        if (this.exp >= this.maxExp) {
            this.exp = 0;
            levelUp();
        }
    }

    public boolean isAlive() {
        return this.hp > 0;
    }

    public void showStats() {
        System.out.println("\n=== STATS " + nama.toUpperCase() + " ===");
        System.out.println("Level: " + level);
        System.out.println("HP: " + hp + "/" + maxHp);
        System.out.println("Mana: " + mana + "/" + maxMana);
        System.out.println("Attack: " + attack);
        System.out.println("Defense: " + defense);
        System.out.println("EXP: " + exp + "/" + maxExp);
    }
}
