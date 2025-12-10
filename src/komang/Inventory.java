package komang;
public class Inventory {
    private Stack bag = new Stack();

    public void tambah(String item) {
        bag.push(item);
    }

    public String ambil() {
        return bag.pop();
    }

    public void tampilkan() {
        System.out.println("Inventory:");
        bag.printStack();
    }
}
