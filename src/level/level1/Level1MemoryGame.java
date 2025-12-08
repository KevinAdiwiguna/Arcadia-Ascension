package level.level1;

import java.util.Scanner;
import utils.ClearScreen;
import player.PlayerNode;

public class Level1MemoryGame {

    private final int TOTAL_NUMBERS = 5;
    private final int TIMEINSECOND = 5;
    private final int DAMAGE_PER_FAIL = 5;

    private final Scanner scanner = new Scanner(System.in);
    private PlayerNode player;
    private int round = 1;

    public Level1MemoryGame(PlayerNode player) {
        this.player = player;
    }

    public void start() {
        System.out.println("====================================================");
        System.out.println("                LEVEL 1 — TRIAL OF MEMORY          ");
        System.out.println("====================================================");
        System.out.println("Seorang penjaga berzirah hitam menatapmu tajam.");
        System.out.println("\"Petualang... sebelum melangkah lebih jauh, buktikan daya ingatmu.\"");
        System.out.println(
                "\"Kau hanya punya " + TIMEINSECOND + " detik. Salah satu angka saja… dan nyawamu melayang.\"");
        System.out.println("====================================================\n");

        System.out.println("KAMU SIAP BOCAH!!!");
        for (int i = 0; i < 15; i++) {
            try {
                Thread.sleep(500);
            } catch (Exception e) {
            }
            System.out.print(".");
        }
        System.out.println("\n");

        while (player.getCurrentHp() > 0) {

            System.out.println("----- ROUND " + round + " -----");

            LinkedListMemoryGame list = new LinkedListMemoryGame();
            list.addRandom(TOTAL_NUMBERS);

            System.out.println("\nAngka-angka muncul dalam cahaya biru:");
            list.show();

            try {
                Thread.sleep(TIMEINSECOND * 1000);
            } catch (Exception e) {
            }

            ClearScreen.clearScreen();

            System.out.println("Cahaya menghilang... semua angka lenyap!");
            System.out.println("Penjaga: \"Sekarang ulangi. Jika salah... jangan salahkan aku.\"");
            System.out.println();

            int[] userInput = new int[TOTAL_NUMBERS];
            System.out.println("Masukkan kembali angka sebanyak " + TOTAL_NUMBERS + ":\n");

            for (int i = 0; i < TOTAL_NUMBERS; i++) {
                System.out.print("Angka ke-" + (i + 1) + ": ");
                userInput[i] = scanner.nextInt();
            }

            if (list.equalsList(userInput)) {

                System.out.println("\n✔ Validasi sukses.");
                System.out.println("Penjaga terdiam. Helm besinya sedikit menunduk.");
                System.out.println("\"Hmm… ketajaman memorimu tidak mengecewakan, petualang.\"");
                System.out.println("Gerbang batu di belakangnya perlahan terbuka…");
                System.out.println("\n>>> LEVEL 1 COMPLETED. Menuju ke level selanjutnya");

                for (int i = 0; i < 503; i++) {
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                    }
                    System.out.print(".");
                }

                break;

            } else {

                System.out.println("\n✖ Jawaban tidak tepat.");
                System.out.println("Penjaga tertawa keras, suaranya menggema di ruangan gelap.");
                System.out.println("\"HAHAHHAHA! Bahkan tantangan dasar saja tak mampu kau selesaikan!\"");
                System.out.println("\"Lemah… sangat lemah. Kembali dan latih otakmu, bocah kecil.\"\"\n");

                player.setCurrentHp(player.getCurrentHp() - DAMAGE_PER_FAIL);
                System.out.println("⚠ Kamu menerima " + DAMAGE_PER_FAIL + " kerusakan!");
                System.out.println("Sisa HP: " + player.getCurrentHp());

                if (player.getCurrentHp() <= 0) {
                    System.out.println("\n===================================");
                    System.out.println("       KAU TERLALU LEMAH...");
                    System.out.println(" Penjaga mengangkat pedangnya tinggi.");
                    System.out.println("        \"GAME OVER, BOCAH.\"");
                    System.out.println("===================================");
                    return;
                }

                System.out.println("\nBersiap untuk ronde berikutnya...");
                round++;

                for (int i = 0; i < 100; i++) {
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                    }
                    System.out.print(".");
                }
                System.out.println("\n");

                ClearScreen.clearScreen();
            }
        }
    }
}
