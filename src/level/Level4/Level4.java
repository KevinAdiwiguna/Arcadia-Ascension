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
        int[] password = {4, 9, 2, 7, 5};

        while (player.getCurrentHp() > 0) {
        System.out.println("=====================================================================================");
        System.out.println("-----------------------   LEVEL 4 - TEBak PASSWORD 5 DIGIT   -------------------------");
        System.out.println("=====================================================================================");
            System.out.print("Masukkan 5 digit (pisah spasi): ");

            int[] guess = new int[5];
            for (int i = 0; i < 5; i++) {
                guess[i] = sc.nextInt();
            }

            // --- Sorting untuk keperluan belajar ---
            int[] passSorted = copy(password);
            int[] guessSorted = copy(guess);
            bubbleSort(passSorted);
            bubbleSort(guessSorted);

            // --- Searching posisi benar (O) & frekuensi untuk Y ---
            int[] freq = new int[10];
            for (int p : password) freq[p]++;

            char[] symbol = new char[5]; // simbol O/Y/X

            // Cek posisi benar (O)
            for (int i = 0; i < 5; i++) {
                if (guess[i] == password[i]) {
                    symbol[i] = 'O';
                    freq[guess[i]]--;
                }
            }

            // Cek angka ada tapi posisi salah (Y) / X
            for (int i = 0; i < 5; i++) {
                if (symbol[i] != 'O') {
                    if (freq[guess[i]] > 0) {
                        symbol[i] = 'Y';
                        freq[guess[i]]--;
                    } else {
                        symbol[i] = 'X';
                    }
                }
            }

            // Tampilkan tebakan & simbol
            System.out.println();
            System.out.println("X=JAWABAN SALAH\n"+"Y=JAWABAN BENAR POSISI SALAH\n"+"o=JAWABAN BENAR POSISI BENAR\n");
            System.out.println();
            for (int i = 0; i < 5; i++) System.out.print(guess[i] + " ");
            System.out.println();
            for (int i = 0; i < 5; i++) System.out.print(symbol[i] + " ");
            System.out.println("\n");


            // Cek semua benar
            boolean semuaBenar = true;
            for (char c : symbol) {
                if (c != 'O') {
                    semuaBenar = false;
                    break;
                }
            }

            if (semuaBenar) {
                System.out.println("Password benar! Pintu terbuka!");
                System.out.println("Lanjut ke level berikutnya");
                return;
            }
            
            player.getDamage(10);
            System.out.println("Password salah! Coba lagi.");
            System.out.println("HP: "+player.getCurrentHp());
        }

        System.out.println("Kesempatan habis. Pintu tetap terkunci.");
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

    // --- Copy array sederhana ---
    private int[] copy(int[] arr) {
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) result[i] = arr[i];
        return result;
    }
}
