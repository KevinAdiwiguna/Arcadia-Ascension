package level.level4;

import java.util.Scanner;
import player.PlayerNode;

public class Level4 {

    PlayerNode player;
    Scanner input = new Scanner(System.in);

    public Level4(PlayerNode player) {
        this.player = player;
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        int[] password = { 4, 9, 2, 7, 5 };

        while (player.getCurrentHp() > 0) {
            System.out.println("\n=====================================================================================");
            System.out.println("-----------------------   LEVEL 4 - TEBAK PASSWORD 5 DIGIT   ------------------------");
            System.out.println("=====================================================================================");
            System.out.print("Masukkan 5 digit (pisah spasi): ");

            int[] guess = new int[5];
            for (int i = 0; i < 5; i++) {
                guess[i] = sc.nextInt();
            }

            // --- Sorting agar bisa dipakai untuk pengecekan Y ---
            int[] passSorted = copy(password);
            int[] guessSorted = copy(guess);
            bubbleSort(passSorted);
            bubbleSort(guessSorted);

            // Penampung simbol
            char[] symbol = new char[5];

            // --- Cek posisi benar (O) ---
            for (int i = 0; i < 5; i++) {
                if (guess[i] == password[i]) {
                    symbol[i] = 'O';
                }
            }

            // --- Cek Y (angka ada tapi posisi salah), berbasis sorted search ---
            for (int i = 0; i < 5; i++) {
                if (symbol[i] != 'O') {
                    if (contains(passSorted, guess[i])) {
                        symbol[i] = 'Y';
                    } else {
                        symbol[i] = 'X';
                    }
                }
            }

            // Tampilkan output
            System.out.println();
            System.out.println("X = SALAH | Y = ADA TAPI POSISI SALAH | O = BENAR POSISI");
            System.out.println();

            for (int i = 0; i < 5; i++) System.out.print(guess[i] + " ");
            System.out.println();
            for (int i = 0; i < 5; i++) System.out.print(symbol[i] + " ");
            System.out.println("\n");

            // Cek semua benar
            boolean semuaBenar = true;
            for (char c : symbol) {
                if (c != 'O') semuaBenar = false;
            }

            if (semuaBenar) {
                System.out.println("Password benar! Pintu terbuka!");
                System.out.println("Lanjut ke level berikutnya");

                delay(25, 150);
                return;
            }

            player.getDamage(25);
            System.out.println("Password salah! Anda menerima damage! HP sekarang: " + player.getCurrentHp());
            delay(25, 100);
        }

        System.out.println("Kesempatan habis. Pintu tetap terkunci.");
        delay(25, 100);
    }

    // --- Bubble Sort ---
    private void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // --- Linear Search pada array terurut ---
    private boolean contains(int[] arr, int target) {
        for (int x : arr) {
            if (x == target) return true;
        }
        return false;
    }

    // --- Copy array ---
    private int[] copy(int[] arr) {
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++)
            result[i] = arr[i];
        return result;
    }

    // --- Delay animasi ---
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
