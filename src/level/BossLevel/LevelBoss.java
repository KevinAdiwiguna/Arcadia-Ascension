package level.BossLevel;

import java.util.Scanner;
import player.PlayerNode;
import utils.ClearScreen;

public class LevelBoss {
    PlayerNode player;
    NodeBoss boss;
    Scanner input = new Scanner(System.in);

    public LevelBoss(PlayerNode player, NodeBoss boss) {
        this.player = player;
        this.boss = boss;
    }

    public void start(){
        ClearScreen.clearScreen();
        System.out.println("##=============================================================##");
        System.out.println("\t\t\t" + boss.nama);
        System.out.println("##=============================================================##");

        delay(25, 10);
        System.out.println("\nBerhati-hatilah");
        delay(25, 10);
        while(player.getCurrentHp() > 0 && boss.getCurrentHp() > 0){
            ClearScreen.clearScreen();

            System.out.println(boss.displayBoss());
            System.out.println("##=============================================================##");
            System.out.println("|| Player Health : " + player.getCurrentHp() + "\t\t\t\t||Boss Health: " + boss.getCurrentHp() + "||");
            System.out.println("|| Player Damage : " + player.getAttackPower() + "\t\t\t\t||Boss Damage: " + boss.getAttackPower() + "||");
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
                    delay(10, 150);
                    break;
                case 2:
                    // Gunakan potion

                default:
                    break;
            }
        }

        if(player.getCurrentHp() > 0){
            System.out.println("\nBos telah dikalahkan.....\n Kamu menang.............\n");
        }else{
            System.out.println("\n" + player.getPlayerName() + ", Kesempatan kamu habis.....\n Kamu Kalah.............\n");
        }

        delay(25, 150);

    }

    static void delay(int length, int ms){
        for (int i = 0; i < length; i++) {
            try {
                Thread.sleep(ms);
            } catch (Exception e) {
            }
            System.out.print(". ");
        }
    }
}