
class HashChaining {

    //array of nodes
    Node[] hash;
    int capacity;
    int size;
    int collisions;

    HashChaining(){
        capacity = HashFunction.hash_size;
        hash = new Node[capacity];
        size = 0;
        collisions = 0;        
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

    int search(int key){
        int index = HashFunction.hash_mod(key);
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
        int index = HashFunction.hash_mod(key);
        Node prev = chaining(hash[index], key);

        // hash[index] is null
        if (prev == null){
            hash[index] = new Node(0, 0, null);
            prev = hash[index];
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
        int index = HashFunction.hash_mod(key);
        Node prev = chaining(hash[index], key);
        if (prev != null && prev.next != null){
            prev.next = prev.next.next;
            size--;
        }
    }


}