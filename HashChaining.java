class HashChaining extends HashAlgo{

    HashChaining(int cap){
        super(cap);
        hash_node = new Node[capacity];
    }

    // search option == 0
    int search(int key){
        int index = Helper.hash_mod(key, capacity);
        Node node = chaining(OP.SEARCH, hash_node[index], key).next;

        if (node == null){
            return -1;
        }
        else{
            return node.val;
        }
    }

    // return previous node
    Node chaining(OP option, Node head, int key){
        last_collision = 0;
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
            last_collision++;
        }

        if (option == OP.INSERT)
            collision += last_collision;
        else if (option == OP.SEARCH)
            search_collision += last_collision;

        return curr;
    }

    int add(int key, int value){
        if (capacity == size) return -1;
        int index = Helper.hash_mod(key, capacity);
        Node prev = chaining(OP.INSERT, hash_node[index], key);

        // hash[index] is null
        
        if (prev == null){
            hash_node[index] = new Node(0, 0, null);
            prev = hash_node[index];
        }

        if (prev.next == null) {
            prev.next = new Node(key, value, null);
            size++;
        }
        else {
            prev.next.key = key;
            prev.next.val = value;
        }
        return 1;
    }

    void delete(int key){
        int index = Helper.hash_mod(key, capacity);
        Node prev = chaining(OP.DELETE, hash_node[index], key);
        if (prev != null && prev.next != null){
            prev.next = prev.next.next;
            size--;
        }
    }

    void print(){
        for (int i = 0; i < capacity; i++) {
            System.out.print(i + ": ");
            Node n = hash_node[i];
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