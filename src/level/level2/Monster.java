package level.level2;
class Monster {
    Monster next;
    String nama;
    int damage;
    int health;
    int index_level; // kalo kepake

    Monster(String namaMonster, int damageMonster, int healthMonster, int indexLevelMon) {
        this.nama = namaMonster;
        this.damage = damageMonster;
        this.health = healthMonster;
        this.index_level = indexLevelMon;
        this.next = null;
    }
}