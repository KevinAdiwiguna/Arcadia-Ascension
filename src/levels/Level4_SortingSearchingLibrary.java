package levels;

import models.NodePlayer;
import java.util.Scanner;
import java.util.Arrays;

public class Level4_SortingSearchingLibrary {
    private NodePlayer player;
    private Scanner scanner;

    private class Spell implements Comparable<Spell> {
        String name;
        int damage;
        int manaCost;

        Spell(String name, int damage, int manaCost) {
            this.name = name;
            this.damage = damage;
            this.manaCost = manaCost;
        }

        @Override
        public int compareTo(Spell other) {
            return this.damage - other.damage; // Sort by damage
        }

        @Override
        public String toString() {
            return name + " (DMG: " + damage + ", Mana: " + manaCost + ")";
        }
    }

    public Level4_SortingSearchingLibrary(NodePlayer player, Scanner scanner) {
        this.player = player;
        this.scanner = scanner;
    }

    public boolean play() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("LEVEL 4: MAGIC LIBRARY - Sorting & Searching Challenge");
        System.out.println("=".repeat(60));
        System.out.println("\nAnda memasuki perpustakaan sihir yang penuh dengan spell book.");
        System.out.println("Anda harus mengorganisir spell dan mencari yang tepat!");

        // Array of spells
        Spell[] spells = {
            new Spell("Fireball", 25, 15),
            new Spell("Ice Storm", 20, 12),
            new Spell("Lightning", 30, 18),
            new Spell("Heal", 0, 10),
            new Spell("Meteor", 40, 25),
            new Spell("Thunder", 35, 20),
            new Spell("Frost Nova", 22, 14)
        };

        System.out.println("\n--- Spell Library Found! ---");
        System.out.println("Total spells: " + spells.length);

        // Challenge 1: Sorting
        System.out.println("\n[Challenge 1: SORTING]");
        System.out.println("Urutkan spell berdasarkan damage (dari terendah ke tertinggi):");
        System.out.println("\nSpell sebelum sorting:");
        for (int i = 0; i < spells.length; i++) {
            System.out.println((i + 1) + ". " + spells[i]);
        }

        // Bubble sort implementation
        bubbleSort(spells);

        System.out.println("\n>>> Spell sudah diurutkan!");
        for (int i = 0; i < spells.length; i++) {
            System.out.println((i + 1) + ". " + spells[i]);
        }

        player.attack += 5;
        System.out.println("\n✓ Attack +5 karena berhasil mengorganisir spell!");

        // Challenge 2: Searching
        System.out.println("\n[Challenge 2: SEARCHING]");
        System.out.println("Cari spell spesifik yang Anda butuhkan!");
        
        String[] targetSpells = {"Lightning", "Meteor", "Heal"};
        int correctSearches = 0;

        for (String target : targetSpells) {
            System.out.print("\nCari spell: " + target + " > ");
            String userInput = scanner.nextLine().trim();

            // Binary search (array harus sorted)
            Spell found = binarySearch(spells, userInput);

            if (found != null && found.name.equalsIgnoreCase(target)) {
                System.out.println("✓ Benar! " + found);
                correctSearches++;
                
                // Add spell to player's arsenal
                if (found.manaCost <= player.mana) {
                    player.mana -= found.manaCost;
                    player.attack += found.damage;
                    System.out.println(">>> Spell ditambahkan! Attack +" + found.damage);
                } else {
                    System.out.println(">>> Mana tidak cukup untuk spell ini!");
                }
            } else if (found != null) {
                System.out.println("✗ Salah! Anda menemukan: " + found.name);
            } else {
                System.out.println("✗ Spell tidak ditemukan!");
            }
        }

        if (correctSearches >= 2) {
            System.out.println("\n--- Perpustakaan mulai bergetar! ---");
            System.out.println("Seorang Ancient Librarian muncul!");
            
            // Battle dengan Librarian
            int librarianHp = 60;
            
            while (librarianHp > 0 && player.isAlive()) {
                System.out.println("\n[Ancient Librarian HP: " + librarianHp + "] [" + player.nama + " HP: " + player.hp + "]");
                System.out.println("1. Serang dengan Spell");
                System.out.println("2. Serang Normal");
                System.out.println("3. Gunakan Potion");
                
                System.out.print("Pilihan: ");
                String action = scanner.nextLine().trim();
                
                if (action.equals("1")) {
                    int damage = player.attack + (int)(Math.random() * 10);
                    librarianHp -= damage;
                    System.out.println("Anda melempar Spell! Damage: " + damage);
                } else if (action.equals("2")) {
                    int damage = player.attack + (int)(Math.random() * 5);
                    librarianHp -= damage;
                    System.out.println("Anda menyerang! Damage: " + damage);
                } else if (action.equals("3")) {
                    player.healHP(35);
                    int enemyDamage = 18 + (int)(Math.random() * 6);
                    player.takeDamage(enemyDamage);
                    continue;
                }
                
                if (librarianHp > 0) {
                    int enemyDamage = 18 + (int)(Math.random() * 8);
                    player.takeDamage(enemyDamage);
                }
            }
            
            if (player.isAlive()) {
                System.out.println("\n*** Anda menang! Ancient Librarian telah dikalahkan! ***");
                player.gainExp(125);
                player.showStats();
                return true;
            } else {
                System.out.println("\n*** Anda kalah! ***");
                return false;
            }
        } else {
            System.out.println("\n*** Anda gagal mencari spell yang cukup! ***");
            return false;
        }
    }

    // Bubble sort
    private void bubbleSort(Spell[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    Spell temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // Binary search
    private Spell binarySearch(Spell[] arr, String name) {
        int left = 0, right = arr.length - 1;
        
        // Linear search since we need to search by name, not sorted key
        for (Spell spell : arr) {
            if (spell.name.equalsIgnoreCase(name)) {
                return spell;
            }
        }
        return null;
    }
}
