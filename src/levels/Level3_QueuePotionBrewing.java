package levels;

import models.NodePlayer;
import utils.Queue;
import java.util.Scanner;

public class Level3_QueuePotionBrewing {
    private NodePlayer player;
    private Scanner scanner;
    private Queue<String> brewingQueue;

    public Level3_QueuePotionBrewing(NodePlayer player, Scanner scanner) {
        this.player = player;
        this.scanner = scanner;
        this.brewingQueue = new Queue<>();
    }

    public boolean play() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("LEVEL 3: POTION BREWING ROOM - Queue Challenge");
        System.out.println("=".repeat(60));
        System.out.println("\nAnda memasuki alchemist's workshop yang penuh dengan ramuan.");
        System.out.println("Anda harus membuat potion dengan urutan yang benar menggunakan Queue!");

        // Queue of potion recipes
        String[] potionRecipes = {
            "Fire Potion|2 Blue Flower + 3 Red Herb",
            "Healing Potion|4 White Flower + 1 Magic Stone",
            "Mana Potion|3 Purple Herb + 2 Crystal",
            "Strength Potion|5 Red Herb + 2 Iron Dust"
        };

        int successfulBrews = 0;
        final int REQUIRED_BREWS = 3;

        System.out.println("\nAnda harus membuat " + REQUIRED_BREWS + " potion dengan benar!");
        System.out.println("Setiap potion akan diproses dalam urutan Queue (FIFO).\n");

        // Enqueue semua potion
        Queue<String> taskQueue = new Queue<>();
        for (String recipe : potionRecipes) {
            taskQueue.enqueue(recipe);
        }

        while (!taskQueue.isEmpty() && successfulBrews < REQUIRED_BREWS) {
            String currentTask = taskQueue.peek();
            String[] parts = currentTask.split("\\|");
            String potionName = parts[0];
            String ingredients = parts[1];

            System.out.println("--- Tugas #" + (successfulBrews + 1) + " ---");
            System.out.println("Buat: " + potionName);
            System.out.println("Bahan: " + ingredients);
            
            System.out.print("Apakah bahan sudah siap? (y/n): ");
            String ready = scanner.nextLine().trim().toLowerCase();

            if (ready.equals("y") || ready.equals("yes")) {
                // Mini game untuk brewing
                System.out.println("\n[Sedang meracik potion...]");
                
                // Brewing minigame - user harus memasukkan urutan tombol
                System.out.println("Tekan tombol dalam urutan: BLUE - RED - GREEN");
                System.out.print("Masukkan urutan (pisahkan dengan spasi): ");
                String sequence = scanner.nextLine().trim().toUpperCase();
                String[] inputs = sequence.split(" ");

                if (inputs.length == 3 && inputs[0].equals("BLUE") && inputs[1].equals("RED") && inputs[2].equals("GREEN")) {
                    System.out.println("✓ Potion berhasil dibuat!");
                    taskQueue.dequeue();
                    successfulBrews++;
                    
                    // Bonus dari potion brewing
                    if (potionName.contains("Healing")) {
                        player.healHP(30);
                        System.out.println(">>> Anda mendapat HP bonus 30!");
                    } else if (potionName.contains("Mana")) {
                        player.mana = Math.min(player.mana + 20, player.maxMana);
                        System.out.println(">>> Anda mendapat Mana bonus 20!");
                    } else if (potionName.contains("Strength")) {
                        player.attack += 3;
                        System.out.println(">>> Attack meningkat +3!");
                    }
                } else {
                    System.out.println("✗ Urutan salah! Potion gagal dibuat.");
                    System.out.println("Seharusnya: BLUE - RED - GREEN");
                }
            }
            
            System.out.println();
        }

        if (successfulBrews >= REQUIRED_BREWS) {
            System.out.println("\n--- Anda telah membuat semua potion! ---");
            System.out.println("Tiba-tiba, api liar muncul di alchemist workshop!");
            
            // Battle with Fire Elemental
            System.out.println("\nSebuah Fire Elemental muncul dari api tersebut!");
            int fireHp = 50;
            
            while (fireHp > 0 && player.isAlive()) {
                System.out.println("\n[Fire Elemental HP: " + fireHp + "] [" + player.nama + " HP: " + player.hp + "]");
                System.out.println("1. Serang dengan Potion Dingin");
                System.out.println("2. Serang Normal");
                System.out.println("3. Gunakan Healing Potion");
                
                System.out.print("Pilihan: ");
                String action = scanner.nextLine().trim();
                
                if (action.equals("1")) {
                    int damage = player.attack + 10; // Extra damage with cold potion
                    fireHp -= damage;
                    System.out.println("Anda menyerang dengan Potion Dingin! Damage: " + damage);
                } else if (action.equals("2")) {
                    int damage = player.attack + (int)(Math.random() * 6);
                    fireHp -= damage;
                    System.out.println("Anda menyerang! Damage: " + damage);
                } else if (action.equals("3")) {
                    player.healHP(30);
                    int enemyDamage = 15 + (int)(Math.random() * 5);
                    player.takeDamage(enemyDamage);
                    continue;
                }
                
                if (fireHp > 0) {
                    int enemyDamage = 15 + (int)(Math.random() * 8);
                    player.takeDamage(enemyDamage);
                }
            }
            
            if (player.isAlive()) {
                System.out.println("\n*** Anda menang! Fire Elemental telah dikalahkan! ***");
                player.gainExp(100);
                player.showStats();
                return true;
            } else {
                System.out.println("\n*** Anda kalah! ***");
                return false;
            }
        } else {
            System.out.println("\n*** Anda gagal membuat potion yang cukup! ***");
            return false;
        }
    }
}
