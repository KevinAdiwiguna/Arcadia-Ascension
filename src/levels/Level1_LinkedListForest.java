package levels;

import models.NodePlayer;
import java.util.Scanner;

public class Level1_LinkedListForest {
    private NodePlayer player;
    private Scanner scanner;
    private int treasuresFound = 0;

    public Level1_LinkedListForest(NodePlayer player, Scanner scanner) {
        this.player = player;
        this.scanner = scanner;
    }

    public boolean play() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("LEVEL 1: THE CHAIN FOREST - Linked List Challenge");
        System.out.println("=".repeat(60));
        System.out.println("\nAnda masuk ke hutan gelap yang penuh dengan peti harta karun.");
        System.out.println("Setiap peti terhubung dalam satu rantai panjang.");
        System.out.println("Anda harus mengumpulkan semua item dari peti-peti tersebut!");

        // Simulasi harta karun dalam linked list
        String[] treasures = {
            "Rusty Sword,Weapon,5",
            "Health Potion,Potion,10",
            "Ancient Map,Key,0",
            "Gold Coin,Currency,25",
            "Iron Shield,Armor,3"
        };

        System.out.println("\n--- Anda menemukan " + treasures.length + " peti harta karun! ---");
        
        for (int i = 0; i < treasures.length; i++) {
            String[] parts = treasures[i].split(",");
            String itemName = parts[0];
            String itemType = parts[1];
            int itemValue = Integer.parseInt(parts[2]);

            System.out.println("\n[Peti " + (i + 1) + "] Anda menemukan: " + itemName);
            
            System.out.print("Ambil item ini? (y/n): ");
            String choice = scanner.nextLine().trim().toLowerCase();
            
            if (choice.equals("y") || choice.equals("yes")) {
                player.inventory.addItem(itemName, itemType, itemValue);
                treasuresFound++;
                
                // Bonus dari mengumpulkan item
                if (itemType.equals("Weapon")) {
                    player.attack += itemValue;
                    System.out.println(">>> Attack meningkat +" + itemValue);
                } else if (itemType.equals("Armor")) {
                    player.defense += itemValue;
                    System.out.println(">>> Defense meningkat +" + itemValue);
                } else if (itemType.equals("Potion")) {
                    player.healHP(itemValue);
                }
            }
        }

        // Battle dengan Goblin
        System.out.println("\n" + "-".repeat(60));
        System.out.println("Tiba-tiba, seekor Goblin Besar keluar dari semak-semak!");
        System.out.println("-".repeat(60));

        int goblinHp = 30;
        boolean battleWon = false;

        while (goblinHp > 0 && player.isAlive()) {
            System.out.println("\n[Goblin HP: " + goblinHp + "] [" + player.nama + " HP: " + player.hp + "]");
            System.out.println("1. Serang");
            System.out.println("2. Gunakan Potion");
            System.out.println("3. Lari");
            
            System.out.print("Pilihan: ");
            String action = scanner.nextLine().trim();

            if (action.equals("1")) {
                int damage = player.attack + (int)(Math.random() * 5);
                goblinHp -= damage;
                System.out.println("Anda menyerang! Damage: " + damage);
                
                if (goblinHp <= 0) {
                    battleWon = true;
                    break;
                }
                
                int goblinDamage = 8 + (int)(Math.random() * 4);
                player.takeDamage(goblinDamage);
                
            } else if (action.equals("2")) {
                player.healHP(20);
                int goblinDamage = 8;
                player.takeDamage(goblinDamage);
                
            } else if (action.equals("3")) {
                System.out.println("Anda berhasil lari dari Goblin!");
                return false;
            }
        }

        if (battleWon) {
            System.out.println("\n*** Anda menang melawan Goblin! ***");
            player.gainExp(50);
            System.out.println("\nLevel 1 Selesai! Anda telah mengumpulkan " + treasuresFound + " item.");
            player.showStats();
            return true;
        } else {
            System.out.println("\n*** Anda kalah! Game Over! ***");
            return false;
        }
    }
}
