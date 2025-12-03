package levels;

import models.NodePlayer;
import java.util.Scanner;

public class Level5_TreeDungeonMaze {
    private NodePlayer player;
    private Scanner scanner;

    private class TreeNode {
        String description;
        String challenge;
        int reward;
        TreeNode left;
        TreeNode right;

        TreeNode(String desc, String challenge, int reward) {
            this.description = desc;
            this.challenge = challenge;
            this.reward = reward;
            this.left = null;
            this.right = null;
        }
    }

    private TreeNode root;

    public Level5_TreeDungeonMaze(NodePlayer player, Scanner scanner) {
        this.player = player;
        this.scanner = scanner;
        buildDungeonTree();
    }

    private void buildDungeonTree() {
        // Build binary tree untuk dungeon maze
        root = new TreeNode(
            "Entrance - Dungeon Gate",
            "Pilih jalan: Kiri (Treasure Hall) atau Kanan (Dragon Chamber)",
            0
        );

        // Left subtree - Treasure Hall
        root.left = new TreeNode(
            "Treasure Hall",
            "Anda menemukan harta karun! Ambil sebanyak mungkin (HP +30)",
            30
        );

        root.left.left = new TreeNode(
            "Safe Room",
            "Ruangan aman, pulihkan HP penuh",
            50
        );

        root.left.right = new TreeNode(
            "Trap Chamber",
            "Jebakan! Ambil damage (HP -20)",
            -20
        );

        // Right subtree - Dragon Chamber
        root.right = new TreeNode(
            "Ancient Dragon's Lair",
            "Pertarungan dengan Ancient Dragon!",
            100
        );

        root.right.left = new TreeNode(
            "Crystal Vault",
            "Kumpulkan kristal magis (Attack +10)",
            0
        );

        root.right.right = new TreeNode(
            "Black Knight's Chamber",
            "Pertarungan dengan Black Knight!",
            75
        );
    }

    public boolean play() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("LEVEL 5: DUNGEON MAZE - Tree Challenge");
        System.out.println("=".repeat(60));
        System.out.println("\nAnda memasuki dungeon yang dalam dan kompleks.");
        System.out.println("Anda harus menavigasi melalui pohon dungeon dengan bijak!");

        boolean escaped = exploreDungeon(root, 1);

        if (escaped && player.isAlive()) {
            System.out.println("\n*** Anda berhasil keluar dari dungeon! ***");
            player.gainExp(150);
            player.showStats();
            return true;
        } else {
            if (!player.isAlive()) {
                System.out.println("\n*** Anda kalah dalam dungeon! ***");
            } else {
                System.out.println("\n*** Anda masih terperangkap! ***");
            }
            return false;
        }
    }

    private boolean exploreDungeon(TreeNode node, int depth) {
        if (node == null || !player.isAlive()) {
            return false;
        }

        String indent = "  ".repeat(depth - 1);
        System.out.println("\n" + indent + "[Level " + depth + "]");
        System.out.println(indent + node.description);
        System.out.println(indent + player.nama + " HP: " + player.hp + "/" + player.maxHp);

        // Special encounters
        if (node.description.contains("Dragon")) {
            return battleAncientDragon();
        } else if (node.description.contains("Black Knight")) {
            return battleBlackKnight();
        } else if (node.description.contains("Trap")) {
            player.takeDamage(20);
            System.out.println(indent + "Anda terkena jebakan!");
            return true; // Continue exploring
        } else if (node.description.contains("Safe Room")) {
            player.healHP(100);
            System.out.println(indent + "Anda beristirahat dan pulih sepenuhnya!");
            return true;
        } else if (node.description.contains("Treasure Hall")) {
            player.healHP(30);
            System.out.println(indent + "Anda menemukan harta karun!");
            return true;
        } else if (node.description.contains("Crystal Vault")) {
            player.attack += 10;
            System.out.println(indent + "Anda mengumpulkan kristal! Attack +10");
            return true;
        } else if (node.description.contains("Entrance")) {
            System.out.println(indent + node.challenge);
            
            System.out.print("\nPilihan jalan (L untuk Kiri / R untuk Kanan): ");
            String choice = scanner.nextLine().trim().toUpperCase();

            if (choice.equals("L")) {
                System.out.println(indent + ">>> Anda memilih jalan ke Kiri (Treasure Hall)");
                return exploreDungeon(node.left, depth + 1);
            } else if (choice.equals("R")) {
                System.out.println(indent + ">>> Anda memilih jalan ke Kanan (Dragon Chamber)");
                return exploreDungeon(node.right, depth + 1);
            } else {
                System.out.println("Pilihan tidak valid!");
                return exploreDungeon(node, depth);
            }
        }

        return true;
    }

    private boolean battleAncientDragon() {
        System.out.println("\n!!! ANCIENT DRAGON MUNCUL !!!");
        int dragonHp = 80;
        int turns = 0;

        while (dragonHp > 0 && player.isAlive() && turns < 8) {
            turns++;
            System.out.println("\n[Turn " + turns + "] Dragon HP: " + dragonHp + " | " + player.nama + " HP: " + player.hp);
            System.out.println("1. Serang\n2. Defensive Stance\n3. Lari");

            System.out.print("Pilihan: ");
            String action = scanner.nextLine().trim();

            if (action.equals("1")) {
                int damage = player.attack + (int)(Math.random() * 12);
                dragonHp -= damage;
                System.out.println("Anda menyerang! Damage: " + damage);
                
                if (dragonHp > 0) {
                    int dragonDamage = 20 + (int)(Math.random() * 10);
                    player.takeDamage(dragonDamage);
                }
            } else if (action.equals("2")) {
                System.out.println("Anda bertahan defensif!");
                int dragonDamage = 10 + (int)(Math.random() * 5);
                player.takeDamage(dragonDamage);
            } else if (action.equals("3")) {
                System.out.println("Anda berhasil lari dari dragon!");
                return true;
            }
        }

        if (dragonHp <= 0) {
            System.out.println("\n*** ANDA MENANG MELAWAN ANCIENT DRAGON! ***");
            player.gainExp(200);
            return true;
        } else {
            System.out.println("\n*** ANDA KALAH MELAWAN ANCIENT DRAGON! ***");
            return false;
        }
    }

    private boolean battleBlackKnight() {
        System.out.println("\n!!! BLACK KNIGHT MUNCUL !!!");
        int knightHp = 65;

        while (knightHp > 0 && player.isAlive()) {
            System.out.println("\nBlack Knight HP: " + knightHp + " | " + player.nama + " HP: " + player.hp);
            System.out.println("1. Serang\n2. Parry\n3. Lari");

            System.out.print("Pilihan: ");
            String action = scanner.nextLine().trim();

            if (action.equals("1")) {
                int damage = player.attack + (int)(Math.random() * 10);
                knightHp -= damage;
                System.out.println("Anda menyerang! Damage: " + damage);
                
                if (knightHp > 0) {
                    int knightDamage = 15 + (int)(Math.random() * 8);
                    player.takeDamage(knightDamage);
                }
            } else if (action.equals("2")) {
                System.out.println("Anda melakukan parry!");
                int knightDamage = 8 + (int)(Math.random() * 4);
                player.takeDamage(knightDamage);
            } else if (action.equals("3")) {
                System.out.println("Anda berhasil lari!");
                return true;
            }
        }

        if (knightHp <= 0) {
            System.out.println("\n*** ANDA MENANG MELAWAN BLACK KNIGHT! ***");
            player.gainExp(175);
            return true;
        } else {
            System.out.println("\n*** ANDA KALAH MELAWAN BLACK KNIGHT! ***");
            return false;
        }
    }
}
