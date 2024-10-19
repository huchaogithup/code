package org.example;

import java.util.*;
import java.util.stream.IntStream;

public class Dd {

    public static void main(String[] args) {

        System.out.println(new Dd().beautifulSubstrings("baeyh", 2));

    }

    public long beautifulSubstrings(String s, int k) {
        for (int i = 1; ; i++) {
            if ((i * i) % (4 * k) == 0) {
                k = i;
                break;
            }
        }

        Set<Character> vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');

        Map<Info, Long> counts = new HashMap<>();
        long res = 0;
        long presum = 0;
        Info info = new Info();
        info.mod = ((-1) % k + k) % k;
        counts.put(info, 1L);
        for (int i = 0; i < s.length(); i++) {
            int v = vowels.contains(s.charAt(i)) ? 1 : -1;
            presum += v;
            info = new Info();
            info.mod = i % k;
            info.preSum = presum;
            res += counts.getOrDefault(info, 0L);
            counts.merge(info, 1L, Long::sum);
        }
        return res;

    }

    public static class Info {
        private long mod;
        private long preSum;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Info info = (Info) o;
            return mod == info.mod && preSum == info.preSum;
        }

        @Override
        public int hashCode() {
            return Objects.hash(mod, preSum);
        }
    }


    private static int p(int[] arr) {
        Map<Integer, Integer> preSumCount = new HashMap<>();
        preSumCount.put(0, 1);
        int res = 0;
        int preSum = 0;
        int up = 0;
        for (int i = 0; i < arr.length; i++) {
            preSum += arr[i] == 1 ? 1 : -1;
            if (arr[i] == 1) {
                up += preSumCount.getOrDefault(preSum - 1, 0);
            } else {
                up -= preSumCount.getOrDefault(preSum, 0);
            }
            res += up;
            res += preSumCount.getOrDefault(preSum, 0);
            preSumCount.merge(preSum, 1, Integer::sum);
        }
        return res;
    }

    private static int baoli(int[] arr) {
        Map<Integer, Integer> sum = new HashMap<>();
        sum.put(0, 1);
        int preSum = 0;
        int min = 0;
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            preSum += arr[i] == 1 ? 1 : -1;
            for (int j = preSum; j >= min; j--) {
                res += sum.getOrDefault(j, 0);
            }
            min = Math.min(min, preSum);
            sum.merge(preSum, 1, Integer::sum);
        }
        return res;

    }


}
