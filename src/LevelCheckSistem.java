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

    public LevelCheckSistem() {
        head = null;
        addLevel(1);
        addLevel(2);
        addLevel(3);
        addLevel(4);
    }

    // ADD LEVEL
    private void addLevel(int levelNumber) {
        LevelNode newNode = new LevelNode(levelNumber);

        if (head == null) {
            head = newNode;
        } else {
            LevelNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }


    // SET LEVEL COMPLETED
    public void setLevelCompleted(int levelNumber) {
        LevelNode temp = head;

        while (temp != null) {
            if (temp.levelNumber == levelNumber) {
                temp.isCompleted = true;
                return;
            }
            temp = temp.next;
        }
    }

    // CHECK ALL LEVEL
    public boolean isAllLevelCompleted() {
        LevelNode temp = head;

        while (temp != null) {
            if (!temp.isCompleted) {
                return false;
            }
            temp = temp.next;
        }
        return true;
    }

    // DISPLAY STATUS (TEKNIS)
    public void showLevelStatus() {
        LevelNode temp = head;

        System.out.println("=== LEVEL STATUS ===");
        while (temp != null) {
            System.out.println(
                "Level " + temp.levelNumber + " : " +
                (temp.isCompleted ? "COMPLETED" : "NOT COMPLETED")
            );
            temp = temp.next;
        }
    }

    // DISPLAY UNFINISHED LEVEL
    public void showUnfinishedLevels() {
        LevelNode temp = head;

        System.out.println("=== UNFINISHED LEVEL ===");
        while (temp != null) {
            if (!temp.isCompleted) {
                System.out.println("Level " + temp.levelNumber + " belum diselesaikan.");
            }
            temp = temp.next;
        }
    }

    // MAIN LEVEL CHECK SYSTEM
    public void runLevelCheck() {
        System.out.println("\n=== LEVEL CHECK SYSTEM ===");

        showLevelStatus();

        if (isAllLevelCompleted()) {
            System.out.println("STATUS : ALL LEVEL COMPLETED");
            System.out.println("BOSS LEVEL : UNLOCKED");
            System.out.println("ACCESS GRANTED");
        } else {
            System.out.println("STATUS : LEVEL INCOMPLETE");
            System.out.println("BOSS LEVEL : LOCKED");
            showUnfinishedLevels();
            System.out.println("ACCESS DENIED");
        }
    }
}
