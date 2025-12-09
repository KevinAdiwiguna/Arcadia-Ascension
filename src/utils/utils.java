package utils;

public class utils {
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
}
