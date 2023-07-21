package deque;

public class LinkedListDeque<type> {
    int size = 0;
    private node sentinel;
    public class node{
        public node prev;
        public node next;
        public type item;
        public node(type i, node n, node p ){
            prev = p;
            next = n;
            item = i;
        }
    }
    public LinkedListDeque() {
        sentinel = new node(null, null, null);
        size = 0;
    }
    public void addFirst(type item){
        size ++;
        node tmp = new node(item, sentinel, sentinel);
        sentinel.next = tmp;
        sentinel.prev = tmp;
    }
}
