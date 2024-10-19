import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(way(4,3));
    }






    static int n = 0;


    public static int way(int n ,int k){
        Main.n=n;
        return p(1,k-1)*k;
    }

    public static int p(int i, int k) {
        if (i == n) {
            return 1;
        }
        int res = 0;
        res += p(i + 1, k);
        if (k > 0) {
            res += p(i + 1, k - 1)*k;
        }
        return res;
    }


}