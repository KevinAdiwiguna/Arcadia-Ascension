import java.util.Scanner;

import player.PlayerNode;
import utils.ClearScreen;
import level.Map.Graph;
import level.NPC.LevelNPC;
import level.BossLevel.LevelBoss;
import level.level1.Level1;
import level.level2.Level2;
import level.Level3.Level3;
import level.Level4.Level4;

public class ArcadiaAscension {
    static Graph graph;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        graph = new Graph(); 
        

        graph.addVertex(0, "Entry"); //mainMenu
        graph.addVertex(1, "Level 1"); //level 1
        graph.addVertex(2, "Level 2"); //level 2
        graph.addVertex(3, "Level 3"); //level 3 
        graph.addVertex(4, "Level 4"); //level 4
        graph.addVertex(5, "Level 5"); //NPC
        graph.addVertex(6, "Level 6");//level check
        graph.addVertex(7, "Level 7");//boss

        graph.addEdge(0, 1, 4);

        graph.addEdge(1, 2, 2); 
        graph.addEdge(1, 3, 5);

        graph.addEdge(2, 4, 8); 
        graph.addEdge(2, 5, 9);

        graph.addEdge(3, 5, 1); 

        graph.addEdge(4, 6, 3);
        graph.addEdge(5, 6, 9); 

        graph.addEdge(6, 7, 2);

        mainMenu(input);
    }

    static void mainMenu(Scanner input) {
        int menuSekarang = 1;

        while (menuSekarang != 0) {
            ClearScreen.clearScreen();
            Banner();
            System.out.println("==================================================");
            System.out.println("                   Main Menu");

            System.out.println("--------------------------------------------------");
            System.out.println((menuSekarang == 1 ? "                 >> Start <<" : "                    Start  "));
            System.out.println((menuSekarang == 2 ? "                 >> About <<" : "                    About  "));
            System.out.println((menuSekarang == 3 ? "                 >> Exit <<" : "                    Exit  "));
            System.out.println("--------------------------------------------------");
            System.out.print("Input (1: Up | 2: Down | 3: Pilih): ");
            String inputStr = input.nextLine().trim();
            int pilihan = 0;
            try {
                pilihan = Integer.parseInt(inputStr);
            } catch (NumberFormatException e) {
                pilihan = 0;
            }

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
                            StartGame();
                            break;
                        case 2:
                            About();
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

    static void StartGame(){
        Scanner input = new Scanner(System.in);

        ClearScreen.clearScreen();
        Banner();
        System.out.println("======================================================");
        System.out.println("\t\tWELCOME PLAYER");
        System.out.println("======================================================");
        System.out.print("Masukan nama anda: ");
        String playerName = input.nextLine();
        delay(25, 100);
        System.out.println("\nHallo " + playerName);

        System.out.println("Siap untuk berpetualang di Arcadia Ascension?");
        System.out.print(" [Tekan ENTER untuk Lanjut]...");
        input.nextLine();

        PlayerNode player = new PlayerNode(playerName, 250, 75);

        // Short intro/delay
        delay(25, 100);

        graph.setPlayerLocation(0);

        Scanner sc = new Scanner(System.in);
        while (player.getCurrentHp() > 0) {
            ClearScreen.clearScreen();
            Banner();

            // Show the map and current location
            graph.displayMap();

            String currentName = graph.getPlayerLocationName().trim();
            System.out.println("Current Location: " + (currentName.equals(" ") ? "Unknown" : currentName));
            System.out.println("Adjacent locations:");
            graph.displayAdjacentLocations(currentName);

            System.out.println("\nMasukkan id lokasi tujuan: ");
            int destId = sc.nextInt();

            String destName = graph.findById(destId).trim();
            if (destName.equals(" ") || destName.equals("")) {
                System.out.println("ID tujuan tidak valid.");
                delay(25, 200);
            }

            if (!graph.isValidDestination(currentName, destName)) {
                System.out.println("Tujuan tidak terhubung dari lokasi saat ini.");
                delay(25, 200);
            }

            if (graph.isVisited(destId)) {
                System.out.println("Level ini sudah dikunjungi: " + destName);
                graph.setPlayerLocation(destId);
                delay(25, 200);
            }

            runLevel(destId, player);

            graph.markVisited(destId);
            graph.setPlayerLocation(destId);

            System.out.println("Kembali ke peta...");
            try { Thread.sleep(700); } catch (Exception e) {}
        }

        System.out.println("Sepertinya keberuntunganmu sudah habis!!!");
        System.out.println("Senang bertemu denganmu " + player.getPlayerName());
        delay(25, 100);
    }

    static void runLevel(int id, PlayerNode player) {
        String name = graph.findById(id);
        System.out.println("Masuk ke level (" + id + ") " + name + "...");
        try { Thread.sleep(800); } catch (Exception e) {}

        ClearScreen.clearScreen();
        switch (id) {
            case 1://memory game
                Level1 level1 = new Level1(player);
                level1.start();
                break;
            case 2:
                Level2 level2 = new Level2(player);
                level2.start();
                break;
            case 3:
                Level3 level3 = new Level3(player);
                level3.start();
                break;
            case 4:
                Level4 level4 = new Level4(player);
                level4.start();
                break;
            case 5:
                LevelNPC npc = new LevelNPC(player);
                npc.Start();
                break;
            case 6: // Hollow Earth -> Level check system (example)
                LevelCheckSistem check = new LevelCheckSistem();
                check.runLevelCheck();
                break;
            case 7: // The Eternal Relic -> Boss
                LevelBoss boss = new LevelBoss(player);
                boss.start();
                break;
            default:
                System.out.println("Input anda tidak valid....");
                delay(25, 100);
                break;
        }
    }

    private static void About(){
        System.out.println("About the Game:");
        System.out.println("Arcadia Ascension is an adventurous RPG game where players explore various levels,");
        System.out.println("battle monsters, and complete quests to ascend to the ultimate relic.");
        System.out.println("Developed by:");
        System.out.println("- Gusti Ayu Marsha Widyaswari");
        System.out.println("- Baiq Erwina Yolanda");
        System.out.println("- Halis Ibrahim Kumala Chandra");
        System.out.println("- M. Sagos");
        System.out.println("- Ni Komang Ayu Sumeitri");
        System.out.println("- Raisa Bunga Astrella");
        System.out.println("-Ida Bagus Kevin Adiwiguna");

        System.out.println("\nVersion: 1.0");

        System.out.println("Thank you for playing Arcadia Ascension!");
        System.out.println("Press Enter to return to the main menu...");
        
        Scanner input = new Scanner(System.in);
        input.nextLine();
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

    static void delay(int length, int ms){
        for (int i = 0; i < length; i++) {
            try {
                Thread.sleep(ms);
            } catch (Exception e) {
            }
            System.out.print(". ");
        }
    }
}
