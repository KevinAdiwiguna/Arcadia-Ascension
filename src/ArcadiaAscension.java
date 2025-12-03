import models.NodePlayer;
import levels.*;
import java.util.Scanner;

public class ArcadiaAscension {
    private static Scanner scanner;
    private static NodePlayer player;


    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        
        // Title screen
        showTitleScreen();
        
        // Create player
        System.out.print("Masukkan nama pahlawan Anda: ");
        String playerName = scanner.nextLine().trim();
        
        if (playerName.isEmpty()) {
            playerName = "Hero";
        }
        
        player = new NodePlayer(playerName);
        
        System.out.println("\nSelamat datang, " + player.nama + "!");
        System.out.println("Perjalananmu menuju Arcadia Ascension dimulai...\n");
        
        // Game flow
        boolean continueGame = true;
        int levelsPassed = 0;
        
        // Level 1: Linked List
        if (continueGame) {
            Level1_LinkedListForest level1 = new Level1_LinkedListForest(player, scanner);
            continueGame = level1.play();
            if (continueGame) {
                levelsPassed++;
            } else {
                gameOver(levelsPassed);
                scanner.close();
                return;
            }
            pauseGame();
        }
        
        // Level 2: Stack
        if (continueGame) {
            Level2_StackMemoryPuzzle level2 = new Level2_StackMemoryPuzzle(player, scanner);
            continueGame = level2.play();
            if (continueGame) {
                levelsPassed++;
            } else {
                gameOver(levelsPassed);
                scanner.close();
                return;
            }
            pauseGame();
        }
        
        // Level 3: Queue
        if (continueGame) {
            Level3_QueuePotionBrewing level3 = new Level3_QueuePotionBrewing(player, scanner);
            continueGame = level3.play();
            if (continueGame) {
                levelsPassed++;
            } else {
                gameOver(levelsPassed);
                scanner.close();
                return;
            }
            pauseGame();
        }
        
        // Level 4: Sorting & Searching
        if (continueGame) {
            Level4_SortingSearchingLibrary level4 = new Level4_SortingSearchingLibrary(player, scanner);
            continueGame = level4.play();
            if (continueGame) {
                levelsPassed++;
            } else {
                gameOver(levelsPassed);
                scanner.close();
                return;
            }
            pauseGame();
        }
        
        // Level 5: Tree
        if (continueGame) {
            Level5_TreeDungeonMaze level5 = new Level5_TreeDungeonMaze(player, scanner);
            continueGame = level5.play();
            if (continueGame) {
                levelsPassed++;
            } else {
                gameOver(levelsPassed);
                scanner.close();
                return;
            }
            pauseGame();
        }
        
        // Final Boss
        if (continueGame && levelsPassed == 5) {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("Anda telah melewati semua 5 level!");
            System.out.println("Saatnya untuk pertarungan final melawan DARK LORD...");
            System.out.println("=".repeat(60));
            pauseGame();
            
            BossFight bossFight = new BossFight(player, scanner);
            boolean playerWon = bossFight.play();
            
            if (playerWon) {
                System.out.println("\nTerima kasih telah bermain Arcadia Ascension!");
                System.out.println("Sampai jumpa di petualangan berikutnya!");
            } else {
                gameOver(levelsPassed);
            }
        }
        
        scanner.close();
    }
    
    private static void showTitleScreen() {
        System.out.println("=".repeat(60));
        System.out.println("    ╔═══════════════════════════════════════════════╗");
        System.out.println("    ║        ARCADIA ASCENSION - RPG GAME          ║");
        System.out.println("    ║                                               ║");
        System.out.println("    ║   Algorithms & Data Structures Challenge     ║");
        System.out.println("    ╚═══════════════════════════════════════════════╝");
        System.out.println("=".repeat(60));
        System.out.println("\nGame Features:");
        System.out.println("✓ Level 1: Linked List - Chain Forest");
        System.out.println("✓ Level 2: Stack - Memory Puzzle Room");
        System.out.println("✓ Level 3: Queue - Potion Brewing");
        System.out.println("✓ Level 4: Sorting & Searching - Magic Library");
        System.out.println("✓ Level 5: Tree - Dungeon Maze");
        System.out.println("✓ Final Boss Fight - Dark Lord");
        System.out.println("\n" + "=".repeat(60) + "\n");
    }
    
    private static void pauseGame() {
        System.out.print("\n[Press Enter untuk lanjut...]");
        scanner.nextLine();
    }
    
    private static void gameOver(int levelsPassed) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("GAME OVER");
        System.out.println("Anda telah melewati " + levelsPassed + " level(s)");
        System.out.println("Coba lagi untuk mencapai lebih jauh!");
        System.out.println("=".repeat(60));
    }
}