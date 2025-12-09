package player;

public class PlayerNode {
    private String playerName;
    private int maxHp;
    private int currentHp;
    private int attackPower;
    private int level = 1;

    private Inventory inventory;

    public PlayerNode(String playerName, int maxHp, int attackPower) {
        this.playerName = playerName;
        this.maxHp = maxHp;
        this.currentHp = maxHp;
        this.attackPower = attackPower;
        this.inventory = new Inventory();
    }

    public String getPlayerName() { return playerName; }
    public int getMaxHp() { return maxHp; }
    public int getCurrentHp() { return currentHp; }
    public int getAttackPower() { return attackPower; }//untuk menyerang
    public int getLevel() { return level; }
    public Inventory getInventory() { return inventory; }
    
    public int attack() { return attackPower; }//untuk menyerang

    //set
    public void setCurrentHp(int hp) { this.currentHp = hp; }
    public void setDamage(int attackPower) { this.attackPower = attackPower; }
    public void levelUp() { this.level++; }

    public void getDamage(int damage){//terima damage
        currentHp -= damage;
        if(currentHp <= 0){
            //game over;
        }
    }

    @Override
    public String toString() {
        return "PlayerNode{" +
                "name='" + playerName + '\'' +
                ", HP=" + currentHp + "/" + maxHp +
                ", DMG=" + attackPower +
                ", Level=" + level +
                '}';
    }
}
