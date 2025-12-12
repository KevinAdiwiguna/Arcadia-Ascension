package level.LevelCheck;

import level.Map.Graph;
import utils.Utils;
import java.util.Scanner;

public class LevelCheckSistem {
    class LevelNode {
        int levelNumber;
        boolean isCompleted;
        LevelNode next;

        LevelNode(int levelNumber) {
            this.levelNumber = levelNumber;
            this.isCompleted = false;
            this.next = null;
        }
    }

    LevelNode head;
    private Graph graph;

    public LevelCheckSistem(Graph graph) {
        head = null;
        addLevel(1);
        addLevel(2);
        addLevel(3);
        addLevel(4);
        addLevel(5);
        this.graph = graph;
        updateCompletionFromGraph();
    }

    // helper to build the small list of levels we check
    private void addLevel(int levelNumber) {
        LevelNode newNode = new LevelNode(levelNumber);
        if (head == null) head = newNode;
        else {
            LevelNode temp = head;
            while (temp.next != null) temp = temp.next;
            temp.next = newNode;
        }
    }

    // Sync level completion flags from graph visited status
    private void updateCompletionFromGraph() {
        if (graph == null) return;
        LevelNode temp = head;
        while (temp != null) {
            temp.isCompleted = graph.isVisited(temp.levelNumber);
            temp = temp.next;
        }
    }

    // Run the level check flow: check visited levels and print shortest paths
    private void runLevelCheck() {
        updateCompletionFromGraph();

        Scanner scanner = new Scanner(System.in);

        boolean allDone = true;
        LevelNode temp = head;
        while (temp != null) {
            if (!temp.isCompleted) {
                allDone = false;
                break;
            }
            temp = temp.next;
        }

        if (allDone) {
            System.out.println("STATUS: Semua level yang diperlukan sudah diselesaikan.");
            System.out.println("Penjaga: \"Kau boleh lewat. Boss level terbuka.\"");
            System.out.println("ACCESS GRANTED");
            System.out.println();
            System.out.print("Tekan ENTER untuk kembali...");
            try { scanner.nextLine(); } catch (Exception e) {}
            return;
        }

        System.out.println("STATUS: Ada level yang belum terselesaikan.");
        System.out.println("Penjaga: \"Kau belum bisa lewat dari sini. Pergilah ke level yang belum selesai.\"");

        int playerLoc = -1;
        if (graph != null) playerLoc = graph.getPlayerLocationId();

        temp = head;
        while (temp != null) {
            if (!temp.isCompleted) {
                System.out.println("\n- Level " + temp.levelNumber + " belum diselesaikan.");
                if (graph != null && playerLoc != -1) {
                    int destId = graph.returnID("Level " + temp.levelNumber);
                    if (destId != 9999) {
                        int[] path = graph.shortestPath(playerLoc, destId);
                        if (path == null || path.length == 0) {
                            System.out.println("  Tidak ada jalur dari lokasi sekarang ke Level " + temp.levelNumber + ".");
                        } else {
                            System.out.print("  Jalur terpendek: ");
                            for (int i = 0; i < path.length; i++) {
                                int id = path[i];
                                String name = graph.findById(id).trim();
                                System.out.print(id + "(" + name + ")");
                                if (i < path.length - 1) System.out.print(" -> ");
                            }
                            System.out.println();
                        }
                    }
                }
            }
            temp = temp.next;
        }

        System.out.println("\nACCESS DENIED");
        System.out.println();
        System.out.print("Tekan ENTER untuk kembali...");
        try { scanner.nextLine(); } catch (Exception e) {}
    }

    // Show a short dialog first, wait for player (ENTER), then run the check.
    public void Start() {
        Scanner scanner = new Scanner(System.in);

        Utils.clearScreen();
        System.out.println("\n=====================================================================================");
        System.out.println("-----------------------   LEVEL 6 - Rungan Kelayakan   -------------------------");
        System.out.println("=====================================================================================");
        System.out.println();
        System.out.println("Seorang penjaga berdiri di hadapanmu dan berkata pendek:");
        System.out.println("\"Sebelum kau melanjutkan, aku harus memastikan semua tugasmu selesai.\"");
        System.out.println();
        System.out.print("Tekan ENTER untuk melanjutkan...");
        scanner.nextLine();
        Utils.clearScreen();
        runLevelCheck();
    }
}
