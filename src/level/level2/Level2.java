package level.level2;

import player.PlayerNode;
import utils.Display;
import utils.Utils;
import java.util.Scanner;

public class Level2 {
    private QueueLevel2 monsterQueue;
    private PlayerNode player;
    private Scanner input;

    public Level2(PlayerNode player) {
        this.player = player;
        this.monsterQueue = new QueueLevel2();
        this.input = new Scanner(System.in);
        initializeMonsters();
    }

    private void initializeMonsters() {
        monsterQueue.enqueue("Scarecrow", 1, 500);
        monsterQueue.enqueue("Slime", 10, 250);
        monsterQueue.enqueue("Goblin", 15, 300);
        monsterQueue.enqueue("Orc", 25, 500);
        monsterQueue.enqueue("Troll", 35, 650);
    }

    public void start() {
        Monster current = monsterQueue.peek();
        System.out.println("\n╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║                ⚔️ PERTEMPURAN DIMULAI ⚔️                           ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════╣");
        System.out.println("║ Kabut tebal menyelimuti medan, udara terasa tegang...           ");
        System.out.println("║ Suara gemerisik dari balik bayangan terdengar...                 ");
        System.out.println("║ Monster muncul dengan aura mematikan, matanya menatapmu tajam!  ");
        System.out.println("╚══════════════════════════════════════════════════════════════════\n");
        Utils.pressEnter();

        while (!monsterQueue.isEmpty() && player.getCurrentHp() > 0) {
            Utils.clearScreen();
            boolean won = battle(current);

            if (won) {
                System.out.println("\n🔥 " + current.nama + " roboh ke tanah!");
                System.out.println("Kamu berhasil mengalahkannya!");

                if (!monsterQueue.isEmpty()) {
                    monsterQueue.dequeue();
                    current = monsterQueue.peek();
                    if (current != null) {
                        Display.showStatistic(player);
                        System.out.println("\n🌫 Dari balik bayangan, terlihat sosok lain bergerak...");
                        System.out.println("Monster baru mendekat perlahan!");
                        System.out.println("────────────────────────────────────────────");
                        System.out.println("Monster Selanjutnya: " + current.nama);
                        System.out.println("Monster HP: " + current.health + " | Damage: " + current.damage);
                        System.out.println("\nTekan enter untuk melanjutkan battle...");
                        input.nextLine();
                    }
                }
            } else {
                System.out.println("\nKamu dikalahkan oleh " + current.nama);
                break;
            }
        }
        if (monsterQueue.isEmpty()) {
            System.out.println("\nLEVEL 2 DITAKLUKAN!");
            System.out.println("Kamu mengalahkan semua monster!");
            System.out.println("Final HP: " + player.getCurrentHp() + "/" + player.getMaxHp());
            player.levelUp();
            Utils.pressEnter();
        } else if (player.getCurrentHp() <= 0) {
            System.out.println("\nKamu Pingsan...");
        }
    }

    private boolean battle(Monster monster) {
        int monsterHealth = monster.health;
        int playerHealth = player.getCurrentHp();

        Utils.clearScreen();
        System.out.println("\n══════════════════════════════════════════");
        System.out.println("            ⚔  BATTLE DIMULAI!  ⚔");
        System.out.println("══════════════════════════════════════════");
        System.out.println("Monster muncul dari balik kabut...");
        System.out.println("➡  " + monster.nama + " menatapmu dengan aura mematikan!");
        System.out.println("══════════════════════════════════════════");

        while (monsterHealth > 0 && playerHealth > 0) {
            System.out.println("\n────────────────────────────────────────────");
            System.out.println("⚔  " + player.getPlayerName() + "  VS  " + monster.nama + "  ⚔");
            System.out.println("────────────────────────────────────────────");
            System.out.println("❤️  HP Kamu   : " + playerHealth + " / " + player.getMaxHp());
            System.out.println("💀  HP Musuh  : " + monsterHealth + " / " + monster.maxhealth);
            System.out.println("────────────────────────────────────────────");

            playerTurn(monster);

            monsterHealth = monster.health;

            if (monsterHealth <= 0) {
                return true;
            }

            monsterTurn();
            playerHealth = player.getCurrentHp();

            if (playerHealth <= 0) {
                player.setCurrentHp(0);
                return false;
            }
        }

        player.setCurrentHp(playerHealth);
        return true;
    }

    private void playerTurn(Monster monster) {
        boolean validChoice = false;

        while (!validChoice) {
            System.out.println("\n🎯 GILIRANMU!");
            System.out.println("Pilihan Aksi:");
            System.out.println("1. 🗡  Serang");
            System.out.println("2. 🧪  Gunakan Potion");
            System.out.print("Pilih Aksi [1/2]: ");

            try {
                int choice = input.nextInt();
                input.nextLine();

                switch (choice) {
                    case 1:
                        Utils.clearScreen();
                        int damageDealt = player.attack();
                        System.out.println("⚡ Kamu meluncurkan serangan cepat!");
                        System.out.println("➡ Seranganmu memberikan " + damageDealt + " damage!");
                        monster.health -= damageDealt;
                        validChoice = true;
                        break;

                    case 2:
                        Utils.potionUse(player);
                        validChoice = false;
                        break;

                    default:
                        System.out.println("Pilihan tidak valid. Silahkan pilih 1, or 2.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Pilihan tidak valid. Tolong masukkan sebuah angka.");
                input.nextLine();
            }
        }
    }

    private void monsterTurn() {
        Monster currentMonster = monsterQueue.peek();
        int monsterDamage = currentMonster.damage;
        int newPlayerHp = player.getCurrentHp();

        System.out.println("\n💥 " + currentMonster.nama + " mengayunkan serangannya!");
        System.out.println("➡ Kamu menerima " + monsterDamage + " damage!");

        newPlayerHp = player.getCurrentHp() - monsterDamage;

        if (newPlayerHp < 0)
            newPlayerHp = 0;

        player.setCurrentHp(newPlayerHp);

        if (newPlayerHp <= 0) {
            System.out.println("Kamu telah dikalahkan...");
        }
    }
}