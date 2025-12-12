package utils;

import java.util.Scanner;

import player.PlayerNode;
public class Display {
    public static void displayBanner() {
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

    public static void showStatistic(PlayerNode player) {
        String name = player.getPlayerName();
        String currentHp = player.getCurrentHp() + "";
        String maxHp = player.getMaxHp() + "";
        String level = player.getLevel() + "";

        int width = 32;

        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("│                    STATUS PEMAIN                 │");
        System.out.println("╠══════════════════════════════════════════════════╣");

        System.out.println("│    Nama       : " + padRight(name, width));
        System.out.println("│    Current HP : " + padRight(currentHp, width));
        System.out.println("│    Max HP     : " + padRight(maxHp, width));
        System.out.println("│    Level      : " + padRight(level, width));

        System.out.println("╚══════════════════════════════════════════════════╝");
    }

    private static String padRight(String text, int length) {
        StringBuilder sb = new StringBuilder(text);
        while (sb.length() < length) {
            sb.append(" ");
        }
        return sb.toString();
    }

    public static void showLevelUp(PlayerNode player) {
        System.out.println("\n╔════════════════════════════════════════════════════════");
        System.out.println("║                           LEVEL UP!                       ");
        System.out.println("╠════════════════════════════════════════════════════════");
        System.out.println("║  Level Baru     : " + player.getLevel());
        System.out.println("║  Current HP    : " + player.getCurrentHp());
        System.out.println("║  Max HP        : " + player.getMaxHp());
        System.out.println("╚════════════════════════════════════════════════════════\n");

        System.out.println(" Tubuhmu terasa lebih kuat, lebih ringan…");
        System.out.println(" Energi baru mengalir dalam dirimu.");
        System.out.println(" Dunia seakan mengakui peningkatan kekuatanmu.\n");
        System.out.println("==============================================================\n");
    }

    public static void showAbout() {
        Scanner input = new Scanner(System.in);

        System.out.println("\n┌─────────────────────────────[ ABOUT THE GAME ]─────────────────────────────┐");
        System.out.println("│ Arcadia Ascension adalah RPG petualangan di mana pemain menjelajahi level,  │");
        System.out.println("│ bertarung melawan monster, dan menyelesaikan quest untuk mencapai relic     │");
        System.out.println("│ tertinggi.                                                                  │");
        System.out.println("├─────────────────────────────────────────────────────────────────────────────┤");
        System.out.println("│ Developed by:                                                               │");
        System.out.println("│    Ida Bagus Kevin Adiwiguna                                              │");
        System.out.println("│    M. Sagos                                                               │");
        System.out.println("│    Gusti Ayu Marsha Widyaswari                                            │");
        System.out.println("│    Baiq Erwina Yolanda                                                    │");
        System.out.println("│    Halis Ibrahim Kumala Chandra                                           │");
        System.out.println("│    Ni Komang Ayu Sumeitri                                                 │");
        System.out.println("│    Raisa Bunga Astrella                                                   │");
        System.out.println("├─────────────────────────────────────────────────────────────────────────────┤");
        System.out.println("│ Version: 1.0                                                                │");
        System.out.println("│ Terima kasih telah memainkan Arcadia Ascension!                             │");
        System.out.println("└─────────────────────────────────────────────────────────────────────────────┘");

        System.out.println("\nTekan Enter untuk kembali ke menu utama...");
        input.nextLine();
    }
}
