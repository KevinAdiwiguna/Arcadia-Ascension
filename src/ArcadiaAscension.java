import java.util.Scanner;

import player.PlayerNode;
import utils.Utils;
import utils.Display;

import level.Map.Graph;
import level.level1.Level1;
import level.level2.Level2;
import level.level3.Level3;
import level.level4.Level4;
import level.NPC.LevelNPC;
import level.BossLevel.LevelBoss;

import level.NPC.LinkedListKalimat;

public class ArcadiaAscension {
    static Graph graph;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        graph = new Graph();

        graph.addVertex(0, "Entry"); // mainMenu
        graph.addVertex(1, "Level 1"); // level 1
        graph.addVertex(2, "Level 2"); // level 2
        graph.addVertex(3, "Level 3"); // level 3
        graph.addVertex(4, "Level 4"); // level 4
        graph.addVertex(5, "Level 5"); // NPC
        graph.addVertex(6, "Level 6");// level check
        graph.addVertex(7, "Level 7");// boss

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
            utils.Utils.clearScreen();
            utils.Display.displayBanner();
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
                            StartGame();
                            break;
                        case 2:
                            Display.showAbout();
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

    static void StartGame() {
        Scanner input = new Scanner(System.in);

        utils.Utils.clearScreen();
        utils.Display.displayBanner();

        System.out.println("======================================================");
        System.out.println("\t\t   WELCOME PLAYER");
        System.out.print("Masukan nama anda: ");
        String playerName = input.nextLine();

        utils.Utils.loadingAnimation(25, 100);

        utils.Utils.clearScreen();
        utils.Display.displayBanner();
        System.out.println("======================================================");
        System.out.println("\t\t   Hallo " + playerName);
        System.out.println("Siap untuk berpetualang di Arcadia Ascension?");
        System.out.print(" [Tekan ENTER untuk Lanjut]...");
        input.nextLine();
        utils.Utils.loadingAnimation(25, 100);

        utils.Utils.clearScreen();
        utils.Display.displayBanner();
        PlayerNode player = new PlayerNode(playerName, 150, 75);
        graph.setPlayerLocation(0);

        Scanner sc = new Scanner(System.in);

        System.out.println("==============================================================");
        System.out.println("\t\t   PETA KERAJAAN ARCADIA");
        System.out.println("==============================================================");

        LinkedListKalimat kalimat = new LinkedListKalimat();
        kalimat.addKalimat("Seorang sosok berjubah mendekat perlahan... bayangannya memanjang di lantai satu.\n" +
                "Suara berat namun tenang terdengar dari balik tudungnya:");
        kalimat.addKalimat("Sosok Berjubah : \"Perjalananmu tidak akan mudah, petualang...\"");
        kalimat.addKalimat("Sosok Berjubah : \"Namun aku akan memberimu panduan agar kau tidak tersesat.\"");
        kalimat.addKalimat("Sosok itu mengeluarkan sehelai gulungan tua dari balik jubahnya.\n" +
                        "Kau dapat melihat simbol-simbol kuno terukir samar di permukaannya...");
        kalimat.addKalimat("Sosok Berjubah : \"Ambillah. Ini adalah peta wilayah Arcadia.\"");
        kalimat.addKalimat("Sosok Berjubah : \"Pahami rutenya... dan jangan biarkan kegelapan mengejarmu.\"");
        kalimat.addKalimat("Saat gulungan itu berada di tanganmu, ia langsung membuka sendiri,\n" +
                        "menampilkan jalur, node, dan lokasi penuh misteri di hadapanmu...");
        
        kalimat.Display();
        
        utils.Utils.loadingAnimation(25, 100);

        // ===========================================================================================

        while (player.getCurrentHp() > 0) {
            utils.Utils.clearScreen();
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
            } else if (!graph.isValidDestination(currentName, destName)) {
                System.out.println("Tujuan tidak terhubung dari lokasi saat ini.");
            }else {
                if(graph.isVisited(destId) == true){
                    graph.setPlayerLocation(destId);
                    System.out.println(player.getPlayerName() + ": \"Sepertinya kita pernah melewati tempat ini\"");
                }else{
                    graph.setPlayerLocation(destId);
                    runLevel(destId, player);
                }
            }
            
            utils.Utils.loadingAnimation(25, 200);
            if(destId == 6) runLevel(destId, player); //DEBUNGGING AJA INI NANTI HAPUS

        }

        System.out.println("Sepertinya keberuntunganmu sudah habis!!!");
        System.out.println("Senang bertemu denganmu " + player.getPlayerName());
        utils.Utils.loadingAnimation(25, 100);
    }

    static void runLevel(int id, PlayerNode player) {
        String name = graph.findById(id);
        System.out.println("Masuk ke level (" + id + ") " + name + "...");
        try {
            Thread.sleep(800);
        } catch (Exception e) {
        }

        Utils.clearScreen();
        switch (id) {
            case 1:
                Level1 level1 = new Level1(player);
                level1.start();
                graph.markVisited(1);
                Utils.clearScreen();
                break;
            case 2:
                Level2 level2 = new Level2(player);
                level2.start();
                graph.markVisited(2);
                Utils.clearScreen();
                break;
            case 3:
                Level3 level3 = new Level3(player);
                level3.start();
                graph.markVisited(3);
                Utils.clearScreen();
                break;
            case 4:
                Level4 level4 = new Level4(player);
                level4.start();
                graph.markVisited(4);
                Utils.clearScreen();
                break;
            case 5:
                LevelNPC npc = new LevelNPC(player);
                npc.Start();
                graph.markVisited(5);
                Utils.clearScreen();
                break;
            case 6:
                level.LevelCheck.LevelCheckSistem lcs = new level.LevelCheck.LevelCheckSistem(graph);
                lcs.Start();
                graph.markVisited(6);
                break;
            case 7:
                LevelBoss bossLevel = new LevelBoss(player);
                bossLevel.start();
                graph.markVisited(7);
                break;
            default:
                System.out.println("Input anda tidak valid....");
                utils.Utils.loadingAnimation(25, 100);
                break;
        }
    }

}
