package level.level2;

import player.PlayerNode;
import java.util.Scanner;

public class Level2Game {
    private QueueLevel2 monsterQueue;
    private PlayerNode player;
    private Scanner input;
    
    private void cls() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Level2Game(PlayerNode player) {
        this.player = player;
        this.monsterQueue = new QueueLevel2();
        this.input = new Scanner(System.in);
        initializeMonsters();
    }
    
    private void initializeMonsters() {
        monsterQueue.enqueue("Scarecrow", 1, 500);
        monsterQueue.enqueue("Slime", 10, 250);
        monsterQueue.enqueue("Goblin", 15, 300);
        monsterQueue.enqueue("Orc", 25, 500);
        monsterQueue.enqueue("Troll", 35, 650);
    }

    public void startL2() {        
        Monster current = monsterQueue.peek();
        while (!monsterQueue.isEmpty() && player.getCurrentHp() > 0) {
            cls();
            boolean won = battle(current);
            
            if (won) {
                System.out.println("\n" + current.nama + " telah dikalahkan!");
                
                if (!monsterQueue.isEmpty()) {
                    monsterQueue.dequeue();
                    current = monsterQueue.peek();
                    System.out.println("\n===============================================");
                    System.out.println("Player: " + player.getPlayerName());
                    System.out.println("Health: " + player.getCurrentHp() + "/" + player.getMaxHp());
                    System.out.println("===============================================");
                    System.out.println("=  Kamu melihat sebuah monster mendekat. . .  =");
                    System.out.println("===============================================");
                    System.out.println("Monster Selanjutnya: " + current.nama);
                    System.out.println("Monster HP: " + current.health + " | Damage: " + current.damage);
                    System.out.println("\nTekan enter untuk melanjutkan battle...");
                    input.nextLine();
                }
            } else {
                System.out.println("\nKamu dikalahkan oleh " + current.nama);
                break;
            }
        }        
        if (monsterQueue.isEmpty()) {
            System.out.println("\nLEVEL 2 DITAKLUKAN!");
            System.out.println("Kamu mengalahkan semua monster!");
            System.out.println("Final HP: " + player.getCurrentHp() + "/" + player.getMaxHp());
            player.levelUp();
            System.out.println("Kamu naik level menjadi level " + player.getLevel() + "!");
        } else if (player.getCurrentHp() <= 0) {
            System.out.println("\nKamu Pingsan...");
        }
    }
    
    private boolean battle(Monster monster) {
        int monsterHealth = monster.health;
        int playerHealth = player.getCurrentHp();
        
        System.out.println("\n--- BATTLE START ---");
        System.out.println("Melawan: " + monster.nama);
        
        while (monsterHealth > 0 && playerHealth > 0) {
            System.out.println("\n============================================");
            System.out.println("BATTLE: " + player.getPlayerName() + " vs " + monster.nama);
            System.out.println("============================================");
            System.out.println(player.getPlayerName() + " HP: " + playerHealth + "/" + player.getMaxHp());
            System.out.println(monster.nama + " HP: " + monsterHealth + "/" + monster.maxhealth);
            System.out.println("============================================");
            
            playerTurn(monster);
            
            monsterHealth = monster.health;
            
            if (monsterHealth <= 0) {
                return true;
            }
            
            monsterTurn();
            playerHealth = player.getCurrentHp();
            
            if (playerHealth <= 0) {
                player.setCurrentHp(0);
                return false;
            }
        }
        
        player.setCurrentHp(playerHealth);
        return true;
    }
    
    private void playerTurn(Monster monster) {
        boolean validChoice = false;
        
        while (!validChoice) {
            System.out.println("\nYOUR TURN:");
            System.out.println("1. Serang (Melakukan " + player.attack() + " damage)");
            System.out.println("2. Gunakan Potion");
            System.out.print("Pilih Aksi [1/2]: ");
            
            try {
                int choice = input.nextInt();
                input.nextLine();
                
                switch (choice) {
                    case 1:
                        cls();
                        int damageDealt = player.attack();
                        System.out.println("Kamu menyerang untuk " + damageDealt + " damage!");
                        monster.health -= damageDealt;
                        validChoice = true;
                        break;
                        
                    case 2:
                        validChoice = potionUse();
                        break;
                                                
                    default:
                        System.out.println("Pilihan tidak valid. Silahkan pilih 1, or 2.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Pilihan tidak valid. Tolong masukkan sebuah angka.");
                input.nextLine();
            }
        }
    }

    private boolean potionUse() {
        cls();
        player.getInventory().printInventory();

        while (true) {
            if (player.getInventory().isEmpty()) {
                System.out.println("\nInventory kamu kosong! Tidak ada potion yang bisa digunakan.");
                System.out.println("Tekan enter untuk melanjutkan...");
                input.nextLine();
                cls();
                return false;
            }

            System.out.println("\nGunakan sebuah potion?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            System.out.print("Pilih Aksi [1/2]: ");

            try {
                int choice = input.nextInt();
                input.nextLine();
                
                switch (choice) {
                    case 1:
                        System.out.print("\nMasukkan nama yang sama (atau tulis 'cancel' untuk kembali): ");
                        String potionName = input.nextLine().trim();
                    
                        if (potionName.equalsIgnoreCase("cancel")) {
                            cls();
                            player.getInventory().printInventory();
                            continue;
                        } else if (player.getInventory().SearchPotion(potionName)) {
                            player.getInventory().UsePotion(potionName);
                            System.out.println("\nTekan enter untuk melanjutkan...");
                            input.nextLine();
                            cls();
                            return true;
                        } else {
                            System.out.println("Potion '" + potionName + "' tidak ditemukan di inventory!");
                            System.out.println("Tekan enter untuk melanjutkan...");
                            input.nextLine();
                            cls();
                            player.getInventory().printInventory();
                            break;
                        }
                            
                    case 2:
                        return false;
                                                
                    default:
                        System.out.println("Pilihan tidak valid. Silahkan pilih 1, or 2.");
                        System.out.println("Tekan enter untuk melanjutkan...");
                        input.nextLine();
                        cls();
                        player.getInventory().printInventory();
                        break;
                }
            } catch (Exception e) {
                System.out.println("Pilihan tidak valid. Tolong masukkan sebuah angka.");
                input.nextLine();
                cls();
            }
        }
    }
    
    private void monsterTurn() {
        Monster currentMonster = monsterQueue.peek();
        int monsterDamage = currentMonster.damage;
        int newPlayerHp = player.getCurrentHp();

            System.out.println("\n" + currentMonster.nama + " menyerang untuk " + monsterDamage + " damage!");
            newPlayerHp = player.getCurrentHp() - monsterDamage;

        if (newPlayerHp < 0) newPlayerHp = 0;
        
        player.setCurrentHp(newPlayerHp);
        
        if (newPlayerHp <= 0) {
            System.out.println("Kamu telah dikalahkan...");
        }
    }
}