package org.example;

import java.util.HashMap;
import java.util.Map;

public class Fe {
    public static void main(String[] args) {
        Map<Integer, Integer> count = new HashMap<>();
        count.put(1, 3);
        count.put(2, 3);
        System.out.println(getJie(6) / getJie(3) / getJie(3));
        System.out.println(p(count, 0, 6));
    }

    public static long getJie(int i) {
        long res = 1;
        for (int j = 1; j <= i; j++) {
            res *= j;
        }
        return res;
    }

    private static long p(Map<Integer, Integer> count, int i, int n) {
        if (i == n) {
            return 1;
        }
        long res = 0;
        for (Map.Entry<Integer, Integer> integerIntegerEntry : count.entrySet()) {
            HashMap<Integer, Integer> integerIntegerHashMap = new HashMap<>(count);
            Integer c = integerIntegerHashMap.get(integerIntegerEntry.getKey());
            c--;
            integerIntegerHashMap.put(integerIntegerEntry.getKey(), c);
            if (c == 0) {
                integerIntegerHashMap.remove(integerIntegerEntry.getKey());
            }
            res += p(integerIntegerHashMap, i + 1, n);
        }
        return res;
    }

}
