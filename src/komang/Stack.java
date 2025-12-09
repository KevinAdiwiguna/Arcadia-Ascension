public class Stack {
    private Node top;

    private static class Node {
        String data;
        Node next;
        Node(String d) { data = d; next = null; }
    }

    public void push(String val) {
        Node n = new Node(val);
        n.next = top;
        top = n;
    }

    public String pop() {
        if (top == null) return null;
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

    public void printBottomToTop() {
        System.out.print("BOTTOM -> ");
        printBottomToTopNode(top);
        System.out.println();
    }

    private void printBottomToTopNode(Node node) {
        if (node == null) return;
        printBottomToTopNode(node.next);
        System.out.print(node.data + " | ");
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
        Stack reverse = new Stack();
        Node cur = top;
        while (cur != null) {
            reverse.push(cur.data);
            cur = cur.next;
        }
        Stack result = new Stack();
        while (!reverse.isEmpty()) {
            result.push(reverse.pop());
        }
        return result;
    }
}
