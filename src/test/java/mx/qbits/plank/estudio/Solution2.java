package mx.qbits.plank.estudio;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
Given an array of integers and an integer k, you need to find the 
number of unique 'k-diff' pairs in the array. 

Please note that a 'k-diff' pair is defined as an integer pair (i, j),
where i and j are both numbers in the array and their absolute difference is k.
*/
public class Solution2 {
    public static int findPairs(int[] nums, int k) {
        // Condiciones iniciales obvias:
        if(k < 0) return 0;
        int result = 0;

        // Mapa que guarda valores no repetidos. 
        // Si guardas 2 valores iguales, se queda con el segundo:
        Map<Integer, Integer> map = new HashMap<>();
        
        // Saca una lista de NO repetidos, pero los repetidos marcalos con un 2:
        for(int i =0;i<nums.length;i++){
            if(map.get(nums[i])==null) {
                map.put(nums[i], 1); // crea la entrada con un 1
            } else {
                map.put(nums[i], 2); // actualiza la entrada con un 2
            }
        }
        
        // Iterar sobre cada llave no repetida:
        Set<Integer> keySet = map.keySet();
        for(Integer key : keySet){
            if (k == 0) {
                if(map.get(key) > 1) result++;
            } else {
                if(map.get(key+k) != null) result++;
            }
        }
        return result;
    }
    
    
    // https://leetcode.com/problems/k-diff-pairs-in-an-array/submissions/
    public static void main(String...argv) {
        // hint: insertar entre la 41 y la 42:
        // //System.out.println(key+", "+(key+k)+ " --> "+k);
        generateRamdomSets();
        //int k=6; int[] a = {43, 83, 6, 8, 81, 12, -14, 56, 46, 89, 94, 98, 73, 16, 8, 56, 94, -58, -80, 16, 17, 9};        
        //System.out.println(findPairs(a, k));
    }
    
    private static void generateRamdomSets() {
        for(int i=1; i<100L; i++) { // 1000 sets
            int k = getIntRandValueBetween(5, 50);
            int[] sec = generateSecuence();
            pintaArreglo(sec, k, i);
        }
    }
    private static void pintaArreglo(int[] sec, int k, int index) {
        if(sec.length<1) return;
        int r = findPairs(sec, k);
        if(r<1) return;
        
        String data = "int[] a"+index+" = {";
        for(int j=0; j<sec.length; j++) {
            data += sec[j]+", ";
        }
        
        data = data.subSequence(0, data.length()-2).toString();
        data += "};";
        
        data = "int k"+index+"="+k+";\n" + data + "\n";
        data = data + "int r"+index+" = Solution2.findPairs(a"+index+", k"+index+");\n";
        data = data +"assertTrue(r"+index+"=="+r+");\n";
        System.out.println(data);
    }
    public static void pintaArreglo2(int[] sec, int k, int index) {
        if(sec.length<1) return;
        int r = findPairs(sec, k);
        if(r<1) return;
        
        String data = "int[] a = {";
        for(int j=0; j<sec.length; j++) {
            data += sec[j]+", ";
        }
        
        data = data.subSequence(0, data.length()-2).toString();
        data += "};";
        System.out.println("public static void calc"+index+"() {");
        System.out.println("int k="+k+";\n"+data+"\nint r = Solution2.findPairs(a, k);\nSystem.out.println(\"Valor obtenido: \" +r);");
        System.out.println("System.out.println(\"Valor buscado: \" +"+r+");");
        System.out.println("}");
    }
    private static int[] generateSecuence() {
        int len = getIntRandValueBetween(0, 25);
        int[] test = new int[len];
        for(int j=0; j<len; j++) {
            test[j] = getIntRandValueBetween(0, 100)*spinner();
        }
        return test;
    }
    private static int getIntRandValueBetween(int a, int b) {
        double rand = a+Math.random()*b; // [1,101) decimals
        return (int)rand;  // [1,100] integers
    }
    
    private static int spinner() {
        double rand = Math.random();
        return (rand>0.8)?-1:1;
    }
}
