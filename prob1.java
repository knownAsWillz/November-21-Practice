import java.util.*;

public class prob1{
    public static void main(String[] args) {
        int n = 20;
        List<Integer> list = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        
        helper(list, n);
        
    for (int i = 0; i < list.size() - 1; i++) {
        if (Math.abs(list.get(i) - list.get(i + 1)) == 2) {
            List<Integer> temp = new ArrayList<>();
            temp.add(list.get(i));
            temp.add(list.get(i + 1));
            res.add(temp);
        }
    }

        
        System.out.print(res);
    }
    
    private static void helper(List<Integer> list, int n) {
        for(int i = 2; i <= n; i++) {
            boolean isPrime = true;
            for(int j = 2; j <= i; j++) {
                if(i % j == 0 && j != i) {
                    isPrime = false;
                    break;
                }
            }
            if(isPrime) list.add(i);
        }
    }
}