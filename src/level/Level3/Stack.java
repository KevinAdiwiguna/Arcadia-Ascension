package level.Level3;

public class Stack {
    private Node top;

    public void push(String val) {
        Node n = new Node(val);
        n.next = top;
        top = n;
    }

    public String pop() {
        if (top == null)
            return null;
        String v = top.data;
        top = top.next;
        return v;
    }

    public String peek() {
        return top == null ? null : top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void printStack() {
        System.out.print("TOP -> ");
        Node cur = top;
        while (cur != null) {
            System.out.print(cur.data + " | ");
            cur = cur.next;
        }
        System.out.println();
    }

    public void printSingleLine() {
        Stack c = cloneStack();
        boolean first = true;
        while (!c.isEmpty()) {
            String v = c.pop();
            System.out.print((first ? "" : " | ") + v);
            first = false;
        }
        System.out.println();
    }

    public Stack cloneStack() {
        Stack temp = new Stack();
        Stack result = new Stack();
        while (!this.isEmpty()) {
            temp.push(this.pop());
        }
        while (!temp.isEmpty()) {
            String v = temp.pop();
            this.push(v);
            result.push(v);
        }
        return result;
    }
}
