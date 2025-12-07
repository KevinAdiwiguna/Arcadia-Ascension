import java.util.Scanner;

public class ArcadiaAscension {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        PlayerNode player = new PlayerNode("Gasos", 100);
        
        mainMenu(input);
    }

    static void mainMenu(Scanner input){
        int menuSekarang = 1;

        while(menuSekarang != 0){
            Banner();
            System.out.println("            Main Menu");
            
            System.out.println("-------------------------");
            System.out.println((menuSekarang == 1 ? ">> " : "   ") + " Start");
            System.out.println((menuSekarang == 2 ? ">> " : "   ") + " About");
            System.out.println((menuSekarang == 3 ? ">> " : "   ") + " Exit");
            System.out.println("-------------------------");
            System.out.print("Input (1: Up | 2: Down | 3: Pilih): ");
            menuSekarang = input.nextInt();

            switch (menuSekarang) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    menuSekarang = 0;
                    break;
                default:
                    break;
            }
        }
    }
    
    private static void Banner(){
        String banner =
            "    _                      _ _                \n" +
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
