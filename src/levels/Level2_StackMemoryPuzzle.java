package levels;

import models.NodePlayer;
import utils.Stack;
import java.util.Scanner;

public class Level2_StackMemoryPuzzle {
    private NodePlayer player;
    private Scanner scanner;
    private Stack<String> undoStack;

    public Level2_StackMemoryPuzzle(NodePlayer player, Scanner scanner) {
        this.player = player;
        this.scanner = scanner;
        this.undoStack = new Stack<>();
    }

    public boolean play() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("LEVEL 2: MEMORY PUZZLE ROOM - Stack Challenge");
        System.out.println("=".repeat(60));
        System.out.println("\nAnda masuk ke ruangan yang penuh dengan papan memori yang misterius.");
        System.out.println("Anda harus mengingat urutan angka dan dapat mengundo langkah dengan Stack!");

        // Membuat puzzle
        String[] correctSequence = {"3", "1", "4", "1", "5"};
        Stack<String> playerSequence = new Stack<>();
        int mistakes = 0;
        final int MAX_MISTAKES = 3;

        System.out.println("\n--- Ingat urutan berikut (akan hilang dalam 3 detik): ---");
        for (String num : correctSequence) {
            System.out.print(num + " ");
        }
        System.out.println("\n");

        // Simulasi delay
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Urutan tersembunyi. Mulai masukkan angka!");
        System.out.println("\nAturan:");
        System.out.println("- Masukkan angka sesuai urutan");
        System.out.println("- Gunakan 'undo' untuk membatalkan input terakhir (Stack)");
        System.out.println("- Gunakan 'done' ketika selesai");

        boolean puzzleSolved = false;

        while (mistakes < MAX_MISTAKES && !puzzleSolved) {
            System.out.print("\nMasukkan angka (atau 'undo'/'done'): ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("undo")) {
                if (playerSequence.isEmpty()) {
                    System.out.println("Tidak ada angka untuk di-undo!");
                } else {
                    String removed = playerSequence.pop();
                    System.out.println(">>> Anda membatalkan input: " + removed);
                }
            } else if (input.equals("done")) {
                if (playerSequence.getSize() == correctSequence.length) {
                    puzzleSolved = true;
                    System.out.println("\n*** Anda menyelesaikan puzzle! ***");
                } else {
                    System.out.println("Anda belum memasukkan semua angka!");
                }
            } else {
                // Check input adalah angka
                try {
                    String digit = input;
                    int currentPosition = playerSequence.getSize();
                    
                    if (digit.equals(correctSequence[currentPosition])) {
                        playerSequence.push(digit);
                        System.out.println("✓ Benar! Angka: " + digit);
                    } else {
                        mistakes++;
                        System.out.println("✗ Salah! Seharusnya: " + correctSequence[currentPosition]);
                        System.out.println("Kesalahan: " + mistakes + "/" + MAX_MISTAKES);
                        
                        if (mistakes >= MAX_MISTAKES) {
                            System.out.println("\n*** Anda kehabisan kesempatan! Game Over! ***");
                            return false;
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Input tidak valid! Masukkan angka saja.");
                }
            }
        }

        if (puzzleSolved) {
            System.out.println("\n--- Pintu mendelik terbuka! ---");
            
            // Battle dengan Illusionist
            System.out.println("\nSeorang penyihir ilusi muncul dari bayangan!");
            int illusionistHp = 40;
            
            while (illusionistHp > 0 && player.isAlive()) {
                System.out.println("\n[Illusionist HP: " + illusionistHp + "] [" + player.nama + " HP: " + player.hp + "]");
                System.out.println("1. Serang\n2. Gunakan Potion\n3. Lari");
                
                System.out.print("Pilihan: ");
                String action = scanner.nextLine().trim();
                
                if (action.equals("1")) {
                    int damage = player.attack + (int)(Math.random() * 8);
                    illusionistHp -= damage;
                    System.out.println("Anda menyerang! Damage: " + damage);
                    
                    if (illusionistHp > 0) {
                        int enemyDamage = 10 + (int)(Math.random() * 5);
                        player.takeDamage(enemyDamage);
                    }
                } else if (action.equals("2")) {
                    player.healHP(25);
                    int enemyDamage = 10;
                    player.takeDamage(enemyDamage);
                } else if (action.equals("3")) {
                    System.out.println("Anda lari dari pertempuran.");
                    return false;
                }
            }
            
            if (player.isAlive()) {
                System.out.println("\n*** Anda menang! Illusionist telah dikalahkan! ***");
                player.gainExp(75);
                player.showStats();
                return true;
            } else {
                System.out.println("\n*** Anda kalah! ***");
                return false;
            }
        }
        
        return false;
    }
}
