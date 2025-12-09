package level.Level3;

import java.util.Scanner;
import player.PlayerNode;
import player.inventory.InventoryNode;
import utils.ClearScreen;

public class Level3Game {

    private PlayerNode player;
    private Scanner scanner;

    public Level3Game(PlayerNode player) {
        this.player = player;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        Stack artefactStack = new Stack();
        Stack holyChest = new Stack();
        Stack playerHand = new Stack();

        // Tumpukan Artefak 
        artefactStack.push("Orb of Light");
        artefactStack.push("Ancient Rune");
        artefactStack.push("Crystal Fang");
        artefactStack.push("Dragon Scale");
        artefactStack.push("Phoenix Feather"); // TOP

        Stack correctStack = new Stack();
        correctStack.push("Orb of Light");
        correctStack.push("Ancient Rune");
        correctStack.push("Phoenix Feather");
        correctStack.push("Dragon Scale");
        correctStack.push("Crystal Fang");  // TOP (pertama dicek)

        int hpPenalty = 10;

        ClearScreen.clearScreen();
        System.out.println("=====================================================================================");
        System.out.println("-----------------------   LEVEL 3 - KUIL ARTEFAK TERBALIK   -------------------------");
        System.out.println("=====================================================================================");
        System.out.println("Tumpukan Artefak Saat Ini:");
        artefactStack.printStack();

        System.out.println("\n\nUrutan Artefak Yang Harus Dibuat:");
        System.out.print("BOTTOM -> ");
        correctStack.printSingleLine();
        System.out.println("\n");

        boolean running = true;

        while (running && player.getCurrentHp() > 0) {
            System.out.println("HP: " + player.getCurrentHp() + " | Level: 3");
            System.out.println("\n1. Ambil Artefak (POP)");
            System.out.println("2. Letakkan ke Peti Suci (PUSH)");
            System.out.println("3. Lihat Semua");
            System.out.println("4. Keluar");
            System.out.print("Pilihan: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    String taken = artefactStack.pop();
                    if (taken == null) {
                        System.out.println("Tidak ada artefak lagi!");
                    } else {
                        playerHand.push(taken);
                        System.out.println();
                        System.out.println("Kamu mengambil: " + taken);
                        showHand(playerHand);
                    }
                    break;

                case "2":
                    String placing = playerHand.pop();
                    if (placing == null) {
                        System.out.println("Tangan kosong!");
                    } else {
                        String expected = correctStack.pop(); 

                        System.out.println("Meletakkan: " + placing);

                        if (!placing.equals(expected)) {
                            System.out.println("SALAH! Seharusnya: " + expected);
                            player.setCurrentHp(player.getCurrentHp() - hpPenalty);
                            System.out.println("HP -" + hpPenalty + " (Sisa: " + player.getCurrentHp() + ")");
                            correctStack.push(expected); 
                            playerHand.push(placing);   
                        } else {
                            holyChest.push(placing);
                            System.out.println("Benar! " + placing + " masuk ke Peti Suci.");
                        }

                        System.out.println("\nPeti Suci Saat Ini:");
                        holyChest.printStack();
                        showHand(playerHand);

                        if (correctStack.isEmpty()) {
                            System.out.println("=====================================================================================");
                            System.out.println("-------------------------------   NICEE KAMU BERHASIL   -----------------------------");
                            System.out.println("=====================================================================================");
                            player.levelUp();
                            InventoryNode potionRegular = new InventoryNode("potion regular",300);
                            player.inventory.push(potionRegular);
                            System.out.println("Kamu mendapatkan Potion Regular (+300 HP) sebagai hadiah!");
                            running = false;
                        }
                    }
                    break;

                case "3":
                    System.out.println("\n--- Tumpukan Artefak ---");
                    artefactStack.printStack();
                    System.out.println("\n--- Tangan Pemain ---");
                    showHand(playerHand);
                    System.out.println("\n--- Peti Suci ---");
                    holyChest.printStack();
                    System.out.println("\n--- Inventory ---");
                    player.inventory.printInventory();
                    break;

                case "4":
                    running = false;
                    break;

                default:
                    System.out.println("Pilihan tidak ada!");
            }
            System.out.println();
        }

        if (player.getCurrentHp() <= 0) {
            System.out.println("HP habis! Kamu KALAH!");
        }
    }

    private void showHand(Stack hand) {
        System.out.println();
        System.out.print("Tangan Pemain -> ");
        if (hand.isEmpty()) {
            System.out.println("(kosong)");
            return;
        }
        Stack temp = hand.cloneStack();
        boolean first = true;
        while (!temp.isEmpty()) {
            String val = temp.pop();
            System.out.print((first ? "" : " | ") + val);
            first = false;
        }
        System.out.println();
    }
}
