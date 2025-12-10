package level.NPC;

import player.*;
import player.inventory.InventoryNode;
import utils.ClearScreen;
import java.util.Scanner;

public class LevelNPC {
    NodeKalimat first;
    PlayerNode player;

    public LevelNPC(PlayerNode player){
        this.player = player;
    }

    public void Start(){
        addKalimat("Hallo " + player.getPlayerName());
        addKalimat("Apa kabar hari ini?");
        addKalimat("Apakah kamu tau bahwa di dunia ini ada banyak rahasia tersembunyi?");
        addKalimat("di ujung gunung ini terdapat sebuah relik kuno yang sangat kuat.");
        addKalimat("tapi ada Dark Lord yang menjaga relik tersebut.");
        addKalimat("kamu perlu senjata untuk mengalahkannya.");
        addKalimat("saya akan memberikanmu sebuah potion legendaris.");
        addKalimat("kalahkan dia dan dapatkan relik itu!");
        
        InventoryNode potionKehidupan = new InventoryNode("potion Kehidupan", 1000);
        InventoryNode potionHidup = new InventoryNode("potion hidup", 1000);
        InventoryNode potion1 = new InventoryNode("potion 1", 1000);
        InventoryNode potion2 = new InventoryNode("potion 2", 1000);
        InventoryNode potion3 = new InventoryNode("potion 3", 1000);
        InventoryNode potion4 = new InventoryNode("potion 4", 1000);

        NodeKalimat current = first;
        Scanner input = new Scanner(System.in);
        ClearScreen.clearScreen();
        Banner();
        while(current != null){
            System.out.println("\nNPC: " + current.kalimat);
            System.out.print(" [Tekan ENTER untuk Lanjut]...");
            current = current.next;
            input.nextLine();
        }

        
        ClearScreen.clearScreen();
        Banner();
        
        System.out.println("=====================================================================================");
        System.out.println("        -----------------------   BERTEMU NPC  -------------------------");
        System.out.println("=====================================================================================");

        System.out.print("Anda mendapakan potion 'kehidupan'.....");
            input.nextLine();
        System.out.print("Gunakan potion ini untuk menambah darah anda.");
            input.nextLine();
        player.inventory.push(potionKehidupan);
        player.inventory.push(potionHidup);
        player.inventory.push(potion1);
        player.inventory.push(potion2);
        player.inventory.push(potion3);
        player.inventory.push(potion4);
    }

    public void addKalimat(String kalimat){
        NodeKalimat newNode = new NodeKalimat(kalimat);
        if(first == null){
            first = newNode;
        }else{
            NodeKalimat current = first;
            while(current.next != null){
                current = current.next;
            }
            current.next = newNode;
            newNode.back = current;
        }
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
