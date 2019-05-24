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
        int[] input = {12,26,92,23,28,94,15};
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
            case 4:
                hc = new QuadraticProbing(capacity);
                break;
        }

        test_analysis(hc, load, list, capacity);
    }

    public static void test_analysis(HashAlgo hc, int load, ArrayList<Integer> list, int capacity){
        hc.collision = 0;
        hc.last_collision = 0;
        long total_time_add = 0;
        long start_t_add = 0;
        long end_t_add = 0;
        long total_t_search = 0;
        long start_t_search = 0;
        long end_t_search = 0;

        boolean resize = false;
        int i = 0;
        for (i = 0; i < load; i++){
            start_t_add = System.nanoTime();
            int insertion = hc.add(list.get(i), Helper.hash_mod(list.get(i), capacity));
            end_t_add = System.nanoTime();
            if (insertion == -1){
                resize = true;
                break;
            }
            total_time_add += (end_t_add - start_t_add);
    
            start_t_search = System.nanoTime();
            int value = hc.search(list.get(i));
            end_t_search = System.nanoTime();
            total_t_search = end_t_search - start_t_search;
        }
        System.out.println("Avg insert collision: " + hc.collision * 1.0 /hc.size);
        System.out.println("Avg insert run time: " + total_time_add/hc.size + " nanosec");

        // avg search
        System.out.println("Avg search collision: " + hc.search_collision * 1.0 / hc.size);
        System.out.println("Avg searh run time: " + total_t_search/hc.size + " nanosec");
        System.out.println();
    }

    public static void test_analysis_helper(HashAlgo hc, int load, ArrayList<Integer> list, int capacity, long total_time, boolean resize){
        if (resize == false){
            long start_t = System.nanoTime();
            hc.add(list.get(load), Helper.hash_mod(list.get(load), capacity));
            long end_t = System.nanoTime();

            System.out.println("Last insert run time: " + (end_t - start_t) + " nanosec");
            System.out.println("Last insert collision: " + hc.last_collision);
        }
        System.out.println();
    }

    public static void general_test(int option, int[] capacity, double[] load_factor, ArrayList<Integer> list){
        // fixed array size (1000), change alpha
        System.out.println("Fixed array size " + capacity[1]);
        for (int i = 0; i < load_factor.length; i++)
            test(option, list, capacity[1], load_factor[i]);

        // fixed load_factor, change array_zie
        System.out.println("Fixed load factor " + load_factor[0]);
        for (int i = 0; i < capacity.length; i++)
            test(option, list, capacity[i], load_factor[0]);
    }

    public static void main(String[] args){

       // int[] capacity = {100, 1000, 10000};
        int[] capacity = {100, 500, 1000, 5000, 10000};
        //double[] load_factor = {1.0/4, 2.0/4, 3.0/4};
        double[] load_factor = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0};
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++){
            list.add(i);
        }

        Collections.shuffle(list, new Random()); 

        System.out.println("Hash Chaining");
        general_test(1, capacity, load_factor, list);
        System.out.println("Linear Probing");
        general_test(2, capacity, load_factor, list);
        System.out.println("Cuckoo Hashing");
        general_test(3, capacity, load_factor, list);
        System.out.println("Quadratic Probing");
        general_test(4, capacity, load_factor, list);
    

    }


}