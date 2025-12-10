package level.NPC;

import player.PlayerNode;
import player.inventory.InventoryNode;
import utils.Display;
import utils.Utils;
import java.util.Scanner;

public class LevelNPC {
    NodeKalimat first;
    PlayerNode player;

    public LevelNPC(PlayerNode player) {
        this.player = player;
    }

    public void Start() {
        addKalimat("Ah... akhirnya kau datang juga, " + player.getPlayerName() + ".");
        addKalimat("Perjalananmu sampai titik ini tidaklah mudah, bukan?");
        addKalimat("Namun semakin jauh kau melangkah, semakin gelap rahasia dunia ini terbuka.");
        addKalimat("Di balik puncak gunung ini terdapat Relik Kuno yang mampu membangkitkan kekuatan para leluhur.");
        addKalimat(
                "Sayangnya... Relik itu dijaga oleh Dark Lord, makhluk yang kekuatannya semakin tumbuh setiap malam.");
        addKalimat("Jika kau ingin bertahan melawannya, kau membutuhkan kekuatan tambahan.");
        addKalimat("Aku akan memberikanmu tiga ramuan penyembuh langka.");
        addKalimat("Gunakan mereka dengan bijak... nasibmu di medan pertempuran bergantung padanya.");

        InventoryNode aetherBloom = new InventoryNode("Aether Bloom (heal potion)", 150);
        InventoryNode elixirVitalis = new InventoryNode("Elixir Vitalis (heal potion)", 300);
        InventoryNode draughtOfLife = new InventoryNode("Draught of Life (heal potion)", 600);

        Scanner input = new Scanner(System.in);

        Utils.clearScreen();
        Display.displayBanner();

        System.out.println("=====================================================================================");
        System.out.println("        -----------------------   BERTEMU NPC  -------------------------");
        System.out.println("=====================================================================================");

        NodeKalimat current = first;

        while (current != null) {
            System.out.println("\nNPC: " + current.kalimat);
            System.out.print(" [Tekan ENTER untuk lanjut]...");
            current = current.next;
            input.nextLine();
        }

        Utils.clearScreen();
        Display.displayBanner();

        System.out.println("=====================================================================================");
        System.out.println("       NPC MEMBERIKANMU 3 RAMUAN PENYEMBUH LANGKA");
        System.out.println("=====================================================================================");

        System.out.print("Anda menerima tiga 'Heal Potion' langka...");
        input.nextLine();
        System.out.print("Gunakan ramuan ini untuk memulihkan kekuatanmu di tengah pertarungan.");
        input.nextLine();

        player.inventory.push(aetherBloom);
        Utils.timeoutInSecond(1);
        player.inventory.push(elixirVitalis);
        Utils.timeoutInSecond(1);
        player.inventory.push(draughtOfLife);
    }

    public void addKalimat(String kalimat) {
        NodeKalimat newNode = new NodeKalimat(kalimat);
        if (first == null) {
            first = newNode;
        } else {
            NodeKalimat current = first;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
            newNode.back = current;
        }
    }

}