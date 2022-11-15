class MyLinkedList {

    public static void main(String[] args)
    {
        MyLinkedList test = new MyLinkedList();
        test.addAtHead(5);
        test.addAtIndex(1, 2);
        test.get(1);
        test.addAtHead(6);
        test.addAtTail(2);
        test.get(3);
        test.addAtTail(1);
        test.get(5);
        test.addAtHead(2);
        test.get(2);
        test.addAtHead(6);
    }
    class Node{
        int data;
        Node next;

        public Node(int data, Node next){
            this.data = data;
            this.next = next;
        }
    }

    Node top;
    int length;

    public MyLinkedList() {
        top = null;
        length =0;
    }

    public int get(int index) {
        int currValue =-1;

        if(index<length && index>=0){
            int counter = 0;
            Node curr = top;
            Node prev = null;
            //1->2->3

            while(counter < index){
                prev = curr;
                curr = curr.next;
                currValue = curr.data;
                counter++;
            }
            if(null==prev){
                currValue = top.data;
            }
        }
        return currValue;
    }

    public void addAtHead(int val) {
        Node newNode = new Node(val, top);
        top = newNode;

        length++;
    }

    public void addAtTail(int val) {

        Node curr = top;
        Node prev = null;

        while(null != curr){
            prev = curr;
            curr = curr.next;
        }

        if(null==prev){
            top = new Node(val, null);
        }else{
            prev.next = new Node(val, null);
        }

        length++;
    }

    public void addAtIndex(int index, int val) {
        if(index == length){
            addAtTail(val);
        }else if(index<length && index>=0){
            int counter = 0;
            Node curr = top;
            Node prev = null;

            while(counter < index){
                prev = curr;
                curr = curr.next;
                counter++;
            }
            if(null==prev){
                top = new Node(val, top);
            }else{
                prev.next = new Node(val, curr);
            }
            length++;

        }
    }

    public void deleteAtIndex(int index) {
        if(index<length && index>=0){
            int counter = 0;
            Node curr = top;
            Node prev = null;

            while(counter < index){
                prev = curr;
                curr = curr.next;
                counter++;
            }
            if(null==prev)
                top = top.next;
            else
                prev.next = curr.next;

            length--;

        }
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */