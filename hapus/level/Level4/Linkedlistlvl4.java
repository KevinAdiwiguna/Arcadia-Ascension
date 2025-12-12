package level.Level4;
class Linkedlistlvl4 {
    Nodelvl4 head;

    public void insertLast(int val) {
        Nodelvl4 newNode = new Nodelvl4(val);
        if (head == null) {
            head = newNode;
            return;
        }
        Nodelvl4 temp = head;
        while (temp.next != null) temp = temp.next;
        temp.next = newNode;
    }

    public int get(int index) {
        Nodelvl4 temp = head;
        int i = 0;
        while (temp != null) {
            if (i == index) return temp.angka;
            temp = temp.next;
            i++;
        }
        return-1;
    }
    
    // copy list
    public Linkedlistlvl4 copy() {
        Linkedlistlvl4 newList = new Linkedlistlvl4();
        Nodelvl4 temp = head;
        while (temp != null) {
            newList.insertLast(temp.angka);
            temp = temp.next;
        }
        return newList;
    }

    // bubble sort linked list
    public void bubbleSort() {
        boolean swapped;
        do {
            swapped = false;
            Nodelvl4 temp = head;
            while (temp != null && temp.next != null) {
                if (temp.angka > temp.next.angka) {
                    int t = temp.angka;
                    temp.angka = temp.next.angka;
                    temp.next.angka = t;
                    swapped = true;
                }
                temp = temp.next;
            }
        } while (swapped);
    }

    public boolean contains(int val) {
        Nodelvl4 temp = head;
        while (temp != null) {
            if (temp.angka == val) return true;
            temp = temp.next;
        }
        return false;
    }
}