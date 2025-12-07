class Monster {
    Monster next;

    String nama;
    int damage;
    int health;
    int index_level; // kalo kepake

    Monster(String namaMonster, int damageMonster, int healthMonster, int indexLevelMon) {
        this.namaMonster = namaMonster;
        this.damageMonster = damageMonster;
        this.healthMonster = healthMonster;
        this.indexLevelMon = indexLevelMon;
        this.next = null;
    }
}