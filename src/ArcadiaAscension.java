import java.util.Scanner;

import player.PlayerNode;
import player.inventory.InventoryNode;
import utils.Utils;
import utils.Display;

import level.Map.Graph;
import level.NPC.LevelNPC;
import level.BossLevel.LevelBoss;
import level.BossLevel.NodeBoss;
import level.NPC.LevelNPC;
import level.Map.Graph;

import level.BossLevel.LevelBoss;
import level.level1.Level1;
import level.level2.Level2;
import level.level3.Level3;
import level.level4.Level4;
import level.NPC.LevelNPC;
import level.BossLevel.LevelBoss;;

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

        // mainMenu(input);

        PlayerNode player = new PlayerNode("kevin", 100000, 75);
        InventoryNode potion1 = new InventoryNode("Potion keberkahan", 500);
        InventoryNode potion2 = new InventoryNode("Potion keberkahan2", 500);
        InventoryNode potion3 = new InventoryNode("Potion keberkahan3", 500);
        InventoryNode potion4 = new InventoryNode("Potion keberkahan4", 500);

        player.inventory.push(potion1);
        player.inventory.push(potion2);
        player.inventory.push(potion3);
        player.inventory.push(potion4);

        // Level1 level1 = new Level1(player);
        // level1.start();
        // Level2 level2 = new Level2(player);
        // level2.start();
        // Level3 level3 = new Level3(player);
        // level3.start();
        Level4 level4 = new Level4(player);
        level4.start();
        // LevelNPC npc = new LevelNPC(player);
        // npc.Start();
        LevelBoss levelBoss = new LevelBoss(player);
        levelBoss.start();


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
        System.out.println("\t\tWELCOME PLAYER");
        System.out.print("Masukan nama anda: ");
        String playerName = input.nextLine();

        utils.Utils.loadingAnimation(25, 100);

        utils.Utils.clearScreen();
        System.out.println("\nHallo " + playerName);
        System.out.println("Siap untuk berpetualang di Arcadia Ascension?");
        System.out.print(" [Tekan ENTER untuk Lanjut]...");
        input.nextLine();

        PlayerNode player = new PlayerNode(playerName, 150, 75);
        InventoryNode potion1 = new InventoryNode("Potion keberkahan", 500);
        InventoryNode potion2 = new InventoryNode("Potion keberkahan2", 500);
        InventoryNode potion3 = new InventoryNode("Potion keberkahan3", 500);
        InventoryNode potion4 = new InventoryNode("Potion keberkahan4", 500);

        player.inventory.push(potion1);
        player.inventory.push(potion2);
        player.inventory.push(potion3);
        player.inventory.push(potion4);
        graph.setPlayerLocation(0);

        Level1 level1 = new Level1(player);
        level1.start();

        utils.Utils.loadingAnimation(25, 100);
        Scanner sc = new Scanner(System.in);

        while (player.getCurrentHp() > 0) {
            Utils.clearScreen();
            // start narasi
            // ===========================================================================================

            System.out.println("==============================================================");
            System.out.println(" 📜 PETA KERAJAAN ARCADIA 📜");
            System.out.println("==============================================================");
            utils.Utils.loadingAnimation(20, 80);

            System.out.println("Seorang sosok berjubah mendekat perlahan... bayangannya memanjang di lantai satu.\n" +
                    "Suara berat namun tenang terdengar dari balik tudungnya:");

            utils.Utils.loadingAnimation(25, 90);
            System.out.println();
            System.out.println(" \"Perjalananmu tidak akan mudah, petualang...\"");
            utils.Utils.loadingAnimation(25, 90);
            System.out.println(" \"Namun aku akan memberimu panduan agar kau tidak tersesat.\"");
            utils.Utils.loadingAnimation(25, 90);

            System.out.println();
            System.out.println(
                    "Sosok itu mengeluarkan sehelai gulungan tua dari balik jubahnya.\n" +
                            "Kau dapat melihat simbol-simbol kuno terukir samar di permukaannya...");

            utils.Utils.loadingAnimation(25, 90);
            System.out.println();
            System.out.println(" \"Ambillah. Ini adalah peta wilayah Arcadia.\"");
            utils.Utils.loadingAnimation(25, 90);
            System.out.println(" \"Pahami rutenya... dan jangan biarkan kegelapan mengejarmu.\"");

            utils.Utils.loadingAnimation(25, 90);
            System.out.println();
            System.out.println(
                    "Saat gulungan itu berada di tanganmu, ia langsung membuka sendiri,\n" +
                            "menampilkan jalur, node, dan lokasi penuh misteri di hadapanmu...");
            utils.Utils.loadingAnimation(20, 80);

            System.out.println("\n==============================================================");
            System.out.println(" 📍 PETA TERBENTANG DI DEPANMU 📍");
            System.out.println("==============================================================\n");
            // end narasi
            // ===========================================================================================

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
                utils.Utils.loadingAnimation(25, 200);
            }

            if (!graph.isValidDestination(currentName, destName)) {
                System.out.println("Tujuan tidak terhubung dari lokasi saat ini.");
                utils.Utils.loadingAnimation(25, 200);
            }

            if (graph.isVisited(destId)) {
                System.out.println("Level ini sudah dikunjungi: " + destName);
                graph.setPlayerLocation(destId);
                utils.Utils.loadingAnimation(25, 200);
            }

            runLevel(destId, player);

            graph.markVisited(destId);
            graph.setPlayerLocation(destId);

            System.out.println("Kembali ke peta...");
            try {
                Thread.sleep(700);
            } catch (Exception e) {
            }
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
                // LevelCheckSistem check = new LevelCheckSistem();
                // check.runLevelCheck();
                break;
            case 7: // The Eternal Relic -> Boss
                // LevelBoss boss = new LevelBoss(player);
                // boss.start();
                break;
            default:
                System.out.println("Input anda tidak valid....");
                utils.Utils.loadingAnimation(25, 100);
                break;
        }
    }

}
