

class Hash {

    public static void main(String[] args){

        int a = HashFunction.hash_mod(26);
        int b = HashFunction.hash_div_mod(26);

        System.out.println(a + " " + b);

    }


}