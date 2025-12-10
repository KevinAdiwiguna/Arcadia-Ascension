package komang;
public class MainGame {
    public static void main(String[] args) {
        PlayerNode player = new PlayerNode("Hero", 100, 10);
        Level3Game level = new Level3Game(player);
        level.start();
    }
}
