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
        while(true){
            ClearScreen.clearScreen();

            System.out.println(boss.displayBoss());
            System.out.println("##=============================================================##");
            System.out.println("|| Player Health : " + player.getCurrentHp() + "        ||Boss Health: " + boss.getCurrentHp() + "||");
            System.out.println("|| Player Damage : " + player.getDamage() + "           ||Boss Damage: " + boss.getDamage() + "||");
            System.out.println("##=============================================================##");
            System.out.println("ADUHAIT BOSS INI KERAS SEKALI! (BELUM SELESAI)");
            int pilihan = input.nextInt();
            
        }
    }
}