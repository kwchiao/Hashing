class HashChaining {

    //array of nodes
    Node[] hash;
    int capacity;
    int size;
    int collision;

    HashChaining(int cap){
        capacity = cap;
        hash = new Node[capacity];
        size = 0;
        collision = 0;        
    }

    int search(int key){
        int index = Helper.hash_mod(key, capacity);
        Node node = chaining(hash[index], key).next;

        if (node == null){
            return -1;
        }
        else{
            return node.val;
        }
    }

    // return previous node
    Node chaining(Node head, int key){
        if (head == null){
            return null;
        }

        Node curr = head;
        Node next = curr.next;
        while (next != null){
            if (next.key == key){
                return curr;
            }
            curr = next;
            next = next.next;
        }
        return curr;
    }

    void add(int key, int value){
        int index = Helper.hash_mod(key, capacity);
        Node prev = chaining(hash[index], key);

        // hash[index] is null
        if (prev == null){
            hash[index] = new Node(0, 0, null);
            prev = hash[index];
        }
        else {
            collision++;
        }

        if (prev.next == null) {
            prev.next = new Node(key, value, null);
            size++;
        }
        else {
            prev.next.key = key;
            prev.next.val = value;
        }
        
    }

    void delete(int key){
        int index = Helper.hash_mod(key, capacity);
        Node prev = chaining(hash[index], key);
        if (prev != null && prev.next != null){
            prev.next = prev.next.next;
            size--;
            collision--;
        }
    }

    void print(){
        for (int i = 0; i < capacity; i++) {
            System.out.print(i + ": ");
            Node n = hash[i];
            if (n != null){
                n = n.next;
            }
            while ( n != null){
                System.out.print(n.key + " ");
                n = n.next;
            }
            System.out.println();
        }
    }
}