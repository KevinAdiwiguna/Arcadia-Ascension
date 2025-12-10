package utils;

import java.util.Scanner;

import player.PlayerNode;

public class Utils {
    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }

    public static void loadingAnimation() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(250);
            } catch (Exception e) {
            }
            System.out.print(".");
        }
    }

    public static void loadingAnimation(int data, int milisecond) {
        try {
            for (int i = 0; i < data; i++) {
                System.out.print(".");
                Thread.sleep(milisecond);
            }
            System.out.println();
        } catch (InterruptedException e) {
        }
    }

    public static void showDamage(int damage, PlayerNode player) {
        int newHp = player.getCurrentHp() - damage;
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║ ⚠ Kamu terkena serangan!             ║");
        System.out.println("║ 💥 Kerusakan :" + damage);
        System.out.println("║ ❤️ Sisa HP   : " + newHp);
        System.out.println("╚══════════════════════════════════════╝");
    }

    public static void timeoutInSecond(int second) {
        try {
            Thread.sleep(second * 1000);
        } catch (Exception e) {
        }
    }

    public static void pressEnter() {
        System.out.println("\nTekan Enter untuk melanjutkan...");
        try {
            System.in.read();
        } catch (Exception e) {
        }
    }

    public static boolean potionUse(PlayerNode player) {
        Scanner input = new Scanner(System.in);
        Utils.clearScreen();

        if (player.getInventory().isEmpty()) {
            System.out.println("\n╔════════════════════════════════════════╗");
            System.out.println("║ ⚠ Inventory kamu kosong!               ║");
            System.out.println("║ Tidak ada potion yang bisa digunakan.  ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.println("Tekan enter untuk melanjutkan...");
            input.nextLine();
            return false;
        }

        while (true) {
            Utils.clearScreen();
            System.out.println("\n╔════════════════════════════════════════════════════╗");
            System.out.println("║                 🧪 INVENTORY POTION 🧪               ║");
            System.out.println("╠══════════════════════════════════════════════════════╣");
            player.getInventory().printInventory();
            System.out.println("╚══════════════════════════════════════════════════════╝");

            System.out.println("\nApa yang ingin kamu lakukan?");
            System.out.println("1. Gunakan potion");
            System.out.println("2. Kembali");
            System.out.print("Pilih Aksi [1/2]: ");

            try {
                int choice = input.nextInt();
                input.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("\nMasukkan nama potion (atau 'cancel'): ");
                        String potionName = input.nextLine().trim();

                        if (potionName.equalsIgnoreCase("cancel")) {
                            return false; // kembali ke playerTurn
                        }

                        if (player.getInventory().SearchPotion(potionName)) {
                            player.getInventory().UsePotion(potionName, player);

                            // UI keren setelah menggunakan potion
                            System.out.println("\n╔════════════════════════════════════════════╗");
                            System.out.println("║ ✨ Potion digunakan!");
                            System.out.println("║ Energi hangat menyebar ke seluruh tubuhmu!");
                            System.out.println("║ ❤️ HP-mu sekarang: " + player.getCurrentHp());
                            System.out.println("╔════════════════════════════════════════════╝");

                            System.out.println("Tekan enter untuk melanjutkan...");
                            input.nextLine();
                            return true;
                        } else {
                            System.out.println("\n╔════════════════════════════════════════╗");
                            System.out.println("║ ❌ Potion '" + potionName + "' tidak ditemukan!           ║");
                            System.out.println("╚════════════════════════════════════════╝");
                            System.out.println("Tekan enter untuk melanjutkan...");
                            input.nextLine();
                            break;
                        }

                    case 2:
                        return false;

                    default:
                        System.out.println("\n╔════════════════════════════════════════╗");
                        System.out.println("║ Pilihan tidak valid!                     ║");
                        System.out.println("╚════════════════════════════════════════╝");
                        System.out.println("Tekan enter untuk melanjutkan...");
                        input.nextLine();
                        break;
                }

            } catch (Exception e) {
                System.out.println("\n╔════════════════════════════════════════╗");
                System.out.println("║ Masukkan angka yang benar!              ║");
                System.out.println("╚════════════════════════════════════════╝");
                input.nextLine();
            }
        }
    }

}
