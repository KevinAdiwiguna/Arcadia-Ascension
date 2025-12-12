package level.Level4;

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

            // input 5 angka sekaligus pakai spasi
            for (int i = 0; i < 5; i++) {
                try {
                    guess[i] = sc.nextInt();
                } catch (Exception e) {
                    System.out.println("Input tidak valid! Masukkan angka saja.");
                    sc.nextLine();
                    i--;  // ulangi input angka yang salah
                }
            }

            // Copy + Sorting
            int[] passSorted = copy(password);
            int[] guessSorted = copy(guess);
            bubbleSort(passSorted);
            bubbleSort(guessSorted);

            char[] symbol = new char[5];

            // Cek posisi benar
            for (int i = 0; i < 5; i++) {
                if (guess[i] == password[i]) {
                    symbol[i] = 'O';
                }
            }

            // Cek posisi salah (Y)
            for (int i = 0; i < 5; i++) {
                if (symbol[i] != 'O') {
                    if (contains(passSorted, guess[i])) {
                        symbol[i] = 'Y';
                    } else {
                        symbol[i] = 'X';
                    }
                }
            }

            // Output
            System.out.println();
            System.out.println("X = SALAH | Y = ADA TAPI POSISI SALAH | O = BENAR POSISI\n");

            for (int n : guess) System.out.print(n + " ");
            System.out.println();
            for (char s : symbol) System.out.print(s + " ");
            System.out.println("\n");

            // Cek menang
            boolean semuaBenar = true;
            for (char c : symbol) {
                if (c != 'O') semuaBenar = false;
            }

            if (semuaBenar) {
                System.out.println("Password benar! Pintu terbuka!");
                delay(25, 150);
                return;
            }

            // Salah → kena damage
            player.getDamage(25);
            System.out.println("Password salah! Anda menerima damage! HP sekarang: " + player.getCurrentHp());
            delay(25, 100);
        }

        System.out.println("Kesempatan habis. Pintu tetap terkunci.");
        delay(25, 100);
    }

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

    private boolean contains(int[] arr, int target) {
        for (int x : arr) {
            if (x == target) return true;
        }
        return false;
    }

    private int[] copy(int[] arr) {
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) result[i] = arr[i];
        return result;
    }

    static void delay(int length, int ms) {
        for (int i = 0; i < length; i++) {
            try { Thread.sleep(ms); } catch (Exception e) {}
            System.out.print(". ");
        }
    }
}