import java.util.*; 

class Hash {

    public static void quad_test(){
        // int[] input = {18, 41, 22, 44, 59, 32, 31, 73, 0, 11};
        int[] input = {18, 41, 22, 44, 59, 32, 31, 73, 5, 57, 70};
        QuadraticProbing qp = new QuadraticProbing(13);

        for (int i = 0; i < input.length; i++)
            qp.add(input[i], Helper.hash_mod(input[i], 13));
        
        qp.print();
    }

    public static void cuckoo_test(){
        // cuckoo hashing test
        int[] input = {12,26,92,23,28,94};
        // int[] input = {12,26, 92};
        CuckooHashing ch = new CuckooHashing(11);

        for (int i = 0; i < input.length; i++){
            ch.add(input[i], input[i]);
            // ch.print();
        }
        // ch.delete(26);
        ch.print();
    }

    public static void linear_test(){
        int[] input = {18, 41, 22, 44, 59, 32, 31, 73, 5, 57, 70};
        LinearProbing lp = new LinearProbing(13);

        for (int i = 0; i < input.length; i++)
            lp.add(input[i], Helper.hash_mod(input[i], 13));
        
        lp.print();
    }

    public static void hashchain_test(){
        int[] input = {18, 41, 22, 44, 59, 32, 31, 73};      
        HashChaining hc = new HashChaining(13);

        for (int i = 0; i < input.length; i++)
            hc.add(input[i], Helper.hash_mod(input[i],13) );
        
        hc.delete(18);
        hc.print();
    }

    public static void hash_implementation_test(){
        hashchain_test();
        linear_test();
        cuckoo_test();
        quad_test();
    }

    public static void test(int hash, ArrayList<Integer> list, int capacity, double alpha){
        int load = (int)Math.round(capacity*alpha);
        System.out.println("load: " + load + "      capacity: " + capacity);

        HashAlgo hc = new HashAlgo(capacity);
        switch(hash){
            case 1:
                hc = new HashChaining(capacity);
                break;
            case 2:
                hc = new LinearProbing(capacity);
                break;
            case 3:
                hc = new CuckooHashing(capacity);
                break;
            // case 4:
            //     hc = new QuadraticProbing(capacity);
            //     break;
        }

        long total_time = 0;
        long start_t = 0;
        long end_t = 0;
        
        for (int i = 0; i < load; i++){
            start_t = System.nanoTime();
            hc.add(list.get(i), Helper.hash_mod(list.get(i), capacity));
            end_t = System.nanoTime();
            total_time += (end_t - start_t);
        }

        System.out.println("Avg insert collision: " + hc.collision * 1.0 /hc.size);
        System.out.println("Avg insert run time: " + total_time/load + " nanosec");

        start_t = System.nanoTime();
        hc.add(list.get(load), Helper.hash_mod(list.get(load), capacity));
        end_t = System.nanoTime();

        System.out.println("Last insert run time: " + (end_t - start_t) + " nanosec");
        System.out.println("Last insert collision: " + hc.last_collision);
        System.out.println();
    }

    public static void main(String[] args){

        int[] capacity = {100, 1000, 10000};
        double[] load_factor = {1.0/4, 2.0/4, 3.0/4};
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++){
            list.add(i);
        }

        Collections.shuffle(list, new Random()); 

        System.out.println("Hash Chaining");
        // fixed array size (1000), change alpha
        for (int i = 0; i < load_factor.length; i++)
            test(1, list, capacity[1], load_factor[i]);

        // fixed load_factor, change array_zie
        for (int i = 0; i < capacity.length; i++)
            test(1, list, capacity[i], load_factor[0]);






    }


}