import java.util.Scanner;

import level.level1.Level1MemoryGame;
import player.PlayerNode;
import utils.ClearScreen;
import level.BossLevel.LevelBoss;
import level.BossLevel.NodeBoss;
import level.Map.Graph;

public class ArcadiaAscension {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Graph graph = new Graph(); 
        

        graph.addVertex(0, "Entry"); 
        graph.addVertex(1, "Ruins Hill"); 
        graph.addVertex(2, "Labyrinth Tower"); 
        graph.addVertex(3, "Dora Mountain"); 
        graph.addVertex(4, "Forest"); 
        graph.addVertex(5, "Hollow Earth"); 
        graph.addVertex(6, "The Eternal Relic"); 
        graph.addVertex(7, "Monster Island"); 
        graph.addVertex(8, "Safety Room"); 
        graph.addVertex(9, "Village"); 
        graph.addEdge(0, 6, 4);graph.addEdge(0, 2, 8); 
        graph.addEdge(0, 3, 7);graph.addEdge(1, 4, 2); 
        graph.addEdge(1, 7, 5);graph.addEdge(1, 3, 8); 
        graph.addEdge(1, 8, 9);graph.addEdge(3, 4, 1); 
        graph.addEdge(3, 6, 3);graph.addEdge(3, 5, 9); 
        graph.addEdge(5, 9, 2);graph.addEdge(5, 8, 10); 
        graph.addEdge(2, 5, 6); 
        

        mainMenu(input);

    }

    static void mainMenu(Scanner input) {
        int menuSekarang = 1;

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
                            StartGame(input);
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

    static void StartGame(Scanner input){
        ClearScreen.clearScreen();
        Banner();
        System.out.println("======================================================");
        System.out.println("\t\tWELCOME PLAYER");
        System.out.print("Masukan nama anda: ");
        String tes = input.nextLine();//buang buffer yang tertahan
        String playerName = input.nextLine();
        System.out.print("Hallo " + playerName);

        
        NodeBoss boss = new NodeBoss("Dark Lord", 1000, 75);
        PlayerNode player = new PlayerNode(playerName, 250, 75);
        LevelBoss bossLevel = new LevelBoss(player, boss);
        Level1MemoryGame level1 = new Level1MemoryGame(player, bossLevel);

        for (int i = 0; i < 15; i++) {
            try {
                Thread.sleep(250);
            } catch (Exception e) {
            }
            System.out.print(".");
        }
        
        level1.start();

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
