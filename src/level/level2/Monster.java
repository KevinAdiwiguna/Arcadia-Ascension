package level.level2;
class Monster {
    Monster next;
    String nama;
    int damage;
    int health;
    int maxhealth;

    Monster(String namaMonster, int damageMonster, int healthMonster) {
        this.nama = namaMonster;
        this.damage = damageMonster;
        this.health = healthMonster;
        this.maxhealth = healthMonster;
        this.next = null;
    }
}