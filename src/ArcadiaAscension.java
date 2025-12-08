import java.util.Scanner;

import level.level1.Level1MemoryGame;
import player.PlayerNode;

public class ArcadiaAscension {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        mainMenu(input);
    }

    static void mainMenu(Scanner input) {
        int menuSekarang = 1;
        PlayerNode player = new PlayerNode("Gasos", 100);

        Level1MemoryGame level1 = new Level1MemoryGame(player);

        while (menuSekarang != 0) {
            cls();
            Banner();
            System.out.println("==================================================");
            System.out.println("                   Main Menu");

            System.out.println("--------------------------------------------------");
            System.out.println((menuSekarang == 1 ? "                 >> Start <<" : "                    Start  "));
            System.out.println((menuSekarang == 2 ? "                 >> About <<" : "                    About  "));
            System.out.println((menuSekarang == 3 ? "                 >> Exit <<" : "                    Exit  "));
            System.out.println("--------------------------------------------------");
            System.out.print("Input (1: Up | 2: Down | 3: Pilih): ");
            int pilihan = input.nextInt();

            switch (pilihan) {
                case 1:
                    if (menuSekarang > 1) {
                        menuSekarang--;
                    } else {
                        menuSekarang = 3;
                    }
                    break;
                case 2:
                    if (menuSekarang < 3) {
                        menuSekarang++;
                    } else {
                        menuSekarang = 1;
                    }
                    break;
                case 3:
                    switch (menuSekarang) {
                        case 1:
                            level1.start();
                            break;
                        case 2:
                            break;
                        case 3:
                            System.out.println("Exiting the game.....");
                            System.out.println("Goodbye!");
                            menuSekarang = 0;
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
    }

    static void cls() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void Banner() {
        String banner = "    _                      _ _                \n" +
                "   / \\   _ __ ___ __ _  __| (_) __ _          \n" +
                "  / _ \\ | '__/ __/ _` |/ _` | |/ _` |         \n" +
                " / ___ \\| | | (_| (_| | (_| | | (_| |         \n" +
                "/_/ _ \\_\\_|  \\___\\__,_|\\__,_|_|\\__,_|         \n" +
                "       / \\   ___  ___ ___ _ __  ___(_) ___  _ __  \n" +
                "      / _ \\ / __|/ __/ _ \\ '_ \\/ __| |/ _ \\| '_ \\ \n" +
                "     / ___ \\\\__ \\ (_|  __/ | | \\__ \\ | (_) | | | |\n" +
                "    /_/   \\_\\___/\\___\\___|_| |_|___/_|\\___/|_| |_|\n";
        System.out.println(banner);
    }
}
