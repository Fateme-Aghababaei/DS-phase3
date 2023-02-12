package Main;

public class Stack<E> {
    private class Node<E> {
        private E data;
        private Node<E> next;

        Node(E data) {
            this.data = data;
            this.next = null;
        }

        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
        }
    }

    private Node<E> top;
    private int size;

    Stack() {
        this.top = null;
        this.size = 0;
    }

    public Stack(Stack<E> stack) {
        if (stack.isEmpty()) {
            top = null;
            size = 0;
        }
        else {
            Node<E> node = stack.top;
            this.top = new Node<>(node.getData());
            this.size = stack.size;

            Node<E> new_node = this.top;
            while (node != null) {
                node = node.next;
                if (node == null) new_node.next = null;
                else new_node.next = new Node<>(node.getData());
                new_node = new_node.next;
            }
        }
    }

    boolean isEmpty() {
        return size == 0;
    }

    void push(E data) {
        Node<E> n = new Node<>(data);
        if (!isEmpty())
            n.next = top;
        top = n;
        size++;
    }

    E pop() {
        if (isEmpty()) return null;
        E data = top.getData();
        top = top.next;
        size--;
        return data;
    }

    E peek() {
        if (isEmpty()) return null;
        return top.getData();
    }

    public int getSize() {
        return size;
    }

    public String toString() {
        if (isEmpty()) return "";
        StringBuilder s = new StringBuilder();
        Node<E> n = top;
        while (n != null) {
            s.append(n.data).append(" ");
            n = n.next;
        }
        return s.toString();
    }
}
