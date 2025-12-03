package levels;

import models.NodePlayer;
import java.util.Scanner;

public class BossFight {
    private NodePlayer player;
    private Scanner scanner;

    public BossFight(NodePlayer player, Scanner scanner) {
        this.player = player;
        this.scanner = scanner;
    }

    public boolean play() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("FINAL BOSS FIGHT: THE DARK LORD");
        System.out.println("=".repeat(60));

        System.out.println("\nSetelah melalui 5 level yang menantang, " + player.nama);
        System.out.println("akhirnya tiba di ruangan terakhir...");

        System.out.println("\n" + "-".repeat(60));
        System.out.println("Sebuah makhluk gelap dengan kekuatan menakjubkan muncul!");
        System.out.println("Ini adalah... THE DARK LORD!");
        System.out.println("-".repeat(60));

        player.showStats();

        int darkLordHp = 150;
        int darkLordAttack = 25;
        int darkLordDefense = 10;
        int roundNumber = 0;
        final int MAX_ROUNDS = 20;

        System.out.println("\n[Dark Lord Stats]");
        System.out.println("HP: " + darkLordHp + " | Attack: " + darkLordAttack + " | Defense: " + darkLordDefense);

        while (darkLordHp > 0 && player.isAlive() && roundNumber < MAX_ROUNDS) {
            roundNumber++;
            System.out.println("\n" + "=".repeat(40));
            System.out.println("ROUND " + roundNumber);
            System.out.println("=".repeat(40));
            System.out.println("Dark Lord HP: " + darkLordHp);
            System.out.println(player.nama + " HP: " + player.hp + "/" + player.maxHp);
            System.out.println(player.nama + " Mana: " + player.mana + "/" + player.maxMana);

            System.out.println("\n[" + player.nama + "'s Action]");
            System.out.println("1. Heavy Attack");
            System.out.println("2. Quick Attack");
            System.out.println("3. Use Ultimate Spell");
            System.out.println("4. Heal");
            System.out.println("5. Defend");

            System.out.print("Pilihan: ");
            String action = scanner.nextLine().trim();

            // Player action
            switch (action) {
                case "1": // Heavy Attack
                    {
                        int damage = (int)(player.attack * 1.5) + (int)(Math.random() * 15);
                        damage = Math.max(1, damage - darkLordDefense);
                        darkLordHp -= damage;
                        System.out.println(">>> " + player.nama + " melakukan Heavy Attack!");
                        System.out.println(">>> Damage: " + damage);
                        break;
                    }
                case "2": // Quick Attack
                    {
                        int damage = player.attack + (int)(Math.random() * 8);
                        damage = Math.max(1, damage - darkLordDefense);
                        darkLordHp -= damage;
                        System.out.println(">>> " + player.nama + " melakukan Quick Attack!");
                        System.out.println(">>> Damage: " + damage);
                        break;
                    }
                case "3": // Ultimate Spell
                    {
                        if (player.mana >= 30) {
                            int damage = (int)(player.attack * 2) + (int)(Math.random() * 20);
                            damage = Math.max(1, damage - darkLordDefense);
                            darkLordHp -= damage;
                            player.mana -= 30;
                            System.out.println(">>> " + player.nama + " melempar ULTIMATE SPELL!");
                            System.out.println(">>> Damage: " + damage);
                            System.out.println(">>> Mana terpakai: 30");
                        } else {
                            System.out.println(">>> Mana tidak cukup! (Butuh 30, punya " + player.mana + ")");
                            System.out.println(">>> " + player.nama + " melakukan Quick Attack sebagai gantinya!");
                            int damage = player.attack + (int)(Math.random() * 8);
                            damage = Math.max(1, damage - darkLordDefense);
                            darkLordHp -= damage;
                            System.out.println(">>> Damage: " + damage);
                        }
                        break;
                    }
                case "4": // Heal
                    {
                        player.healHP(40);
                        System.out.println(">>> " + player.nama + " menggunakan skill heal!");
                        break;
                    }
                case "5": // Defend
                    {
                        System.out.println(">>> " + player.nama + " melakukan defensive stance!");
                        break;
                    }
                default:
                    {
                        System.out.println(">>> Aksi tidak valid!");
                        roundNumber--;
                        continue;
                    }
            }

            if (darkLordHp <= 0) break;

            // Dark Lord counter-attack
            System.out.println("\n[Dark Lord's Counter-Attack]");

            int darkAction = (int)(Math.random() * 3);
            switch (darkAction) {
                case 0: // Normal Attack
                    {
                        int damage = darkLordAttack + (int)(Math.random() * 10);
                        
                        // If player defended, reduce damage
                        if (action.equals("5")) {
                            damage = (int)(damage * 0.5);
                            System.out.println(">>> Dark Lord melakukan Powerful Strike!");
                            System.out.println(">>> (Berkurang karena Anda defend)");
                        } else {
                            System.out.println(">>> Dark Lord melakukan Powerful Strike!");
                        }
                        
                        player.takeDamage(damage);
                        break;
                    }
                case 1: // Dark Magic
                    {
                        int damage = darkLordAttack + 10 + (int)(Math.random() * 15);
                        System.out.println(">>> Dark Lord melempar Dark Magic!");
                        player.takeDamage(damage);
                        break;
                    }
                case 2: // Drain Life
                    {
                        int damage = Math.max(10, player.hp / 4);
                        darkLordHp += damage / 2; // Dark Lord recover
                        System.out.println(">>> Dark Lord menggunakan Drain Life!");
                        System.out.println(">>> Dark Lord recover " + (damage / 2) + " HP!");
                        player.takeDamage(damage);
                        break;
                    }
            }

            // Restore some mana each turn
            player.mana = Math.min(player.mana + 5, player.maxMana);
        }

        System.out.println("\n" + "=".repeat(60));

        if (darkLordHp <= 0) {
            System.out.println("***.***.***.***.***.***.***.***.***.***");
            System.out.println("***   ANDA TELAH MENGALAHKAN DARK LORD!  ***");
            System.out.println("***.***.***.***.***.***.***.***.***.***");
            System.out.println("\nSelamat datang, " + player.nama + "!");
            System.out.println("Anda telah menyelamatkan dunia dari kegelapan!");
            System.out.println("\n--- FINAL STATS ---");
            player.showStats();
            System.out.println("\n=== GAME COMPLETED ===");
            return true;
        } else {
            System.out.println("*** ANDA KALAH MELAWAN DARK LORD ***");
            System.out.println("\nDark Lord masih berdiri dengan HP: " + darkLordHp);
            System.out.println("Anda harus mencoba lagi...");
            return false;
        }
    }
}
