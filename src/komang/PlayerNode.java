public class PlayerNode {
    private String playerName;
    private int maxHp;
    private int currentHp;
    private int damage;
    private int level = 1;

    private Inventory inventory;

    public PlayerNode(String playerName, int maxHp, int damage) {
        this.playerName = playerName;
        this.maxHp = maxHp;
        this.currentHp = maxHp;
        this.damage = damage;
        this.inventory = new Inventory();
    }

    public String getPlayerName() { return playerName; }
    public int getMaxHp() { return maxHp; }
    public int getCurrentHp() { return currentHp; }
    public int getDamage() { return damage; }
    public int getLevel() { return level; }
    public Inventory getInventory() { return inventory; }

    public void setCurrentHp(int hp) { this.currentHp = hp; }
    public void setDamage(int damage) { this.damage = damage; }
    public void levelUp() { this.level++; }
    public void kurangiHp(int n) { currentHp -= n; }
    public void tambahXp(int n) { level += n; }
}
