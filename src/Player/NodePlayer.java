package Player;

public class NodePlayer{
    String nama;
    int maxHealth;
    int health;
    int maxMana;
    int mana;
    int damage;
    int defense;
    int level;

    public NodePlayer(String nama, int maxHealth, int maxMana){
        this.nama = nama;
        this.health = maxHealth;
        this.mana = maxMana;
        this.maxHealth = maxHealth;
        this.maxMana = maxMana;
    }

}