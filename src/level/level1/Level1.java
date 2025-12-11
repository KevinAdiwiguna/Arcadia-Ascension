package level.level1;

import java.util.Scanner;

import player.PlayerNode;
import player.inventory.InventoryNode;
import utils.Utils;

public class Level1 {

    private final int TOTAL_NUMBERS = 5;
    private final int TIMEINSECOND = 5;
    private final int DAMAGE_PER_FAIL = 5;

    private final Scanner scanner = new Scanner(System.in);
    private PlayerNode player;
    private int round = 1;

    public Level1(PlayerNode player) {
        this.player = player;
    }

    public void start() {
        System.out.println("\n══════════════════════════════════════════════════════════════");
        System.out.println("                    LEVEL 1 - TRIAL OF MEMORY ");
        System.out.println("══════════════════════════════════════════════════════════════");

        System.out.println("\nSeorang penjaga berzirah hitam berdiri menghadang jalanmu.");
        System.out.println("Tatapannya tajam… seolah mampu menembus isi hatimu.");

        System.out.println("\n\"Petualang… sebelum melangkah lebih jauh,");
        System.out.println(" buktikan kekuatan ingatanmu.\"");

        System.out.println("\n\"Kau hanya punya " + TIMEINSECOND + " detik.");
        System.out.println(" Satu angka saja yang salah… dan hidupmu bisa berakhir di sini.\"");

        System.out.println("\nUdara menjadi berat… langkahmu terasa diawasi.");
        System.out.println("Ini bukan sekadar tes—ini adalah gerbang pertama menuju takdirmu.");

        System.out.println("══════════════════════════════════════════════════════════════\n");

        System.out.println();
        System.out.println(">>>   BERSIAPLAH. UJIAN DIMULAI...   <<<");
        Utils.pressEnter();

        while (player.getCurrentHp() > 0) {
            LinkedListLevel1 list = new LinkedListLevel1();
            list.addRandom(TOTAL_NUMBERS);

            Utils.clearScreen();

            System.out.println("\n╔═══════════════════════════════════════════════╗");
            System.out.println("                      ROUND " + round);
            System.out.println("╚═══════════════════════════════════════════════╝\n");
            System.out.println(" Angka-angka muncul dalam cahaya biru misterius…");
            System.out.println(" Cahaya itu bergetar, menantang fokus dan ketenangan jiwamu.");
            System.out.println(" Perhatikan dengan seksama… setiap angka bisa menentukan nasibmu!\n");

            list.show();

            Utils.timeoutInSecond(TIMEINSECOND);
            Utils.clearScreen();

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
                System.out.println("Akanku berikan kau sebuah hadiah kecil.");

                Utils.loadingAnimation(25, 100);
                System.out.println("\n");
                player.inventory.push(new InventoryNode("Potion keberkahan", 500));
                Utils.loadingAnimation(25, 100);

                player.levelUp();

                System.out.println("\n>>> LEVEL 1 COMPLETED. Menuju ke level selanjutnya");
                Utils.pressEnter();
                break;

            } else {
                System.out.println("\n╔════════════════════════════════════════════════════════╗");
                System.out.println("║                          JAWABAN SALAH                  ║");
                System.out.println("╠════════════════════════════════════════════════════════╣");
                System.out.println("║ Penjaga tertawa keras, suaranya menggema di seluruh    ║");
                System.out.println("║ ruangan…                                               ║");
                System.out.println("║                                                        ║");
                System.out.println("║ \"HAHAHAHA! Bahkan tantangan sederhana ini pun gagal    ║");
                System.out.println("║ kau pecahkan!\"                                         ║");
                System.out.println("║ \"Begitu rapuh… begitu lamban. Aku nyaris kasihan       ║");
                System.out.println("║ melihat usahamu.\"                                      ║");
                System.out.println("║ \"Kembalilah saat pikiranmu sudah siap menghadapi       ║");
                System.out.println("║ dunia nyata.\"                                          ║");
                System.out.println("╚════════════════════════════════════════════════════════╝");

                Utils.showDamage(DAMAGE_PER_FAIL, player);

                if (player.getCurrentHp() <= 0) {
                    System.out.println("\n╔══════════════════════════════════════╗");
                    System.out.println("║        KAU TELAH TUMBANG...          ║");
                    System.out.println("╠══════════════════════════════════════╣");
                    System.out.println("║ Penjaga mengangkat pedangnya tinggi, ║");
                    System.out.println("║ seakan menegaskan kekalahanmu.       ║");
                    System.out.println("║                                      ║");
                    System.out.println("║        \"TIDURLAH DALAM KEGAGALAN.\"   ║");
                    System.out.println("╚══════════════════════════════════════╝");
                    return;
                }

                Utils.loadingAnimation();
                System.out.println("\nBersiap untuk ronde berikutnya...");
                round++;

                Utils.pressEnter();
                Utils.clearScreen();
            }
        }
    }
}
