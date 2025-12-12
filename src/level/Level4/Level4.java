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
       Linkedlistlvl4 password = new Linkedlistlvl4();
        password.insertLast(4);
        password.insertLast(9);
        password.insertLast(2);
        password.insertLast(7);
        password.insertLast(5);

        while (player.getCurrentHp() > 0) {
            System.out.println("\n=====================================================================================");
            System.out.println("-----------------------   LEVEL 4 - TEBAK PASSWORD 5 DIGIT   ------------------------");
            System.out.println("=====================================================================================");

            System.out.print("Masukkan 5 digit (pisah spasi): ");

           Linkedlistlvl4 guess = new Linkedlistlvl4();

            // input 5 angka sekaligus pakai spasi
           for (int i = 0; i < 5; i++) {
                try {
                    guess.insertLast(sc.nextInt());
                } catch (Exception e) {
                    System.out.println("Input ga valid, masukkin angka ya.");
                    sc.nextLine();
                    i--;
                }
            }

            // Copy + Sorting
            Linkedlistlvl4 passSorted = password.copy();
            Linkedlistlvl4 guessSorted = guess.copy();
            passSorted.bubbleSort();
            guessSorted.bubbleSort();

            char[] symbol = new char[5];

            // Cek posisi benar
           for (int i = 0; i < 5; i++) {
                if (guess.get(i) == password.get(i)) {
                    symbol[i] = 'O';
                }
            }

            // Cek posisi salah (Y)
            for (int i = 0; i < 5; i++) {
                if (symbol[i] != 'O') {
                    if (passSorted.contains(guess.get(i))) symbol[i] = 'Y';
                    else symbol[i] = 'X';
                }
            }

            // Output
            System.out.println();
            System.out.println("X = SALAH | Y = ADA TAPI POSISI SALAH | O = BENAR POSISI\n");

             for (int i = 0; i < 5; i++) System.out.print(guess.get(i) + " ");
            System.out.println();

            for (char s : symbol) System.out.print(s + " ");
            System.out.println("\n");

            // Cek menang
           boolean menang = true;
            for (char c : symbol) {
                if (c != 'O') menang = false;
            }

            if (menang) {
                System.out.println("Password benar! Pintu terbuka!");
                delay(25, 150);
                return;
            }

            player.getDamage(25);
            System.out.println("Password salah! Kena damage! HP sekarang: " + player.getCurrentHp());
            delay(25, 100);
        }

        System.out.println("Kesempatan habis. Pintu tetep kekunci.");
        delay(25, 100);
    }
    
    static void delay(int length, int ms) {
        for (int i = 0; i < length; i++) {
            try { Thread.sleep(ms); } catch (Exception e) {}
            System.out.print(". ");
        }
    }
}