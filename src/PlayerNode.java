public class PlayerNode {
    String playerName;
    int maxHp, currentHp;
    int damage;
    int level   = 1;

    Inventory inventory;

    public PlayerNode(String playerName, int maxHp) {
        this.playerName = playerName;
        this.maxHp = maxHp;
        this.currentHp = maxHp;
        this.inventory = new Inventory();
    }
}
