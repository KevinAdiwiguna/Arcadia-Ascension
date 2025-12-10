package level.level3;

import java.util.Scanner;

import player.PlayerNode;
import player.inventory.InventoryNode;
import utils.Utils;

public class Level3 {

    private PlayerNode player;
    private Scanner scanner;

    public Level3(PlayerNode player) {
        this.player = player;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        Stack artefactStack = new Stack(); // tumpukan artefak awal
        Stack holyChest = new Stack(); // peti suci
        Stack playerHand = new Stack(); // tangan pemain

        // Tumpukan artefak awal
        artefactStack.push("Orb of Light");
        artefactStack.push("Ancient Rune");
        artefactStack.push("Crystal Fang");
        artefactStack.push("Dragon Scale");
        artefactStack.push("Phoenix Feather");

        // Target urutan benar (BOTTOM → TOP)
        String[] target = {
                "Crystal Fang",
                "Dragon Scale",
                "Phoenix Feather",
                "Ancient Rune",
                "Orb of Light"
        };

        boolean running = true;

        Utils.clearScreen();
        System.out.println("=====================================================================================");
        System.out.println("---------------------------   LEVEL 3 - KUIL ARTEFAK   ------------------------------");
        System.out.println("=====================================================================================");
        System.out.println("\nKau memasuki sebuah ruangan kuno yang di tengahnya terdapat Peti Suci.");
        System.out.println("Untuk membuka Peti Suci, kau harus menyusun artefak sesuai urutan ritual kuno.");
        System.out.println("Gunakan tanganmu untuk memindahkan artefak antara tumpukan dan peti suci.");
        System.out.println("Ingat... semuanya memakai prinsip **STACK (LIFO)**.");
        System.out.println("=====================================================================================\n");

        while (running) {
            displayAll(artefactStack, playerHand, holyChest, target);
            System.out.println("HP: " + player.getCurrentHp() + " | Level: 3");
            System.out.println("--------------------------------------------------");
            System.out.println("1. Ambil Artefak dari Tumpukan");
            System.out.println("2. Ambil Artefak dari Peti Suci");
            System.out.println("3. Taruh Artefak ke Peti Suci");
            System.out.println("4. Taruh Artefak ke Tumpukan");
            System.out.println("5. Selesai Menyusun");
            System.out.print("Pilihan: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {

                case "1": // ambil dari tumpukan artefak
                    Utils.clearScreen();
                    String a = artefactStack.pop();
                    if (a == null) {
                        System.out.println("Tumpukan artefak kosong!");
                    } else {
                        playerHand.push(a);
                        System.out.println("Kamu mengambil: " + a);
                    }

                    displayAll(artefactStack, playerHand, holyChest, target);

                    break;

                case "2": // ambil dari peti suci
                    Utils.clearScreen();
                    String b = holyChest.pop();
                    if (b == null) {
                        System.out.println("Peti Suci kosong!");
                    } else {
                        playerHand.push(b);
                        System.out.println("Kamu mengambil dari Peti Suci: " + b);
                    }

                    displayAll(artefactStack, playerHand, holyChest, target);

                    break;

                case "3": // taruh ke peti suci
                    Utils.clearScreen();
                    String c = playerHand.pop();
                    if (c == null) {
                        System.out.println("Tanganmu kosong!");
                    } else {
                        holyChest.push(c);
                        System.out.println("Kamu menaruh ke Peti Suci: " + c);
                    }

                    displayAll(artefactStack, playerHand, holyChest, target);
                    break;

                case "4": // taruh ke tumpukan artefak
                    Utils.clearScreen();
                    String d = playerHand.pop();
                    if (d == null) {
                        System.out.println("Tanganmu kosong!");
                    } else {
                        artefactStack.push(d);
                        System.out.println("Kamu menaruh kembali ke Tumpukan: " + d);
                    }

                    displayAll(artefactStack, playerHand, holyChest, target);
                    break;

                case "5": // cek apakah sudah sesuai target
                    Utils.clearScreen();
                    if (checkCorrect(holyChest, target)) {
                        System.out.println("==============================================================");
                        System.out.println("           ⚔️ RITUAL SUKSES! ARTEFAK TERSUSUN SEMPURNA! ⚔️");
                        System.out.println("==============================================================");

                        System.out.println("Seketika... ruangan menjadi sunyi.");
                        delay(30, 90);
                        System.out.println(
                                "\nUdara bergetar pelan, seolah menyadari bahwa urutanmu TAK TERSELIPKAN KESALAHAN.");
                        delay(30, 90);
                        System.out.println(
                                "\nPeti Suci berpendar—cahayanya mulai merayap mengikuti ukiran kuno pada permukaannya.");
                        delay(30, 90);

                        Utils.clearScreen();
                        System.out.println("✨ Cahaya Ilahi Meledak Dari Dalam Peti Suci! ✨");
                        delay(40, 110);

                        System.out.println(
                                "\nRitual kuno akhirnya menerima persembahanmu. Setiap artefak yang kau susun\n"
                                        + "beresonansi satu sama lain, menciptakan harmoni energi yang sempurna.");
                        delay(40, 110);

                        System.out.println(
                                "\nGelombang energi hangat menyelimuti tubuhmu, mengangkat beban perjalanan panjangmu.");
                        delay(40, 110);

                        System.out.println("\nLEVEL UP! ⚡ Kekuatanmu meningkat!");
                        player.levelUp();

                        delay(30, 90);
                        System.out.println("\nPeti Suci membuka perlahan... di dalamnya bersinar sebuah botol.");
                        delay(30, 90);

                        System.out.println("\n");
                        InventoryNode potion = new InventoryNode("Potion Regular (+300 HP)", 300);
                        player.inventory.push(potion);

                        System.out.println("\nKamu mendapatkan: 💖 Potion Regular (+300 HP)!");
                        delay(30, 90);

                        System.out.println(
                                "\nDengan ritual yang kini berhasil, energi suci di ruangan kembali tenang.\n"
                                        + "Tapi kau merasakan sesuatu... seolah dunia kini mengakui keberadaanmu.");
                        delay(40, 120);

                        System.out.println("\n==============================================================");
                        System.out.println("        ✔️  KAU TELAH MENAKLUKKAN RITUAL ARTEFAK KUNO! ✔️");
                        System.out.println("==============================================================\n");

                        running = false;
                    } else {
                        player.setCurrentHp(player.getCurrentHp() - 25);

                        System.out.println("❗ Energi Ajaib Berbalik ❗");
                        System.out.println("Kau merasakan semburan energi kacau keluar dari Peti Suci...");
                        System.out.println("Susunan artefak yang kau letakkan ternyata SALAH.");
                        System.out.println(
                                "Ritual kuno itu bereaksi terhadap kesalahanmu, dan sebuah gelombang kekuatan liar menerjang tubuhmu!");
                        delay(25, 100);
                        Utils.clearScreen();

                        System.out.println("HP -25 ❗");
                        System.out.println(
                                "Cahaya di ruangan bergetar... seolah memperingatkanmu untuk lebih berhati-hati.");
                        System.out.println(
                                "Cobalah lagi, namun ingat... hanya urutan yang BENAR yang dapat membuka Peti Suci.");

                        delay(25, 100);
                        System.out.println("\n");
                        Utils.clearScreen();
                    }
                    break;

                default:
                    System.out.println("Pilihan tidak valid!");
            }

            System.out.println();
        }
    }

    // tampilkan semua stack
    private void displayAll(Stack artefactStack, Stack playerHand, Stack holyChest, String[] target) {
        System.out.println("============= STATUS ARTEFAK =============\n");

        System.out.println("Urutan Benar:");
        System.out.print("BOTTOM -> ");
        for (int i = 0; i < target.length; i++) {
            System.out.print(target[i]);
            if (i < target.length - 1)
                System.out.print(" | ");
        }
        System.out.println("\n");

        System.out.println("--- Tumpukan Artefak ---");
        artefactStack.printStack();

        System.out.println("\n--- Tangan Pemain ---");
        showHand(playerHand);

        System.out.println("\n--- Peti Suci ---");
        holyChest.printStack();
    }

    // cek apakah peti suci sudah sesuai urutan bawah → atas
    private boolean checkCorrect(Stack chest, String[] target) {
        Stack clone = chest.cloneStack();
        for (int i = target.length - 1; i >= 0; i--) {
            String val = clone.pop();
            if (val == null || !val.equals(target[i]))
                return false;
        }
        return clone.isEmpty();
    }

    // tampilkan stack tangan pemain dalam 1 baris
    private void showHand(Stack hand) {
        System.out.print("Tangan Pemain -> ");
        if (hand.isEmpty()) {
            System.out.println("(kosong)");
            return;
        }

        Stack temp = hand.cloneStack();
        boolean first = true;
        while (!temp.isEmpty()) {
            String v = temp.pop();
            System.out.print((first ? "" : " | ") + v);
            first = false;
        }
        System.out.println();
    }

    static void delay(int length, int ms) {
        for (int i = 0; i < length; i++) {
            try {
                Thread.sleep(ms);
            } catch (Exception e) {
            }
            System.out.print(". ");
        }
    }
}
