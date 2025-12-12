package level.level3;

import java.util.Scanner;
import utils.Utils;
import player.PlayerNode;
import player.inventory.InventoryNode;

public class Level3 {

    private PlayerNode player;
    private Scanner scanner;

    public Level3(PlayerNode player) {
        this.player = player;
        this.scanner = new Scanner(System.in);
    }

    public void start() {

        Stack artefactStack = new Stack();
        Stack Peti = new Stack();
        Stack playerHand = new Stack();

        artefactStack.push("Orb of Light");
        artefactStack.push("Ancient Rune");
        artefactStack.push("Crystal Fang");
        artefactStack.push("Dragon Scale");
        artefactStack.push("Phoenix Feather");

        Stack correctStack = new Stack();
        correctStack.push("Orb of Light");
        correctStack.push("Ancient Rune");
        correctStack.push("Phoenix Feather");
        correctStack.push("Dragon Scale");
        correctStack.push("Crystal Fang");

        Stack displayCorrect = correctStack.cloneStack();

        int hpPenalty = 10;
        boolean running = true;

        while (running && player.getCurrentHp() > 0) {

            Utils.clearScreen();
            System.out.println("=====================================================================================");
            System.out.println("-----------------------   LEVEL 3 - KUIL ARTEFAK TERBALIK   -------------------------");
            System.out.println("Kamu memasuki Kuil Artefak Terbalik.\n" +
                                "Di depanmu ada tumpukan artefak yang harus disusun kembali dengan benar.\n" +
                                "Hati-hati, setiap kesalahan akan mengurangi HP-mu.");
            System.out.println("=====================================================================================");

            System.out.println("Tumpukan Artefak Saat Ini:");
            artefactStack.printStack();

            System.out.println();
            System.out.print("Urutan Artefak Yang Harus Dibuat:\nBOTTOM -> ");
            displayCorrect.printSingleLine();
            System.out.println();

            System.out.println("HP: " + player.getCurrentHp());
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
                        System.out.println("Kamu mengambil: " + taken);
                        showHand(playerHand);
                    }
                    System.out.println("Enter...");
                    scanner.nextLine();
                    break;

                case "2":
                    String placing = playerHand.pop();
                    if (placing == null) {
                        System.out.println("Tangan kosong!");
                        System.out.println("Enter...");
                        scanner.nextLine();
                        break;
                    }
                    scanner.nextLine();
                    String expected = correctStack.pop();
                    System.out.println("Meletakkan: " + placing);

                    if (!placing.equals(expected)) {
                        System.out.println("SALAH! Seharusnya: " + expected);
                        player.setCurrentHp(player.getCurrentHp() - hpPenalty);
                        System.out.println("HP -" + hpPenalty + " (Sisa: " + player.getCurrentHp() + ")");
                        correctStack.push(expected);
                        artefactStack.push(placing);
                        System.out.println("Artefak dikembalikan ke tumpukan artefak.");

                    } else {
                        Peti.push(placing);
                        System.out.println("Benar! " + placing + " masuk ke Peti Suci.");
                    }

                    System.out.println("\nPeti Suci Saat Ini:");
                    Peti.printStack();
                    showHand(playerHand);


                    if (correctStack.isEmpty()) {
                        System.out.println("=====================================================================================");
                        System.out.println("-------------------------------   NICEE KAMU BERHASIL   -----------------------------");
                        System.out.println("=====================================================================================");
                        System.out.println("Cahaya suci memancar dari Peti Suci… seluruh artefak akhirnya tersusun dengan benar.\n" +
                                            "Kekuatan kuno mengalir ke tubuhmu, memulihkan sebagian energi yang hilang.");
                        player.levelUp();
                        player.getInventory().push(new InventoryNode("Heal Potion", 100));
                        System.out.println("HP kamu saat ini : " + player.getCurrentHp());
                        running = false;
                    }

                    System.out.println("Enter...");
                    scanner.nextLine();
                break;

                case "3":
                    System.out.println("--- Tumpukan Artefak ---");
                    artefactStack.printStack();

                    System.out.println("\n--- Tangan Pemain ---");
                    showHand(playerHand);

                    System.out.println("\n--- Peti Suci ---");
                    Peti.printStack();

                    System.out.println("\n--- Inventory ---");
                    player.getInventory().printInventory();

                    System.out.println("Enter...");
                    scanner.nextLine();
                    break;

                default:
                    System.out.println("Pilihan tidak ada!");
                    System.out.println("Enter...");
                    scanner.nextLine();
            }
        }

        if (player.getCurrentHp() <= 0) {
            System.out.println("HP habis! Kamu KALAH!");
        }
    }

    private void showHand(Stack hand) {
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
