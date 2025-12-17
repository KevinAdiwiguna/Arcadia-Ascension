package level.BossLevel;

import java.util.Scanner;

import player.*;
import utils.Utils;

public class LevelBoss {
    PlayerNode player;
    NodeBoss boss;
    Scanner input = new Scanner(System.in);
    Scanner inputString = new Scanner(System.in);

    public LevelBoss(PlayerNode player) {
        this.player = player;
    }

    public void start() {

        NodeBoss boss = new NodeBoss("Dark Lord", 1000, 75);

        Utils.clearScreen();
        System.out.println("##=============================================================##");
        System.out.println("\t\t\t" + boss.nama);
        System.out.println("##=============================================================##");

        Utils.loadingAnimation(25, 10);
        System.out.println("\nBerhati-hatilah");
        Utils.loadingAnimation(25, 10);
        while (player.getCurrentHp() > 0 && boss.getCurrentHp() > 0) {
            Utils.clearScreen();

            System.out.println(boss.displayBoss());
            System.out.println("##=============================================================##");
            System.out.println("|| Player Health : " + player.getCurrentHp() + "\t\t\t\t||Boss Health: "
                    + boss.getCurrentHp() + "||");
            System.out.println("|| Player Damage : " + player.getAttackPower() + "\t\t\t\t||Boss Damage: "
                    + boss.getAttackPower() + "||");
            System.out.println("##=============================================================##");

            System.out.println("Berikan pilihan anda: ");
            System.out.println("1. Serang");
            System.out.println("2. Gunakan Potion");
            int pilihan = input.nextInt();
            switch (pilihan) {
                case 1:
                    boss.takeDamage(player.attack());
                    System.out.println("Boss marah dan menyerang anda lagi...");
                    player.getDamage(boss.getAttackPower());
                    Utils.loadingAnimation(10, 150);
                    break;
                case 2:
                    player.inventory.printInventory();
                    int count = player.inventory.hitungTotalInventory();
                    if (count <= 0) {
                        System.out.println("Anda tidak memiliki barang..");
                    } else {
                        Utils.potionUse(player);
                    }

                default:
                    break;
            }
        }

        if (player.getCurrentHp() > 0) {
            System.out.println("\nBos telah dikalahkan.....\n Kamu menang.............\n");
        } else {
            System.out.println(
                    "\n" + player.getPlayerName() + ", Kesempatan kamu habis.....\n Kamu Kalah.............\n");
        }
 // tes push
        Utils.loadingAnimation(25, 150);

    }
}